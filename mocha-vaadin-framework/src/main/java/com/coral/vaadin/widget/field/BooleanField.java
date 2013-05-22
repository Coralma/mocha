/**
 * 
 */
package com.coral.vaadin.widget.field;

import com.coral.vaadin.widget.Field;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.CheckBox;

/**
 * @author Administrator
 *
 */
public class BooleanField extends AbstractField implements Field {

	String FIELD = "Field";
	String DEFAULT = "Default";
//	CheckBox checkbox = new CheckBox();
	
	public BooleanField(String label) {
		super(label);
		field = new CheckBox();
		addComponent(field);
	}
	
	public BooleanField(String label, Boolean isChecked) {
		field = new CheckBox();
		field.setCaption(label);
		field.setValue(isChecked);
		addComponent(field);
	}
	
	public void setError(boolean isError, String errorMsg) {
		if(isError) {
			((CheckBox)field).setComponentError(new UserError(errorMsg));
		} else {
			((CheckBox)field).setComponentError(null);
		}
	}
	
	@Override
	public Object getValue() {
		return field.getValue();
	}

	@Override
	public void setValue(Object value) {
		field.setValue(value);
	}

	@Override
	public void addListener(ValueChangeListener listener) {
		
	}

	@Override
	public Field getField() {
		return this;
	}
}
