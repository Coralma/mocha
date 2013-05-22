/**
 * 
 */
package com.coral.vaadin.widget.fields;

import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.TextField;

/**
 * @author Coral
 *
 */
public class BigDecimalFieldWidget extends FieldWidget {

	public BigDecimalFieldWidget(String label) {
		super(label);
	}
	
	public void initField() {
		field = new TextField(label);
		((TextField)field).setNullRepresentation("");
		((TextField)field).setNullSettingAllowed(true);
		field.addValidator(new RegexpValidator("^(?=[^A-Za-z]+$).*[0-9].*$", "The input value must be a number."));
		field.addListener(this);
	}

}
