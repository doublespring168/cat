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
package com.dianping.cat.task;

import com.dianping.cat.Cat;
import com.dianping.cat.helper.TimeHelper;
import com.dianping.cat.message.Transaction;
import com.doublespring.common.U;
import com.doublespring.log.LogUtil;
import org.unidal.helper.Threads;
import org.unidal.helper.Threads.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class TimerSyncTask implements Task {

	private static final long DURATION = TimeHelper.ONE_MINUTE;

	private static TimerSyncTask m_instance = new TimerSyncTask();

	private static ExecutorService s_threadPool = Threads.forPool().getFixedThreadPool("Cat-ConfigSyncTask", 3);

	private static boolean m_active = false;

	private List<SyncHandler> m_handlers = new ArrayList<SyncHandler>();

	public static TimerSyncTask getInstance() {

		LogUtil.info("加载 TimerSyncTask 实例");

		if (!m_active) {
			synchronized (TimerSyncTask.class) {
				if (!m_active) {
					Threads.forGroup("cat").start(m_instance);

					m_active = true;
				}
			}
		}
		return m_instance;
	}

	@Override
	public String getName() {
		return "timer-sync-task";
	}

	@Override
	public void shutdown() {
	}

	public void register(SyncHandler handler) {
		synchronized (this) {
			LogUtil.info("添加SyncHandler", U.format("SyncHandler", U.toString(handler)));
			m_handlers.add(handler);
		}
	}

	@Override
	public void run() {

		boolean active = TimeHelper.sleepToNextMinute();

		LogUtil.info("1min后启动数据同步线程");


		while (active) {

			LogUtil.info("开始数据同步");

			long current = System.currentTimeMillis();

			for (final SyncHandler handler : m_handlers) {
				s_threadPool.submit(new Runnable() {

					@Override
					public void run() {
						final Transaction t = Cat.newTransaction("TimerSync", handler.getName());

						try {
							LogUtil.info("运行数据同步任务", U.format("handler", handler.getName()));
							handler.handle();
							t.setStatus(Transaction.SUCCESS);
						} catch (Exception e) {
							t.setStatus(e);
							Cat.logError(e);
						} finally {
							t.complete();
						}
					}
				});
			}

			long duration = System.currentTimeMillis() - current;

			try {
				if (duration < DURATION) {
					Thread.sleep(DURATION - duration);
				}
			} catch (InterruptedException e) {
				LogUtil.info("数据同步线程休眠过程抛出异常,设置active=false");
				active = false;
			}
		}
	}

	public interface SyncHandler {

		String getName();

		void handle() throws Exception;

	}

}
