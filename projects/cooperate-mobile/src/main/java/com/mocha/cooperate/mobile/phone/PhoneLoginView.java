/**
 * 
 */
package com.mocha.cooperate.mobile.phone;

import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class PhoneLoginView extends VerticalLayout implements MobileView {

	private Button loginButton = new Button("Login");
	private TextField username = new TextField("Username: ");
	private PasswordField password = new PasswordField("Password: ");
	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("phone-login");
		layout.setWidth("100%");
		
		VerticalLayout titleLayout = new VerticalLayout();
		titleLayout.setSpacing(true);
		Label title = new Label("Mocha Platform");
		title.addStyleName("mocha-title");
		titleLayout.addComponent(title);
		titleLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		Label additionalTitle = new Label("Smartphone Version");
		additionalTitle.setWidth("122px");
		additionalTitle.addStyleName("additional-title");
		titleLayout.addComponent(additionalTitle);
		titleLayout.setComponentAlignment(additionalTitle, Alignment.MIDDLE_CENTER);
		layout.addComponent(titleLayout);
		layout.setComponentAlignment(titleLayout, Alignment.MIDDLE_CENTER);

		// username & password input area.
		VerticalComponentGroup componentGroup = new VerticalComponentGroup();
		
		username.setWidth("100%");
		componentGroup.addComponent(username);
		
		password.setWidth("100%");
		componentGroup.addComponent(password);
		layout.addComponent(componentGroup);

		// login button area
		CssLayout btnLayout = new CssLayout();
		btnLayout.setMargin(true);
		btnLayout.setWidth("100%");
		
		loginButton.setWidth("100%");
		btnLayout.addComponent(loginButton);
		layout.addComponent(btnLayout);
		addComponent(layout);
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

	/**
	 * @return the username
	 */
	public TextField getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(TextField username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public PasswordField getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(PasswordField password) {
		this.password = password;
	}
}
