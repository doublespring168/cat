package org.unidal.net.transport;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import org.codehaus.plexus.logging.LogEnabled;
import org.codehaus.plexus.logging.Logger;
import org.unidal.helper.Threads.Task;
import org.unidal.lookup.annotation.Named;
import org.unidal.net.transport.handler.ClientStateHandler;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Named(type = ClientTransportHandler.class, instantiationStrategy = Named.PER_LOOKUP)
public class ClientTransportHandler implements Task, LogEnabled {
    private ClientTransportDescriptor m_descriptor;

    private ClientChannelManager m_channelManager;

    private AtomicBoolean m_active = new AtomicBoolean(true);

    private CountDownLatch m_latch = new CountDownLatch(1);

    private CountDownLatch m_warmup = new CountDownLatch(1);

    private Logger m_logger;

    public void awaitTermination(int timeout, TimeUnit unit) throws InterruptedException {
        m_latch.await(timeout, unit);
    }

    public void awaitWarmup() {
        try {
            m_warmup.await();
        } catch (InterruptedException e) {
            // ignore it
        }
    }

    @Override
    public void enableLogging(Logger logger) {
        m_logger = logger;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public void shutdown() {
        m_active.set(false);
    }

    @Override
    public void run() {
        try {
            m_channelManager = new ClientChannelManager();

            long expireTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(1);

            while (m_channelManager.getActiveChannel() == null && System.currentTimeMillis() < expireTime) {
                TimeUnit.MILLISECONDS.sleep(1);
            }

            m_warmup.countDown();
            run0();
        } catch (Throwable e) {
            m_logger.error(e.getMessage(), e);
            m_warmup.countDown();
        } finally {
            if (m_channelManager != null) {
                m_channelManager.close();
            }

            m_latch.countDown();
        }
    }

    private void run0() throws InterruptedException {
        ByteBufAllocator allocator = m_descriptor.getByteBufAllocator();
        int initialCapacity = 4 * 1024; // 4K
        ByteBuf buf = allocator.buffer(initialCapacity);
        TransportHub hub = m_descriptor.getHub();

        while (m_active.get()) {
            Channel channel = m_channelManager.getActiveChannel();

            if (channel != null && channel.isWritable()) {
                do {
                    if (hub.fill(buf)) {
                        channel.writeAndFlush(buf);
                        buf = allocator.buffer(initialCapacity);
                    } else {
                        break;
                    }
                } while (channel.isWritable());
            }

            TimeUnit.MILLISECONDS.sleep(1); // 1ms
        }

        long end = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(3); // 3s timeout

        while (true) {
            Channel channel = m_channelManager.getActiveChannel();

            if (channel != null && channel.isWritable()) {
                do {
                    if (hub.fill(buf)) {
                        channel.writeAndFlush(buf);
                        buf = allocator.buffer(initialCapacity);
                    } else {
                        break;
                    }
                } while (channel.isWritable());
            }

            if (System.currentTimeMillis() >= end) {
                throw new InterruptedException("Timeout with messages left in the queue!");
            }

            TimeUnit.MILLISECONDS.sleep(1); // 1ms
        }
    }

    public void setDescriptor(ClientTransportDescriptor descriptor) {
        m_descriptor = descriptor;
    }

    private class ClientChannelInitializer extends ChannelInitializer<Channel> {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();

            pipeline.addLast(new ClientStateHandler(m_descriptor.getName()));

            for (Map.Entry<String, ChannelHandler> e : m_descriptor.getHandlers().entrySet()) {
                pipeline.addLast(e.getKey(), e.getValue());
            }
        }
    }

    private class ClientChannelManager {
        private List<InetSocketAddress> m_addresses;

        private Bootstrap m_bootstrap;

        private Channel m_channel;

        private int m_index = -1;

        private ChannelFuture m_primary;

        private long m_failBackCheckInternal = 2 * 1000L;

        private long m_lastCheckTime;

        public ClientChannelManager() {
            Bootstrap bootstrap = new Bootstrap();
            Class<? extends Channel> channelClass = m_descriptor.getChannelClass();

            bootstrap.group(m_descriptor.getGroup()).channel(channelClass);
            bootstrap.handler(new ClientChannelInitializer());

            for (Map.Entry<ChannelOption<Object>, Object> e : m_descriptor.getOptions().entrySet()) {
                bootstrap.option(e.getKey(), e.getValue());
            }

            m_bootstrap = bootstrap;
        }

        public void close() {
            m_descriptor.getGroup().shutdownGracefully();

            if (m_channel != null) {
                m_channel.close();
            }
        }

        public Channel getActiveChannel() throws InterruptedException {
            List<InetSocketAddress> addresses = m_descriptor.getRemoteAddresses();

            if (!addresses.equals(m_addresses)) { // first time or addresses changed
                m_addresses = addresses;

                for (int i = 0; i < addresses.size(); i++) {
                    InetSocketAddress address = addresses.get(i);
                    ChannelFuture future = m_bootstrap.connect(address).sync();

                    if (future.isSuccess()) {
                        // close old channel
                        if (m_channel != null) {
                            m_channel.close();
                        }

                        // LogUtil.info(String.format("Connected to %s server(%s:%s)", m_descriptor.getName(),
                        // address.getHostName(), address.getPort()));
                        m_channel = future.channel();
                        m_index = i;
                        break;
                    }
                }

                return m_channel;
            } else {
                // closed by peer
                if (m_channel != null && m_channel.closeFuture().isSuccess()) {
                    // TODO
                }

                // try to recover connection to primary server
                if (m_index > 0) {
                    if (m_primary == null) {
                        long now = System.currentTimeMillis();

                        if (m_lastCheckTime + m_failBackCheckInternal < now) {
                            InetSocketAddress address = m_addresses.get(m_index);

                            m_lastCheckTime = now;
                            m_primary = m_bootstrap.connect(address);
                        }
                    } else {
                        Channel channel = m_primary.channel();

                        if (channel.isOpen() && channel.isActive()) {
                            m_channel = channel;
                            m_index = 0;
                        }
                    }
                }

                if (m_channel != null && m_channel.isOpen() && m_channel.isActive()) {
                    return m_channel;
                } else {
                    return null;
                }
            }
        }
    }
}
