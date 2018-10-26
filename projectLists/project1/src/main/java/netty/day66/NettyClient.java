package netty.day66;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(worker)
				 .channel(NioSocketChannel.class)
				 .handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						socketChannel.pipeline().addLast(new ClientHandler());
					}
				});
		ChannelFuture future = bootstrap.connect("127.0.0.1", 8379).sync();
		String clientMsg = "我是客户端的消息";
		future.channel().writeAndFlush(Unpooled.copiedBuffer(clientMsg.getBytes()));
		future.channel().closeFuture().sync();
		worker.shutdownGracefully();
	}

}
