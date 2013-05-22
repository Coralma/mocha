package com.coral.vaadin.view.template.sat.panel;

import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.field.FieldStatus;
import com.vaadin.ui.ComponentContainer;

public interface ISectionPanel extends ComponentContainer {

	public String getLabel();

	public void setLabel(String label);
	
	public void addField(Field field);
	
	public boolean validate();
	
	public void setSpacing(boolean spacing);
}
