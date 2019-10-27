package org.unidal.net;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import org.unidal.net.transport.TransportHub;

import java.util.concurrent.TimeUnit;

public interface ServerTransport {
    ServerTransport bind(int port);

    ServerTransport handler(String name, ChannelHandler handler);

    ServerTransport name(String name);

    <T> ServerTransport option(ChannelOption<T> option, T value);

    ServerTransport start(TransportHub hub);

    void stop(int timeout, TimeUnit unit) throws InterruptedException;

    ServerTransport withBossThreads(int bossThreads);

    ServerTransport withWorkerThreads(int workerThreads);
}
