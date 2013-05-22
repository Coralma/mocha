/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;

/**
 * @author Coral.Ma
 *
 */
public interface IViewPanel extends ComponentContainer {

	public String getLabel();
	public void setLabel(String label);
	public void addSection(ISectionPanel section);
	public void setActionPanel(IActionPanel actionPanel);
	public boolean validate();
}
