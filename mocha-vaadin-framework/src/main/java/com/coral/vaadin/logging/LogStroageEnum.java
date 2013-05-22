package com.coral.vaadin.logging;

import ch.qos.logback.core.FileAppender;

public enum LogStroageEnum {
	FILE(FileAppender.class),MONGODB(MongodbAppender.class);
	
	private Class logAppender;
	
	LogStroageEnum(Class logAppender){
		this.logAppender=logAppender;
	}
	
	public Class getLogAppender(){
		return this.logAppender;
	}
}
