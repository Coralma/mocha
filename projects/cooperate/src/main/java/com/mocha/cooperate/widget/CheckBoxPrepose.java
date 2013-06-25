/**
 * 
 */
package com.mocha.cooperate.widget;

import com.mocha.cooperate.InnerStyle;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * @author Coral
 *
 */
public class CheckBoxPrepose extends HorizontalLayout {

	private Label label;
	private CheckBox checkBox;
	private boolean value;
	
	public CheckBoxPrepose(String caption) {
		label = new Label("<span style=\""+ InnerStyle.checkBoxPrepose_style +"\">" +caption + "</span>", Label.CONTENT_XHTML);
		checkBox = new CheckBox();
		this.addComponent(label);
		this.addComponent(checkBox);
	}
	
	public void setValue(boolean value) {
		this.value = value;
		checkBox.setValue(value);
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	public void checklistener(ClickListener listener) {
		checkBox.addListener(listener);
	}
}
