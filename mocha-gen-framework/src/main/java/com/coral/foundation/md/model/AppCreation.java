/**
 * 
 */
package com.coral.foundation.md.model;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Coral
 *
 */
public class AppCreation {

	public List<AppMenu> appMenus = Lists.newArrayList();

	public void addAppMenu(AppMenu appMenu) {
		appMenus.add(appMenu);
	}
	/**
	 * @return the appMenus
	 */
	public List<AppMenu> getAppMenus() {
		return appMenus;
	}

	/**
	 * @param appMenus the appMenus to set
	 */
	public void setAppMenus(List<AppMenu> appMenus) {
		this.appMenus = appMenus;
	}
	
}
