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
public class RequiredValidator extends AbstractValidator implements Validator {

	public RequiredValidator(Field field) {
		super(field);
	}

	public boolean validate(String status) {
		if(StrUtils.isEmpty(field.getValue())) {
			if(COMMIT.equals(status)) {
				notification(getNotNullMsg());
			}
			field.setError(true,getNotNullMsg());
			return false;
		} else {
			field.setError(false, null);
		}
		return true;
	}
	
	public String getNotNullMsg() {
		return field.getLabel() + " can not be null.";
	}
}
