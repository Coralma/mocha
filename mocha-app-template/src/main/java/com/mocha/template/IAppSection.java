package com.mocha.template;

import java.util.List;

import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.vaadin.ui.ComponentContainer;

public interface IAppSection extends ComponentContainer {

	public String getLabel();

	public void setLabel(String label);
	
	public void addField(Field field);
	
	public List<Result> validate();
	
	public void setSpacing(boolean spacing);
}
