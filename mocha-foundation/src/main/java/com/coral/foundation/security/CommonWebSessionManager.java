package com.coral.foundation.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

public class CommonWebSessionManager extends DefaultWebSessionManager {
	
	private static int defaultWebAge=0;
	private Session session;
	private Cookie cookie;
	
	public CommonWebSessionManager(){
		super();
		getSessionIdCookie().setMaxAge(getDefaultWebAge());
		
	}
	
	
	public int getWebAge(){
		return getSessionIdCookie().getMaxAge();
	}


	public static int getDefaultWebAge() {
		return defaultWebAge;
	}


	public static void setDefaultWebAge(int defaultWebAge) {
		CommonWebSessionManager.defaultWebAge = defaultWebAge;
	}
	
	
	public void saveSession(Session session){
		this.setSession(session);
	}


	public Session getSession() {
		return session;
	}


	public void setSession(Session session) {
		this.session = session;
	}
}
