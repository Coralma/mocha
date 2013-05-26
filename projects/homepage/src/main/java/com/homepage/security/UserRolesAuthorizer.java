package com.homepage.security;

import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

public class UserRolesAuthorizer implements IRoleCheckingStrategy {
	
	public UserRolesAuthorizer(){
		
	}

	@Override
	public boolean hasAnyRole(Roles roles) {
		SecuritySession session = (SecuritySession) SecuritySession.get();
		
		if (session.isSignedIn()) {
			return true;
		}
		return false;
	}

}
