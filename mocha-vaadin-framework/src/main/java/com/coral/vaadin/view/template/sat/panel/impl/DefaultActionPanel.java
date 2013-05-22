package com.coral.vaadin.view.template.sat.panel.impl;

import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class DefaultActionPanel extends HorizontalLayout implements IActionPanel {

	public DefaultActionPanel() {
		this.setSpacing(true);
	}
	
	public void addButton(Button button) {
		this.addComponent(button);
	}
}
