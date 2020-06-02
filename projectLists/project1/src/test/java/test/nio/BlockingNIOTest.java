package test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class BlockingNIOTest {
	
	/*
	 * 	使用nio完成网络通信 需要三个核心对象
	 * 1.通道Channel
	 * java.nio.channels.Channel接口
	 * SocketChannel
	 * ServerSocketChannel
	 * DatagramChannel
	 * 
	 * 管道相关：
	 * Pipe.SinkChannel
	 * Pipe.SourceChannel
	 * 
	 * 2.缓冲buffer:负责存储数据
	 * 
	 * 3.Selector:是selectableChannel的多路复用器，主要是用于监控selectableChannel的IO状况
	 */
	
	//1.阻塞式的网络通信：通过客户端socket向服务端的socket发送图片，服务端将图片保存到本地
	@Test
	public void client() {
		SocketChannel sChannel = null;
		FileChannel inChannel = null;
		try {
			//1.获取通道
			sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
			//1.2.创建文件通道
			inChannel = FileChannel.open(Paths.get("E:\\\\git\\\\myGitHub\\\\project\\\\projectLists\\\\project1\\\\src\\\\test\\\\java\\\\test\\\\nio\\\\1.jpg"), StandardOpenOption.READ);
			//2.分配指定大小的缓冲区
			ByteBuffer buf = ByteBuffer.allocate(1024);
			//3.发送数据，需要读取文件
			while(inChannel.read(buf) != -1) {
				buf.flip();//切换buf模式为读模式
				sChannel.write(buf);//将buf中的数据写入通道中
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
			if(inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void server() {
		ServerSocketChannel ssChannel = null;
		FileChannel outChannel = null;
		SocketChannel sChannel = null;
		try {
			//1.获取通道
			ssChannel = ServerSocketChannel.open();
			//1.2创建一个输出通道，将读取到的数据写入输出通道中，保存在2.jpg文件中
			outChannel = FileChannel.open(Paths.get("E:\\\\\\\\git\\\\\\\\myGitHub\\\\\\\\project\\\\\\\\projectLists\\\\\\\\project1\\\\\\\\src\\\\\\\\test\\\\\\\\java\\\\\\\\test\\\\\\\\nio\\\\\\\\2.jpg"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);
			//2.绑定端口
			ssChannel.bind(new InetSocketAddress(9898));
			//3.等待客户端连接，当连接成功，就会得到一个连接的通道
			sChannel = ssChannel.accept();
			//4.创建缓冲区
			ByteBuffer buf = ByteBuffer.allocate(1024);
			//5.接受客户端的数据，并且储存到本地
			while(sChannel.read(buf) != -1) {
				buf.flip();
				outChannel.write(buf);
				buf.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//6.关闭通道
			if(outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(sChannel != null) {
				try {
					sChannel.close();
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
	

}
