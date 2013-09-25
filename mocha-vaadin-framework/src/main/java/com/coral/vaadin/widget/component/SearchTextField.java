/**
 * 
 */
package com.coral.vaadin.widget.component;

import com.vaadin.event.ShortcutListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class SearchTextField extends HorizontalLayout {

	private static final long serialVersionUID = -387128853454630525L;
	public String textFieldWidth = "100%";
	public Label searchIcon = new Label();
	public TextField textField = new TextField();
	
	public SearchTextField() {
		this.setWidth("100%");
		this.addStyleName("search-text-field");
	}

	public void attach() {
		searchIcon.setIcon(new ThemeResource("icons/search.png"));
		searchIcon.setWidth("20px");
		searchIcon.setStyleName("search-icon");
		this.addComponent(searchIcon);
		this.setExpandRatio(searchIcon, 1.0f);
		textField.setWidth(textFieldWidth);
		this.addComponent(textField);
		this.setExpandRatio(textField, 100.0f);
	}

	public void setTextFieldWidth(String width) {
		super.setWidth(width);
	}
	
	public void addShortcutListener(ShortcutListener shortcutListener){
		textField.addShortcutListener(shortcutListener);
	}
	
	public void setValue(Object value) {
		textField.setValue(value);
	}
}
