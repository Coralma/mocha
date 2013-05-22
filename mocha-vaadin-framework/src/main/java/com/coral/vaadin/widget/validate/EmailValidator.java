/**
 * 
 */
package com.coral.vaadin.widget.validate;

import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Field;

/**
 * @author Coral
 *
 */
public class EmailValidator extends AbstractValidator implements Validator {

	public EmailValidator(Field field) {
		super(field);
	}

	public boolean validate(String status) {
		String email = (String) field.getValue();
		if(!StrUtils.isValidEmail(email)) {
			field.setError(true,getErrorMessage());
			return false;
		} else {
			field.setError(false,getErrorMessage());
			return true;
		}
	}
	
	public String getErrorMessage() {
		return field.getLabel() + " is not a correct Email.";
	}

}
