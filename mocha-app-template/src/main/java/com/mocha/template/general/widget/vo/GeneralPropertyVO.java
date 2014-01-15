package com.mocha.template.general.widget.vo;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class GeneralPropertyVO {

	private String caption;
	private int type = 1; // 1 means using Map<key,value>
	
	// type 1
	private LinkedHashMap<String, String> propertyMap = Maps.newLinkedHashMap();
	
	// type 2
	private List<String> propertyLabels = Lists.newArrayList(); // should be 2
	private List<String> propertyVariables = Lists.newArrayList(); // should be 2
	private Object data;
	
	public GeneralPropertyVO(String caption, LinkedHashMap<String, String> propertyMap) {
		this.caption = caption;
		this.propertyMap = propertyMap;
		this.type = 1;
	}
	
	public GeneralPropertyVO(String caption, List<String> propertyLabels, List<String> propertyVariables, Object data) {
		this.caption = caption;
		this.propertyVariables = propertyVariables;
		this.propertyLabels = propertyLabels;
		this.data = data;
		this.type = 2;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the propertyMap
	 */
	public LinkedHashMap<String, String> getPropertyMap() {
		return propertyMap;
	}

	/**
	 * @param propertyMap the propertyMap to set
	 */
	public void setPropertyMap(LinkedHashMap<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}

	/**
	 * @return the propertyVariables
	 */
	public List<String> getPropertyVariables() {
		return propertyVariables;
	}

	/**
	 * @param propertyVariables the propertyVariables to set
	 */
	public void setPropertyVariables(List<String> propertyVariables) {
		this.propertyVariables = propertyVariables;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the propertyLabels
	 */
	public List<String> getPropertyLabels() {
		return propertyLabels;
	}

	/**
	 * @param propertyLabels the propertyLabels to set
	 */
	public void setPropertyLabels(List<String> propertyLabels) {
		this.propertyLabels = propertyLabels;
	}
	
}
