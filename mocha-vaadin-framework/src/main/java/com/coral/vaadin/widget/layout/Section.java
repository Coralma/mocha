package com.coral.vaadin.widget.layout;

import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.ActionButton;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Layout;

public interface Section extends Layout {
	
	public void addField(Widget field);
	
	public void addButton(ActionButton button);
	
	public void setFieldWidth(String fieldWidth);
	
	public void setButtonAlignment(Alignment alignment);
}
