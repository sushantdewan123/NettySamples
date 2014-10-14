package org.example.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A simple chat client that is responsible for running the client, registering
 * the channel using event loop group, bootstrapping the channel and accepting
 * string input from clients in order to write to that channel
 * 
 * @author sushantdewan
 *
 */
public class ChatClient {

	private final String host;
	private final int port;

	public ChatClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap().group(group)
					.channel(NioSocketChannel.class)
					.handler(new ChatClientInitializer());
			Channel channel = bootstrap.connect(host, port).sync().channel();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			while (true) {
				channel.writeAndFlush(in.readLine() + "\r\n");
			}
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new ChatClient("localhost", 5555).run();
	}
}
