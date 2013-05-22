/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.google.common.collect.Lists;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ViewPanel extends VerticalLayout implements IViewPanel {

	private static final long serialVersionUID = -8099705376602570362L;
	private String label;
	private List<ISectionPanel> sections = Lists.newArrayList();
	private IActionPanel actionPanel;

	public ViewPanel() {
		addStyleName("viewPanel");
		setSpacing(true);
	}

	public void attach() {
		// set view label
		if(label != null) {
			Label viewTitle = new Label(label);
			addComponent(viewTitle);
		}
		// add sections
		for(ISectionPanel section : sections) {
			addComponent(section);
		}
		// set action Panel
		if(actionPanel != null) {
			this.addComponent(actionPanel);
			this.setComponentAlignment(actionPanel, Alignment.TOP_CENTER);
		}
	}
	
	public boolean validate() {
		boolean valid = true;
		for(ISectionPanel section : sections) {
			boolean sectionValidate = section.validate();
			if(sectionValidate == false) {
				valid = false;
			}
		}
		return valid;
	}
	
	public void setReadOnly(boolean readonly) {
		for(ISectionPanel section : sections) {
			section.setReadOnly(readonly);
		}
	}
	
	public void addSection(ISectionPanel section) {
		sections.add(section);
	}
	
	public void setActionPanel(IActionPanel actionPanel) {
		this.actionPanel = actionPanel;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
}
