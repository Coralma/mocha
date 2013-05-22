/**
 * 
 */
package com.coral.vaadin.widget.fields.search;

import com.coral.vaadin.widget.fields.FieldWidget;
import com.vaadin.ui.Label;

/**
 * @author Coral
 *
 */
public class DisplayFieldWidget extends FieldWidget {

	Label displayField;
	public DisplayFieldWidget(String label) {
		super(label);
		this.addStyleName("display-field-widget");
	}

	public void initField() {
		label = label + ":";
		displayField = new Label();
		displayField.setCaption(label);
	}

	public void attach() {
		displayField.setWidth(fieldWidth);
		displayField.setPropertyDataSource(property);
		this.addComponent(displayField);
	}
}
