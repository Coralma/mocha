package com.mocha.soicalAPI;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.linkedin.LinkedinImpl;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.schema.Person;

public class LinkedinLoginPresnter extends CommonPresenter implements Presenter {
	private BasicUser user;
	BasicUserDao buDao = SpringContextUtils.getBean(BasicUserDao.class);
	// String linkedinViewPage = "com.mocha.soicalAPI.IbLinkedinConnectsionViewPresenter";
	String linkedinViewPage = "com.mocha.soicalAPI.IbLinkedinConnectsionViewPresenter";
	LinkedInAccessToken linkedinAccessToken;

	public LinkedinLoginPresnter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.user = buDao.findUserByUserName(eventBus.getUser().getUserName());
		boolean needLinkedAuth = true;
		for (SoicalApp soicalApp : user.getSoicalApp()) {
			if (soicalApp.getName().equals("linkedin") && soicalApp.getAuthToken() != null && soicalApp.getAuthTokenSecret() != null) {
				needLinkedAuth = false;
				if (soicalApp.getLinkedinPersonProfiles().size() > 0) {
					LinkedinImpl linkedImpl = new LinkedinImpl();
					linkedinAccessToken = new LinkedInAccessToken(soicalApp.getAuthToken(), soicalApp.getAuthTokenSecret());
				}
				else {
					saveCurrentUserProfile(soicalApp);
				}
				break;
			}
		}
		this.viewer = new LinkedinLoginViewer(linkedinAccessToken, eventBus.getUser());
	}

	private Person saveCurrentUserProfile(SoicalApp soicalApp) {
		LinkedinImpl linkedinImpl = new LinkedinImpl();
		linkedinAccessToken = new LinkedInAccessToken(soicalApp.getAuthToken(), soicalApp.getAuthTokenSecret());
		Person person = linkedinImpl.getProfileForCurrentUser(linkedinAccessToken);
		LinkedinPersonProfile personProfile = new LinkedinPersonProfile();
		personProfile.setFirstName(person.getFirstName());
		personProfile.setLastName(person.getLastName());
		personProfile.setPictUrl(person.getPictureUrl());
		personProfile.setHeadline(person.getHeadline());
		getUser().getSoicalApp().remove(soicalApp);
		soicalApp.getLinkedinPersonProfiles().add(personProfile);
		getUser().getSoicalApp().add(soicalApp);
		buDao.merge(getUser());
		setUser(user);
		return person;
	}

	@Override
	public void bind() {
		LinkedinLoginViewer linkedLoginView = (LinkedinLoginViewer) viewer;
		linkedLoginView.setListener(new AppAuthWindowLister() {
			@Override
			public void closeWindow() {
				AppContentEvent appContentEvent = new AppContentEvent();
				appContentEvent.setCustomizeClass(linkedinViewPage);
				eventBus.post(appContentEvent);

				BasicUser user = buDao.findUserByUserName(eventBus.getUser().getUserName());
				List<SoicalApp> sApps = user.getSoicalApp();
				for (SoicalApp sApp : sApps) {
					if (sApp.getName().equals("linkedin")) {
						LinkedInAccessToken token = new LinkedInAccessToken(sApp.getAuthToken(), sApp.getAuthTokenSecret());
						for (LinkedinPersonProfile profile : sApp.getLinkedinPersonProfiles()) {
							final TimerTask timerTask = new LinkedinScheduleTimterTask(profile, token);
							Timer timer = new Timer() {
							};
							timer.schedule(timerTask, 1000, APIKeys.linkedinSyncNetworkStatusInternval);
						}
					}
				}
			}
		});
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

	public BasicUser getUser() {
		return user;
	}

	public void setUser(BasicUser user) {
		this.user = user;
	}
}
