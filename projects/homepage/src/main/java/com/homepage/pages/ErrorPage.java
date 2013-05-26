package com.homepage.pages;

import org.apache.wicket.markup.html.basic.Label;

import com.homepage.pages.BasePage;

public class ErrorPage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void ErrorPage() {
		build();
	}

	@Override
	public void build() {
		Label errorLabel = new Label("errorLabel");
		add(errorLabel);
	}

}
