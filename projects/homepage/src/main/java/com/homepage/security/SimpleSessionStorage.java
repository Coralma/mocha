package com.homepage.security;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.wicket.Session;

public class SimpleSessionStorage implements SessionStorageInterface {

	private static ConcurrentHashMap<String, SecuritySession> commonSessionsStores = new ConcurrentHashMap<String, SecuritySession>();

	@Override
	public void saveSession() {
		// TODO Auto-generated method stub

	}

	@Override
	public Session getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ConcurrentHashMap<String, SecuritySession> getCommonSessionsStores() {
		return commonSessionsStores;
	}

	public static void setCommonSessionsStores(ConcurrentHashMap<String, SecuritySession> commonSessionsStores) {
		SimpleSessionStorage.commonSessionsStores = commonSessionsStores;
	}

}
