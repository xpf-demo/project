package com.util;


import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author xpf_d
 */
public class Base64Utils{
	
	private static final Logger logger = LoggerFactory.getLogger(Base64Utils.class);
	
	private static final String charset = "utf-8";

	public static String encode(String str, String _charset) throws UnsupportedEncodingException {
		if(StringUtils.isEmpty(str)){
			return null;
		}
		_charset = StringUtils.isBlank(_charset) ? charset : _charset;
		byte[] res = Base64.encodeBase64(str.getBytes(_charset));
		return new String(res, _charset);
	}
	
	public static String encodeByte(byte[] bytes){
		return new String(Base64.encodeBase64(bytes));
	}
	
	public static String encode(String str) {
		try {
			return encode(str, charset);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static byte[] decodeByte(String str) {
		return Base64.decodeBase64(str);
	}

	public static String decode(String str, String _charset) throws UnsupportedEncodingException {
		if(StringUtils.isBlank(str)){
			return null;
		}
		byte[] b = Base64.decodeBase64(str);
		_charset = StringUtils.isBlank(_charset) ? charset : _charset;
		return new String(b, _charset);
	}
	
	public static String decode(String str) {
		try {
			return decode(str, charset);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 *   base64加密
	 * @param str
	 * @return
	 */
	public static String encodeBase64URLSafeString(String str) {
		try {
			return Base64.encodeBase64URLSafeString(str.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static String encodeBase64URLSafeString(byte[] data) {
		
		return encodeByte(data).replace('+', '-').replace('/', '_');
	}
	
	/**
	 * base64解密
	 * @param safeBase64Str
	 * @return
	 */
	public static String safeUrlBase64Decode(String safeBase64Str){
		try {
			return safeUrlBase64Decode(safeBase64Str, null);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static String safeUrlBase64Decode(String safeBase64Str, String _charset) throws UnsupportedEncodingException {
		String base64Str = safeBase64Str.replace('-', '+').replace('_', '/');
		int mod4 = base64Str.length() % 4;
		if (mod4 > 0) {
			base64Str = base64Str + "====".substring(mod4);
		}
		byte[] b = decodeByte(base64Str);
		_charset = StringUtils.isBlank(_charset) ? charset : _charset;
		return new String(b, _charset);
	}

}

