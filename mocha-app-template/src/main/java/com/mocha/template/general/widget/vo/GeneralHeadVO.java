package com.mocha.template.general.widget.vo;

import java.util.List;

import com.google.common.collect.Lists;

public class GeneralHeadVO {

	private String appName;
	private List<GeneralMenuVO> menus = Lists.newArrayList();

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return the menus
	 */
	public List<GeneralMenuVO> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(List<GeneralMenuVO> menus) {
		this.menus = menus;
	}
	
	public void addMenu(GeneralMenuVO menu) {
		this.menus.add(menu);
	}
}
