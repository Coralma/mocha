/**
 * 
 */
package com.mocha.soicalAPI;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.linkedin.LinkedinImpl;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.SoicalAppDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.socialAPI.AppAccessToken;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.themes.Reindeer;

import facebook4j.Account;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;
import facebook4j.auth.Authorization;

public class AppAuthenciateWindow extends Window implements ClickListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	VerticalLayout contentLayout = new VerticalLayout();
	BasicUser user;
	String callBackUrl = APIKeys.LinkedinCallBackUrl;
	String linkedInAPIId = APIKeys.linkedInAPIId;
	String linkedInSecertKey = APIKeys.linkedInSecertKey;

	SoicalAppDao saDao = SpringContextUtils.getBean(SoicalAppDao.class);
	final VerticalLayout mainLayout = new VerticalLayout();
	LinkedInAccessToken linkedinAccessToken;
	AppAccessToken appAccessToken;
	private final Refresher refresher = new Refresher();

	private String socialAppName;

	public AppAuthenciateWindow(AppAccessToken appAccessToken, BasicUser user) {
		this.user = user;
		this.appAccessToken = appAccessToken;
		this.setCaption("Application Authenciation");
		this.center();
		this.addStyleName("mocha-app");
		this.setWidth("860px");
		this.setClosable(true);
		this.setResizeLazy(true);
		this.setResizable(true);
		this.setModal(true);
		this.addStyleName(Reindeer.WINDOW_LIGHT);
		this.setImmediate(true);
		this.setHeight("400px");
	}

	// socialAppName: check APIKeys.facebookAPIName
	public AppAuthenciateWindow(BasicUser user, String socialAppName) {
		this.user = user;
		this.socialAppName = socialAppName;
		this.setCaption("Application Authenciation");
		this.center();
		this.addStyleName("mocha-app");
		this.setWidth("860px");
		this.setClosable(true);
		this.setResizeLazy(true);
		this.setResizable(true);
		this.setModal(true);
		this.addStyleName(Reindeer.WINDOW_LIGHT);
		this.setImmediate(true);
		this.setHeight("400px");
	}

	@Override
	public void attach() {
		addComponent(mainLayout);
		mainLayout.setSizeFull();
		if (socialAppName == null) {
			if (appAccessToken.getLinkedinAccessToken() != null) {
				buildPersonInfo();
			}
			else {
				buildLinkedInPanel();
			}
			if (appAccessToken.getFacebookToken() != null) {
				buildFacebookConViewInfo();
			}
			else {
				buildFacebookLoginInfo();
			}
		}
		else if (socialAppName.equals(APIKeys.facebookAPIName)) {
			buildFacebookLoginInfo();
		}
	}

	private void buildFacebookLoginInfo() {
		final NativeButton fbLogin = new NativeButton("Login With Facebook");
		final NativeButton closeBtn = new NativeButton("Success Login From Facebook");
		closeBtn.addListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});
		closeBtn.addStyleName("mocha-button");

		fbLogin.addStyleName("mocha-button");
		mainLayout.addComponent(fbLogin);
		refresher.setRefreshInterval(5000);
		refresher.addListener(new RefreshListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void refresh(Refresher source) {
				SoicalApp sa = saDao.findSoicaAppByName(user, APIKeys.facebookAPIName);
				if (sa != null && sa.getAuthToken() != null) {
					mainLayout.replaceComponent(fbLogin, closeBtn);
					// close();
				}
			}
		});
		fbLogin.addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Facebook facebook = new FacebookFactory().getInstance();
				facebook.setOAuthAppId(APIKeys.facebookAPIId, APIKeys.facebookSecertKey);
				facebook.setOAuthCallbackURL(APIKeys.facebookCallBackUrl);
				getApplication().getMainWindow().open(new ExternalResource(APIKeys.facebookOAuthUrl), "", -1, -1, Window.BORDER_DEFAULT);
			}
		});
		addComponent(refresher);
	}

	private void buildFacebookConViewInfo() {

	}

	private void buildLinkedInPanel() {
		LinkedinImpl linkedinImpl = new LinkedinImpl(linkedInAPIId, linkedInSecertKey, callBackUrl);
		LinkedInRequestToken linkedinRequestToken = linkedinImpl.getLinkedInRequestToken();
		String token = linkedinRequestToken.getToken();
		String tokenSecret = linkedinRequestToken.getTokenSecret();
		SoicalApp soicalApp = new SoicalApp();
		soicalApp.setName("linkedin");
		soicalApp.setRequesToken(token);
		soicalApp.setRequesTokenSecret(tokenSecret);
		soicalApp.setUser(user);
		final String linkedAuthUrl = linkedinRequestToken.getAuthorizationUrl();
		if (linkedAuthUrl != null) {
			// saDao.merge(soicalApp);
			// buDao.merge(user);
		}

		final NativeButton linkedinBtn = new NativeButton("Authencation On LinkedIn");
		linkedinBtn.addStyleName("mocha-button");
		mainLayout.addComponent(linkedinBtn);
		linkedinBtn.addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getApplication().getMainWindow().open(new ExternalResource(linkedAuthUrl), "", -1, -1, Window.BORDER_DEFAULT);
				buildWaitForAuthLayout();
				setImmediate(true);

				LinkedinImpl linkedinImpl = new LinkedinImpl(linkedInAPIId, linkedInSecertKey, callBackUrl);
				LinkedInRequestToken linkedinRequestToken = linkedinImpl.getLinkedInRequestToken();
				String token = linkedinRequestToken.getToken();
				String tokenSecret = linkedinRequestToken.getTokenSecret();
				SoicalApp soicalApp = new SoicalApp();
				soicalApp.setName("linkedin");
				soicalApp.setRequesToken(token);
				soicalApp.setRequesTokenSecret(tokenSecret);
				soicalApp.setUser(user);
				final String linkedAuthUrl = linkedinRequestToken.getAuthorizationUrl();
				if (linkedAuthUrl != null) {
					user.getSoicalApp().add(soicalApp);
					saDao.merge(soicalApp);
				}
				// mainLayout.addComponent(refresher);
				getApplication().getMainWindow().open(new ExternalResource(linkedAuthUrl), "", -1, -1, Window.BORDER_DEFAULT);
				buildWaitForAuthLayout();
				setImmediate(true);
			}

			// wait for user authencation from linkedIn
			private void buildWaitForAuthLayout() {
				setImmediate(true);
				final NativeButton doneAuthBtn = new NativeButton("Done with LinkedIn Authencation");
				doneAuthBtn.addStyleName("mocha-button");
				mainLayout.replaceComponent(linkedinBtn, doneAuthBtn);
				doneAuthBtn.addListener(new ClickListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						getApplication().getMainWindow().requestRepaintAll();
						mainLayout.requestRepaintAll();
						for (SoicalApp soicalApp : saDao.findSoicalAppByUser(user)) {
							if (soicalApp.getName().equals("linkedin") && soicalApp.getAuthToken() != null) {
								LinkedinImpl linkedinImpl = new LinkedinImpl();
								LinkedInAccessToken linkedinAccessToken = new LinkedInAccessToken(soicalApp.getAuthToken(), soicalApp.getAuthTokenSecret());
								Person person = linkedinImpl.getProfileForCurrentUser(linkedinAccessToken);
								// buildPersonInfo();
							}
						}
					}
				});
			}
		});
	}

	private void buildWaitForAuthLayout() {
		setImmediate(true);
		final NativeButton doneAuthBtn = new NativeButton("I'm done with LinkedIn Authencation");
		doneAuthBtn.addStyleName("mocha-button");
		mainLayout.addComponent(doneAuthBtn);
		doneAuthBtn.addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				buildPersonInfo();
			}
		});
	}

	protected void buildPersonInfo() {
		mainLayout.removeAllComponents();
		Label userHasTokenLabel = new Label("Here are your personal LinkedIn profile:");
		mainLayout.addComponent(userHasTokenLabel);
		mainLayout.setComponentAlignment(userHasTokenLabel, Alignment.TOP_RIGHT);
		GridLayout userInfoLayout = new GridLayout(2, 1);
		userInfoLayout.setSpacing(true);
		userInfoLayout.addStyleName("linkedinUserInfoLayout");
		mainLayout.addComponent(userInfoLayout);
		mainLayout.setComponentAlignment(userInfoLayout, Alignment.MIDDLE_CENTER);

		for (SoicalApp soicalApp : saDao.findSoicalAppByUser(user)) {
			List<LinkedinPersonProfile> profiles = soicalApp.getLinkedinPersonProfiles();
			for (LinkedinPersonProfile p : profiles) {
				userInfoLayout.removeAllComponents();
				Label userName = new Label(p.getFirstName() + "." + p.getLastName());
				userInfoLayout.addComponent(userName);
				Label userHead = new Label(p.getHeadline());
				userInfoLayout.addComponent(userHead);
			}
		}

		NativeButton doneAuthBtn = new NativeButton("View Connections");
		doneAuthBtn.addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});
		doneAuthBtn.addStyleName("mocha-button");
		userInfoLayout.addComponent(doneAuthBtn);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		System.out.println(event.getSource());
	}

	public class LinkButton extends NativeButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String url;

		public LinkButton(String url, String caption) {
			super(caption);
			this.url = url;

			setImmediate(true);
			addListener(new Button.ClickListener() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					LinkButton.this.getWindow().open(new ExternalResource(LinkButton.this.url));
				}
			});
		}
	}

}
