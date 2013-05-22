/**
 * 
 */
package com.coral.vaadin.widget.component;

import com.vaadin.event.ShortcutListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral
 *
 */
public class GlobleSearchWidget extends HorizontalLayout {

	private static final long serialVersionUID = -387128853454630525L;
	public String textFieldWidth = "350px";
	public TextField textField = new TextField();
	public Button searchButton = new Button();
	
	public GlobleSearchWidget() {
		this.setWidth("100%");
		this.addStyleName("globle-search-widget");
		build();
	}

	public void build() {
		textField.setWidth(textFieldWidth);
		this.addComponent(textField);
		
		searchButton.addStyleName(BaseTheme.BUTTON_LINK);
		searchButton.setIcon(new ThemeResource("icons/search_btn_icon.png"));
		this.addComponent(searchButton);
	}

	public void setTextFieldWidth(String width) {
		super.setWidth(width);
	}
	
	public void addShortcutListener(ShortcutListener shortcutListener){
		textField.addShortcutListener(shortcutListener);
	}

	public void setInputPrompt(String inputPrompt) {
		textField.setInputPrompt(inputPrompt);
	}
}
