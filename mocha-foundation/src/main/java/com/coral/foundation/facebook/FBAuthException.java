package com.coral.foundation.facebook;

import oauth.signpost.exception.OAuthException;

public class FBAuthException extends OAuthException {
	private static final long serialVersionUID = 1L;

	public FBAuthException(String message, Throwable cause) {
		super(message, cause);
		System.out.println("message is " + message);
	}

}
