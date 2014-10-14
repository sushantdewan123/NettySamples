package org.example.netty.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Client side handler that handles inbound messages (sent from server to this
 * client)
 * 
 * @author sushantdewan
 *
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		System.out.println(msg);
	}
}
