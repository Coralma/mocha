package com.mocha.hr.view;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import com.coral.vaadin.widget.Viewer;
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
import com.mocha.template.general.widget.listener.GeneralTableListener;
import com.mocha.template.general.widget.vo.GeneralPropertyVO;
import com.mocha.template.general.widget.vo.GeneralSectionContentVO;
import com.mocha.template.general.widget.vo.GeneralSectionMessageVO;
import com.mocha.template.general.widget.vo.GeneralTableVO;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class LeaveApplyView extends CommonViewer implements Viewer {

	public String total_width = GeneralTemplateCst.total_width;
	public String app_name_width = GeneralTemplateCst.app_name_width;
	public String menu_width = GeneralTemplateCst.menu_width;
	
	public String left_layout_width = GeneralTemplateCst.left_layout_width;
	public String left_content_width = GeneralTemplateCst.left_content_width;
	public String right_layout_width = GeneralTemplateCst.right_layout_width;

	public Button createLeaveButton;
	
	public LeaveApplyView() {
		
	}
	
	public void attach() {
		HorizontalLayout pageContentLayout = new HorizontalLayout();
		pageContentLayout.setWidth(total_width);
		pageContentLayout.addStyleName("page-content");
		
		VerticalLayout leftInfoLayout = new VerticalLayout();
		leftInfoLayout.setWidth(left_layout_width);
		
		// MY MESSAGE CENTER section
		GeneralSectionContentVO sectionContent = new GeneralSectionContentVO();
		sectionContent.setSectionLabel("Leave History");
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
		
		
		// RIGHT layout
		VerticalLayout rightInfoLayout = new VerticalLayout();
		rightInfoLayout.setWidth("300px");
		
		// create button
		VerticalLayout rightUserInfoLayout = new VerticalLayout();
		rightUserInfoLayout.setWidth("280px");
		rightUserInfoLayout.addStyleName("create-button-layout");
		createLeaveButton = GeneralTemplateWidgetFactory.buildCreateButton("Apply New Leave");
		createLeaveButton.setWidth("280px");
		createLeaveButton.setHeight("50px");
		rightUserInfoLayout.addComponent(createLeaveButton);
		rightInfoLayout.addComponent(rightUserInfoLayout);
		
		LinkedHashMap<String, String> propertyMap = Maps.newLinkedHashMap();
		propertyMap.put("Used Additional Annual Leave","6 days");
		propertyMap.put("Used Mandatory Annual Leave","5 days");
		propertyMap.put("Used Paid Sick Leave","1 days");
		propertyMap.put("Total Used Leave","12 days");

		GeneralPropertyVO generalPropertyVO = new GeneralPropertyVO("Leave Use Statistics", propertyMap);
		GeneralPropertyWidget propertyWidget = GeneralTemplateWidgetFactory.buildGeneralPropertyWidget(generalPropertyVO, new GeneralPropertyListener() {});
		rightInfoLayout.addComponent(propertyWidget);
		
		pageContentLayout.addComponent(rightInfoLayout);
		
		this.addComponent(pageContentLayout);
	}
	
	public List<GeneralSectionMessageVO> getMessageData() {
		List<GeneralSectionMessageVO> messages = Lists.newArrayList();
		messages.add(new GeneralSectionMessageVO("You submit a leave application from 2013/11/27 to 2013/11/28 and Waiting for Hugo's approvement."));
		messages.add(new GeneralSectionMessageVO("Your leave application from 2013/10/21 to 2013/10/23 has been approved by Hugo."));
		messages.add(new GeneralSectionMessageVO("Your leave application at 2013/09/20 has been approved by Hugo."));
		messages.add(new GeneralSectionMessageVO("Your leave application at 2013/08/17 has been approved by Hugo."));
		messages.add(new GeneralSectionMessageVO("Your leave application at 2013/07/08 has been approved by Hugo."));
		messages.add(new GeneralSectionMessageVO("Your leave application from 2013/06/11 to 2013/06/13 has been approved by Hugo."));
		messages.add(new GeneralSectionMessageVO("Your leave application at 2013/05/11 has been approved by Hugo."));
		messages.add(new GeneralSectionMessageVO("Your leave application from 2013/03/01 to 2013/03/05 has been approved by Hugo."));
		return messages;
	}

	@Override
	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the createLeaveButton
	 */
	public Button getCreateLeaveButton() {
		return createLeaveButton;
	}

}
