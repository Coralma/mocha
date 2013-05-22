/**
 * 
 */
package com.coral.vaadin.widget.field;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.validate.DecimalValidator;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.TextField;

/**
 * @author Coral
 *
 */
public class BigDecimalField extends AbstractField implements Field {

	private static final long serialVersionUID = 61397659613283759L;
//	private TextField decimalField = new TextField();
	private NumberFormat numberFormat;
//	private BigDecimal value;
	private String format;

	public BigDecimalField(String label) {
		this(label, null);
	}
	
	public BigDecimalField(String label, String custFormat) {
		super(label);
		field = new TextField();
		this.format = custFormat;
		if(format == null) {
			format = "0.00";
		}
		numberFormat = new DecimalFormat(format);
		field.setWidth(fieldWidth);
		((TextField)field).setImmediate(true);
		validators.add(new DecimalValidator(getField(),format));
		
		addListener(this);
		addComponent(field);
	}

	public Object getValue() {
//		return value;
		String value = (String) field.getValue();
		if(!StrUtils.isEmpty(value)) {
			BigDecimal returnValue = new BigDecimal(value);
			return returnValue;
		} else {
			return null;
		}
	}
	
	public void setError(boolean isError, String errorMsg) {
		if(isError) {
			((TextField)field).setComponentError(new UserError(errorMsg));
		} else {
			((TextField)field).setComponentError(null);
		}
	}

	public void setValue(Object t) {
		if(t != null) {
//			value = t;
			field.setValue(numberFormat.format(t));
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
