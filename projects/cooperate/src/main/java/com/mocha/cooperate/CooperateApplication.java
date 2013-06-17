/**
 * 
 */
package com.mocha.cooperate;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.utils.FileUtils;
import com.coral.vaadin.app.MochaApplication;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.terminal.gwt.server.WebBrowser;

/**
 * @author Administrator
 *
 */
public class CooperateApplication extends MochaApplication  {
	private static final long serialVersionUID = 5003822334158074903L;
	
	@Override
	public void init() {
		// init language
		if(cookieLanguage != null) {
			if(RuntimeConstant.SUPPORTED_LANGUAGES[1].equals(cookieLanguage)) {
				setLocale(Locale.CHINESE);
			} else {
				setLocale(Locale.ENGLISH);
			}
		} else {
			setLocale(((WebApplicationContext)getContext()).getBrowser().getLocale());
		}
		// get Web Browser info. 
//		WebBrowser webBrowser = ((WebApplicationContext)getContext()).getBrowser();
//		webBrowser.isTouchDevice();
		
		// real code
		super.init();
		
		setTheme("fresh");
		checkDocumentPath();
		
		this.addWindow(new FileShareWindow());
	}

	public void checkDocumentPath() {
//		FileUtils.createDir(SystemProperty.initFilePath());
		FileUtils.createDir(SystemProperty.USER_PHOTO_PATH);
	}
	
	@Override
	public String applicationModel() {
		return "cooperateModel.xml";
	}

	@Override
	public String applicationTitle() {
		return "Cooperate";
	}
	
	@Override
	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		if (cookieUsername == null || cookieLanguage == null) {
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
	            for (int i=0; i<cookies.length; i++) {
	                if (SystemProperty.COOKIE_USERNAME.equals(cookies[i].getName()))
	                    // Log the user in automatically
	                	cookieUsername = cookies[i].getValue();
	                if(SystemProperty.COOKIE_LANGUAGE.equals(cookies[i].getName()))
	                	cookieLanguage = cookies[i].getValue();
	            }
            }
        }
        // Store the reference to the response object for using it in event listeners
        this.response = response;		
	}

	@Override
	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
}
