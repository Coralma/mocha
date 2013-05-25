package com.homepage.security;

import org.apache.wicket.Session;

public interface SessionStorageInterface {
	public void saveSession();
	
	public Session getSession();
}
