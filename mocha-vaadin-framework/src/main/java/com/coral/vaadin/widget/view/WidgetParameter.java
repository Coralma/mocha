/**
 * 
 */
package com.coral.vaadin.widget.view;

/**
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class WidgetParameter {

	String label;
	String data;
	String type;
	Class viewClass;
	Class tableClass;
	String style;
	boolean isRequired;
	String fieldWidth;
	
	public void setTableWidgetParams(String label,String data,Class viewClass,Class tableClass,String style) {
		this.label = label;
		this.data = data;
		this.viewClass = viewClass;
		this.tableClass =tableClass;
		this.style = style;
	}
	
	public void setWidgetParams(String label, String data, String type, String style, boolean isRequired, String fieldWidth) {
		this.label = label;
		this.data = data;
		this.type = type;
		this.style = style;
		this.isRequired = isRequired;
		this.fieldWidth = fieldWidth;
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
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * @return the viewClass
	 */
	public Class getViewClass() {
		return viewClass;
	}
	/**
	 * @param viewClass the viewClass to set
	 */
	public void setViewClass(Class viewClass) {
		this.viewClass = viewClass;
	}
	/**
	 * @return the tableClass
	 */
	public Class getTableClass() {
		return tableClass;
	}
	/**
	 * @param tableClass the tableClass to set
	 */
	public void setTableClass(Class tableClass) {
		this.tableClass = tableClass;
	}
	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the isRequired
	 */
	public boolean isRequired() {
		return isRequired;
	}

	/**
	 * @param isRequired the isRequired to set
	 */
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * @return the fieldWidth
	 */
	public String getFieldWidth() {
		return fieldWidth;
	}

	/**
	 * @param fieldWidth the fieldWidth to set
	 */
	public void setFieldWidth(String fieldWidth) {
		this.fieldWidth = fieldWidth;
	}
}
