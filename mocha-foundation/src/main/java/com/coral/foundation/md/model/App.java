package com.coral.foundation.md.model;


public class App {

	public String name;
	public String label;
	public AppNavigation appNavigation;
	public AppCreation appCreation;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the appNavigation
	 */
	public AppNavigation getAppNavigation() {
		return appNavigation;
	}
	/**
	 * @param appNavigation the appNavigation to set
	 */
	public void setAppNavigation(AppNavigation appNavigation) {
		this.appNavigation = appNavigation;
	}
	/**
	 * @return the appCreation
	 */
	public AppCreation getAppCreation() {
		return appCreation;
	}
	/**
	 * @param appCreation the appCreation to set
	 */
	public void setAppCreation(AppCreation appCreation) {
		this.appCreation = appCreation;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
}
