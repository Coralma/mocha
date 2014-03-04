package com.coral.foundation.socialAPI;

import facebook4j.auth.AccessToken;

/*
 * The facebook token 
 * */
public class FacebookToken extends AccessToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String token;

	public FacebookToken(String token) {
		super(token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
