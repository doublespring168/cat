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
package com.dianping.cat.message.io;

import com.dianping.cat.configuration.ClientConfigManager;
import com.dianping.cat.message.internal.MessageIdFactory;
import com.doublespring.common.U;
import com.doublespring.log.LogUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.codehaus.plexus.logging.Logger;
import org.unidal.helper.Splitters;
import org.unidal.helper.Threads.Task;
import org.unidal.lookup.util.StringUtils;
import org.unidal.tuple.Pair;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ChannelManager implements Task {

    private ClientConfigManager m_configManager;

    private Bootstrap m_bootstrap;

    private boolean m_active = true;

    private int m_channelStalledTimes = 0;

    private ChannelHolder m_activeChannelHolder;

    private MessageIdFactory m_idFactory;

    private AtomicInteger m_attempts = new AtomicInteger();

    private Logger m_logger;

    public ChannelManager(Logger logger, List<InetSocketAddress> serverAddresses, ClientConfigManager configManager,
                          MessageIdFactory idFactory) {
        m_logger = logger;
        m_configManager = configManager;
        m_idFactory = idFactory;

        LogUtil.info("实例化 ChannelManager");

        EventLoopGroup group = new NioEventLoopGroup(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
            }
        });

        m_bootstrap = bootstrap;

        String routerConfig = m_configManager.getRouters();

        if (StringUtils.isNotEmpty(routerConfig)) {

            LogUtil.info("根据 routerConfig 实例化 Channel", U.format("routerConfig", U.toString(routerConfig)));

            List<InetSocketAddress> configedAddresses = parseSocketAddress(routerConfig);
            ChannelHolder holder = initChannel(configedAddresses, routerConfig);

            if (holder != null) {
                m_activeChannelHolder = holder;
            } else {
                m_activeChannelHolder = new ChannelHolder();
                m_activeChannelHolder.setServerAddresses(configedAddresses);
            }

        } else {

            LogUtil.info("根据 serverAddresses 实例化 Channel", U.format("serverAddresses", U.toString(serverAddresses)));

            ChannelHolder holder = initChannel(serverAddresses, null);

            if (holder != null) {
                m_activeChannelHolder = holder;
            } else {

                m_activeChannelHolder = new ChannelHolder();
                m_activeChannelHolder.setServerAddresses(serverAddresses);

                LogUtil.info("根据 client.xml 初始化 cat module 失败", U.format("ChannelHolder", U.toString(m_activeChannelHolder)));
            }
        }
    }

    private List<InetSocketAddress> parseSocketAddress(String content) {
        try {
            List<String> strs = Splitters.by(";").noEmptyItem().split(content);
            List<InetSocketAddress> address = new ArrayList<InetSocketAddress>();

            for (String str : strs) {
                List<String> items = Splitters.by(":").noEmptyItem().split(str);
                address.add(new InetSocketAddress(items.get(0), Integer.parseInt(items.get(1))));
            }
            return address;
        } catch (Exception e) {
            m_logger.error(e.getMessage(), e);
        }
        return new ArrayList<InetSocketAddress>();
    }

    private ChannelHolder initChannel(List<InetSocketAddress> addresses, String serverConfig) {
        try {
            int len = addresses.size();

            for (int i = 0; i < len; i++) {
                InetSocketAddress address = addresses.get(i);
                String hostAddress = address.getAddress().getHostAddress();
                ChannelHolder holder = null;

                if (m_activeChannelHolder != null && hostAddress.equals(m_activeChannelHolder.getIp())) {
                    holder = new ChannelHolder();
                    holder.setActiveFuture(m_activeChannelHolder.getActiveFuture()).setConnectChanged(false);
                } else {
                    ChannelFuture future = createChannel(address);

                    if (future != null) {
                        holder = new ChannelHolder();
                        holder.setActiveFuture(future).setConnectChanged(true);
                    }
                }
                if (holder != null) {
                    holder.setActiveIndex(i).setIp(hostAddress);
                    holder.setActiveServerConfig(serverConfig).setServerAddresses(addresses);

                    LogUtil.info("初始化 CAT server 成功, new active holder" + holder.toString());
                    return holder;
                }
            }
        } catch (Exception e) {
            m_logger.error(e.getMessage(), e);
        }

        try {
            StringBuilder sb = new StringBuilder();

            for (InetSocketAddress address : addresses) {
                sb.append(address.toString()).append(";");
            }
            LogUtil.info("初始化 CAT server 失败" + sb.toString());
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    private ChannelFuture createChannel(InetSocketAddress address) {

        String serverIp = address.toString();
        LogUtil.info("即将连接 Cat server", U.format("server ip", serverIp));
        ChannelFuture future = null;

        try {

            future = m_bootstrap.connect(address);
            future.awaitUninterruptibly(100, TimeUnit.MILLISECONDS); // 100 ms

            if (!future.isSuccess()) {
                LogUtil.info("连接 Cat server失败,即将关闭Channel", U.format("server ip", serverIp));
                closeChannel(future);
            } else {
                LogUtil.info("连接 Cat server成功", U.format("server ip", serverIp));
                return future;
            }
        } catch (Throwable e) {
            LogUtil.info("连接 Cat server 抛出异常,即将关闭Channel", U.format("server ip", serverIp, "Throwable", U.toString(e)));

            if (future != null) {
                closeChannel(future);
            }
        }
        return null;
    }

    private void closeChannel(ChannelFuture channel) {
        try {
            if (channel != null) {
                SocketAddress address = channel.channel().remoteAddress();

                if (address != null) {
                    LogUtil.info("即将关闭 Channel ", U.format("remoteAddress", address));
                }
                channel.channel().close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

    public ChannelFuture channel() {
        if (m_activeChannelHolder != null) {
            ChannelFuture future = m_activeChannelHolder.getActiveFuture();

            if (checkWritable(future)) {
                return future;
            }
        }
        return null;
    }

    private boolean checkWritable(ChannelFuture future) {
        boolean isWriteable = false;

        if (future != null) {
            Channel channel = future.channel();

            if (channel.isActive() && channel.isOpen()) {
                if (channel.isWritable()) {
                    isWriteable = true;
                } else {
                    channel.flush();
                }
            } else {
                int count = m_attempts.incrementAndGet();
                if (count % 1000 == 0 || count == 1) {
                    LogUtil.info("Channel 连接已关闭,不发送消息,尝试次数", U.format("count", count));
                }
            }
        }

        return isWriteable;
    }

    private boolean checkActive(ChannelFuture future) {
        boolean isActive = false;

        if (future != null) {
            Channel channel = future.channel();

            if (channel.isActive() && channel.isOpen()) {
                isActive = true;
            } else {
                LogUtil.info("Channel 连接已失效", U.format("server ip", future.channel().remoteAddress()));
            }
        }

        LogUtil.info("Channel 连接检测结果", U.format("isActive", isActive));

        return isActive;
    }

    private void checkServerChanged() {
        Pair<Boolean, String> pair = routerConfigChanged();

        if (pair.getKey()) {
            LogUtil.info("server 路由配置发生变更 :" + pair.getValue());
            String servers = pair.getValue();
            List<InetSocketAddress> serverAddresses = parseSocketAddress(servers);
            ChannelHolder newHolder = initChannel(serverAddresses, servers);

            if (newHolder != null) {
                if (newHolder.isConnectChanged()) {
                    ChannelHolder last = m_activeChannelHolder;

                    m_activeChannelHolder = newHolder;
                    closeChannelHolder(last);
                    LogUtil.info("切换连接 Channel 为: " + m_activeChannelHolder);
                } else {
                    m_activeChannelHolder = newHolder;
                }
            }
        }
    }

    private void closeChannelHolder(ChannelHolder channelHolder) {
        try {
            ChannelFuture channel = channelHolder.getActiveFuture();
            closeChannel(channel);
        } catch (Exception e) {
            // ignore
        }
    }

    private void doubleCheckActiveServer(ChannelHolder channelHolder) {
        try {
            if (isChannelStalled(channelHolder)) {
                closeChannelHolder(m_activeChannelHolder);
                channelHolder.setActiveIndex(-1);
                LogUtil.info("Channel 连接失败,即将关闭相关资源", U.format("ip", channelHolder.getIp()));
            } else {
                LogUtil.info("Channel 连接检测正常");
            }
        } catch (Throwable e) {
            m_logger.error(e.getMessage(), e);
        }
    }

    @Override
    public String getName() {
        return "TcpSocketSender-ChannelManager";
    }

    @Override
    public void shutdown() {
        m_active = false;
    }

    private boolean isChannelStalled(ChannelHolder holder) {

        ChannelFuture future = holder.getActiveFuture();
        boolean active = checkActive(future);

        if (!active) {
            return (++m_channelStalledTimes) % 3 == 0;
        } else {
            if (m_channelStalledTimes > 0) {
                m_channelStalledTimes--;
            }
            return false;
        }
    }

    private void reconnectDefaultServer(ChannelFuture activeFuture, List<InetSocketAddress> serverAddresses) {
        try {

            LogUtil.info("重新连接 Cat Server");

            int reconnectServers = m_activeChannelHolder.getActiveIndex();

            if (reconnectServers == -1) {
                reconnectServers = serverAddresses.size();
            }
            for (int i = 0; i < reconnectServers; i++) {
                ChannelFuture future = createChannel(serverAddresses.get(i));

                if (future != null) {
                    ChannelFuture lastFuture = activeFuture;

                    m_activeChannelHolder.setActiveFuture(future);
                    m_activeChannelHolder.setActiveIndex(i);
                    closeChannel(lastFuture);
                    LogUtil.info("重新连接 Cat Server 失败");

                    break;
                } else {
                    LogUtil.info("重新连接 Cat Server 成功", U.format("remoteAddress", future.channel().remoteAddress()));
                }
            }
        } catch (Throwable e) {
            LogUtil.info("重新连接 Cat Server 抛出异常", U.format("message", e.getMessage(), "Throwable", U.toString(e)));
        }
    }

    private Pair<Boolean, String> routerConfigChanged() {
        String routerConfig = m_configManager.getRouters();

        Pair<Boolean, String> result = null;
        if (!StringUtils.isEmpty(routerConfig) && !routerConfig.equals(m_activeChannelHolder.getActiveServerConfig())) {
            result = new Pair<Boolean, String>(true, routerConfig);
        } else {
            result = new Pair<Boolean, String>(false, routerConfig);
        }

        LogUtil.info("routerConfig 发生变化", U.format("routerConfig", U.toString(routerConfig), "result", U.toString(result)));

        return result;
    }

    @Override
    public void run() {

        LogUtil.info("启动 server 连接检测线程");
        while (m_active) {

            LogUtil.info("即将检测 Cat server 连接状态");

            // make save message id index asyc
            m_idFactory.saveMark();
            checkServerChanged();

            ChannelFuture activeFuture = m_activeChannelHolder.getActiveFuture();
            List<InetSocketAddress> serverAddresses = m_activeChannelHolder.getServerAddresses();

            doubleCheckActiveServer(m_activeChannelHolder);
            reconnectDefaultServer(activeFuture, serverAddresses);

            try {
                Thread.sleep(10 * 1000L); // check every 10 seconds
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    public static class ChannelHolder {
        private ChannelFuture m_activeFuture;

        private int m_activeIndex = -1;

        private String m_activeServerConfig;

        private List<InetSocketAddress> m_serverAddresses;

        private String m_ip;

        private boolean m_connectChanged;

        public ChannelHolder() {
            LogUtil.info("实例化 ChannelHolder");
        }

        public ChannelFuture getActiveFuture() {
            return m_activeFuture;
        }

        public ChannelHolder setActiveFuture(ChannelFuture activeFuture) {
            m_activeFuture = activeFuture;
            return this;
        }

        public int getActiveIndex() {
            return m_activeIndex;
        }

        public ChannelHolder setActiveIndex(int activeIndex) {
            m_activeIndex = activeIndex;
            return this;
        }

        public String getActiveServerConfig() {
            return m_activeServerConfig;
        }

        public ChannelHolder setActiveServerConfig(String activeServerConfig) {
            m_activeServerConfig = activeServerConfig;
            return this;
        }

        public String getIp() {
            return m_ip;
        }

        public ChannelHolder setIp(String ip) {
            m_ip = ip;
            return this;
        }

        public List<InetSocketAddress> getServerAddresses() {
            return m_serverAddresses;
        }

        public ChannelHolder setServerAddresses(List<InetSocketAddress> serverAddresses) {
            m_serverAddresses = serverAddresses;
            return this;
        }

        public boolean isConnectChanged() {
            return m_connectChanged;
        }

        public ChannelHolder setConnectChanged(boolean connectChanged) {
            m_connectChanged = connectChanged;
            return this;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("active future :").append(m_activeFuture.channel().remoteAddress());
            sb.append(" index:").append(m_activeIndex);
            sb.append(" ip:").append(m_ip);
            sb.append(" server config:").append(m_activeServerConfig);
            return sb.toString();
        }
    }


}