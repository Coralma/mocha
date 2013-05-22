/**
 * 
 */
package com.coral.vaadin.widget.fields;

import com.vaadin.ui.PopupDateField;

/**
 * @author Coral.Ma
 *
 */
public class DateFieldWidget extends FieldWidget {

	public DateFieldWidget(String label) {
		super(label);
	}
	
	public void initField() {
		field = new PopupDateField(label);
		((PopupDateField)field).setDateFormat("yyyy-MM-dd");
		((PopupDateField)field).setImmediate(true);
 		((PopupDateField)field).setResolution(PopupDateField.RESOLUTION_DAY);
		field.addListener(this);
	}

}
