package org.unidal.net.transport;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * The hub for message to send out or come in.
 */
public interface TransportHub {
    /**
     * Fills the buffer to send out.
     *
     * @param buf the buffer to be filled
     * @return true if the buffer is filled, false otherwise
     */
    boolean fill(ByteBuf buf);

    /**
     * Be triggered when a message comes in.
     *
     * @param buf     the buffer contains message content
     * @param channel where the message comes from
     */
    void onMessage(ByteBuf buf, Channel channel);
}
