/**
 * 
 */
package com.mocha.cooperate.widget;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

/**
 * @author Administrator
 *
 */
public class CheckBoxTextField extends HorizontalLayout {

	private CheckBox checkBox = new CheckBox();
	private TextField textField = new TextField();

	public CheckBoxTextField() {
		textField.setWidth("500px");
		addComponent(checkBox);
		addComponent(textField);
	}
}
