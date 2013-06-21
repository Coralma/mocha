package com.coral.vaadin.view.template.sat.panel.impl;

import com.coral.vaadin.view.template.sat.panel.ISectionPanel;

public class EditableSection extends AbstractViewLayout implements ISectionPanel {

	public String label;
	
	public EditableSection() {
		super.setColumn(3);
	}
	
	public void attach() {
		layout();
	}
	
	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

}
