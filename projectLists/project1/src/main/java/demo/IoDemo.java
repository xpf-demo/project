package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class IoDemo {
	
	public static void main(String[] args) {
		//readFile("E:/report-2018-05-07-0.log");
		//wirteAndReadFile();
		Long number = 1234567891045454545L;
		System.out.println(number.intValue());
		
		Map<String,Object> map = new HashMap<>();
	}
	
	
	/**
	 * @Description: 读取文件里面的内容
	 * @author: xiepanfeng     
	 * @date:   2018年5月11日 下午2:23:18   
	 * @version V1.0
	 */
	public static void readFile(String fileName) {
		FileInputStream fis = null;
		try {
			//1.读取文件
			File file = new File(fileName);
			Long fileLength = file.length();
			byte[] fileContent = new byte[fileLength.intValue()];
			//2.创建输入流
			fis = new FileInputStream(file);
			fis.read(fileContent);
			System.out.println(new String(fileContent, "utf-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void wirteAndReadFile() {
		//获取一个文件，如果不存在就新建
		File file = new File("D:/a.txt");
		OutputStream os = null;
		OutputStreamWriter osw = null;
		
		InputStream is = null;
		InputStreamReader isr = null;
		try {
			//写文件
			os = new FileOutputStream(file);
			osw = new OutputStreamWriter(os, "gbk");
			osw.write("炫小峰");
			osw.flush();
			//读文件
			is = new FileInputStream(file);
			isr = new InputStreamReader(is, "gbk");
			char[] fileContent = new char[Long.valueOf(file.length()).intValue()];
			while(isr.ready()) {
				isr.read(fileContent);
			}
			System.out.println(new String(fileContent));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	 
}
