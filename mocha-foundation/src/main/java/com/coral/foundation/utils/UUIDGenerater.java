/**
 * 
 */
package com.coral.foundation.utils;

import java.util.UUID;

/**
 * @author coral.ma
 * 
 */
public class UUIDGenerater {

	public static String generate() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(UUIDGenerater.generate());
		System.out.println(UUIDGenerater.generate().length());
	}
}
