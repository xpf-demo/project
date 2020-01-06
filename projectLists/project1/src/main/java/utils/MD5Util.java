package utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description MD5加密工具类
 * @date 2016年6月3日
 * @author madison
 */
public class MD5Util {

	private final static Logger logger = LoggerFactory.getLogger(MD5Util.class);
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	
	/**
	 * 转换字节数组为16进制字串
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	/**
	 * 转换字节数组为高位字符串
	 * @param b 字节数组
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	
	 /**
     * @Description 获取摘要信息
     * @param bites
     * @param algorithm
     * @return
     * @throws Exception
     * @date 2016年9月5日
     * @author madison
     */
    public static String calc(byte[] bites, String algorithm){
         try {
             return byteArrayToHexString(MessageDigest.getInstance(algorithm).digest(bites));
         } catch (Exception e) {
             throw new RuntimeException("获取摘要失败", e);
         }
    }
    
    /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
		return calc(origin.getBytes(), "MD5");
    }
	
	public static void main(String[] args) {
		String signTest1 = MD5Util.MD5Encode("appId=wxd678efh567hg6787&nonceStr=5K8264ILTKCH16CQ2502SI8ZNMTM67VS&package=prepay_id=wx2017033010242291fcfe0db70013231072&signType=MD5&timeStamp=1490840662&key=qazwsxedcrfvtgbyhnujmikolp111111").toUpperCase();
		System.out.println("signTest:"+signTest1);
	}

}