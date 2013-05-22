package com.coral.foundation.security;

import org.apache.shiro.authz.Permission;

public class UserPermission implements Permission {

	@Override
	public boolean implies(Permission permission) {
	
		return false;
	}
	
}
