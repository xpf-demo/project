package com.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils{
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 *//*
	public static boolean isEmpty(String str){
		if(str == null){
			return true;
		}
		if(str.length()<1){
			return true;
		}
		if("".equals(str)){
			return true;
		}
		return false;
	}
	
	
	public static boolean isBlank(String str){
		if(str == null){
			return true;
		}
		if(str.length()<1){
			return true;
		}
		if("".equals(str)){
			return true;
		}
		return false;
	}*/
	
	/**
	 * 是否存在空字符串或者空白字符串
	 * @param strings
	 * @return true 存在，false不存在
	 * @date 2016年11月16日
	 * @author liubo04
	 */
	public static boolean isExistBlank(String ... strings){
		if(strings == null || strings.length == 0){
			return true;
		}
		for (String string : strings) {
			if(isBlank(string)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断是否全部为非空空字符串
	 * @param strings
	 * @return true全为非空字符串，false存在空串
	 * @date 2016年11月16日
	 * @author liubo04
	 */
	public static boolean isAllNotBlank(String ... strings){
		if(strings == null || strings.length == 0){
			return false;
		}
		for (String string : strings) {
			if(isBlank(string)){
				return false;
			}
		}
		return true;
	}

	/**
	 * @function:去掉空格
	 * @param str
	 * @return String
	 * @exception @since
	 *                1.0.0
	 */
	public static String trimAllEmpty(String str) {
		return str == null ? str : str.replaceAll(" +", "");
	}

	

	/**
	 * @function: 随机8位数字邀请码
	 * @return int
	 * @exception @since
	 *                1.0.0
	 */
	public static int inviteCode() {
		int code = 0;
		int number = 100000000;
		while (true) {
			code = (int) (Math.random() * number);
			if (String.valueOf(code).length() == 8 && code > 10000000 && code < 99999999) {
				break;
			}
		}
		return code;
	}

	/**
	 * 
	 * @function: 将空字符串转为null
	 * @param str
	 *            空字符串
	 * @return
	 * @return type Object
	 * @exception @since
	 *                1.0.0
	 */
	public static Object covertEmptyStringToNull(String str) {
		if (str != null && "".equals(str.trim())) {
			return null;
		} else {
			return str;
		}
	}

	/**
	 * 
	 * @function: 将null转为空字符串
	 * @param str
	 * @return
	 * @return type Object
	 * @exception @since
	 *                1.0.0
	 */
	public static String covertNullToEmptyString(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	public static Object covertNullObjectToEmptyString(Object str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	// 金额保存两位小数，并且不四舍五入
	public static BigDecimal moneyToKeepDecimals(Object object, int len) {
		String newMoney = "0.00";
		if (null != object) {
			String money = object.toString();
			if (money.contains(".")) {
				String[] strArray = money.split("\\.");
				if (1 == strArray[1].length()) {
					newMoney = money + "0";
				} else if (2 == strArray[1].length()) {
					newMoney = money;
				} else {
					newMoney = strArray[0] + "." + strArray[1].substring(0, len);
				}
			} else {
				newMoney = money + ".00";
			}
		}
		return new BigDecimal(newMoney);
	}

	public static String formatNumber(Double num) {
		try {
			return new DecimalFormat("##.##").format(num);
		} catch (Exception e) {
			return null;
		}
	}

	public static String formatTime(Date date) {
		if (date != null)
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return null;
	}

	public static String formatTime(String format, Date date) {
		if (date != null)
			return new SimpleDateFormat(format).format(date);
		return null;
	}

	/**
	 * 
	 * @function: 生成 interestIntoParmStr 参数收益转入接口参数
	 * @param param
	 * @return String
	 * @exception @since
	 *                1.0.0
	 */
	public static String buildInterestIntoParmStr(Map<String, Map<String, String>> param) {
		Set<String> keys = param.keySet();
		StringBuffer res = new StringBuffer();
		for (String k : keys) {
			Map<String, String> map = param.get(k);
			res.append(map.get("uid") + ",");
			res.append(map.get("hxAccName") + ",");
			res.append(map.get("loginPwd") + ",");
			res.append(map.get("amount") + ",");
			res.append(map.get("orderNo") + ",");
			res.append(map.get("datetime") + ",");
			res.append(map.get("remarkInfo") + ";");
		}
		return res.toString().substring(0, res.length() - 1);
	}

	/**
	 * 
	 * @function: 把Double格式化保留两位小数，但不四舍五入
	 * @param d
	 * @return String
	 * @exception @since
	 *                1.0.0
	 */
	public static String formatDouble(Double d) {
		if (d != null) {
			DecimalFormat formater = new DecimalFormat();
			formater.setMaximumFractionDigits(2);
			formater.setGroupingSize(0);
			formater.setRoundingMode(RoundingMode.FLOOR);
			return formater.format(d);
		} else {
			return null;
		}
	}

	/**
	 * 过滤HTML标签
	 * 
	 * @function:
	 * @param str
	 * @return String
	 * @exception @since
	 *                1.0.0
	 */
	public static String toHTMLStr(String str) {
		String value = "";
		if (str == null)
			return null;
		value = str.replaceAll("&", "&amp;");
		value = value.replaceAll(">", "&gt;");
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll("\"", "&quot;");
		value = value.replaceAll("\'", "&#x27;");
		value = value.replaceAll("/", "&#x2F;");
		return value;
	}

	/**
	 * 把数字精确到小数点后两位，并且以千元分隔符分隔
	 * 
	 * @function:
	 * @param str
	 * @return String
	 * @exception @since
	 *                1.0.0
	 */
	public static String strToThousandSign(String str) {
		String newStr = "";
		DecimalFormat df = new DecimalFormat("###,###,###,##0.00");
		try {
			double d = Double.parseDouble(str);
			newStr = df.format(d);
		} catch (IllegalArgumentException e) {
			newStr = null;
		}
		return newStr;
	}

	/**
	 * 删除指定内容的字符串方法
	 * 
	 * @function:
	 * @param 文本
	 * @param 需要去除的内容([^0-9])
	 * @return String
	 * @exception @since
	 *                1.0.0
	 */
	public static String removeString(String str, String param) {

		Pattern pattern = Pattern.compile(param);
		Matcher matcher = pattern.matcher(str);
		String result = matcher.replaceAll("");
		return result;
	}

	/**
	 * 根据特点字符来分割字符串 str： 要分割的字符串 type：分隔符 t：每隔t数量来分割字符串str 如str： 1324545334
	 * stype：， t：3 结果为：1,324,545,334
	 * 
	 * @exception @since
	 *                1.0.0
	 */
	public static String getStringByType(String str, String type, int t) {
		String result = ""; // 要返回的字符串结果
		if (str.length() <= t) {
			return str;
		} else {
			str = new StringBuilder(str).reverse().toString(); // 先将字符串颠倒顺序
			for (int i = 0; i < str.length(); i++) {
				if (i * t + t > str.length()) {
					result += str.substring(i * t, str.length());
					break;
				}
				result += str.substring(i * t, i * t + t) + type;
			}
			if (result.endsWith(type)) {
				result = result.substring(0, result.length() - 1);
			}
		}
		return new StringBuilder(result).reverse().toString();
	}

	/*
	 * 银行卡号格式化bankNo:银行卡 ；frontNum:取前多少位；backNum:取后多少位
	 */
	public static String formatBankNo(String bankNo, int frontNum, int backNum) {
		if (StringUtils.isEmpty(bankNo)) {
			return "";
		}
		char[] s = bankNo.toCharArray();
		String newStr = "";
		for (int i = 0; i < s.length; i++) {
			if (i < frontNum) {// 取前多少位
				newStr += s[i];
			} else if ((backNum + 1) < i && i < s.length - backNum) {// 取后多少位
				newStr += "*";
			} else {
				newStr += s[i];
			}
			if ((i + 1) % 4 == 0) {
				newStr += " ";
			}
		}
		return newStr;
	}

	/**
	 * @function: 去掉字符串中的回车，换行等空白字符
	 * @param str
	 * @return String
	 * @exception @since
	 *                1.0.0
	 */
	public static String replaceBlank(String str) {
		String dest = new String();
		if (null != str) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * @function: 得到随机数字串
	 * @param n
	 *            长度
	 * @return String
	 * @exception @since
	 *                1.0.0
	 */
	public static String getRandomStr(int n) {
		String str1 = "1234567890";
		String str2 = "";
		int len = str1.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = (Math.random()) * len;
			str2 = str2 + str1.charAt((int) r);
		}
		return str2;
	}
	
	/**
	 * 检测是否有emoji字符
	 * 
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (isEmpty(source)) {
			return false;
		}

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}
	
	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		if (!containsEmoji(source)) {
			return source;// 如果不包含，直接返回
		}

		StringBuilder buf = null;

		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}

				buf.append(codePoint);
			} else {
			}
		}

		if (buf == null) {
			return source;// 如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}

	/**
	 * 将集合转换为字符串，使用英文逗号作为分隔符 例如：[str1,str2,str3...]=>"str1,str2,str3..."
	 * 
	 * @param colle
	 * @param separator
	 * @return
	 * @date 2016年9月5日
	 * @author liubo04
	 */
	public static String list2Str(Collection<String> colle) {
		return list2Str(colle, null);
	}

	/**
	 * 将集合转换为字符串，根据给定分隔符 例如：用逗号分隔，[str1,str2,str3...]=>"str1,str2,str3..."
	 * 
	 * @param colle
	 * @param separator
	 * @return
	 * @date 2016年9月5日
	 * @author liubo04
	 */
	public static String list2Str(Collection<String> colle, String separator) {
		if (colle == null) {
			return null;
		}
		if (colle.isEmpty()) {
			return "";
		}
		if (separator == null) {
			separator = ",";
		}
		StringBuilder s = new StringBuilder();
		Iterator<String> it = colle.iterator();
		if (it.hasNext()) {
			s.append(it.next());
		}
		while (it.hasNext()) {
			s.append(separator).append(it.next());
		}
		return s.toString();
	}

	/**
	 * 将字符串转换为List，使用英文逗号作为分隔符 例如："str1,str2,str3..."=>[str1,str2,str3...]
	 * 
	 * @param str
	 * @return
	 * @date 2016年9月5日
	 * @author liubo04
	 */
	public static List<String> str2List(String str) {
		return str2List(str, null);
	}

	/**
	 * 将字符串转换为List，根据给定分隔符 例如：用逗号分隔，"str1,str2,str3..."=>[str1,str2,str3...]
	 * 
	 * @param str
	 * @param separator
	 * @return
	 * @date 2016年9月5日
	 * @author liubo04
	 */
	public static List<String> str2List(String str, String separator) {
		if (str == null) {
			return null;
		}
		if (str.isEmpty()) {
			return Collections.emptyList();
		}
		if (separator == null) {
			separator = ",";
		}
		String[] strs = str.split(separator);
		return Arrays.asList(strs);
	}

	public static void main(String[] args) {
		System.out.println(toHTMLStr("<script>alert(1);</script>"));
		System.out.println(strToThousandSign("456789.12322"));
	}

    /**
     * 将驼峰状字符串转化为castor形式字符串，例如:userName===>user-name
     *
     * @param str
     * @return
     */
   /* public static String hump2CastorStyle(String str) {
        Validate.notNull(str, "传入字符串不能为null");

        char[] carr = str.toCharArray();

        CharArrayList list = new CharArrayList();
        for (int i = 0; i < carr.length; i++) {
            char c = carr[i];

            if (Character.isUpperCase(c)) {
                if (i == 0) {// 整个单词的首字母变小写
                    c = Character.toLowerCase(c);
                }

                if (i != 0 && !Character.isUpperCase(carr[i - 1])) {
                    c = Character.toLowerCase(c);
                    list.add('-');
                }
            }

            list.add(c);
        }

        return new String(list.toArray());
    }*/

    /**
     * 将驼峰状字符串转化为下划线形式字符串<br>
     * 例如：userNo 转换之后为USER_NO
     *
     * @param str
     * @return
     */
   /* public static String hump2underlineUpperCase(String str) {
        Validate.notNull(str, "传入字符串不能为null");

        char[] carr = str.toCharArray();

        CharArrayList list = new CharArrayList();
        for (int i = 0; i < carr.length; i++) {
            char c = carr[i];

            if (Character.isUpperCase(c)) {
                if (i != 0 && !Character.isUpperCase(carr[i - 1])) {
                    c = Character.toLowerCase(c);
                    list.add('_');
                }
            }

            list.add(c);
        }

        return new String(list.toArray()).toUpperCase();
    }*/
}
