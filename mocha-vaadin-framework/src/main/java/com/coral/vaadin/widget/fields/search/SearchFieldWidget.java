/**
 * 
 */
package com.coral.vaadin.widget.fields.search;

import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.field.FieldStatus;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.FormLayout;

/**
 * @author Coral
 *
 */
public abstract class SearchFieldWidget extends FormLayout implements ValueChangeListener,Field {

	protected String label;
	protected String propertyName;
	protected com.vaadin.ui.Field field;
	protected String fieldWidth = "220px";
	protected FieldStatus fieldStatus;

	public SearchFieldWidget(String label) {
		this.label = label;
		this.setMargin(false);
		this.setWidth("100%");
		this.addStyleName("field-widget");
		initField();
	}
	
	public abstract void initField();

	public void attach() {
		field.setWidth(fieldWidth);
		((AbstractField)field).setImmediate(true);
		this.addComponent(field);
	}
	
	@Override
	public FieldStatus getFieldStatus() {
		return fieldStatus ;
	}

	@Override
	public void setFieldStatus(FieldStatus fieldStatus) {
		this.fieldStatus = fieldStatus;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRequired(boolean required) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFieldWidth(String width) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setError(boolean isError, String errorMsg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	
}
