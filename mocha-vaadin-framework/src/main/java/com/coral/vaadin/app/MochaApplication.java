/**
 * 
 */
package com.coral.vaadin.app;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.service.BasicUserService;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window;

/**
 * @author Administrator
 * 
 */
public abstract class MochaApplication extends Application implements HttpServletRequestListener {
	
	protected HttpServletResponse response;
	protected String cookieUsername;
	protected String cookieLanguage;
	private boolean developMode = false;
	
	@Override
	public void init() {
		setTheme("mocha");
//		ModelCenter.initModel(applicationModel());
		//init user data;
//		DataPopulate.initDate();

		Window window = new Window();
		window.setCaption(applicationTitle());
		window.setSizeFull();
		window.addStyleName("mocha-window");
		setMainWindow(window);
		boolean needLogin = true;
		// check user
		if(getUser() != null) {
			needLogin = false;
		} else {
			if(isDevelopMode()) {
				setUser(loadTestUser());
				needLogin = false;
			}
		}
		if (!needLogin) {
			AbstractMainPage homepage = SpringContextUtils.getBean("homepage", AbstractMainPage.class);
			homepage.setResponse(response);
			window.setContent(homepage);
		} else {
			LoginScreen loginScreen = new LoginScreen(cookieUsername);
			loginScreen.setResponse(response);
			window.setContent(loginScreen);
		}
	}
	
	public abstract String applicationModel();

	public abstract String applicationTitle();

	/**
	 * Return a test user if it is develop mode.
	 * @return
	 */
	public BasicUser loadTestUser() {
		BasicUserService service = new BasicUserService();
		return service.loadUserById(new Long(1));
	}
	
	/**
	 * @return the developMode
	 */
	public boolean isDevelopMode() {
		return developMode;
	}

	/**
	 * @param developMode the developMode to set
	 */
	public void setDevelopMode(boolean developMode) {
		this.developMode = developMode;
	}
	
}
