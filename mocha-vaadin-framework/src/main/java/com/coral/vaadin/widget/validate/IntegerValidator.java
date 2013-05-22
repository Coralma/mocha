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
public class IntegerValidator extends AbstractValidator implements Validator {
	
	NumberFormat numberFormat;
	
	public IntegerValidator(Field field) {
		super(field);
		numberFormat = new DecimalFormat("0");
	}

	public boolean validate(String status) {
		String input = (String) field.getValue();
		if(!StrUtils.isEmpty(input)) {
			if(!StrUtils.isNumber(input)) {
				if(COMMIT.equals(status)) {
					notification(getErrorMessage());
				}
				field.setError(true, getErrorMessage());
				return false;
			} else {
				BigDecimal decimal = new BigDecimal(input);
				field.setError(false, null);
				field.setValue(numberFormat.format(decimal));
				return true;
			}
		}
		return true;
	}

	public String getErrorMessage() {
		return field.getLabel() + " only support number data.";
	}
}
