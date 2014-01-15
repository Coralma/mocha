package com.mocha.hr.view;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import com.coral.vaadin.widget.Viewer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mocha.hr.view.demo.data.LeaveManage;
import com.mocha.template.general.AbstractGeneralViewer;
import com.mocha.template.general.utils.GeneralTemplateWidgetFactory;
import com.mocha.template.general.widget.GeneralPropertyWidget;
import com.mocha.template.general.widget.GeneralSectionWidget;
import com.mocha.template.general.widget.GeneralTableWidget;
import com.mocha.template.general.widget.listener.GeneralPropertyListener;
import com.mocha.template.general.widget.listener.GeneralTableListener;
import com.mocha.template.general.widget.vo.GeneralPropertyVO;
import com.mocha.template.general.widget.vo.GeneralSectionContentVO;
import com.mocha.template.general.widget.vo.GeneralTableVO;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class InterviewView extends AbstractGeneralViewer implements Viewer {
	
	public Button createLeaveButton;
	
	public InterviewView() {
		
	}
	
	public void attach() {
		HorizontalLayout pageContentLayout = new HorizontalLayout();
		pageContentLayout.setWidth(total_width);
		pageContentLayout.addStyleName("page-content");
		
		VerticalLayout leftInfoLayout = new VerticalLayout();
		leftInfoLayout.setWidth(left_layout_width);
		
		// MY MESSAGE CENTER section
		GeneralSectionContentVO sectionContent = new GeneralSectionContentVO();
		sectionContent.setSectionLabel("Candidate List & Interview Result History");
		GeneralSectionWidget sectionWidget = GeneralTemplateWidgetFactory.buildGeneralSectionWidget(sectionContent);
		leftInfoLayout.addComponent(sectionWidget);
		
		pageContentLayout.addComponent(leftInfoLayout);
		
		// RIGHT layout
		VerticalLayout rightInfoLayout = new VerticalLayout();
		rightInfoLayout.setWidth("300px");
		
		// create button
		LinkedHashMap<String, String> propertyMap = Maps.newLinkedHashMap();
		propertyMap.put("Total Candidates","0 P");
		propertyMap.put("Admitted","0 P");
		propertyMap.put("Candidates Giveup","0 P");
		propertyMap.put("Abandon","0 P");

		GeneralPropertyVO generalPropertyVO = new GeneralPropertyVO("Interview Statistics", propertyMap);
		GeneralPropertyWidget propertyWidget = GeneralTemplateWidgetFactory.buildGeneralPropertyWidget(generalPropertyVO, new GeneralPropertyListener() {});
		rightInfoLayout.addComponent(propertyWidget);
		
		pageContentLayout.addComponent(rightInfoLayout);
		
		this.addComponent(pageContentLayout);
	}

	@Override
	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
