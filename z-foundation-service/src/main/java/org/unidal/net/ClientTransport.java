package org.unidal.net;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import org.unidal.net.transport.TransportHub;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public interface ClientTransport {
    ClientTransport connect(InetSocketAddress... addresses);

    ClientTransport connect(SocketAddressProvider provider);

    ClientTransport connect(String host, int port);

    ClientTransport handler(String name, ChannelHandler handler);

    ClientTransport name(String name);

    <T> ClientTransport option(ChannelOption<T> option, T value);

    ClientTransport start(TransportHub hub);

    void stop(int timeout, TimeUnit unit) throws InterruptedException;

    ClientTransport withThreads(int threads);
}
