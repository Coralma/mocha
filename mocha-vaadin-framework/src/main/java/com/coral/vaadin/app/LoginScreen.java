package com.coral.vaadin.app;

import javax.servlet.http.HttpServletResponse;

import com.coral.foundation.security.CommonSecurityManager;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.helper.NotificationHelper;
import com.github.wolfie.sessionguard.SessionGuard;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

public class LoginScreen extends VerticalLayout {

	protected HttpServletResponse response;
	protected BasicUserDao userDao = SpringContextUtils.getBean(BasicUserDao.class);
	protected String fieldWidth = "240px";
	protected Message message;
	protected VerticalLayout loginPanel = new VerticalLayout();
	private String cookieUsername;
	
	public LoginScreen(String cookieUsername) {
		this.cookieUsername = cookieUsername;
		setSizeFull();
		addStyleName("mocha-login");
		loginPanel.setSizeFull();
		loginPanel.addStyleName(getRandomBackground());
		addComponent(loginPanel);
	}
	
	public String getRandomBackground() {
		Double d = Math.ceil(Math.random() * 11);
		Integer random = d.intValue();
		String bgCss = "login-background-" + random;
		return bgCss;
	}

	public void attach() {
		message = new Message(getApplication().getLocale());
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("500px");
		loginPanel.addComponent(layout);
		loginPanel.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
		Label loginLogo = new Label();
		loginLogo.setCaption(message.getString("login.company"));
		loginLogo.setIcon(new ThemeResource("images/login-logo.png"));
		loginLogo.addStyleName("login-logo");
		loginLogo.setWidth("500px");
		layout.addComponent(loginLogo);
//		this.setComponentAlignment(loginLogo, Alignment.MIDDLE_CENTER);
		
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		formLayout.setMargin(true);
		Panel loginPanel = new Panel();
		loginPanel.setContent(formLayout);
		loginPanel.setCaption(message.getString("login.Welcome"));
		loginPanel.setWidth("500px");
		loginPanel.setHeight("260px");
		
		layout.addComponent(loginPanel);
//		this.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		
		final TextField userName = new TextField();
		userName.setCaption(message.getString("login.Username"));
		if(cookieUsername != null) {
			userName.setValue(cookieUsername);
		}
		userName.setWidth(fieldWidth);
//		userName.setRequiredError("Please input username");
		loginPanel.addComponent(userName);
		
		final PasswordField password = new PasswordField();
		password.setCaption(message.getString("login.Password"));
		password.setWidth(fieldWidth);
//		password.setValue("root");
//		password.setRequiredError("Please input password");
		loginPanel.addComponent(password);

		final CheckBox rememberMe = new CheckBox("Save to this computer?");
		rememberMe.setValue(false);
		NativeButton loginButton = new NativeButton();
		loginButton.setClickShortcut(KeyCode.ENTER);
		loginButton.setCaption(message.getString("login.Login"));
		loginButton.addStyleName("login-button");
		loginButton.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				String inputUserName = userName.getValue().toString();
				String inputPassword = password.getValue().toString();
				BasicUser user = CommonSecurityManager.build().login(inputUserName, inputPassword);
				
				if(user==null){
					errorHandling();
					return;
				}
				
				// check box default value
				if (rememberMe.getValue().equals(true)) {
					user.setRememberMe(true);
				}

				AbstractMainPage homepage = SpringContextUtils.getBean(
						"homepage", AbstractMainPage.class);
				homepage.setResponse(response);
				getApplication().setUser(user);
				getWindow().setContent(homepage);
			}

			public void errorHandling() {
				getWindow().showNotification(NotificationHelper.getErrorNotification(message.getString("login.errorMessage")));
			}
		});
		loginPanel.addComponent(loginButton);
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
