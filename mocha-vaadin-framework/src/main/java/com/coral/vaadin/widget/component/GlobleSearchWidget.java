/**
 * 
 */
package com.coral.vaadin.widget.component;

import com.coral.foundation.utils.StrUtils;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
	protected GlobleSearchListener listener;
	
	public GlobleSearchWidget() {
		this.setWidth("100%");
		this.setHeight("30px");
		this.addStyleName("globle-search-widget");
		build();
		bind();
	}

	public void build() {
		textField.setWidth(textFieldWidth);
		this.addComponent(textField);
		
		searchButton.setClickShortcut(KeyCode.ENTER);
		searchButton.addStyleName(BaseTheme.BUTTON_LINK);
		searchButton.setIcon(new ThemeResource("icons/search_btn_icon.png"));
		this.addComponent(searchButton);
	}
	
	public void bind() {
		searchButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String value  = (String)textField.getValue();
				listener.search(value);
			}
		});
	}
	
	public void focus() {
		textField.focus();
	}

	public void setTextFieldWidth(String width) {
		textField.setWidth(width);
	}
	
	public void addShortcutListener(ShortcutListener shortcutListener){
		textField.addShortcutListener(shortcutListener);
	}

	public void setInputPrompt(String inputPrompt) {
		textField.setInputPrompt(inputPrompt);
	}
	
	public void addSearchListener() {
		
	}
	
	public interface GlobleSearchListener {
		public void search(String condition);
	}

	/**
	 * @return the listener
	 */
	public GlobleSearchListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(GlobleSearchListener listener) {
		this.listener = listener;
	}
}
