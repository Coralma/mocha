package com.coral.vaadin.logging;

import java.util.StringTokenizer;

public class LoggerConfiguration {
	static{
	LoggerStatusListener.init();
	}
	
	private static String defaultSeparator=",";
	/*
	 * Currently we have three logStroage status:
	 * 1. file
	 * 2. mongodb
	 * 3. both of them
	 */	
	private static String defaultLogStore="file";

	public static String getDefaultLogStorge() {
		return defaultLogStore;
	}

	
	public LoggerConfiguration(){
		
	}
	
	public LoggerConfiguration(String logStore){
		
	}


	public void doConfigure() {
		
	}


	/*
	 * Fix it, current log storage is based on the logback.xml configuration
	 */
	public void doConfigure(String logStroages){
//		StringTokenizer logStoragesArray=new StringTokenizer(logStroages,defaultSeparator); 
//		while(logStoragesArray.hasMoreElements()){
//			String logStorageKey=logStoragesArray.nextToken();
//			if(logStorageKey.equals("mongodb")){
//				AbstarctMongodbAppssenderBase.setNeedToLogDB(true);
//			}
//		}
	}
	
	
}
