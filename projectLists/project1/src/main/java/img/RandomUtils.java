package img;

/**
 * 获取数据的随机数
 * @author madison
 *
 */
public class RandomUtils {
	
	/**朋友圈被访问次数的取值范围*/
	public static final int min_value_timesOfVisitsFriends = 799;
	public static final int max_value_timesOfVisitsFriends = 9999;
	/**抢的红包个数取值范围*/
	public static final int min_value_timesOfRedPacket = 89;
	public static final int max_value_timesOfRedPacket = 799;
	/**最佳手气概率取值范围*/
	public static final double min_value_probability = 0.13;
	public static final double max_value_probability = 0.21;
	/**最多一天发表情次数取值范围*/
	public static final int min_value_timesOfLook = 51;
	public static final int max_value_timesOfLook = 231;
	/**支付次数取值范围*/
	public static final int min_value_timesOfPay = 71;
	public static final int max_value_timesOfPay = 501;
	/**最喜欢用的词取值范围*/
	public static final String[] terms = {"哈哈哈","是的","无语","尬聊","老铁","戏精","杠精","佛系","呵呵","你好","烦","哦","随便","马上","睡觉","晚安","在吗","干吗","嗯嗯"};
	/**朋友圈访问次数排名取值范围*/
	public static final int min_value_rankingOfVisitsFriends = 1000;
	public static final int max_value_rankingOfVisitsFriends = 9999;
	
	/**
	 * 朋友圈被访问次数
	 * @return
	 */
	public static String timesOfVisitsFriends() {
		
		return String.valueOf(getRandomNumber(min_value_timesOfVisitsFriends, max_value_timesOfVisitsFriends));
	}
	
	/**
	 * 抢的红包个数
	 * @return
	 */
	public static String timesOfRedPacket() {
		return String.valueOf(getRandomNumber(min_value_timesOfRedPacket, max_value_timesOfRedPacket));
	}
	
	/**
	 * 获取抢红包的最佳次数
	 * @return
	 */
	public static String timesOfOptimal(int numberOfRedPacket) {
		return String.valueOf(getRandomNumber((int)(numberOfRedPacket*min_value_probability), (int)(numberOfRedPacket*max_value_probability)));
	}
	
	/**
	 * 获取表情次数
	 * @return
	 */
	public static String timesOfLook() {
		return String.valueOf(getRandomNumber(min_value_timesOfLook, max_value_timesOfLook));
	}
	
	/**
	 * 获取支付次数
	 * @return
	 */
	public static String timesOfPay() {
		return String.valueOf(getRandomNumber(min_value_timesOfPay, max_value_timesOfPay));
	}
	
	/**
	 * 获取最喜欢用的词
	 * @return
	 */
	public static String getTerms() {
		return terms[getRandomNumber(0, terms.length)];
	}
	
	/**
	 * 获取朋友圈访问次数排名
	 * @return
	 */
	public static String rankingOfVisitsFriends() {
		return String.valueOf(getRandomNumber(min_value_rankingOfVisitsFriends, max_value_rankingOfVisitsFriends)/100);
	}
	
	/**
	 * 获取当前范围内的随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomNumber(int min, int max) {
		return min + (int)(Math.random()*(max-min));
	}
	
	public static void main(String[] args) {
		System.out.println(getTerms());
	}
	
}
