/**
 * 
 */
package com.coral.vaadin.widget.component;

import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class SearchTextField extends HorizontalLayout implements BlurListener, FocusListener {

	private static final long serialVersionUID = -387128853454630525L;
	public String textFieldWidth = "250px";
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
		addComponent(searchIcon);
		textField.setWidth(textFieldWidth);
		textField.addListener((BlurListener)this);
		textField.addListener((FocusListener)this);
		addComponent(textField);
	}

	public void setTextFieldWidth(String width) {
		super.setWidth(width);
	}
	
	public void addShortcutListener(ShortcutListener shortcutListener){
		textField.addShortcutListener(shortcutListener);
	}

	@Override
	public void focus(FocusEvent event) {
		searchIcon.setStyleName("search-icon-focus");
		
	}

	@Override
	public void blur(BlurEvent event) {
		searchIcon.setStyleName("search-icon");		
	}
}
