/**
 * 
 */
package com.coral.vaadin.widget.fields;

import com.vaadin.ui.TextArea;

/**
 * @author Coral.Ma
 *
 */
public class StringAreaFieldWidget extends FieldWidget {

	public StringAreaFieldWidget(String label) {
		super(label);
	}
	
	public void initField() {
		field = new TextArea(label);
		((TextArea)field).setNullRepresentation("");
		((TextArea)field).setNullSettingAllowed(true);
		setRows(1);
		field.addListener(this);
	}

	public void setRows(int rowIndex) {
		((TextArea)field).setRows(rowIndex);
	}
}
