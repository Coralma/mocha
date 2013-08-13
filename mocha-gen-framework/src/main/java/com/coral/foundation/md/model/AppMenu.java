/**
 * 
 */
package com.coral.foundation.md.model;

/**
 * @author Coral.Ma
 *
 */
public class AppMenu {

	public String name;
	public String label;
	public String viewName;
	public String customizedClass;

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
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}
	/**
	 * @param viewName the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	/**
	 * @return the customizedClass
	 */
	public String getCustomizedClass() {
		return customizedClass;
	}
	/**
	 * @param customizedClass the customizedClass to set
	 */
	public void setCustomizedClass(String customizedClass) {
		this.customizedClass = customizedClass;
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
