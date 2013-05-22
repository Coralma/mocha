/**
 * 
 */
package com.coral.vaadin.widget.field;

import com.coral.vaadin.widget.Field;
import com.vaadin.data.Property.ValueChangeListener;

/**
 * @author Administrator
 *
 */
public class LabelField extends AbstractField implements Field {

	public LabelField(String label) {
		super(label);
		fieldLabel.setWidth(largeFieldWidth);
	}
	
	public void setError(boolean isError, String errorMsg) {
		
	}
	
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public Field getField() {
		return this;
	}
}
