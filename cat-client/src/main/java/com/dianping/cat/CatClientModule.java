/*
 * Copyright (c) 2011-2018, Meituan Dianping. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dianping.cat;

import com.dianping.cat.analyzer.LocalAggregator;
import com.dianping.cat.configuration.ClientConfigManager;
import com.dianping.cat.message.internal.MilliSecondTimer;
import com.dianping.cat.message.io.TransportManager;
import com.dianping.cat.status.StatusUpdateTask;
import com.doublespring.common.U;
import com.doublespring.log.LogUtil;
import org.unidal.helper.Threads;
import org.unidal.helper.Threads.AbstractThreadListener;
import org.unidal.initialization.AbstractModule;
import org.unidal.initialization.DefaultModuleContext;
import org.unidal.initialization.Module;
import org.unidal.initialization.ModuleContext;
import org.unidal.lookup.annotation.Named;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.LockSupport;

@Named(type = Module.class, value = CatClientModule.ID)
public class CatClientModule extends AbstractModule {
    public static final String ID = "cat-client";

    @Override
    protected void execute(final ModuleContext ctx) throws Exception {

        LogUtil.info("初始化 CatClientModule,当前工作目录为", U.format("user.dir", System.getProperty("user.dir")));

        // initialize milli-second resolution level timer
        LogUtil.info("初始化 MilliSecondTimer");
        MilliSecondTimer.initialize();

        // tracking thread start/stop
        LogUtil.info("实例化线程监控 CatThreadListener");
        CatThreadListener catThreadListener = new CatThreadListener(ctx);

        LogUtil.info("注册线程监控 CatThreadListener");
        Threads.addListener(catThreadListener);

        ClientConfigManager clientConfigManager = ctx.lookup(ClientConfigManager.class);

        // warm up Cat
        Cat.getInstance().setContainer(((DefaultModuleContext) ctx).getContainer());

        // bring up TransportManager
        ctx.lookup(TransportManager.class);

        if (clientConfigManager.isCatEnabled()) {
            // start status update task
            StatusUpdateTask statusUpdateTask = ctx.lookup(StatusUpdateTask.class);
            Threads.forGroup("cat").start(statusUpdateTask);

            Threads.forGroup("cat").start(new LocalAggregator.DataUploader());

            LockSupport.parkNanos(10 * 1000 * 1000L); // wait 10 ms
            // MmapConsumerTask mmapReaderTask = ctx.lookup(MmapConsumerTask.class);
            // Threads.forGroup("cat").start(mmapReaderTask);
        }
    }

    @Override
    public Module[] getDependencies(ModuleContext ctx) {
        LogUtil.info("加载依赖模块");
        return null; // no dependencies
    }

    public static final class CatThreadListener extends AbstractThreadListener {
        private final ModuleContext m_ctx;

        private CatThreadListener(ModuleContext ctx) {
            LogUtil.info("初始化 CatThreadListener", U.format("ctx", U.toJSS(ctx)));
            m_ctx = ctx;
        }

        @Override
        public void onThreadGroupCreated(ThreadGroup group, String name) {
            m_ctx.info(String.format("创建ThreadGroup(%s)", name));
        }

        @Override
        public void onThreadPoolCreated(ExecutorService pool, String name) {
            m_ctx.info(String.format("创建 ExecutorService(%s)", name));
        }

        @Override
        public void onThreadStarting(Thread thread, String name) {
            m_ctx.info(String.format("启动线程(%s)", name));
        }

        @Override
        public void onThreadStopping(Thread thread, String name) {
            m_ctx.info(String.format("停止线程(%s)", name));
        }

        @Override
        public boolean onUncaughtException(Thread thread, Throwable e) {
            m_ctx.error(String.format("捕获到未知异常,线程名称(%s)", thread.getName()), e);
            return true;
        }
    }
}
