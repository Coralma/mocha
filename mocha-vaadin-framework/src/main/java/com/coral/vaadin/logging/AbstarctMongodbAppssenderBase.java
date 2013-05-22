package com.coral.vaadin.logging;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.net.SocketFactory;
import javax.persistence.Entity;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBDecoderFactory;
import com.mongodb.DBEncoderFactory;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

import ch.qos.logback.access.db.DBAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.db.DBAppenderBase;

public abstract class AbstarctMongodbAppssenderBase extends
		UnsynchronizedAppenderBase<ILoggingEvent> {

	private static DB db;

	private static Mongo mongo;
	private String rootName = "vance";
	private String host = "localhost";
	private String dbname = "logService";
	private int port = 27017;
	private String rootPw = "vance";
	private static int connectionsPerHost;
	private static int threadsAllowedToBlockForConnectionMultiplier;
	private static int maxWaitTime;
	private static int connectTimeout;
	private static int socketTimeout;
	private static boolean socketKeepAlive;
	private static boolean autoConnectRetry;
	private static long maxAutoConnectRetryTime;
	private static boolean slaveOk;
	private static DBDecoderFactory dbDecoderFactory;
	private static DBEncoderFactory dbEncoderFactory;
	private static boolean safe;
	private static int w;
	private static int wtimeout;
	private static boolean fsync;
	private static boolean j;
	private static SocketFactory socketFactory;
	private static String dbCollectionName="appLog";

	public static boolean needToLogDB = false;

	public AbstarctMongodbAppssenderBase(String host, String rootName,
			String rootPw, String dbName) {
		if (host != null) {
			this.host = host;
			this.rootName = rootName;
			this.rootPw = rootPw;
			this.dbname = dbName;
		}
	}

	@Override
	public void start() {
		if(connectionToDB()){
			super.start();
		}
	}

	abstract BasicDBObject mongodbEntrySave(ILoggingEvent eventObject);

	private boolean connectionToDB() {
		if (mongo == null) {
			try {
				MongoOptions mongoOptions = buildOptions();
				mongo = new Mongo(new ServerAddress(this.host, this.port));
				db = mongo.getDB(dbname);
				db.authenticate(this.rootName, this.rootPw.toCharArray());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
		}
		if(db.isAuthenticated()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void append(ILoggingEvent eventObject) {
		DBObject options=new BasicDBObject();
		if(!db.collectionExists(dbCollectionName)){
			db.createCollection(dbCollectionName, options);
		}
		DBCollection dbCollection=db.getCollection(dbCollectionName);
		DBObject messageDB=mongodbEntrySave(eventObject);
//		messageDB.put("message", eventObject.getMessage());
		dbCollection.save(messageDB);
	}

	private MongoOptions buildOptions() {
		MongoOptions mongoOptions = new MongoOptions();
		// mongoOptions.connectionsPerHost = getConnectionsPerHost();
		// mongoOptions.threadsAllowedToBlockForConnectionMultiplier =
		// getThreadsAllowedToBlockForConnectionMultiplier();

		return mongoOptions;
	}

	public static boolean isNeedToLogDB() {
		return needToLogDB;
	}

	public static void setNeedToLogDB(boolean needToLogDB) {
		AbstarctMongodbAppssenderBase.needToLogDB = needToLogDB;
	}

	public static int getConnectionsPerHost() {
		return connectionsPerHost;
	}

	public static void setConnectionsPerHost(int connectionsPerHost) {
		AbstarctMongodbAppssenderBase.connectionsPerHost = connectionsPerHost;
	}

	public static int getThreadsAllowedToBlockForConnectionMultiplier() {
		return threadsAllowedToBlockForConnectionMultiplier;
	}

	public static void setThreadsAllowedToBlockForConnectionMultiplier(
			int threadsAllowedToBlockForConnectionMultiplier) {
		AbstarctMongodbAppssenderBase.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
	}

	public static int getMaxWaitTime() {
		return maxWaitTime;
	}

	public static void setMaxWaitTime(int maxWaitTime) {
		AbstarctMongodbAppssenderBase.maxWaitTime = maxWaitTime;
	}

	public static int getConnectTimeout() {
		return connectTimeout;
	}

	public static void setConnectTimeout(int connectTimeout) {
		AbstarctMongodbAppssenderBase.connectTimeout = connectTimeout;
	}

	public static int getSocketTimeout() {
		return socketTimeout;
	}

	public static void setSocketTimeout(int socketTimeout) {
		AbstarctMongodbAppssenderBase.socketTimeout = socketTimeout;
	}

	public static boolean isAutoConnectRetry() {
		return autoConnectRetry;
	}

	public static void setAutoConnectRetry(boolean autoConnectRetry) {
		AbstarctMongodbAppssenderBase.autoConnectRetry = autoConnectRetry;
	}

	public static long getMaxAutoConnectRetryTime() {
		return maxAutoConnectRetryTime;
	}

	public static void setMaxAutoConnectRetryTime(long maxAutoConnectRetryTime) {
		AbstarctMongodbAppssenderBase.maxAutoConnectRetryTime = maxAutoConnectRetryTime;
	}

	public static boolean isSlaveOk() {
		return slaveOk;
	}

	public static void setSlaveOk(boolean slaveOk) {
		AbstarctMongodbAppssenderBase.slaveOk = slaveOk;
	}

	public static DBDecoderFactory getDbDecoderFactory() {
		return dbDecoderFactory;
	}

	public static void setDbDecoderFactory(DBDecoderFactory dbDecoderFactory) {
		AbstarctMongodbAppssenderBase.dbDecoderFactory = dbDecoderFactory;
	}

	public static DBEncoderFactory getDbEncoderFactory() {
		return dbEncoderFactory;
	}

	public static void setDbEncoderFactory(DBEncoderFactory dbEncoderFactory) {
		AbstarctMongodbAppssenderBase.dbEncoderFactory = dbEncoderFactory;
	}

	public static boolean isSocketKeepAlive() {
		return socketKeepAlive;
	}

	public static void setSocketKeepAlive(boolean socketKeepAlive) {
		AbstarctMongodbAppssenderBase.socketKeepAlive = socketKeepAlive;
	}

	public static boolean isSafe() {
		return safe;
	}

	public static void setSafe(boolean safe) {
		AbstarctMongodbAppssenderBase.safe = safe;
	}

	public static int getW() {
		return w;
	}

	public static void setW(int w) {
		AbstarctMongodbAppssenderBase.w = w;
	}

	public static int getWtimeout() {
		return wtimeout;
	}

	public static void setWtimeout(int wtimeout) {
		AbstarctMongodbAppssenderBase.wtimeout = wtimeout;
	}

	public static boolean isFsync() {
		return fsync;
	}

	public static void setFsync(boolean fsync) {
		AbstarctMongodbAppssenderBase.fsync = fsync;
	}

	public static SocketFactory getSocketFactory() {
		return socketFactory;
	}

	public static void setSocketFactory(SocketFactory socketFactory) {
		AbstarctMongodbAppssenderBase.socketFactory = socketFactory;
	}

	public static boolean isJ() {
		return j;
	}

	public static void setJ(boolean j) {
		AbstarctMongodbAppssenderBase.j = j;
	}

}
