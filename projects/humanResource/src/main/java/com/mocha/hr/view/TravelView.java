package com.mocha.hr.view;

import java.util.LinkedHashMap;

import com.coral.foundation.utils.DateUtils;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mocha.hr.view.demo.data.TravelApply;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.mocha.template.general.utils.GeneralTemplateWidgetFactory;
import com.mocha.template.general.widget.GeneralPropertyWidget;
import com.mocha.template.general.widget.GeneralTableWidget;
import com.mocha.template.general.widget.listener.GeneralPropertyListener;
import com.mocha.template.general.widget.listener.GeneralTableListener;
import com.mocha.template.general.widget.vo.GeneralPropertyVO;
import com.mocha.template.general.widget.vo.GeneralTableVO;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class TravelView extends CommonViewer implements Viewer {

	public String total_width = GeneralTemplateCst.total_width;
	public String app_name_width = GeneralTemplateCst.app_name_width;
	public String menu_width = GeneralTemplateCst.menu_width;
	
	public String left_layout_width = GeneralTemplateCst.left_layout_width;
	public String left_content_width = GeneralTemplateCst.left_content_width;
	public String right_layout_width = GeneralTemplateCst.right_layout_width;
	
	public Button createButton;
	
	public TravelView() {
		
	}
	
	public void attach() {
		try {
			HorizontalLayout pageContentLayout = new HorizontalLayout();
			pageContentLayout.setWidth(total_width);
			pageContentLayout.addStyleName("page-content");
			
			VerticalLayout leftInfoLayout = new VerticalLayout();
			leftInfoLayout.setWidth(left_layout_width);
	
			// MY LEAVE POLICY section
			GeneralTableVO tableVO = new GeneralTableVO();
			tableVO.setCaption("My Travel History");
			tableVO.setTableHeadLabels(Lists.newArrayList("Destination","Duration","Departure Date","Return Date"));
			tableVO.setTableHeadVariables(Lists.newArrayList("destination","duration","departureDate","returnDate"));
			tableVO.addData(new TravelApply("Los Angeles", 16l, DateUtils.toDate("2013-12-10", DateUtils.dayFormat), DateUtils.toDate("2013-12-26", DateUtils.dayFormat)));
			tableVO.addData(new TravelApply("Amsterdam", 9l, DateUtils.toDate("2013-11-09", DateUtils.dayFormat),DateUtils.toDate("2013-11-18", DateUtils.dayFormat)));
			tableVO.addData(new TravelApply("Detroit", 8l, DateUtils.toDate("2013-10-10", DateUtils.dayFormat), DateUtils.toDate("2013-10-18", DateUtils.dayFormat)));
			tableVO.addData(new TravelApply("Prague", 11l, DateUtils.toDate("2013-08-07", DateUtils.dayFormat), DateUtils.toDate("2013-08-17", DateUtils.dayFormat)));
			tableVO.addData(new TravelApply("Cairo", 12l, DateUtils.toDate("2013-05-10", DateUtils.dayFormat),DateUtils.toDate("2013-05-21", DateUtils.dayFormat)));
			tableVO.addData(new TravelApply("Shanghai", 3l, DateUtils.toDate("2013-03-13", DateUtils.dayFormat), DateUtils.toDate("2013-03-16", DateUtils.dayFormat)));
			tableVO.addData(new TravelApply("Ottawa", 5l, DateUtils.toDate("2013-02-05", DateUtils.dayFormat), DateUtils.toDate("2013-02-10", DateUtils.dayFormat)));
			tableVO.addData(new TravelApply("Paris", 8l, DateUtils.toDate("2012-12-10", DateUtils.dayFormat), DateUtils.toDate("2012-12-18", DateUtils.dayFormat)));
			tableVO.addData(new TravelApply("Beijing", 2l, DateUtils.toDate("2012-09-01", DateUtils.dayFormat), DateUtils.toDate("2012-09-03", DateUtils.dayFormat)));
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
			createButton = GeneralTemplateWidgetFactory.buildCreateButton("Apply Travel");
			createButton.setWidth("280px");
			createButton.setHeight("50px");
			rightUserInfoLayout.addComponent(createButton);
			rightInfoLayout.addComponent(rightUserInfoLayout);
			
			LinkedHashMap<String, String> propertyMap = Maps.newLinkedHashMap();
			propertyMap.put("Total Travel Days","68 days");
			propertyMap.put("2014 Travel Days","0 days");
			propertyMap.put("2013 Travel Days","51 days");
			propertyMap.put("2012 Travel Days","17 days");
	
			GeneralPropertyVO generalPropertyVO = new GeneralPropertyVO("Travel Statistics", propertyMap);
			GeneralPropertyWidget propertyWidget = GeneralTemplateWidgetFactory.buildGeneralPropertyWidget(generalPropertyVO, new GeneralPropertyListener() {});
			rightInfoLayout.addComponent(propertyWidget);
			
			pageContentLayout.addComponent(rightInfoLayout);
			
			this.addComponent(pageContentLayout);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
