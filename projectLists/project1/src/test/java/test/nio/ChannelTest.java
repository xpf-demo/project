package test.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class ChannelTest {
	
	
	@Test
	public void test02() {
		long start = System.currentTimeMillis();
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			//1.使用直接缓冲区完成文件的复制(基内存映射文件)
			inChannel = FileChannel.open(Paths.get("E:\\\\git\\\\myGitHub\\\\project\\\\projectLists\\\\project1\\\\src\\\\test\\\\java\\\\test\\\\nio\\\\kafka.tgz"), StandardOpenOption.READ);
			outChannel = FileChannel.open(Paths.get("E:\\\\git\\\\myGitHub\\\\project\\\\projectLists\\\\project1\\\\src\\\\test\\\\java\\\\test\\\\nio\\\\kafka1.tgz"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
			//2.进行内存映射文件
			MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
			MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
			//3.对缓冲区进行数据的读写操作
			byte[] dst = new byte[inMappedByteBuffer.limit()];
			inMappedByteBuffer.get(dst);
			outMappedByteBuffer.put(dst);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//4.回收资源
			if(inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long end = System.currentTimeMillis();
		 System.out.println("test02最终耗时为:" + (end - start));
	}
	
	@Test
	public void test01() {
		long start = System.currentTimeMillis();
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			//1.创建输入输出流对象
			 fis = new FileInputStream("E:\\git\\myGitHub\\project\\projectLists\\project1\\src\\test\\java\\test\\nio\\kafka.tgz");
			 fos = new FileOutputStream("E:\\git\\myGitHub\\project\\projectLists\\project1\\src\\test\\java\\test\\nio\\kafka2.tgz");
			//2.通过流对象获取通道对象channel
			 inChannel = fis.getChannel();
			 outChannel = fos.getChannel();
			//3.创建指定大小的缓冲区
			ByteBuffer buf = ByteBuffer.allocate(1024);
			//4.将通道中的数据存入到缓冲区中
			while(inChannel.read(buf) != -1) {
				//4.1缓冲区切换到读取数据模式
				buf.flip();
				//5.将缓冲区中的数据写入输出通道
				outChannel.write(buf);
				buf.clear();//清空缓冲区
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//6.回收资源
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			 if(inChannel != null){
		            try {
		                inChannel.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }

		        }
		        if(fos != null){
		            try {
		                fos.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		        if(fis != null){
		            try {
		                fis.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		}
		long end = System.currentTimeMillis();
	    System.out.println("test01最终耗时为:" + (end - start));
		
	}

}
