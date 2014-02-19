package com.coral.vaadin.app;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.security.CommonSecurityManager;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.helper.NotificationHelper;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout {

	protected Message message;
	protected String cookieUserName;
	protected HttpServletResponse response;
	private static Logger log = LoggerFactory.getLogger(LoginView.class);
	
	public LoginView(String cookieUserName) {
		this.cookieUserName = cookieUserName;
		this.setStyleName("m-mocha-login");
	}
	
	public void attach() {
		message = new Message(getApplication().getLocale());
		VerticalLayout headLayout = buildLayout();
		headLayout.addStyleName("m-login-head-layout");
		headLayout.setHeight("55px");
		
		HorizontalLayout headContentLayout = new HorizontalLayout();
		headContentLayout.setWidth("100%");
		LogoWidget logoWidget = new LogoWidget();
		headContentLayout.addComponent(logoWidget);
//		Link link = new Link("Mobile Version",new ExternalResource("http://106.186.123.7/insurance/mobile/login.html"));
//		link.setIcon(new ThemeResource("img/mobile.png"));
//		headContentLayout.addComponent(link);
//		headContentLayout.setComponentAlignment(link, Alignment.TOP_RIGHT);
		headLayout.addComponent(headContentLayout);
		this.addComponent(headLayout);
		
		VerticalLayout contentLayout = buildLayout();
		contentLayout.addStyleName("m-login-content-layout");
		contentLayout.setHeight("470px");
		this.addComponent(contentLayout);
		
		VerticalLayout loginPanel = bulidLoginPanel();
		contentLayout.addComponent(loginPanel);
		contentLayout.setComponentAlignment(loginPanel, Alignment.TOP_RIGHT);
		
		VerticalLayout messagePanel = bulidContentMessagePanel();
		contentLayout.addComponent(messagePanel);
		contentLayout.setComponentAlignment(messagePanel, Alignment.BOTTOM_LEFT);
		
		VerticalLayout bottomLayout = buildBottomLayout();
		this.addComponent(bottomLayout);
	}
	
	protected VerticalLayout bulidLoginPanel() {
		String panel_width = "470px";
		String panel_height = "95px";
		String field_width = "290px";
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("m-login-panel");
		layout.setWidth(panel_width);
		
		VerticalLayout labelLayout = new VerticalLayout();
		labelLayout.addStyleName("m-signin-label-layout");
		Label loginLogo = new Label(message.getString("login.Login"));
		loginLogo.addStyleName("m-signin-label");
		loginLogo.setWidth(panel_width);
		labelLayout.addComponent(loginLogo);
		layout.addComponent(labelLayout);

		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		formLayout.setWidth(panel_width);
		formLayout.setHeight(panel_height);
		final TextField userName = new TextField();
		userName.setCaption("Username");
		userName.setWidth(field_width);
		formLayout.addComponent(userName);
		final PasswordField password = new PasswordField();
		password.setCaption("Password");
		password.setWidth(field_width);
		formLayout.addComponent(password);
		if(cookieUserName != null) {
			userName.setValue(cookieUserName);
		}
		layout.addComponent(formLayout);
		
		HorizontalLayout btnLayout = new HorizontalLayout();
		btnLayout.addStyleName("m-login-button-layout");
		NativeButton loginButton = new NativeButton();
		loginButton.setClickShortcut(KeyCode.ENTER);
		loginButton.setCaption("Sign in");
		loginButton.addStyleName("m-login-button");
		loginButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					String inputUserName = userName.getValue().toString();
					String inputPassword = password.getValue().toString();
					CommonSecurityManager cm = new CommonSecurityManager();
					BasicUser user = cm.build().login(inputUserName, inputPassword);
					if (user == null) {
						errorHandling(inputUserName, inputPassword);
						return;
					}
					else {
						AbstractMainPage homepage = SpringContextUtils.getBean("homepage", AbstractMainPage.class);
						homepage.setResponse(response);
						getApplication().setUser(user);
						getWindow().setContent(homepage);
					}
				}
				catch (NullPointerException e) {
					log.error("Errors occus on user login page");
					log.error(e.getMessage());
				}
			}

			public void errorHandling(String inputUserName, String inputPassword) {
				log.error("Login Failed when using " + inputPassword + " and " + inputPassword);
				getWindow().showNotification(NotificationHelper.getErrorNotification(message.getString("login.errorMessage")));
			}
		});
		btnLayout.addComponent(loginButton);
		layout.addComponent(btnLayout);
		return layout;
	}
	
	protected VerticalLayout bulidContentMessagePanel() {
		String message = "Go Ahead. You Can Rely on Us.";
		String subMessge = "Better collaboration and software solution for your industry.";
		VerticalLayout layout = buildLayout();
		layout.addStyleName("m-content-message-panel");
		
		VerticalLayout messageLayout = new VerticalLayout();
		messageLayout.addStyleName("m-message-layout");
		Label label = new Label(message);
		label.setStyleName("m-message-label");
		messageLayout.addComponent(label);
		Label subLabel = new Label(subMessge);
		subLabel.setStyleName("m-submessage-label");
		messageLayout.addComponent(subLabel);
		layout.addComponent(messageLayout);
		
		VerticalLayout spacingLayout = buildLayout();
		spacingLayout.setHeight("20px");
		spacingLayout.addStyleName("m-spacing-panel");
		layout.addComponent(spacingLayout);
		return layout;
	}

	protected VerticalLayout buildBottomLayout() {
		VerticalLayout bottomLayout = buildLayout();
		bottomLayout.setHeight("80px");
		bottomLayout.setStyleName("m-login-bottom-layout");
		HorizontalLayout layout = new HorizontalLayout();
		layout.addStyleName("m-link-layout");
		layout.setSpacing(true);
		layout.addComponent(WidgetFactory.createLink("Mocha Platform Homepage"));
		layout.addComponent(WidgetFactory.createSeperator());
		layout.addComponent(WidgetFactory.createLink("About Us"));
		layout.addComponent(WidgetFactory.createSeperator());
		layout.addComponent(WidgetFactory.createLink("Blog"));
		layout.addComponent(WidgetFactory.createSeperator());
		layout.addComponent(WidgetFactory.createLink("Status"));
		layout.addComponent(WidgetFactory.createSeperator());
		layout.addComponent(WidgetFactory.createLink("Terms"));
		layout.addComponent(WidgetFactory.createSeperator());
		layout.addComponent(WidgetFactory.createLink("Help"));
		layout.addComponent(WidgetFactory.createSeperator());
		layout.addComponent(WidgetFactory.createLabel("© 2012-2014 Mocha, Inc"));
		
		bottomLayout.addComponent(layout);
		bottomLayout.setComponentAlignment(layout, Alignment.BOTTOM_RIGHT);
		return bottomLayout;
	}
	
	protected VerticalLayout buildLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("100%");
		return layout;
	}
	
	public class LogoWidget extends VerticalLayout {
		private String title = "Mocha Platform";
		public LogoWidget() {
			this.addStyleName("m-logo-widget");
		}
		
		public void attach() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setHeight("52px");
			layout.setSpacing(true);
//			Label logo = new Label();
//			logo.setWidth("20px");
//			logo.addStyleName("m-logo-icon");
//			logo.setIcon(new ThemeResource("icons/logo.png"));
//			layout.addComponent(logo);
			Label logoTitle = new Label(title);
			logoTitle.addStyleName("m-logo-label");
			layout.addComponent(logoTitle);
			
			this.addComponent(layout);
		}
}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
