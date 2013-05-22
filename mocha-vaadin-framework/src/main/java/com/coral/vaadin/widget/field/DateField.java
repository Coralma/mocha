/**
 * 
 */
package com.coral.vaadin.widget.field;

import java.util.Date;

import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Field;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

/**
 * @author Coral
 *
 */
public class DateField extends AbstractField implements Field {

	private static final long serialVersionUID = -3815560342711840293L;
//	PopupDateField dateField = new PopupDateField();
	
	public DateField(String label) {
		super(label);
		field = new PopupDateField();
		((PopupDateField)field).setDateFormat("yyyy-MM-dd");
		((PopupDateField)field).setImmediate(true);
 		((PopupDateField)field).setResolution(PopupDateField.RESOLUTION_DAY);
		field.setWidth(fieldWidth);
		addListener(this);
		addComponent(field);
	}

	public Object getValue() {
		Object value = field.getValue();
		if(StrUtils.isEmpty(value)) {
			return null;
		} else {
			return (Date)value;
		}
	}

	public void setValue(Object t) {
		field.setValue(t);
	}

//	public void addListener(ValueChangeListener listener) {
//		field.addListener(listener);
//	}
	
	public void setError(boolean isError, String errorMsg) {
		if(isError) {
			((PopupDateField)field).setComponentError(new UserError(errorMsg));
		} else {
			((PopupDateField)field).setComponentError(null);
		}
	}
	
	@Override
	public Field getField() {
		return this;
	}
}
