package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class DisCardServerHandler extends ChannelHandlerAdapter {

	
	/**
	 * 这里我们覆盖了channelRead()事件处理方法。每当从客户端收到新的数据时，这个方法会在收到信息时被调用,
	 * 这个例子中，收到的信息的类型是byteBuf
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		/*try {
			ByteBuf in = (ByteBuf)msg;
			//打印客户端输入，传输过来的字符
			System.out.print(in.toString(CharsetUtil.UTF_8));
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			
			 * ByteBuf是一个引用计数对象，这个对象必须显示的调用release()方法来释放
			 * 请记住处理器的职责是释放所有传递到处理器的引用计数对象
			 
			//抛弃收到的数据
			ReferenceCountUtil.release(msg);
		}*/
//		super.channelRead(ctx, msg);
		
		ctx.write(msg);//这里不会把消息写到通道中，而是被缓存在内部
		ctx.flush();//这个方法吧缓存区中的数据强行输出
		//ctx.writeAndFlush(msg);//等同于上面的两个操作
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		/**
		 * exceptionCaught()事件处理方法是当出现Throwable对象才会被调用，即当Netty由于IO错误或者处理器在处理事件
		 * 时抛出的异常时。在大部分情况下，捕获的异常应该被记录下来，并且把关联的channel给关闭掉。然而这个方法的处理方式
		 * 会在遇到不同异常的情况下有不同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息
		 */
		//出现异常就关闭
		cause.printStackTrace();
		ctx.close();
//		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		final ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int)(System.currentTimeMillis()/1000L+2208988800L));
		
		final ChannelFuture f = ctx.writeAndFlush(time);
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				assert f == future;
				ctx.close();
			}
		});
	}

}
