package demo;

import java.util.concurrent.TimeUnit;

/**
 * @Description:    TimeUnit类的用法
 * @author: xiepanfeng     
 * @date:   2018年4月26日 上午10:43:26   
 * @version V1.0
 */
public class TimeUnitDemo {
	
	public static void main(String[] args) {
		
		try {
			System.out.println(TimeUnit.DAYS.toHours(1));//一天有多少小时
			TimeUnit.SECONDS.sleep(5);//延迟五秒
			System.out.println(TimeUnit.HOURS.toDays(48));//48小时有多少天
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
