/**
 * 
 */
package com.coral.foundation.utils;

/**
 * @author coral.ma
 *
 */
public class SqlUtils {

	public static String like(String str) {
		return "%"+str+"%";
	}
	
	public static String cleanLastSign(String conditionStr) {
		int li = conditionStr.lastIndexOf(",");
		if(li < 0) return "";
		String first = conditionStr.substring(0,li);
		String last = conditionStr.substring(li + 1);
		return first + last;
//		return conditionStr.substring(0,conditionStr.lastIndexOf(",")) + conditionStr.substring(conditionStr.lastIndexOf(",") + 1);
	}
	
	public static void main(String[] args) {
		System.out.println(SqlUtils.cleanLastSign("a"));
		System.out.println(SqlUtils.cleanLastSign("a,"));
		System.out.println(SqlUtils.cleanLastSign("a,b,c,"));
		System.out.println(SqlUtils.cleanLastSign(new String()));
	}
}
