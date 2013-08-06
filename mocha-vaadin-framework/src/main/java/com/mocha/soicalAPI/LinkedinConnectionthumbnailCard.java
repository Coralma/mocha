package com.mocha.soicalAPI;

import java.net.MalformedURLException;
import java.net.URL;

import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

public class LinkedinConnectionthumbnailCard extends LinkedinConnectsCard {

	private LinkedinConnection connUser;
	private LinkedinPersonProfile personProfile;
	private String frame_size = "150px";
	private String photo_size = "100px";
	LinkedinConnectionDao connectionDao = SpringContextUtils.getBean(LinkedinConnectionDao.class);
	private VerticalLayout mainLayout = new VerticalLayout();
	private NativeButton viewDetailBtn = new NativeButton("View Details");
	private LinkedinThumbnailListener tnListener;

	public LinkedinConnectionthumbnailCard(LinkedinConnection connUser, LinkedinPersonProfile personProfile) {
		super(connUser, personProfile);
		this.connUser = connUser;
		this.personProfile = personProfile;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void attach() {
		this.setWidth("100px");
		this.setHeight("150px");
		this.addComponent(mainLayout);
		VerticalLayout userPhotoPanel = new VerticalLayout();
		userPhotoPanel.addStyleName("user-info-photo");
		VerticalLayout photoAreaPanel = new VerticalLayout();
		photoAreaPanel.addStyleName("user-photo-moderate");
		photoAreaPanel.setWidth(frame_size);
		photoAreaPanel.setHeight(frame_size);
		// Embedded userPhoto = PageBuildHelper.buildUserPhoto(connUser.getPictUrl(), getApplication());
		Embedded userPhoto = null;
		// userPhoto = PageBuildHelper.buildUserPhoto(APIKeys.user_icon_path
		// + APIKeys.user_defalut_photo_name, getApplication());
		URL userIcon = null;
		try {
			if (connUser.getPictUrl() != null) {
				userIcon = new URL(connUser.getPictUrl());
				userPhoto = PageBuildHelper.buildUserPhotoFromURL(userIcon, getApplication());
			}
			else {
				String file = null;
				userPhoto = PageBuildHelper.buildUserPhoto(file, getApplication());
			}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		userPhoto.setWidth("100px");
		userPhoto.setHeight("100px");
		photoAreaPanel.addComponent(userPhoto);
		userPhotoPanel.addComponent(photoAreaPanel);
		mainLayout.addComponent(userPhoto);
		mainLayout.setComponentAlignment(userPhoto, Alignment.TOP_LEFT);

		VerticalLayout userInfoPanel = new VerticalLayout();
		userInfoPanel.setWidth("100px");
		userInfoPanel.addStyleName("user-info-detail");
		Label userNameLabel = new Label(connUser.getFirstName() + "." + connUser.getLastName());
		userNameLabel.addStyleName("user-name-caption");
		userInfoPanel.addComponent(userNameLabel);
		Label companyNameLabel = new Label(connUser.getCompanyName());
		userInfoPanel.addComponent(companyNameLabel);
		mainLayout.addComponent(userInfoPanel);

		// GridLayout memberGroupLayout = new GridLayout(5, connUser.getLinkedinGroups().size() % 5 < 1 ? 1 : connUser.getLinkedinGroups().size() % 5);
		// for (LinkedinGroup lg : connUser.getLinkedinGroups()) {
		// Label linkedGroupLabel = new Label(lg.getName());
		// userInfoPanel.addComponent(linkedGroupLabel);
		// }
		//
		// HorizontalLayout userDetailPanel = new HorizontalLayout();
		// userDetailPanel.setWidth("10px");
		// FormLayout basicInfo = new FormLayout();
		// basicInfo.setSpacing(true);
		//
		// // basicInfo.setCaption(user.getRealName());
		// // basicInfo.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Email"), WidgetFactory.createProperty(user, "email")));
		// // basicInfo.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.JobTitle"), WidgetFactory.createProperty(user,
		// "jobTitle")));
		// // basicInfo.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Mobile"), WidgetFactory.createProperty(user, "mobile")));
		// userDetailPanel.addComponent(basicInfo);
		//
		// FormLayout basicInfo2 = new FormLayout();
		// basicInfo2.setSpacing(true);
		// // FIXME the basic role should always existed.
		// // basicInfo2.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Extension"), WidgetFactory.createProperty(user,
		// // "extension")));
		// // basicInfo2.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Role"), user.getBasicRole() == null ? "User" : user
		// // .getBasicRole().getRoleName()));
		// // basicInfo2.addComponent(WidgetFactory.createCaptionLabel("Status", WidgetFactory.createProperty(user, "status")));
		// userDetailPanel.addComponent(basicInfo2);
		// userInfoPanel.addComponent(userDetailPanel);
		// this.addComponent(userInfoPanel);
	}

}
