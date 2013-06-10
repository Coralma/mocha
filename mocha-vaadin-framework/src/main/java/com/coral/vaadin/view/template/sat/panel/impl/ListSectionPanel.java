/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.WidgetFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ListSectionPanel extends VerticalLayout implements ISectionPanel {

	public String label;
	public Button newButton = WidgetFactory.createButton("New");
	
	public ListSectionPanel(String label) {
		this.label = label;
		this.setWidth("100%");
		this.setSpacing(true);
		this.addStyleName("list-section-panel");
	}
	
	public void attach() {
		initTitle();
	}
	
	private void initTitle() {
		Label sectionTitle = new Label(label);
		sectionTitle.setWidth("100%");
		sectionTitle.addStyleName("section-label");
		this.addComponent(sectionTitle);
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		Label noRecordLabel = WidgetFactory.createLabel("No records found");
		noRecordLabel.addStyleName("no-section-content");
		layout.addComponent(noRecordLabel);
		layout.addComponent(newButton);
		this.addComponent(layout);
	}
	
	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void addField(Field field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Result> validate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the newButton
	 */
	public Button getNewButton() {
		return newButton;
	}

	/**
	 * @param newButton the newButton to set
	 */
	public void setNewButton(Button newButton) {
		this.newButton = newButton;
	}

}
