/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class DefaultSearchCardActionPanel extends VerticalLayout implements IActionPanel {

	public DefaultSearchCardActionPanel() {
		this.setSpacing(true);
	}
	
	public void addButton(Button button) {
		this.addComponent(button);
	}
}
