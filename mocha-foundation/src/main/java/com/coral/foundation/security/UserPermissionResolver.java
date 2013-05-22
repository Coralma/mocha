package com.coral.foundation.security;

import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.RolePermissionResolver;

public class UserPermissionResolver implements PermissionResolver,RolePermissionResolver {
	
	
	
	@Override
	public Permission resolvePermission(String roleString) {
		
		return new UserPermission();
	}

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		
		return null ;
	}

}
