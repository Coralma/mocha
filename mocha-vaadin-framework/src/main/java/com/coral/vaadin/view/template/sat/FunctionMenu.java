/**
 * 
 */
package com.coral.vaadin.view.template.sat;


/**
 * @author Coral
 *
 */
public class FunctionMenu {

	String name;
	String label;
	String viewName;
	String customizeClass;
	String wholeAction;
	boolean separator = false;
	
	public static FunctionMenu create() {
		FunctionMenu fm = new FunctionMenu();
		return fm;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public FunctionMenu setName(String name) {
		this.name = name;
		return this;
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
	public FunctionMenu setLabel(String label) {
		this.label = label;
		return this;
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
	public FunctionMenu setViewName(String viewName) {
		this.viewName = viewName;
		return this;
	}
	/**
	 * @return the customizeClass
	 */
	public String getCustomizeClass() {
		return customizeClass;
	}
	/**
	 * @param customizeClass the customizeClass to set
	 */
	public FunctionMenu setCustomizeClass(String customizeClass) {
		this.customizeClass = customizeClass;
		return this;
	}
	/**
	 * @return the wholeAction
	 */
	public String getWholeAction() {
		return wholeAction;
	}
	/**
	 * @param wholeAction the wholeAction to set
	 */
	public FunctionMenu setWholeAction(String wholeAction) {
		this.wholeAction = wholeAction;
		return this;
	}
	/**
	 * @return the separator
	 */
	public boolean isSeparator() {
		return separator;
	}
	/**
	 * @param separator the separator to set
	 */
	public FunctionMenu setSeparator(boolean separator) {
		this.separator = separator;
		return this;
	}
}
