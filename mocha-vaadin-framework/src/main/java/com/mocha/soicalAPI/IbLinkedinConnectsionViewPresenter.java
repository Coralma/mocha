package com.mocha.soicalAPI;

import java.util.List;
import java.util.TimerTask;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.common.eventbus.EventBus;
import com.google.gwt.user.client.Timer;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class IbLinkedinConnectsionViewPresenter extends AppCommonPresenter implements Presenter {

	BasicUserDao buDao = SpringContextUtils.getBean(BasicUserDao.class);
	LinkedinConnectionDao conDao = SpringContextUtils.getBean(LinkedinConnectionDao.class);

	public IbLinkedinConnectsionViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new IbLinkedInConnectionViewer(buDao.findUserByUserName(eventBus.getUser().getUserName()));
	}

	@Override
	public void bind() {
		final IbLinkedInConnectionViewer viewer = (IbLinkedInConnectionViewer) this.viewer;
		viewer.attach();
		viewer.setFollowedConnection(findFollowedConnections());
		viewer.getFollowedLink().addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				viewer.setFollowedConnection(findFollowedConnections());
				System.out.println("User click follow link");
				viewer.buildFollowedConnectionsLayout();
			}
		});

		viewer.getAllLink().addListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				System.out.println("User click all link");
				viewer.buildSearchConnectionLayout();
			}
		});

	}

	@Override
	public String getPresenterName() {

		return null;
	}

	public List<LinkedinConnection> findFollowedConnections() {
		BasicUser bu = buDao.findUserByUserName(eventBus.getUser().getUserName());
		for (SoicalApp soicalApp : bu.getSoicalApp()) {
			if (soicalApp.getName().equals("linkedin") && soicalApp.getLinkedinPersonProfiles().size() > 0) {
				LinkedinPersonProfile profile = soicalApp.getLinkedinPersonProfiles().get(0);
				return conDao.findFollowedConnectionByPerson(profile);
			}
		}
		return null;
	}

}
