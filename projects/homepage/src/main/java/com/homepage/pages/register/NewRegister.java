package com.homepage.pages.register;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.homepage.application.HomepageSystemProperty;
import com.homepage.model.SystemProperty;
import com.homepage.pages.BasePage;
import com.homepage.pages.ErrorPage;
import com.homepage.pages.Homepage;
import com.homepage.security.SimpleSessionStorage;

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

		boolean checkValidateSessionFlag = SimpleSessionStorage
				.checkRequestSessions();

		BookmarkablePageLink homepageUrl = new BookmarkablePageLink(
				"homepageUrl", Homepage.class);
		homepageUrl.setDefaultModel(new ResourceModel("newRegister.homepage"));
		add(homepageUrl);

		ExternalLink cooperateUrl = new ExternalLink("cooperateUrl",
				HomepageSystemProperty.getCooperate_full_url());
		homepageUrl.setDefaultModel(new ResourceModel("newRegister.homepage"));
		add(cooperateUrl);

		if (checkValidateSessionFlag) {
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
	public PageParameters getParams() {
		return params;
	}

	public void setParams(PageParameters params) {
		this.params = params;
	}

}
