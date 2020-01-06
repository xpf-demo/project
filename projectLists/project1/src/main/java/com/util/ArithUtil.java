package com.util;

import java.math.BigDecimal;
/**
 * 
 * @author madison
 */
public class ArithUtil {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 2;

	// 构造器私有化，让这个类不能实例化
	private ArithUtil() {
	}

	// 提供精确的加法运算
	public static double add(double v1, double v2) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.add(b2).doubleValue();
	}

	// 精确的减法运算
	public static double sub(double v1, double v2) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.subtract(b2).doubleValue();
	}

	// 精确的乘法运算
	public static double mul(double v1, double v2) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.multiply(b2).doubleValue();
	}

	// 提供（相对）精确的除法运算，当发生除不尽的情况时
	// 精确到小数点后2位的数字四舍五入
	public static double div(double v1, double v2) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	

}
