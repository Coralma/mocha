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
public class NumberRangeValidator extends AbstractValidator implements Validator {
	
	private double from;
	private double to;
	
	public NumberRangeValidator(Field field, String range) {
		super(field);
		from = Double.parseDouble(range.substring(0,range.lastIndexOf("..")));
		to= Double.parseDouble(range.substring(range.lastIndexOf("..") + 2));
	}

	public boolean validate(String status) {
//		String input = field.getText();
//		if(!StrUtils.isEmpty(input)) {
//			if(!StrUtils.isNumber(input)) {
//				MessageCenter.getMessageCenter().alertMessage("\""+field.getLabel()+"\"数据只允许输入数字").open();
//				field.setError(true);
//				return false;
//			} else {
//				double number = Double.parseDouble(input);
//				if(number < from || number > to) {
//					MessageCenter.getMessageCenter().alertMessage("\""+field.getLabel()+"\"只允许输入"+from+"到"+to+"范围内的数字").open();
//				} else {
//					field.setError(false);
//					return true;
//				}
//			}
//		}
		return true;
	}
}
