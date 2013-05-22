/**
 * 
 */
package com.coral.vaadin.widget.validate;


import org.apache.poi.util.IntegerField;

import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.field.BigDecimalField;

/**
 * @author Coral
 *
 */
public class ValidatorFactory {

	public static Validator getFieldValidator(Field field, String...params) {
		if(field instanceof BigDecimalField) {
			return new DecimalValidator(field, params[0]);
		} else if(field instanceof IntegerField) {
			return new IntegerValidator(field);
//		} else if(field instanceof ReferField) {
//			return new ReferenceValidation(field, params[0], params[1]);
		}
		return null;
	}
	
	public static Validator getRequiredValidator(Field field) {
		return new RequiredValidator(field);
	}
}
