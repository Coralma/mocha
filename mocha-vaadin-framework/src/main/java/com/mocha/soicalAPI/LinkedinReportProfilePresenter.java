package com.mocha.soicalAPI;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.facebook.FBImpl;
import com.coral.foundation.facebook.FBUserModel;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.basic.dao.LinkedinConnectionNetworkUpdateDao;
import com.coral.foundation.security.basic.dao.SoicalAppDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.vaadin.Application;
import com.vaadin.event.MouseEvents;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import facebook4j.FacebookException;
import facebook4j.auth.AccessToken;

public class LinkedinReportProfilePresenter extends AppCommonPresenter implements Presenter {

	private LinkedinConnection linkedConn;

	private LinkedinConnectionNetworkUpdateDao dao = SpringContextUtils.getBean(LinkedinConnectionNetworkUpdateDao.class);
	private SoicalAppDao saDao = SpringContextUtils.getBean(SoicalAppDao.class);

	public LinkedinReportProfilePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		linkedConn = (LinkedinConnection) eventBus.getContext().get("linkedConn");
		List<LinkedinConnectionNetworkUpdate> updates = dao.findUpdateByConnection(linkedConn);
		if (updates != null) {
			linkedConn.setLinkedinConnectionNetworkUpdate(updates);
		}
		this.viewer = new LinkedinReportProfileViewer(linkedConn);
	}

	@Override
	public void bind() {
		final LinkedinReportProfileViewer viewer = (LinkedinReportProfileViewer) this.viewer;
		viewer.getSearchEngineBtn().addListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// String script = "$( function() {$('.simpleApps').slideDown();}";
				// application.getMainWindow().executeJavaScript(script);
				viewer.buildSoicalAppLayout();
				viewer.getSearchEngineBtn().setEnabled(false);
			}
		});

		viewer.getFacebookCommonSearchBtn().addListener(new ClickListener() {
			/**;
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				List<FBUserModel> fbUserModels = FBImpl.fuzzysearchFBUsers(linkedConn.getFirstName() + " " + linkedConn.getLastName());
				viewer.setFbUserModel(fbUserModels);
				viewer.requestRepaintAll();
				viewer.build();
			}
		});

		// no need to signIn facebook every time
		viewer.getFacebookSearchFriend().addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				SoicalApp sa = saDao.findSoicaAppByName(eventBus.getUser(), "facebook");
				
				// auth from facebook
				if (sa == null) {
					String sourceURL = "https://www.facebook.com/dialog/oauth?client_id=" + APIKeys.facebookAPIId 
							+ "&redirect_uri="
							+ APIKeys.facebookCallBackUrl;
					viewer.getApplication().getMainWindow().open(new ExternalResource(sourceURL));
				}
				else {
					try {
						List<FBUserModel> fbUserModels = FBImpl.fuzzySearchFriends(new AccessToken(sa.getAuthToken()), linkedConn.getFirstName(),
								linkedConn.getLastName(), linkedConn.getFirstName() + " " + linkedConn.getLastName());
						viewer.setFbUserModel(fbUserModels);
						viewer.requestRepaintAll();
						viewer.build();
					}
					catch (FacebookException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	@Override
	public String getPresenterName() {
		return null;
	}
}
