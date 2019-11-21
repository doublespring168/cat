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
package com.dianping.cat.configuration;

import com.dianping.cat.configuration.client.entity.ClientConfig;
import com.dianping.cat.configuration.client.entity.Domain;
import com.dianping.cat.configuration.client.entity.Server;
import com.dianping.cat.configuration.client.transform.DefaultValidator;
import com.doublespring.common.U;
import com.doublespring.log.LogUtil;

import java.text.MessageFormat;
import java.util.Date;

public class ClientConfigValidator extends DefaultValidator {
    private ClientConfig m_config;

    private void log(String severity, String message) {
        MessageFormat format = new MessageFormat("[{0,date,MM-dd HH:mm:ss.sss}] [{1}] [{2}] {3}");

        System.out.println(format.format(new Object[]{new Date(), severity, "cat", message}));
    }

    @Override
    public void visitConfig(ClientConfig config) {

        LogUtil.info("开始验证ClientConfig配置", U.format("ClientConfig", U.toJSS(config)));

        config.setMode("client");

        if (config.getServers().size() == 0) {
            config.setEnabled(false);
            LogUtil.info("CAT servers不存在,设置client为disabled");
        } else if (!config.isEnabled()) {
            LogUtil.info("当前未开启client,设置client为disabled");
        }

        m_config = config;
        super.visitConfig(config);

        if (m_config.isEnabled()) {
            for (Domain domain : m_config.getDomains().values()) {
                if (!domain.isEnabled()) {
                    m_config.setEnabled(false);
                    LogUtil.info("client 端配置不连接当前 domain", U.format("domain", U.toJSS(domain)));
                }
                LogUtil.info("有domain供当前client连接");
                break; // for first domain only
            }
        }
    }

    @Override
    public void visitDomain(Domain domain) {
        super.visitDomain(domain);

        // set default values
        if (domain.getEnabled() == null) {
            domain.setEnabled(true);
        }

        if (domain.getIp() == null) {
            domain.setIp(getLocalAddress());
        }
    }

    private String getLocalAddress() {
        return NetworkInterfaceManager.INSTANCE.getLocalHostAddress();
    }

    @Override
    public void visitServer(Server server) {
        super.visitServer(server);

        // set default values
        if (server.getPort() == null) {
            server.setPort(2280);
        }

        if (server.getEnabled() == null) {
            server.setEnabled(true);
        }
    }

}
