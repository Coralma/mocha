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
public class AppNavigation {

	public List<AppMenu> appMenus = Lists.newArrayList();
	public List<AppMenuGroup> appMenuGroups = Lists.newArrayList();
	
	public void addAppMenu(AppMenu appMenu) {
		appMenus.add(appMenu);
	}
	
	public void addAppMenuGroup(AppMenuGroup appMenuGroup) {
		appMenuGroups.add(appMenuGroup);
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
	/**
	 * @return the appMenuGroups
	 */
	public List<AppMenuGroup> getAppMenuGroups() {
		return appMenuGroups;
	}
	/**
	 * @param appMenuGroups the appMenuGroups to set
	 */
	public void setAppMenuGroups(List<AppMenuGroup> appMenuGroups) {
		this.appMenuGroups = appMenuGroups;
	}
}
