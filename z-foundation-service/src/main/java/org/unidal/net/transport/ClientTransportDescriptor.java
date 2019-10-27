package org.unidal.net.transport;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.unidal.net.SocketAddressProvider;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;

public class ClientTransportDescriptor implements TransportDescriptor {
    private String m_name;

    private SocketAddressProvider m_addressProvider;

    private int m_threads = 3;

    private Map<ChannelOption<Object>, Object> m_options = new HashMap<ChannelOption<Object>, Object>();

    private Map<String, ChannelHandler> m_handlers = new LinkedHashMap<String, ChannelHandler>();

    private TransportHub m_hub;

    public void addHandler(String name, ChannelHandler handler) {
        m_handlers.put(name, handler);
    }

    public ByteBufAllocator getByteBufAllocator() {
        Object allocator = m_options.get(ChannelOption.ALLOCATOR);

        if (allocator == null) {
            return PooledByteBufAllocator.DEFAULT;
        } else {
            return (ByteBufAllocator) allocator;
        }
    }

    @Override
    public Class<? extends Channel> getChannelClass() {
        return NioSocketChannel.class;
    }

    @Override
    public EventLoopGroup getGroup() {
        ThreadFactory factory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);

                t.setName(m_name + "-NioEventLoopGroup");
                t.setDaemon(true);
                return t;
            }
        };

        return new NioEventLoopGroup(m_threads, factory);
    }

    @Override
    public Map<String, ChannelHandler> getHandlers() {
        return m_handlers;
    }

    @Override
    public TransportHub getHub() {
        return m_hub;
    }

    @Override
    public String getName() {
        return m_name;
    }

    @Override
    public Map<ChannelOption<Object>, Object> getOptions() {
        return m_options;
    }

    @Override
    public void validate() {
    }

    public void setName(String name) {
        m_name = name;
    }

    public void setHub(TransportHub hub) {
        m_hub = hub;
    }

    public List<InetSocketAddress> getRemoteAddresses() {
        return m_addressProvider.getAddresses();
    }

    public void setAddressProvider(SocketAddressProvider addressProvider) {
        m_addressProvider = addressProvider;
    }

    public void setThreads(int threads) {
        m_threads = threads;
    }
}
