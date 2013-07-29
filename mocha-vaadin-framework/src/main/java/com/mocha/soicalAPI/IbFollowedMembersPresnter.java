package com.mocha.soicalAPI;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
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

public class IbFollowedMembersPresnter extends AppCommonPresenter implements Presenter {

	LinkedinConnectionDao dao = SpringContextUtils.getBean(LinkedinConnectionDao.class);
	BasicUserDao buDao=SpringContextUtils.getBean(BasicUserDao.class);
	List<LinkedinConnection> linkedinConnections;
	private static String viewConProfilePage="com.mocha.soicalAPI.SoicalConnectionProfileViewPresenter";

	public IbFollowedMembersPresnter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		eventBus.setUser(buDao.findUserByUserName(eventBus.getUser().getUserName()));
		for (SoicalApp soicalApp : eventBus.getUser().getSoicalApp()) {
			if (soicalApp.getName().equals("linkedin") && soicalApp.getLinkedinPersonProfiles().size() > 0) {
				for (LinkedinPersonProfile p : soicalApp.getLinkedinPersonProfiles()) {
					linkedinConnections = dao.findFollowedConnectionByPerson(p);
					break;
				}
			}
		}
		System.out.println("linked matche member is:"+linkedinConnections.size());
		this.viewer = new IbFollowedMembersViewer(linkedinConnections, eventBus.getUser());
	}

	@Override
	public void bind() {
		final IbFollowedMembersViewer viewer=(IbFollowedMembersViewer) this.viewer;
		viewer.getGroupbtn().addListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				System.out.println("User select linkedin connection "+viewer.getSelectCon().getFirstName());
				AppContentEvent appContextEvent=new AppContentEvent();
				appContextEvent.setCustomizeClass(viewConProfilePage);
				eventBus.put("linkedConn", viewer.getSelectCon());
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
