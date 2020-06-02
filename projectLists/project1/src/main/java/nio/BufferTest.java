package nio;

import java.nio.ByteBuffer;

import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

public class BufferTest {
	
	/**
	 * 管理方法几乎一致，可以通过allocate获取缓冲区
	 * 
	 */
	public static void main(String[] args) {
		//1.创建一个缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("-----allocate-----");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//2.通过put方法 向缓冲区存入数据
		String str = "acvds";
		buf.put(str.getBytes());
		System.out.println("-----put-----");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//3.通过get方法 获取缓冲区的数据 前提是需要切换缓冲区的模式
		buf.flip();
		System.out.println("-----flip-----");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		
		//4.获取数据
		byte[] datas = new byte[buf.capacity()];
		buf.get(datas);
		System.out.println(new String());
		buf.hasRemaining();
		//ByteBuffer.
		

	}

}
