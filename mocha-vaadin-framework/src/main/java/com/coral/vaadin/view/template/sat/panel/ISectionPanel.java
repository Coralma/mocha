package com.coral.vaadin.view.template.sat.panel;

import java.util.List;

import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.vaadin.ui.ComponentContainer;

public interface ISectionPanel extends ComponentContainer {

	public String getLabel();

	public void setLabel(String label);
	
	public void addField(Field field);
	
	public List<Result> validate();
	
	public void setSpacing(boolean spacing);
}
