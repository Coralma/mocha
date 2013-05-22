/**
 * 
 */
package com.coral.vaadin.widget.field;

import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Field;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.TextField;


/**
 * @author Coral
 *
 */
public class StringField extends AbstractField implements Field {

	private static final long serialVersionUID = -8142790346061175420L;
//	private TextField stringField = new TextField();
	
	public StringField(String label) {
		super(label);
		field = new TextField();
		((TextField)field).setImmediate(true);
		field.setWidth(fieldWidth);
		addListener(this);
		addComponent(field);
//		field.addStyleName("c-field-error");
	}

	public Object getValue() {
		Object value = field.getValue();
		if(StrUtils.isEmpty(value)) {
			return null;
		} else {
			return value.toString();
		}
	}

	public void setValue(Object t) {
		field.setValue(t);		
	}

//	public void addListener(ValueChangeListener listener) {
//		field.addListener(listener);
//	}

	@Override
	public Field getField() {
		return this;
	}
	
	public void setError(boolean isError, String errorMsg) {
		if(isError) {
			((TextField)field).setComponentError(new UserError(errorMsg));
		} else {
			((TextField)field).setComponentError(null);
		}
	}
}
