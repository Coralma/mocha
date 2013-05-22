/**
 * 
 */
package com.coral.vaadin.widget.field;

import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Field;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * @author Administrator
 *
 */
public class StringRichField extends AbstractField implements Field{

private static final long serialVersionUID = -8142790346061175420L;

	public StringRichField(String label) {
		super(label);
		field = new RichTextArea();
		field.setWidth(largeFieldWidth);
		((RichTextArea)field).setImmediate(true);
		addListener(this);
		addComponent(field);
	}

	public Object getValue() {
		Object value = field.getValue();
		if(StrUtils.isEmpty(value)) {
			return null;
		} else {
			return value;
		}
	}

	public void setValue(Object t) {
		field.setValue(t);		
	}
	
	public void setFieldHeight(String height) {
		((RichTextArea)field).setHeight(height);
	}
	
	public void setError(boolean isError, String errorMsg) {
		if(isError) {
			((TextField)field).setComponentError(new UserError(errorMsg));
		} else {
			((TextField)field).setComponentError(null);
		}
	}

	public void addListener(ValueChangeListener listener) {
		field.addListener(listener);
	}
	
	@Override
	public Field getField() {
		return this;
	}
}
