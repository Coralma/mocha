package com.mocha.hr.view;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mocha.hr.view.demo.data.LeaveManage;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.mocha.template.general.utils.GeneralTemplateWidgetFactory;
import com.mocha.template.general.widget.GeneralPropertyWidget;
import com.mocha.template.general.widget.GeneralSectionWidget;
import com.mocha.template.general.widget.GeneralTableWidget;
import com.mocha.template.general.widget.listener.GeneralPropertyListener;
import com.mocha.template.general.widget.listener.GeneralSectionListener;
import com.mocha.template.general.widget.listener.GeneralTableListener;
import com.mocha.template.general.widget.vo.GeneralPropertyVO;
import com.mocha.template.general.widget.vo.GeneralSectionContentVO;
import com.mocha.template.general.widget.vo.GeneralSectionMessageVO;
import com.mocha.template.general.widget.vo.GeneralTableVO;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MyHrView extends CommonViewer implements Viewer {

	public String total_width = GeneralTemplateCst.total_width;
	public String app_name_width = GeneralTemplateCst.app_name_width;
	public String menu_width = GeneralTemplateCst.menu_width;
	
	public String left_layout_width = GeneralTemplateCst.left_layout_width;
	public String left_content_width = GeneralTemplateCst.left_content_width;
	public String right_layout_width = GeneralTemplateCst.right_layout_width;
	
	public BasicUser currentUser;
	
	public MyHrView(BasicUser currentUser) {
		this.currentUser = currentUser;
	}
	
	public void attach() {
		this.addComponent(buildPageContent());
	}

	public Layout buildPageContent() {
		HorizontalLayout pageContentLayout = new HorizontalLayout();
		pageContentLayout.setWidth(total_width);
		pageContentLayout.addStyleName("page-content");
		
		VerticalLayout leftInfoLayout = new VerticalLayout();
		leftInfoLayout.setWidth(left_layout_width);
		
		// MY MESSAGE CENTER section
		GeneralSectionContentVO sectionContent = new GeneralSectionContentVO();
		sectionContent.setSectionLabel("My Message Center");
		sectionContent.setMessages(getMessageData());
		GeneralSectionWidget sectionWidget = GeneralTemplateWidgetFactory.buildGeneralSectionWidget(sectionContent);
		leftInfoLayout.addComponent(sectionWidget);
		
		// MY LEAVE POLICY section
		GeneralTableVO tableVO = new GeneralTableVO();
		tableVO.setCaption("My Leave Policy");
		tableVO.setTableHeadLabels(Lists.newArrayList("Leave Type","Assigned","Remaining Balance"));
		tableVO.setTableHeadVariables(Lists.newArrayList("leaveType","assigned","remainingBalance"));
		tableVO.addData(new LeaveManage("Additional Annual Leave",new BigDecimal("10.00"),new BigDecimal("4.00")));
		tableVO.addData(new LeaveManage("Mandatory Annual Leave",new BigDecimal("5.00"),new BigDecimal("0.00")));
		tableVO.addData(new LeaveManage("Paid Sick Leave",new BigDecimal("6.00"),new BigDecimal("5.00")));
		tableVO.addData(new LeaveManage("Unpaid Personal Leave",new BigDecimal("30.00"),new BigDecimal("30.00")));
		tableVO.addData(new LeaveManage("Unpaid Sick Leave",new BigDecimal("60.00"),new BigDecimal("0.00")));
		GeneralTableWidget tableWidget = GeneralTemplateWidgetFactory.buildGeneralTableWidget(tableVO, new GeneralTableListener() {});
		leftInfoLayout.addComponent(tableWidget);
		
		pageContentLayout.addComponent(leftInfoLayout);
		
		// right content
		VerticalLayout rightInfoLayout = new VerticalLayout();
		rightInfoLayout.setWidth("300px");
		
		VerticalLayout rightUserInfoLayout = new VerticalLayout();
		rightUserInfoLayout.setMargin(true);
		rightUserInfoLayout.setWidth("280px");
		rightUserInfoLayout.addStyleName("user-panel-layout");
		
		HorizontalLayout avatarLayout = new HorizontalLayout();
		Component avatar = WidgetFactory.createMidAvatar(currentUser, getApplication());
		avatarLayout.addComponent(avatar);
		
		Label userName = new Label(currentUser.getRealName());
		userName.addStyleName("user-name-title");
		avatarLayout.addComponent(userName);
		avatarLayout.setComponentAlignment(userName, Alignment.MIDDLE_LEFT);
		rightUserInfoLayout.addComponent(avatarLayout);
		
		rightInfoLayout.addComponent(rightUserInfoLayout);

		// USE STATISTICS - property content
		LinkedHashMap<String, String> propertyMap = Maps.newLinkedHashMap();
		propertyMap.put("Missed Timesheet","5 days");
		propertyMap.put("Total Timesheet","120 days");
		propertyMap.put("Userd Leave","6 days");
		propertyMap.put("Sick Leave","1 days");
		propertyMap.put("Reimbursement","$8500");
		propertyMap.put("Total Interview","18");

		GeneralPropertyVO generalPropertyVO = new GeneralPropertyVO("Use Statistics", propertyMap);
		GeneralPropertyWidget propertyWidget = GeneralTemplateWidgetFactory.buildGeneralPropertyWidget(generalPropertyVO, new GeneralPropertyListener() {});
		rightInfoLayout.addComponent(propertyWidget);
		
		pageContentLayout.addComponent(rightInfoLayout);
		
		return pageContentLayout;
	}
	
	public List<GeneralSectionMessageVO> getMessageData() {
		List<GeneralSectionMessageVO> messages = Lists.newArrayList();
		messages.add(new GeneralSectionMessageVO("Please submit your timesheet (2013/11/18-2013/11/22) before 2013/11/26 Tuesday 9am."));
		messages.add(new GeneralSectionMessageVO("You submit a leave application at 2013/11/27 and Waiting for Hugo's approvement."));
		messages.add(new GeneralSectionMessageVO("You submit a timeshet (2013/11/04-2013/11/08)."));
		messages.add(new GeneralSectionMessageVO("You hava a interview with Don Quixote at 2013/11/28."));
		messages.add(new GeneralSectionMessageVO("You need provide your feedback for the interview with Flamenco."));
		messages.add(new GeneralSectionMessageVO("You submit a timeshet (2013/10/25-2013/10/29)."));
		messages.add(new GeneralSectionMessageVO("Your leave application has been approved by Hugo."));
		return messages;
	}
	
	@Override
	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
