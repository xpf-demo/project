package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取地址类
 * 
 */
public class AddressUtils {
	private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

	public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

	public static String getRealAddressByIP(String ip) {
		String address = "XX XX";
		// 内网不查询
		if (IpUtils.internalIp(ip)) {
			return "内网IP";
		}
		try {
			String rspStr = "";//HttpConnectionUtil.httpRequest(IP_URL, "ip=" + ip);
			if (StringUtils.isEmpty(rspStr)) {
				log.error("获取地理位置异常 {}", ip);
				return address;
			}
			JSONObject obj = JSONObject.parseObject(rspStr);
			JSONObject data = obj.getJSONObject("data");
			String region = data.getString("region");
			String city = data.getString("city");
			address = region + " " + city;
		} catch (Exception e) {
			log.error("获取地理位置异常 {}", ip);
		}
		return address;
	}
}
