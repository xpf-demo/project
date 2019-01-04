package com.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
	
	/**
	 * 连接url 
	 * @param context 用户信息
	 * @param path 地址
	 * @return
	 */
	public static String httpRequest(String path,String params){
		URL url = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		DataOutputStream dos = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader;
		StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
		
		StringBuffer paramSbf = new StringBuffer();
		if(params != null){
			paramSbf.append(params);
		}
		
		try{
			url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8;");
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.connect();
	
			dos = new DataOutputStream(conn.getOutputStream());
			dos.write(paramSbf.toString().getBytes("UTF-8"));
			dos.flush();
			dos.close();
			
			is = conn.getInputStream();
            inputStreamReader = new InputStreamReader(is,"utf-8");
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(dos != null)
					dos.close();
				if(is != null)
					is.close();
				if(conn != null)
					conn.disconnect();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return resultBuffer.toString();
	}
	
	public static void main(String[] args) {
		
		
	}
	

}
