package com.mocha.soicalAPI;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.basic.dao.LinkedinPersonProfileDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinGroup;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.report.CrmReportViewer.ReportCardGroup;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LinkedinConnectsCard extends HorizontalLayout implements LayoutClickListener {

	private LinkedinConnection connUser;
	private LinkedinPersonProfile personProfile;
	private String frame_size = "90px";
	private String photo_size = "80px";
	private LinkedinConnectionDao connectionDao = SpringContextUtils.getBean(LinkedinConnectionDao.class);
	private LinkedinPersonProfileDao personProfileDao = SpringContextUtils.getBean(LinkedinPersonProfileDao.class);
	private Label followedLabel=new Label("Followed");
	private VerticalLayout userInfoPanel = new VerticalLayout();

	public LinkedinConnectsCard(LinkedinConnection connUser,LinkedinPersonProfile personProfile) {
		this.personProfile=personProfile;
		this.connUser = connUser;
		this.setWidth("746px");
//		this.setHeight("80px");
		this.addStyleName("user-info-card");
		this.addListener(this);
	}

	@Override
	public void attach() {
		
//		VerticalLayout userPhotoPanel = new VerticalLayout();
//		userPhotoPanel.addStyleName("user-info-photo");
//		VerticalLayout photoAreaPanel = new VerticalLayout();
//		photoAreaPanel.addStyleName("user-photo-moderate");
//		photoAreaPanel.setWidth(frame_size);
//		photoAreaPanel.setHeight(frame_size);
//		Embedded userPhoto = PageBuildHelper.buildUserPhoto(connUser.getPictUrl(), getApplication());
//		userPhoto.setWidth(photo_size);
//		userPhoto.setHeight(photo_size);
//		photoAreaPanel.addComponent(userPhoto);
//		userPhotoPanel.addComponent(photoAreaPanel);
//		this.addComponent(userPhotoPanel);

		
//		userInfoPanel.setWidth("800px");
//		userInfoPanel.addStyleName("user-info-detail");
//		Label userNameLabel = new Label(connUser.getFirstName() + "." + connUser.getLastName());
//		userNameLabel.addStyleName("user-name-caption");
//		userInfoPanel.addComponent(userNameLabel);
//		Label companyNameLabel = new Label(connUser.getCompanyName());
//		userInfoPanel.addComponent(companyNameLabel);
//		Label headLine = new Label(connUser.getHeadline());
//		userInfoPanel.addComponent(headLine);
		
//		GridLayout memberGroupLayout = new GridLayout(5, connUser.getLinkedinGroups().size() % 5 < 1 ? 1 : connUser.getLinkedinGroups().size() % 5);
//		for (LinkedinGroup lg : connUser.getLinkedinGroups()) {
//			Label linkedGroupLabel = new Label(lg.getName());
//			userInfoPanel.addComponent(linkedGroupLabel);
//		}

		HorizontalLayout userDetailPanel = new HorizontalLayout();
		userInfoPanel.addStyleName("user-info-detail");
		
		VerticalLayout userPhotoPanel = new VerticalLayout();
		userPhotoPanel.addStyleName("user-info-photo");
		VerticalLayout photoAreaPanel = new VerticalLayout();
		photoAreaPanel.addStyleName("user-photo-moderate");
		photoAreaPanel.setWidth(frame_size);
		photoAreaPanel.setHeight(frame_size);
		String icon=null;
		Embedded userPhoto = PageBuildHelper.buildUserPhoto(icon, getApplication());
		userPhoto.setWidth(photo_size);
		userPhoto.setHeight(photo_size);
		photoAreaPanel.addComponent(userPhoto);
		userPhotoPanel.addComponent(photoAreaPanel);
		userDetailPanel.addComponent(userPhotoPanel);
		
		Label userNameLabel = new Label(connUser.getFirstName() + "." + connUser.getLastName());
		userNameLabel.addStyleName("user-name-caption");
		userInfoPanel.addComponent(userNameLabel);
		Label companyNameLabel = new Label(connUser.getCompanyName());
		userInfoPanel.addComponent(companyNameLabel);
		Label headLine = new Label(connUser.getHeadline());
		userInfoPanel.addComponent(headLine);
		Label industry = new Label(connUser.getIndustry());
		userInfoPanel.addComponent(industry);
		userInfoPanel.setWidth("520px");
		
		userDetailPanel.addComponent(userInfoPanel);
		this.addComponent(userDetailPanel);
		
//		FormLayout basicInfo = new FormLayout();
//		userDetailPanel.setSpacing(true);

		// basicInfo.setCaption(user.getRealName());
		// basicInfo.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Email"), WidgetFactory.createProperty(user, "email")));
		// basicInfo.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.JobTitle"), WidgetFactory.createProperty(user, "jobTitle")));
		// basicInfo.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Mobile"), WidgetFactory.createProperty(user, "mobile")));
//		userDetailPanel.addComponent(basicInfo);

//		FormLayout basicInfo2 = new FormLayout();
//		basicInfo2.setSpacing(true);
//		// FIXME the basic role should always existed.
//		// basicInfo2.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Extension"), WidgetFactory.createProperty(user,
//		// "extension")));
//		// basicInfo2.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Role"), user.getBasicRole() == null ? "User" : user
//		// .getBasicRole().getRoleName()));
//		// basicInfo2.addComponent(WidgetFactory.createCaptionLabel("Status", WidgetFactory.createProperty(user, "status")));
//		userDetailPanel.addComponent(basicInfo2);
//		userInfoPanel.addComponent(userDetailPanel);
//		this.addComponent(userInfoPanel);
		
		if(connUser.getNeedFollow() !=null && connUser.getNeedFollow().equals("00000001")){
			buildFollowedlabel();
		}
		
	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
		if (connUser.getNeedFollow() == null || connUser.getNeedFollow() == false) {
			connUser.setNeedFollow(true);
			buildFollowedlabel();
		}
		else {
//			connUser.setNeedFollow(false);
		}
		connectionDao.merge(connUser);
		requestRepaintAll();
	}

	private void buildFollowedlabel() {
		followedLabel.addStyleName("connection-followed");
		this.addComponent(followedLabel);
		this.setComponentAlignment(followedLabel, Alignment.BOTTOM_RIGHT);
	}

	public void buildCard() {
		// ToolbarAdvance toolbar = new ToolbarAdvance();
		// toolbar.setToolbarWidth(RuntimeConstant.APP_CONTENT_WIDTH);
		// toolbar.addLeftComponent(WidgetFactory.createLink("All Report"));
		// toolbar.addLeftComponent(WidgetFactory.createLink("Customized Report"));
		//
		// this.addComponent(toolbar);
		//
		// ReportCardGroup systemReport = new ReportCardGroup("System Reports", getAppReports());
		// this.addComponent(systemReport);
		//
		// ReportCardGroup customizedReport = new ReportCardGroup("Customized Reports", getCustomizedAppReports());
		// this.addComponent(customizedReport);
		//
		// bind();
	}

	public LinkedinPersonProfile getPersonProfile() {
		return personProfile;
	}

	public void setPersonProfile(LinkedinPersonProfile personProfile) {
		this.personProfile = personProfile;
	}
}
