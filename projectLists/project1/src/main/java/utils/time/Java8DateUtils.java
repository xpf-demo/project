package utils.time;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Java8DateUtils {
	
	public static final DateTimeFormatter dateTimeFormatteryyyyMMdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter dateTimeFormatteryyyyMMddHH = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
	public static final DateTimeFormatter dateTimeFormatteryyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	public static final DateTimeFormatter dateTimeFormatteryyyyMMddHHmmss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	
	public static void main(String[] args) {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.format(dateTimeFormatteryyyyMMdd));
		System.out.println(localDateTime.format(dateTimeFormatteryyyyMMddHH));
		System.out.println(localDateTime.format(dateTimeFormatteryyyyMMddHHmm));
		System.out.println(localDateTime.format(dateTimeFormatteryyyyMMddHHmmss));
		
		System.out.println(localDateTime.getDayOfMonth());
		System.out.println(localDateTime.plusYears(2));//加
		System.out.println(localDateTime.minusYears(2));//减
		System.out.println(localDateTime.getDayOfYear());
		System.out.println(localDateTime.getDayOfMonth());
		System.out.println(localDateTime.getDayOfWeek().getValue());
		System.out.println(localDateTime.getMonth());
		System.out.println(localDateTime.getMonth().getValue());
		System.out.println(localDateTime.getMonthValue());
		System.out.println(LocalDateTime.of(1994, 11, 12, 10, 10, 10, 100));
		LocalDateTime birthDay = LocalDateTime.of(1994, 11, 12, 10, 10, 10);
		LocalDateTime nowDay  = LocalDateTime.now();
		
		Period period = Period.between(birthDay.toLocalDate(), nowDay.toLocalDate());
		System.out.printf("年龄 : %d 年 %d 月 %d 日 \n", period.getYears(), period.getMonths(), period.getDays());
		
		
		long daysDiff = ChronoUnit.DAYS.between(birthDay, nowDay);
		System.out.println("时间相差天数："+daysDiff);
		System.out.println("时间相差月数："+ChronoUnit.MONTHS.between(birthDay, nowDay));
		System.out.println("时间相差年数："+ChronoUnit.YEARS.between(birthDay, nowDay));
		
		System.out.println("一万年以后的日期："+birthDay.plusDays(10000).format(dateTimeFormatteryyyyMMdd));
		
	}

}
