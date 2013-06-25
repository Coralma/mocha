/**
 * 
 */
package com.coral.vaadin.widget.fields;

import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.ui.TextField;

/**
 * @author Coral.Ma
 *
 */
public class StringFieldWidget extends FieldWidget {

	public StringFieldWidget(String label) {
		super(label);
	}
	
	public void initField() {
		field = new TextField(label);
		((TextField)field).setNullRepresentation("");
		((TextField)field).setNullSettingAllowed(true);
		field.addListener(this);
	}
	
	public void addBlurListener(BlurListener blurListener) {
		((TextField)field).addListener(blurListener);
	}

}
