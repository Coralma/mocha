/**
 * 
 */
package com.coral.foundation.md.model.helper;

import com.coral.foundation.md.model.Entity;

/**
 * @author Coral
 *
 */
public class GLog {

	public static void startClass(String calssName) {
		System.out.println("[@@@] Start generate " + calssName);
	}
	
	public static void endClass(String calssName) {
		System.out.println("[@@@] Finish generate " + calssName);
		System.out.println();
	}
	
	public static void info(String message) {
		System.out.println("[Info] " + message);
	}
	
	public static void error(String message) {
		System.out.println("[!!!Error] " + message);
	}
}
