package com.mocha.cooperate.page.hrapp;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.common.collect.Lists;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class HumanResourceView extends CommonViewer implements Viewer {
	
	public VerticalLayout layout = new VerticalLayout();
	
	public String total_width = "1000px";
	public String app_name_width = "180px";
	public String menu_width = "820px";

	public MochaEventBus eventBus;
	public BasicUser currentUser;
	
	public HumanResourceView(MochaEventBus eventBus) {
		super();
		this.eventBus = eventBus;
		this.currentUser = eventBus.getUser();
		layout.addStyleName("general-app");
//		build();
	}
	
//	public void build() {
	public void attach() {
		layout.addComponent(buildHeadMenu());
		layout.addComponent(buildPageContent());
		this.addComponent(layout);
	}
	
	/**
	 * Head of app. The display sample is like below.
	 * 
	 * appName   menu1  menu2  menu3  menu4  menu5
	 * 
	 */
	public HorizontalLayout buildHeadMenu() {
		HorizontalLayout headLayout = new HorizontalLayout();
		headLayout.addStyleName("app-head-layout");
		headLayout.setWidth(total_width);

		HorizontalLayout appNameLayout = new HorizontalLayout();
		Label appName = WidgetFactory.createLabel("Human Resource");
		appNameLayout.addComponent(appName);
		appNameLayout.addStyleName("app-name");
		
		appName.setWidth(app_name_width);
		appNameLayout.setWidth(app_name_width);
		headLayout.addComponent(appNameLayout);
		headLayout.setComponentAlignment(appNameLayout, Alignment.MIDDLE_LEFT);

		Button mainMenu =  WidgetFactory.createNativeButton("My HR");
		Button leaveMenu =  WidgetFactory.createNativeButton("Leave");
		Button timesheetMenu =  WidgetFactory.createNativeButton("Timesheet");
		Button interviewMenu =  WidgetFactory.createNativeButton("Interview");
		Button reimbursementMenu =  WidgetFactory.createNativeButton("Reimbursement");
		Button travelMenu =  WidgetFactory.createNativeButton("Travel");

		HorizontalLayout menuLayout = new HorizontalLayout();
		menuLayout.setWidth(menu_width);
		HorizontalLayout menus = new HorizontalLayout();
		menus.addStyleName("app-menu-layout");
		menus.setMargin(true);
		menus.setSpacing(true);
		menus.addComponent(mainMenu);
		menus.addComponent(leaveMenu);
		menus.addComponent(timesheetMenu);
		menus.addComponent(interviewMenu);
		menus.addComponent(reimbursementMenu);
		menus.addComponent(travelMenu);
		menuLayout.addComponent(menus);
		headLayout.addComponent(menuLayout);
		headLayout.setComponentAlignment(menuLayout, Alignment.MIDDLE_LEFT);
		return headLayout; 
	}
	
	String left_layout_width = "680px";
	String left_content_width = "658px";
	String right_layout_width = "320px";
	
	public Layout buildPageContent() {
		HorizontalLayout pageContentLayout = new HorizontalLayout();
		pageContentLayout.setWidth(total_width);
		pageContentLayout.addStyleName("page-content");
		
		VerticalLayout leftInfoLayout = new VerticalLayout();
		leftInfoLayout.setWidth(left_layout_width);
		
		VerticalLayout msgCenterLayout = new VerticalLayout();
		msgCenterLayout.setWidth(left_layout_width);
		
		VerticalLayout msgCenterCaptionLayout = new VerticalLayout();
		msgCenterCaptionLayout.setWidth(left_content_width);
		msgCenterCaptionLayout.addStyleName("caption-layout");
		msgCenterCaptionLayout.setHeight("48px");

		// app section caption - message center.
		Label messageCenterCaption = WidgetFactory.createLabel("My Message Center");
		messageCenterCaption.setWidth(left_content_width);
		messageCenterCaption.addStyleName("app-section-caption");
		msgCenterCaptionLayout.addComponent(messageCenterCaption);
		msgCenterCaptionLayout.setComponentAlignment(messageCenterCaption, Alignment.MIDDLE_LEFT);
		msgCenterLayout.addComponent(msgCenterCaptionLayout);
		leftInfoLayout.addComponent(msgCenterLayout);
		
		
		// app section content - message center content.
		VerticalLayout messageContentLayout = new VerticalLayout();
		messageContentLayout.addStyleName("app-section-content");
		for(String message : getMessageData()) {
			Button messageLink =  WidgetFactory.createLink(message);
			messageContentLayout.addComponent(messageLink);
		}
		leftInfoLayout.addComponent(messageContentLayout);
		
		// my leave policy caption
		VerticalLayout myLeavePolicyCaption = buildSectionHead("My Leave Policy");
		leftInfoLayout.addComponent(myLeavePolicyCaption);
		
		
		List<String> heads = Lists.newArrayList("Leave Type","Assigned","Remaining Balance");
		
		List<String> data1 = Lists.newArrayList("Additional Annual Leave","10.00","4.00");
		List<String> data2 = Lists.newArrayList("Mandatory Annual Leave","5.00","0.00");
		List<String> data3 = Lists.newArrayList("Paid Sick Leave","6.00","5.00");
		List<String> data4 = Lists.newArrayList("Unpaid Personal Leave","30.00","30.00");
		List<String> data5 = Lists.newArrayList("Unpaid Sick Leave","60.00","0.00");
		
		List<List<String>> dataList = Lists.newArrayList();
		dataList.add(data1);
		dataList.add(data2);
		dataList.add(data3);
		dataList.add(data4);
		dataList.add(data5);
		
		VerticalLayout sectionTable = buildSectionTable(heads, dataList, left_content_width);
		sectionTable.setWidth(left_content_width);
		leftInfoLayout.addComponent(sectionTable);
		
		pageContentLayout.addComponent(leftInfoLayout);
		// right content
		
		VerticalLayout rightInfoLayout = new VerticalLayout();
		rightInfoLayout.setWidth("300px");
		
		VerticalLayout rightUserInfoLayout = new VerticalLayout();
		rightUserInfoLayout.setMargin(true);
		rightUserInfoLayout.setWidth("280px");
		rightUserInfoLayout.addStyleName("user-panel-layout");
		
		
//		Label userInfoCaption = WidgetFactory.createLabel("userInfo Center");
//		rightInfoLayout.addComponent(userInfoCaption);
		
		HorizontalLayout avatarLayout = new HorizontalLayout();
		Component avatar = WidgetFactory.createMidAvatar(currentUser, getApplication());
		avatarLayout.addComponent(avatar);
		
		Label userName = new Label(currentUser.getRealName());
		userName.addStyleName("user-name-title");
		avatarLayout.addComponent(userName);
		avatarLayout.setComponentAlignment(userName, Alignment.MIDDLE_LEFT);
		rightUserInfoLayout.addComponent(avatarLayout);
		
		rightInfoLayout.addComponent(rightUserInfoLayout);
		//
		List<String> uheads = Lists.newArrayList("Use Statistics");
		
		List<String> udata1 = Lists.newArrayList("Missed Timesheet","5 days");
		List<String> udata2 = Lists.newArrayList("Total Timesheet","120 days");
		List<String> udata3 = Lists.newArrayList("Userd Leave","6 days");
		List<String> udata4 = Lists.newArrayList("Sick Leave","1 days");
		List<String> udata5 = Lists.newArrayList("Reimbursement","$8500");
		List<String> udata6 = Lists.newArrayList("Total Interview","18");

		List<List<String>> udataList = Lists.newArrayList();
		udataList.add(udata1);
		udataList.add(udata2);
		udataList.add(udata3);
		udataList.add(udata4);
		udataList.add(udata5);
		udataList.add(udata6);
		
		VerticalLayout userSectionTable = buildSectionTable(uheads, udataList, "280px");
		rightInfoLayout.addComponent(userSectionTable);
		
		pageContentLayout.addComponent(rightInfoLayout);
		
		return pageContentLayout;
	}
	
	public VerticalLayout buildSectionHead(String caption) {
		VerticalLayout msgCenterLayout = new VerticalLayout();
		msgCenterLayout.setWidth(left_layout_width);

		VerticalLayout msgCenterCaptionLayout = new VerticalLayout();
		msgCenterCaptionLayout.setWidth(left_content_width);
		msgCenterCaptionLayout.addStyleName("caption-layout");
		msgCenterCaptionLayout.setHeight("48px");

		// app section caption - message center.
		Label messageCenterCaption = WidgetFactory.createLabel(caption);
		messageCenterCaption.setWidth(left_content_width);
		messageCenterCaption.addStyleName("app-section-caption");
		msgCenterCaptionLayout.addComponent(messageCenterCaption);
		msgCenterCaptionLayout.setComponentAlignment(messageCenterCaption, Alignment.MIDDLE_LEFT);
		msgCenterLayout.addComponent(msgCenterCaptionLayout);
		return msgCenterLayout;
	}
	
	public VerticalLayout buildSectionTable(List<String> heads, List<List<String>> dataList, String tableWidth) {
		VerticalLayout table = new VerticalLayout();
		table.setWidth(tableWidth);
		table.addStyleName("app-section-content");
		
		if(heads.size() > 0) {
			HorizontalLayout tableHeadLayout = new HorizontalLayout();
			tableHeadLayout.addStyleName("app-section-table");
			tableHeadLayout.setWidth(tableWidth);
			for(String head : heads) {
				Label leaveTypeHead = WidgetFactory.createLabel(head);
				tableHeadLayout.addComponent(leaveTypeHead);
			}
			table.addComponent(tableHeadLayout);
		}
		for(List<String> datas : dataList) {
			HorizontalLayout tableDataLayout = new HorizontalLayout();
			tableDataLayout.setWidth(tableWidth);
			for(String data : datas) {
				Label dataText = WidgetFactory.createLabel(data);
				tableDataLayout.addComponent(dataText);
			}
			table.addComponent(tableDataLayout);
		}
		return table;
	}
	
	
	public List<String> getMessageData() {
		List<String> messages = Lists.newArrayList();
		messages.add("Please submit your timesheet (2013/11/18-2013/11/22) before 2013/11/26 Tuesday 9am.");
		messages.add("You submit a leave application at 2013/11/27 and Waiting for Hugo's approvement.");
		messages.add("You submit a timeshet (2013/11/04-2013/11/08).");
		messages.add("You hava a interview with Don Quixote at 2013/11/28.");
		messages.add("You need provide your feedback for the interview with Flamenco.");
		messages.add("You submit a timeshet (2013/10/25-2013/10/29).");
		messages.add("Your leave application has been approved by Hugo.");
		return messages;
	}
	

	@Override
	public String getViewerTitle() {
		return null;
	}

	
}
