package utils;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 	从字符串中获取电话号码
 * @author madison
 */
public class CheckIfIsPhoneNumberUtil {

	/**
	 * 获得电话号码的正则表达式：包括固定电话和移动电话 符合规则的号码： 1》、移动电话 86+‘-’+11位电话号码 86+11位正常的电话号码
	 * 11位正常电话号码a (+86) + 11位电话号码 (86) + 11位电话号码 2》、固定电话 区号 + ‘-’ + 固定电话 + ‘-’ + 分机号
	 * 区号 + ‘-’ + 固定电话 区号 + 固定电话
	 * 
	 * @return 电话号码的正则表达式
	 */
	public static String isPhoneRegexp() {
		String regexp = "";
		// 能满足最长匹配，但无法完成国家区域号和电话号码之间有空格的情况
		String mobilePhoneRegexp = "(?:(\\(\\+?86\\))((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|"
				+ "(?:86-?((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|"
				+ "(?:((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})";
		// System.out.println("regexp = " + mobilePhoneRegexp);
		// 固定电话正则表达式
		String landlinePhoneRegexp = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|"
				+ "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
		regexp += "(?:" + mobilePhoneRegexp + "|" + landlinePhoneRegexp + ")";
		return regexp;
	}

	/**
	 * 从dataStr中获取出所有的电话号码（固话和移动电话），将其放入Set
	 * @param dataStr  待查找的字符串
	 * @param phoneSet dataStr中的电话号码
	 */
	public static void getPhoneNumFromStrIntoSet(String dataStr, Set<String> phoneSet) {
		// 获得固定电话和移动电话的正则表达式
		String regexp = isPhoneRegexp();
		System.out.println("Regexp = " + regexp);
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(dataStr);
		// 找与该模式匹配的输入序列的下一个子序列
		while (matcher.find()) {
			// 获取到之前查找到的字符串，并将其添加入set中
			phoneSet.add(matcher.group());
		}
	}
	
	/**
	 * 从context中验证是否存在电话号码
	 * @param context
	 * @return
	 */
	public static boolean contextExistPhone(String context) {
		// 获得固定电话和移动电话的正则表达式
		String regexp = isPhoneRegexp();
		System.out.println("Regexp = " + regexp);
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(context);
		// 找与该模式匹配的输入序列的下一个子序列
		while (matcher.find()) {
			//能找到则返回true
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(contextExistPhone(SensitiveWordUtil.dealContext("哈哈哈哈哈，这是一条没有敏感词的回复有。。。18772101153")));
	}

}
