/*package com.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;


public class HttpServletUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpServletUtils.class);

	public static final String CHARSET = "UTF-8";


	*//**
	 * 输出文件（前端下载文件）
	 * 
	 * @param fileName 下载的文件名称
	 * @param filePath 路径（不包含文件）
	 * @date 2016年11月9日
	 * @author liubo04
	 *//*
	public static void outWriteFile(String fileName, String filePath) {
		outWriteFile(fileName, filePath, null);
	}

	*//**
	 * 输出文件（前端下载文件）
	 * 
	 * @param filePath 路径,完整的文件路径
	 * @date 2016年11月9日
	 * @author liubo04
	 *//*
	public static void outWriteFileByPath(String filePath) {
		outWriteFileByPath(filePath, null);
	}

	*//**
	 * 输出文件（前端下载文件）
	 * 
	 * @param filePath 路径,完整的文件路径
	 * @param displayFileName 输出到前端的文件名称
	 * @date 2016年11月9日
	 * @author liubo04
	 *//*
	public static void outWriteFileByPath(String filePath, String displayFileName) {
		if (!filePath.contains(File.separator)) {
			outWriteFile(filePath, "", displayFileName);
		} else {
			int li = filePath.lastIndexOf(File.separator);
			outWriteFile(filePath.substring(li + 1), filePath.substring(0, li), displayFileName);
		}
	}

	*//**
	 * 输出文件（前端下载文件）
	 * 
	 * @param fileName 下载的文件名称
	 * @param filePath 路径（不包含文件）
	 * @param displayFileName 输出到前端的文件名称
	 * @date 2016年11月9日
	 * @author liubo04
	 *//*
	public static void outWriteFile(String fileName, String filePath, String displayFileName) {
		File file = new File(filePath + File.separator + fileName);
		if (!file.exists()) {
			logger.error("path:{}", filePath + File.separator + fileName);
			//throw new BusinessException("未找到文件");
		}
		if (StringUtils.isEmpty(displayFileName)) {
			displayFileName = fileName;
		}
		try {
			outWriteFile(new FileInputStream(file), displayFileName);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
			//throw new BusinessException("未找到文件");
		}
	}

	*//**
	 * 输出文件（前端下载文件）
	 * 
	 * @param is 要下载的文件流
	 * @param displayFileName 输出到前端的文件名称
	 * @date 2016年11月9日
	 * @author liubo04
	 *//*
	public static void outWriteFile(InputStream is, String displayFileName) {
		BufferedOutputStream out = null;
		BufferedInputStream in = null;
		try {
			HttpServletResponse response = ServletUtils.getResponse();
			displayFileName = new String(displayFileName.getBytes(CHARSET), "ISO8859-1");// 解决中文乱码和不显示问题
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			response.setCharacterEncoding(CHARSET);
			response.setHeader("Content-Disposition", "attachment; filename=" + displayFileName);
			in = new BufferedInputStream(is);
			out = new BufferedOutputStream(response.getOutputStream());
			response.setHeader("Content-Length", String.valueOf(in.available()));
			byte[] data = new byte[1024];
			int len = 0;
			while (-1 != (len = in.read(data, 0, data.length))) {
				out.write(data, 0, len);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			//throw new BusinessException("下载文件异常");
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	
}

*/