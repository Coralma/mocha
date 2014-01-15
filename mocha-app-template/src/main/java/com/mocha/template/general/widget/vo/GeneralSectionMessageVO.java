package com.mocha.template.general.widget.vo;

public class GeneralSectionMessageVO {

	private String name;
	private String label;
	private String action;
	
	public GeneralSectionMessageVO(String data) {
		this.name = data;
		this.label = data;
		this.action = data;
	}
	
	public GeneralSectionMessageVO(String name, String label, String action) {
		this.name = name;
		this.label = label;
		this.action = action;
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
	public void setName(String name) {
		this.name = name;
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
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
}
