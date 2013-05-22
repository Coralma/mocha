package com.coral.foundation.security;

public interface UserAuthenciation {
	
	boolean login(String userName,String pw);
	
	boolean logout();

}
