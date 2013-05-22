package com.coral.vaadin.widget.layout;

import com.coral.vaadin.widget.field.ActionButton;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class AbstractSectionLayout extends VerticalLayout{

	public VerticalLayout buttonLayout;
	public HorizontalLayout btnsLayout;
	public String fieldWidth;
	public Alignment alignment = Alignment.MIDDLE_RIGHT;
	
	public void addButton(ActionButton button) {
		if (buttonLayout == null) {
			buttonLayout = new VerticalLayout();
			buttonLayout.setMargin(true, true, false, false);
			buttonLayout.setMargin(false);
			buttonLayout.setWidth("100%");
			super.addComponent(buttonLayout);
			btnsLayout = new HorizontalLayout();
			btnsLayout.addStyleName("mocha-section-button");
			btnsLayout.setSpacing(true);
			buttonLayout.addComponent(btnsLayout);
			buttonLayout.setComponentAlignment(btnsLayout, alignment);
		}
		btnsLayout.addComponent(button);
	}
	
	public void setButtonAlignment(Alignment alignment) {
		this.alignment = alignment;
	}
	
	public void setFieldWidth(String fieldWidth) {
		this.fieldWidth = fieldWidth;
	}
}
