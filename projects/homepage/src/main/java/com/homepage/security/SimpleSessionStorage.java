package com.homepage.security;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;

import org.apache.commons.logging.LogFactory;
import org.apache.wicket.Session;
import org.apache.wicket.request.http.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleSessionStorage implements SessionStorageInterface {

	private static Logger log = LoggerFactory
			.getLogger(SimpleSessionStorage.class);
	private static ConcurrentHashMap<String, SecuritySession> commonSessionsStores = new ConcurrentHashMap<String, SecuritySession>();

	final static String publicSessionName = "mochaSession";

	@Override
	public void saveSession() {
		// TODO Auto-generated method stub

	}

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	public SimpleSessionStorage() {
		super();
	}

	public static ConcurrentHashMap<String, SecuritySession> getCommonSessionsStores() {
		return commonSessionsStores;
	}

	public static void setCommonSessionsStores(
			ConcurrentHashMap<String, SecuritySession> commonSessionsStores) {
		SimpleSessionStorage.commonSessionsStores = commonSessionsStores;
	}

	public static boolean checkRequestSessions() {
		// List<javax.servlet.http.Cookie> cookies = ((WebRequest)
		// getRequestCycle()
		// .getRequest()).getCookies();
		// if (getCurrentWebRequest() != null) {
		// List<javax.servlet.http.Cookie> cookies = getCurrentWebRequest()
		// .getCookies();
		// boolean flag = false;
		// for (javax.servlet.http.Cookie cookie : cookies) {
		// if (cookie.getName().contains(publicSessionName)) {
		// flag = true;
		// break;
		// }
		// }
		// return flag;
		// }
		// log.error("request session can't be null");
		// return false;

		HashMap<String, String> simpleMap = SecuritySession.get()
				.getSimpleMap();
		for (String key : simpleMap.keySet()) {
			if (key.contains(publicSessionName)) {
				return true;
			}
		}
		return false;
	}

	public static void setPublicSession() {
		SecuritySession.get().getSimpleMap()
				.put(publicSessionName, UUID.randomUUID().toString());
	}

}
