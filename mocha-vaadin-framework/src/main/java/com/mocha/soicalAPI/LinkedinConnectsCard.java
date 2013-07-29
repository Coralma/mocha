package com.mocha.soicalAPI;

import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.basic.dao.LinkedinPersonProfileDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
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
	private Label followedLabel = new Label("Followed");
	private VerticalLayout userInfoPanel = new VerticalLayout();

	public LinkedinConnectsCard(LinkedinConnection connUser, LinkedinPersonProfile personProfile) {
		this.personProfile = personProfile;
		this.setConnUser(connUser);
		this.setWidth("746px");
		// this.setHeight("80px");
		this.addStyleName("user-info-card");
		this.addListener(this);
		this.connUser = connUser;
	}

	@Override
	public void attach() {

		// VerticalLayout userPhotoPanel = new VerticalLayout();
		// userPhotoPanel.addStyleName("user-info-photo");
		// VerticalLayout photoAreaPanel = new VerticalLayout();
		// photoAreaPanel.addStyleName("user-photo-moderate");
		// photoAreaPanel.setWidth(frame_size);
		// photoAreaPanel.setHeight(frame_size);
		// Embedded userPhoto = PageBuildHelper.buildUserPhoto(connUser.getPictUrl(), getApplication());
		// userPhoto.setWidth(photo_size);
		// userPhoto.setHeight(photo_size);
		// photoAreaPanel.addComponent(userPhoto);
		// userPhotoPanel.addComponent(photoAreaPanel);
		// this.addComponent(userPhotoPanel);

		// userInfoPanel.setWidth("800px");
		// userInfoPanel.addStyleName("user-info-detail");
		// Label userNameLabel = new Label(connUser.getFirstName() + "." + connUser.getLastName());
		// userNameLabel.addStyleName("user-name-caption");
		// userInfoPanel.addComponent(userNameLabel);
		// Label companyNameLabel = new Label(connUser.getCompanyName());
		// userInfoPanel.addComponent(companyNameLabel);
		// Label headLine = new Label(connUser.getHeadline());
		// userInfoPanel.addComponent(headLine);

		// GridLayout memberGroupLayout = new GridLayout(5, connUser.getLinkedinGroups().size() % 5 < 1 ? 1 : connUser.getLinkedinGroups().size() % 5);
		// for (LinkedinGroup lg : connUser.getLinkedinGroups()) {
		// Label linkedGroupLabel = new Label(lg.getName());
		// userInfoPanel.addComponent(linkedGroupLabel);
		// }

		HorizontalLayout userDetailPanel = new HorizontalLayout();
		userInfoPanel.addStyleName("user-info-detail");

		VerticalLayout userPhotoPanel = new VerticalLayout();
		userPhotoPanel.addStyleName("user-info-photo");
		VerticalLayout photoAreaPanel = new VerticalLayout();
		photoAreaPanel.addStyleName("user-photo-moderate");
		photoAreaPanel.setWidth(frame_size);
		photoAreaPanel.setHeight(frame_size);
		String icon = null;
		Embedded userPhoto = PageBuildHelper.buildUserPhoto(icon, getApplication());
		userPhoto.setWidth(photo_size);
		userPhoto.setHeight(photo_size);
		photoAreaPanel.addComponent(userPhoto);
		userPhotoPanel.addComponent(photoAreaPanel);
		userDetailPanel.addComponent(userPhotoPanel);

		Label userNameLabel = new Label(getConnUser().getFirstName() + "." + getConnUser().getLastName());
		userNameLabel.addStyleName("user-name-caption");
		userInfoPanel.addComponent(userNameLabel);
		Label companyNameLabel = new Label(getConnUser().getCompanyName());
		userInfoPanel.addComponent(companyNameLabel);
		Label headLine = new Label(getConnUser().getHeadline());
		userInfoPanel.addComponent(headLine);
		Label industry = new Label(getConnUser().getIndustry());
		userInfoPanel.addComponent(industry);
		userInfoPanel.setWidth("520px");

		userDetailPanel.addComponent(userInfoPanel);
		this.addComponent(userDetailPanel);

		if (getConnUser().getNeedFollow() != null && getConnUser().getNeedFollow().equals("00000001")) {
			buildFollowedlabel();
		}

	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
		if (connectionDao.findEntireConnectionByConn(connUser).size() > 0) {
			LinkedinConnection mergedUserConn = connectionDao.findEntireConnectionByConn(connUser).get(0);
			mergedUserConn.setNeedFollow(true);
			connectionDao.merge(mergedUserConn);
			setConnUser(mergedUserConn);
			buildFollowedlabel();
			requestRepaintAll();
		}

	}

	private void buildFollowedlabel() {
		followedLabel.addStyleName("connection-followed");
		this.addComponent(followedLabel);
		this.setComponentAlignment(followedLabel, Alignment.BOTTOM_RIGHT);
	}

	public LinkedinPersonProfile getPersonProfile() {
		return personProfile;
	}

	public void setPersonProfile(LinkedinPersonProfile personProfile) {
		this.personProfile = personProfile;
	}

	public LinkedinConnection getConnUser() {
		return connUser;
	}

	public void setConnUser(LinkedinConnection connUser) {
		this.connUser = connUser;
	}
}
