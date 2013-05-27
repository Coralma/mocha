/**
 * 
 */
package com.coral.vaadin.widget;

import com.coral.vaadin.widget.fields.FieldStatus;
import com.vaadin.data.Property.ValueChangeListener;

/**
 * @author Administrator
 *
 */
public interface Field extends Widget {

	public static String THREE_COLUMN_WIDTH = "140";
	public static String TWO_COLUMN_WIDTH = "250";

	public void addListener(ValueChangeListener listener);
	
	public void setRequired(boolean required);
	
	public void setFieldWidth(String width);
	
	public void setError(boolean isError, String errorMsg);
	
	public String getLabel();
	
	public FieldStatus getFieldStatus();

	public void setFieldStatus(FieldStatus fieldStatus);
	
}
