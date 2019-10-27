package org.unidal.net.transport;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;

import java.util.Map;

public interface TransportDescriptor {
    Class<? extends Channel> getChannelClass();

    EventLoopGroup getGroup();

    Map<String, ChannelHandler> getHandlers();

    TransportHub getHub();

    String getName();

    Map<ChannelOption<Object>, Object> getOptions();

    void validate();
}
