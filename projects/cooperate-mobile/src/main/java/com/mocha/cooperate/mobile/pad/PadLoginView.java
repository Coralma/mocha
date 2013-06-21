/**
 * 
 */
package com.mocha.cooperate.mobile.pad;

import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class PadLoginView extends VerticalLayout implements MobileView {

	private Button loginButton = new Button("Login");
	private Button spButton = new Button("SmartPhone Version");
	
	@Override
	public void attach() {
		super.attach();
		String panelWidth = "70%";
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("pad-login");
		layout.setWidth(panelWidth);
		
		HorizontalLayout titleLayout = new HorizontalLayout();
		titleLayout.setSpacing(true);
		Label title = new Label("Mocha Platform");
		title.addStyleName("mocha-title");
		titleLayout.addComponent(title);
		titleLayout.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
		Label additionalTitle = new Label("Pad Version");
		additionalTitle.addStyleName("additional-title");
		titleLayout.addComponent(additionalTitle);
		titleLayout.setComponentAlignment(additionalTitle, Alignment.MIDDLE_LEFT);
		layout.addComponent(titleLayout);

		// username & password input area.
		VerticalComponentGroup componentGroup = new VerticalComponentGroup();
		TextField username = new TextField("Username: ");
		username.setWidth("100%");
		componentGroup.addComponent(username);
		PasswordField password = new PasswordField("Password: ");
		password.setWidth("100%");
		componentGroup.addComponent(password);
		layout.addComponent(componentGroup);

		// login button area
		HorizontalLayout btnLayout = new HorizontalLayout();
		btnLayout.setMargin(false,true,false,false);
		btnLayout.setSpacing(true);
		
		String btnWidth = "220px";
		loginButton.setWidth(btnWidth);
		spButton.setWidth(btnWidth);
		btnLayout.addComponent(loginButton);
		btnLayout.addComponent(spButton);
		layout.addComponent(btnLayout);
		layout.setComponentAlignment(btnLayout, Alignment.MIDDLE_RIGHT);
		this.addComponent(layout);
		this.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
	}

	/**
	 * @return the loginButton
	 */
	public Button getLoginButton() {
		return loginButton;
	}

	/**
	 * @param loginButton the loginButton to set
	 */
	public void setLoginButton(Button loginButton) {
		this.loginButton = loginButton;
	}
}
