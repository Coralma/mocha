package com.coral.foundation.security;


import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleRole;

public interface SimpleUser {
	
	boolean validateTokenBySecurityManager(AuthenticationToken token,SecurityManager securityManger);
	
	boolean validateTokenBySessionId(AuthenticationToken token,Session sessionId);
	
	Permission getPermission(AuthenticationToken token);
	
	SimpleRole getSimpleRole(AuthenticationToken token);
	
	boolean hasPermission(Permission p);
	
	boolean isAuthenciate();
	
}
