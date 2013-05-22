package com.coral.vaadin.logging;

import javax.persistence.Entity;

import com.mongodb.BasicDBObject;

import ch.qos.logback.classic.spi.ILoggingEvent;

public class MongodbAppender extends AbstarctMongodbAppssenderBase {

	public MongodbAppender(String host, String rootName, String rootPw,
			String dbName) {
		super(host, rootName, rootPw, dbName);
		
	}
	
	public MongodbAppender(){
		super(null,null,null,null);
	}
	
	@Override
	public void start(){
		setNeedToLogDB(true);
		if (isNeedToLogDB()) {
			super.start();
		}
	}

	@Override
	BasicDBObject mongodbEntrySave(ILoggingEvent eventObject) {
		BasicDBObject basicDBOjbect=new BasicDBObject(); 
		basicDBOjbect.put("timeStamp", eventObject.getTimeStamp());
		basicDBOjbect.put("loggerName", eventObject.getLoggerName());
		basicDBOjbect.put("message", eventObject.getMessage());
		basicDBOjbect.put("brithTime", eventObject.getLoggerContextVO().getBirthTime());
//		basicDBOjbect.put("logLevel", eventObject.getLevel());
		
		return basicDBOjbect;
	}

	
	
	

}
