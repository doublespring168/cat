package org.unidal.net.transport;

import com.doublespring.log.LogUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.codehaus.plexus.logging.LogEnabled;
import org.codehaus.plexus.logging.Logger;
import org.unidal.helper.Reflects;
import org.unidal.helper.Threads.Task;
import org.unidal.lookup.annotation.Named;
import org.unidal.net.transport.handler.ServerStateHandler;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Named(type = ServerTransportHandler.class, instantiationStrategy = Named.PER_LOOKUP)
public class ServerTransportHandler implements Task, LogEnabled {
    private ServerTransportDescriptor m_descriptor;

    private ChannelGroup m_channelGroup = new DefaultChannelGroup("Cat", GlobalEventExecutor.INSTANCE);

    private CountDownLatch m_latch = new CountDownLatch(1);

    private CountDownLatch m_warmup = new CountDownLatch(1);

    private Logger m_logger;

    private Channel m_channel;

    public void awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        m_latch.await(timeout, unit);
    }

    public void awaitWarmup() throws InterruptedException {
        m_warmup.await();
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
        m_channel.close();
    }

    @Override
    public void run() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            InetSocketAddress localAddress = m_descriptor.getLocalAddress();
            Class<? extends ServerChannel> channelClass = m_descriptor.getChannelClass();

            bootstrap.group(m_descriptor.getBossGroup(), m_descriptor.getGroup()).channel(channelClass);
            bootstrap.childHandler(new ServerChannelInitializer());

            for (Map.Entry<ChannelOption<Object>, Object> e : m_descriptor.getOptions().entrySet()) {
                bootstrap.childOption(e.getKey(), e.getValue());
            }

            ChannelFuture future = bootstrap.bind(localAddress).sync();

            if (future.isSuccess()) {
                String address = localAddress.getAddress().getHostAddress();
                int port = localAddress.getPort();

                m_warmup.countDown();
                LogUtil.info(String.format("%s server is listening on %s:%s", m_descriptor.getName(), address, port));
            }

            m_channel = future.channel();
            m_channel.closeFuture().sync();
        } catch (Throwable e) {
            m_logger.error(e.getMessage(), e);
        } finally {
            m_descriptor.getBossGroup().shutdownGracefully();
            m_descriptor.getGroup().shutdownGracefully();
            m_latch.countDown();
        }
    }

    public void setDescriptor(ServerTransportDescriptor descriptor) {
        m_descriptor = descriptor;
    }

    private class ChannelGroupHandler extends ChannelInboundHandlerAdapter implements Cloneable {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();

            m_channelGroup.add(channel);
            super.channelActive(ctx);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            Channel channel = ctx.channel();

            m_channelGroup.remove(channel);
            super.channelInactive(ctx);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.channel().close();
        }
    }

    private class ChannelInboundHandler extends ByteToMessageDecoder implements Cloneable {
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
            if (buf.readableBytes() < 6) {
                return;
            }

            int index = buf.readerIndex();
            short b1 = buf.getUnsignedByte(index);
            short b2 = buf.getUnsignedByte(index + 1);
            int length = buf.getInt(index + 2);

            if (b1 != 0xCA || b2 != 0xFE) { // not 0xCAFE
                throw new DecoderException("Bad header bytes!");
            } else if (buf.readableBytes() >= length + 6) {
                ByteBuf frame = buf.slice(index + 6, length);

                buf.readerIndex(index + 6 + length);
                m_descriptor.getHub().onMessage(frame, ctx.channel());
            }
        }
    }

    private class ServerChannelInitializer extends ChannelInitializer<Channel> {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();

            pipeline.addLast(new ChannelGroupHandler());
            pipeline.addLast(new ChannelInboundHandler());
            pipeline.addLast(new ServerStateHandler(m_descriptor.getName()));

            for (Map.Entry<String, ChannelHandler> e : m_descriptor.getHandlers().entrySet()) {
                String name = e.getKey();
                ChannelHandler handler = e.getValue();

                if (handler instanceof Cloneable) {
                    Method method = Reflects.forMethod().getDeclaredMethod(Object.class, "clone");

                    method.setAccessible(true);
                    pipeline.addLast(name, (ChannelHandler) method.invoke(handler));
                } else {
                    pipeline.addLast(name, handler);
                }
            }
        }
    }
}
