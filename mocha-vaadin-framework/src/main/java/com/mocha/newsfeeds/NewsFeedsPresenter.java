package com.mocha.newsfeeds;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;

public class NewsFeedsPresenter extends AppCommonPresenter implements Presenter {

	LinkedinConnectionDao dao = SpringContextUtils.getBean(LinkedinConnectionDao.class);
	BasicUserDao buDao = SpringContextUtils.getBean(BasicUserDao.class);
	List<LinkedinConnectionNetworkUpdate> updateStatus;

	public NewsFeedsPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		BasicUser user = buDao.findUserByUserName(eventBus.getUser().getUserName());
		LinkedinPersonProfile lpp = null;
		
		for (SoicalApp sa : user.getSoicalApp()) {
			if (sa.getName().equals("linkedin")) {
				if (sa.getAuthToken() != null && sa.getAuthTokenSecret() != null) {
					lpp = sa.getLinkedinPersonProfiles().get(0);
				}
			}
		}

		eventBus.setUser(buDao.findUserByUserName(eventBus.getUser().getUserName()));
		for (SoicalApp soicalApp : eventBus.getUser().getSoicalApp()) {
			if (soicalApp.getName().equals("linkedin") && soicalApp.getLinkedinPersonProfiles().size() > 0) {
				for (LinkedinPersonProfile p : soicalApp.getLinkedinPersonProfiles()) {
					updateStatus = dao.findUpdateStatusWithFollowedConnections(p);
					break;
				}
			}
		}
		this.viewer = new NewsFeedsViewer(updateStatus);
	}

	@Override
	public void bind() {

	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
