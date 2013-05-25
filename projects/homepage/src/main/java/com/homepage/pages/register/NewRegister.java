package com.homepage.pages.register;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.homepage.pages.BasePage;
import com.homepage.pages.ErrorPage;

public class NewRegister extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PageParameters params;

	public NewRegister(PageParameters params) {
		setParams(params);
		build();
	}

	@Override
	public void build() {
		if (checkRequestSessions()) {
			if (params != null) {
				StringValue userName = params.get("userName");
				StringValue emailAddress = params.get("emailAddressName");
				Label userNameLabel = new Label("userName", Model.of(userName
						.toString()));
				add(userNameLabel);
				Label emailAddressLable = new Label("emailAddress",
						Model.of(emailAddress.toString()));
				add(emailAddressLable);
			}
		} else {
			setResponsePage(ErrorPage.class);
		}
	}

	private boolean checkRequestSessions() {
		List<javax.servlet.http.Cookie> cookies = ((WebRequest) getRequestCycle()
				.getRequest()).getCookies();
		boolean flag = false;
		for (javax.servlet.http.Cookie cookie : cookies) {
			if (cookie.getName().contains("mochaSession")) {
				flag = true;
				break;
			}
		}
		return flag;

	}

	public PageParameters getParams() {
		return params;
	}

	public void setParams(PageParameters params) {
		this.params = params;
	}

}
