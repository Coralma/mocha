/**
 * 
 */
package com.coral.vaadin.widget.validate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Field;

/**
 * @author Coral
 *
 */
public class DecimalValidator extends AbstractValidator implements Validator {

	NumberFormat numberFormat;
	
	public DecimalValidator(Field field, String format) {
		super(field);
		if(format == null) {
			format = "0.00";
		}
		numberFormat = new DecimalFormat(format);
	}

	public boolean validate(String status) {
		String input = null;
		try {
			Object value = field.getValue();
			if(value != null) {
				input = value.toString();
			}
		} catch (Exception e) {
			input = "abc";
		}
		if(!StrUtils.isEmpty(input)) {
			if(!StrUtils.isNumber(input)) {
				if(COMMIT.equals(status)) {
					notification(getErrorMessage());
				}
				field.setError(true,getErrorMessage());
				return false;
			} else {
				BigDecimal decimal = new BigDecimal(input);
				field.setError(false, null);
				String formatedNumber = numberFormat.format(decimal);
				BigDecimal returnValue = new BigDecimal(formatedNumber);
				field.setValue(returnValue);
				return true;
			}
		}
		return true;
	}
	
	public String getErrorMessage() {
		return field.getLabel() + " only support number data.";
	}
}
