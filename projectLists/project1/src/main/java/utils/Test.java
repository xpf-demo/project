package utils;

import java.math.BigDecimal;

import cn.hutool.Hutool;
import cn.hutool.core.util.ImageUtil;

public class Test {
	
	private Boolean isAa;
	private boolean isBb;

	public static void main(String[] args) {
		
		double numd1 = 10.002d;
		float numf1 = 10.1f;
		double numd2 = 10.001d; 
		System.out.println(numd1-numf1);
		System.out.println(numd1-numd2);
		
		BigDecimal bd1 = new BigDecimal(Double.toString(numd1));
		BigDecimal bd2 = new BigDecimal(Double.toString(numd2));
		System.out.println(bd1.subtract(bd2).doubleValue());
		//ImageUtil.pressText(imageFile, destFile, pressText, color, font, x, y, alpha);
		
		
	}
	

}
