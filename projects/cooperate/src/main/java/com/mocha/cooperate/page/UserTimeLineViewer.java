package com.mocha.cooperate.page;

import java.util.Date;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.widget.TimeOfLine;
import com.mocha.cooperate.widget.UserPhotoWidget;
import com.mocha.cooperate.widget.listener.TimeOfLineListener;
import com.mocha.cooperate.widget.wrap.TimeLineSheetWrap;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class UserTimeLineViewer extends CommonViewer implements Viewer {

	private static final long serialVersionUID = 4842748239753201705L;
	private BasicUser user;
	private TimeOfLineListener timeOfLineListener;
	private String infoPanelWidth = "550px";
	private Date oldestDate;
	
	private VerticalLayout timelineListPanel = new VerticalLayout();;
	private TimeOfLine timeOfLine;
	
	public UserTimeLineViewer(BasicUser user,Date oldestDate) {
		this.user = user;
		this.oldestDate = oldestDate;
		this.addStyleName("timeline");
	}
	
	@Override
	public void attach() {
		HorizontalLayout timelinePanel = new HorizontalLayout();
		timelinePanel.setWidth(SystemProperty.application_page_Width);
		
		VerticalLayout userTimelinePanel = new VerticalLayout();
		userTimelinePanel.setWidth(SystemProperty.content_page_width);

		HorizontalLayout userProfilePanel = new HorizontalLayout();
		userProfilePanel.addStyleName("user-profile");
		
		VerticalLayout userInfoPanel = userInformationPanel();
		userInfoPanel.setWidth(infoPanelWidth);
		// todo add user info to panel
		userProfilePanel.addComponent(userInfoPanel);
		
		VerticalLayout userPhotoPanel = new VerticalLayout();
		userPhotoPanel.setWidth("220px");
		UserPhotoWidget userPhoto =new UserPhotoWidget(user);
		userPhoto.setHideName(true);
		userPhotoPanel.addComponent(userPhoto);
		userPhotoPanel.setComponentAlignment(userPhoto, Alignment.MIDDLE_LEFT);
		userProfilePanel.addComponent(userPhotoPanel);
		
		userTimelinePanel.addComponent(userProfilePanel);
		
		userTimelinePanel.addComponent(timelineListPanel);
		buildTimelinePanel(new Date());
		
		timelinePanel.addComponent(userTimelinePanel);
		
		// set the timeline
		timeOfLine = new TimeOfLine(new Date(), oldestDate);
		timeOfLine.setWidth("150px");
		timelinePanel.addComponent(timeOfLine);
		
		this.addComponent(timelinePanel);
	}
	
	public void buildTimelinePanel(Date date) {
		timelineListPanel.removeAllComponents();

		TimeLineSheetWrap timeLineSheetWrap = new TimeLineSheetWrap(user, date);
		timelineListPanel.addComponent(timeLineSheetWrap.getTimeLineSheet());
	}
	
	public VerticalLayout userInformationPanel() {
		VerticalLayout userInfoPanel = new VerticalLayout();
		userInfoPanel.addStyleName("user-info");
		userInfoPanel.setSpacing(true);
		
		Label userNameLabel = new Label(user.getRealName());
		userNameLabel.addStyleName("user-name-title");
		userInfoPanel.addComponent(userNameLabel);
		
		HorizontalLayout infoPanel = new HorizontalLayout();
		infoPanel.setSizeFull();
		FormLayout contactInfoLayout = new FormLayout();
		contactInfoLayout.addComponent(WidgetFactory.createCaptionLabel("Job Title", user.getJobTitle()));
		contactInfoLayout.addComponent(WidgetFactory.createCaptionLabel("Email", user.getEmail()));

		FormLayout relatedInfoLayout = new FormLayout();
		relatedInfoLayout.addComponent(WidgetFactory.createCaptionLabel("Extension", user.getExtension()));
		relatedInfoLayout.addComponent(WidgetFactory.createCaptionLabel("Mobile", user.getMobile()));
		
		infoPanel.addComponent(contactInfoLayout);
		infoPanel.addComponent(relatedInfoLayout);

		userInfoPanel.addComponent(infoPanel);
		return userInfoPanel;
	}
	
	@Override
	public String getViewerTitle() {
		return null;
	}

	/**
	 * @return the timeOfLineListener
	 */
	public TimeOfLineListener getTimeOfLineListener() {
		return timeOfLineListener;
	}

	/**
	 * @param timeOfLineListener the timeOfLineListener to set
	 */
	public void setTimeOfLineListener(TimeOfLineListener timeOfLineListener) {
		timeOfLine.setListener(timeOfLineListener);
		this.timeOfLineListener = timeOfLineListener;
	}

}
