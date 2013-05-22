/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 * @author Administrator
 *
 */
public class FunctionButtonLayout extends HorizontalLayout {
	
	public List<Button> buttonList = new ArrayList<Button>();

	public FunctionButtonLayout() {
		this.setSpacing(true);
	}
	
	public void addButton(Button button) {
		addComponent(button);
		buttonList.add(button);
	}
	
	public List<Button> getButtonList() {
		return buttonList;
	}
}
