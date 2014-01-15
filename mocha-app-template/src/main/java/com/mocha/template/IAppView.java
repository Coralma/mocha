package com.mocha.template;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.widget.Result;
import com.vaadin.ui.ComponentContainer;

public interface IAppView extends ComponentContainer {

	public String getLabel();
	
	public void setLabel(String label);
	
	public void addSection(IAppSection section);
	
	public void setActionSection(IAppActionSection actionSection);
	
	public List<Result> validate();
}
