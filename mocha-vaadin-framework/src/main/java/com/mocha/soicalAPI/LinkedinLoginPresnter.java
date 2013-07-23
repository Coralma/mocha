package com.mocha.soicalAPI;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.linkedin.LinkedinImpl;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.MochaReport;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.schema.Person;
import com.mocha.report.CrmReportViewer;

public class LinkedinLoginPresnter extends CommonPresenter implements Presenter {
	Person person;
	BasicUserDao buDao=SpringContextUtils.getBean(BasicUserDao.class);
	String homepage="com.mocha.ib.presenter.IBDashboardPresenter";
	
	
	public LinkedinLoginPresnter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		boolean userHasToken=false;
		BasicUser bu=buDao.findUserByUserName(eventBus.getUser().getUserName());
		for(SoicalApp soicalApp:bu.getSoicalApp()){
			if(soicalApp.getName().equals("linkedin")&& soicalApp.getAuthToken()!=null && soicalApp.getAuthTokenSecret()!=null){
				userHasToken=true;
				LinkedinImpl linkedinImpl=new LinkedinImpl();
				LinkedInAccessToken linkedinAccessToken=new LinkedInAccessToken(soicalApp.getAuthToken(),soicalApp.getAuthTokenSecret());
				person=linkedinImpl.getProfileForCurrentUser(linkedinAccessToken);
				break;
			}
		}
		this.viewer = new LinkedinLoginViewer(userHasToken,eventBus.getUser(),person);
	} 
	
	@Override
	public void bind() {
		LinkedinLoginViewer linkedLoginView=(LinkedinLoginViewer) viewer;
		linkedLoginView.setListener(new AppAuthWindowLister() {
			@Override
			public void closeWindow() {
				System.out.println("user call back on linkeind page window when clikcing close button");
				AppContentEvent appContentEvent = new AppContentEvent();
				appContentEvent.setCustomizeClass(homepage);
				eventBus.post(appContentEvent);
			}
		});
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}
}
