/**
 * 
 */
package com.coral.foundation.core.impl;

import java.util.Map;

import com.coral.foundation.security.model.BasicUser;
import com.google.common.collect.Maps;
import com.google.common.eventbus.EventBus;

/**
 * @author Coral.Ma
 *
 */
public class MochaEventBus {

	private EventBus eventBus = new EventBus();
	private BasicUser user;
	private Map<Object, Object> context = Maps.newHashMap();
	
	public void register(Object object) {
		eventBus.register(object);
	}
	
	public void unregister(Object object) {
		eventBus.unregister(object);
	}
	
	public void post(Object event) {
		eventBus.post(event);
	}

	/**
	 * @return the user
	 */
	public BasicUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(BasicUser user) {
		this.user = user;
	}

	/**
	 * @return the context
	 */
	public Map<Object, Object> getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Map<Object, Object> context) {
		this.context = context;
	}
	
	public void resetContext() {
		context = Maps.newHashMap();
	}
}
