package com.mocha.soicalAPI;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.report.ProfileReport;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class IbEntireConnectionProfilePresnter extends AppCommonPresenter implements Presenter {

	LinkedinConnectionDao dao = SpringContextUtils.getBean(LinkedinConnectionDao.class);
	BasicUserDao buDao = SpringContextUtils.getBean(BasicUserDao.class);
	List<LinkedinConnection> linkedinConnections;
	private static String viewConProfilePage = "com.mocha.soicalAPI.LinkedinReportProfilePresenter";

	public IbEntireConnectionProfilePresnter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		eventBus.setUser(buDao.findUserByUserName(eventBus.getUser().getUserName()));

		for (SoicalApp soicalApp : eventBus.getUser().getSoicalApp()) {
			if (soicalApp.getName().equals("linkedin") && soicalApp.getLinkedinPersonProfiles().size() > 0) {
				for (LinkedinPersonProfile p : soicalApp.getLinkedinPersonProfiles()) {
					LinkedinConnection currentUser = new LinkedinConnection();
					currentUser.setLinkedinPersonProfile(p);
					currentUser.setFirstName(p.getFirstName());
					currentUser.setLastName(p.getLastName());
					linkedinConnections = dao.findEntireConnectionByConn(currentUser);
					break;
				}
			}
		}

		this.viewer = new IbFollowedMembersViewer(linkedinConnections, eventBus.getUser());
	}

	@Override
	public void bind() {
		final IbFollowedMembersViewer viewer = (IbFollowedMembersViewer) this.viewer;
		viewer.getGroupbtn().addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				String linkedinProfileUrl = viewer.getSelectCon().getPublicProfileUrl();
				String profileEductionCSSPath = "html.os-other body#pagekey-nprofile-public-success.en div#body div.wrapper div#main.profile div#content.resume div#profile-education.section";
				String profileExperenceCSSPath = "html.os-other body#pagekey-nprofile-public-success.en div#body div.wrapper div#main.profile div#content.resume div#profile-experience.section div.content";
				ProfileReport p = new ProfileReport(linkedinProfileUrl, profileExperenceCSSPath, profileEductionCSSPath);
				LinkedinConnection conn = p.parseProfilePage();

				AppContentEvent appContextEvent = new AppContentEvent();
				appContextEvent.setCustomizeClass(viewConProfilePage);
				eventBus.put("linkedConn", conn);
				eventBus.post(appContextEvent);
			}
		});
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
