package test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

public class NIOTest {
	/*
     * 使用nio完成网络通信需要三个核心对象
     *
     * 1. 通道Channel
     *  java.nio.channels.Channel接口
     *   SocketChannel
     *   ServerSocketChannel
     *   DatagramChannel
     *
     *   管道相关:
     *   Pipe.SinkChannel
     *   Pipe.SourceChannel
     *
     * 2. 缓冲buffer: 负责存储数据
     *
     * 3. Selector: 是SelectableChannel的多路复用器, 主要是用于监控SelectableChannel的IO状况
     * */
	@Test
	public void server() {
		ServerSocketChannel ssChannel = null;
		Selector selector = null;
		SocketChannel sChannel = null;
		try {
			//1.获取通道
			ssChannel = ServerSocketChannel.open();
			//2.将阻塞的套接字设置为非阻塞
			ssChannel.configureBlocking(false);
			//3.绑定端口号
			ssChannel.bind(new InetSocketAddress(9898));
			//4.创建选择器对象
			selector = Selector.open();
			//5.将通道注册到选择器上，此时选择器就开始监听这个通道的接收事件，如果有接收，接收准备就绪，才开始下一步的操作
			ssChannel.register(selector, SelectionKey.OP_ACCEPT);
			//6.通过轮训的方式获取选择器上准备就绪的事件
			while(selector.select() > 0) {//如果大于0，至少有一个selectionKey准备就绪
				//7.获取当前选择器中所有注册的选择键（已经就绪的监听事件）
				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				//8.迭代获取已经准备就绪的事件
				while(it.hasNext()) {
					//9.获取已经准备就绪的事件
					SelectionKey sk = it.next();
					if(sk.isAcceptable()) {
						//调用accpet()
						sChannel = ssChannel.accept();
						//10.将sChannel设置为非阻塞的
						sChannel.configureBlocking(false);
						//11.将该通道注册到选择器上，想让选择器能够监听这个通道，同样也需要完成注册
						sChannel.register(selector, SelectionKey.OP_READ);
					}else if(sk.isReadable()) {
						//12.如果读状态已经准备就绪，就开始读取数据。
						//12.1获取当前选择器上读状态准备就绪的通道
						sChannel = (SocketChannel) sk.channel();
						//12.2读取客户端发送的数据，需要先创建缓冲区
						ByteBuffer buf = ByteBuffer.allocate(1024);
						//13.读取缓冲区的数据
						int len = 0;
						while((len = sChannel.read(buf)) > 0) {
							buf.flip();
							System.out.println(new String(buf.array(), 0, len));
							//清空缓冲区
							buf.clear();
						}
					}
					//当selectKey使用完毕之后需要移除，否则会一直优先
					it.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//关闭资源
			if(sChannel != null) {
				try {
					sChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(selector != null) {
				try {
					selector.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(ssChannel != null) {
				try {
					ssChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	@Test
	public void client() {
		SocketChannel sChannel = null;
		try {
			//1.获取通道，默认就是阻塞
			sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
			//1.2将阻塞的套接字变为非阻塞
			sChannel.configureBlocking(false);
			//2.创建指定大小的缓冲区
			ByteBuffer buf = ByteBuffer.allocate(1024);
			//3.发送数据给服务端，直接将数据存储到buf
			Scanner scan = new Scanner(System.in);
			while(scan.hasNext()) {
				String str = scan.next();
				buf.put((new Date().toString()+":"+str).getBytes());
				//将缓冲区变为读模式
				buf.flip();
				//4.将缓冲区的数据写入到sChannel当中
				sChannel.write(buf);
				buf.clear();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//4.关闭通道
			if(sChannel != null) {
				try {
					sChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
}
