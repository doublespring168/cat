package org.unidal.net.transport.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.codehaus.plexus.logging.Logger;
import org.unidal.lookup.logger.LoggerFactory;

import java.net.InetSocketAddress;

public class ClientStateHandler extends ChannelInboundHandlerAdapter implements Cloneable {
    private Logger m_logger = LoggerFactory.getLogger(getClass());

    private String m_name;

    public ClientStateHandler(String name) {
        m_name = name;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();

        m_logger.info(String.format("Connected to %s server at %s:%s", m_name, address.getHostName(), address.getPort()));
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        InetSocketAddress address = (InetSocketAddress) channel.remoteAddress();

        m_logger.info(String.format("Disconnected from %s server at %s:%s", m_name, address.getHostName(),
                address.getPort()));
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();

        m_logger.error(cause.getMessage(), cause);
    }
}
