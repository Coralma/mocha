package com.coral.vaadin.logging;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusManager;


public class LoggerStatusListener {
	

	
	public LoggerStatusListener(){
		
	}
	
	//Make sure the logback has been configured correctly
	public static void init() {
		
		LoggerContext lc=(LoggerContext)LoggerFactory.getILoggerFactory();
		StatusManager statusManager=lc.getStatusManager();
		OnConsoleStatusListener onConsoleStatusListener=new OnConsoleStatusListener();
		statusManager.add(onConsoleStatusListener);		
		
	}

}
