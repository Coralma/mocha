/**
 * 
 */
package com.coral.foundation.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author coral.ma
 *
 */
public class MathUtils {
	
	public static String SUM(String... factors) {
		BigDecimal sum = new BigDecimal("0");
		for(String factor : factors) {
			BigDecimal addDecimal = new BigDecimal(factor);
			sum = sum.add(addDecimal);
		}
		return sum.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public static String sumInt(String... factors) {
		BigDecimal sum = new BigDecimal("0");
		for(String factor : factors) {
			BigDecimal addDecimal = new BigDecimal(factor);
			sum = sum.add(addDecimal);
		}
		return sum.setScale(0).toString();
	}
	
	public static String subtract(String subtrahend, String... minuends) {
		BigDecimal subtrahendDecimal = new BigDecimal(subtrahend);
		for(String minuend : minuends) {
			BigDecimal minuendDecimal = new BigDecimal(minuend);
			subtrahendDecimal = subtrahendDecimal.subtract(minuendDecimal);
		}
		return subtrahendDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public static boolean isNumber(String value) {
		if(value.equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(value);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String multiply(String... factors) {
		if(factors == null) {
			return null;
		}
		if(factors.length == 1) {
			return factors[0]; 
		}
		BigDecimal sum = new BigDecimal(factors[0]);
		for(int i=1; i < factors.length; i++) {
			BigDecimal addDecimal = new BigDecimal(factors[i]);
			sum = sum.multiply(addDecimal);
		}
		return sum.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public static void main(String[] args) {
		System.out.println(MathUtils.sumInt("100", "20","10","5"));
		System.out.println(MathUtils.subtract("100", "20","10","5"));
		System.out.println(MathUtils.subtract("100.9999", "20.1111","10.3333"));
//		System.out.println(MathUtils.multiply("2","3","4"));
//		System.out.println(MathUtils.multiply("2"));
//		System.out.println(MathUtils.multiply(null));
		//is number
//		System.out.println(MathUtils.isNumber("a"));
//		System.out.println(MathUtils.isNumber("@"));
//		System.out.println(MathUtils.isNumber("1"));
//		System.out.println(MathUtils.isNumber("243871029734"));
//		System.out.println(MathUtils.isNumber("243871a29734"));
		
		// sum
//		MathUtils.addFactor("11.111");
//		MathUtils.addFactor("11.114");
//		System.out.print(MathUtils.SUM());
	}
}
