/**
 * 
 */
package com.mocha.hr.view;

import java.util.LinkedHashMap;

import com.coral.foundation.utils.DateUtils;
import com.coral.vaadin.widget.Viewer;
import com.google.common.collect.Maps;
import com.mocha.hr.view.demo.data.ReimbursementApply;
import com.mocha.template.general.AbstractGeneralViewer;
import com.mocha.template.general.utils.GeneralTemplateWidgetFactory;
import com.mocha.template.general.widget.GeneralEntityCardSectionWidget;
import com.mocha.template.general.widget.GeneralPropertyWidget;
import com.mocha.template.general.widget.listener.GeneralPropertyListener;
import com.mocha.template.general.widget.vo.GeneralEntityCardSectionVO;
import com.mocha.template.general.widget.vo.GeneralPropertyVO;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class ReimbursementView extends AbstractGeneralViewer implements Viewer {

	public Button createButton;
	
	public ReimbursementView() {
		
	}
	
	public void attach() {
		try {
			HorizontalLayout pageContentLayout = new HorizontalLayout();
			pageContentLayout.setWidth(total_width);
			pageContentLayout.addStyleName("page-content");

			// left info panel
			VerticalLayout leftInfoLayout = new VerticalLayout();
			leftInfoLayout.setWidth(left_layout_width);
			// general entity
			GeneralEntityCardSectionVO entityCardSectionVO = new GeneralEntityCardSectionVO();
			entityCardSectionVO.setSectionLabel("Reimbursement History");
			entityCardSectionVO.setFieldLabels(new String[]{"Reimbursement Date","Total Amount","Currency","Charge Department","Approver", "Processing Date"});
			entityCardSectionVO.setFieldVariables(new String[]{"reimbursementDate","totalAmount","currency","chargeDepartment","approver", "processingDate"});
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2014-01-15", DateUtils.dayFormat), "130", "USD", "R&D", "Howard"));
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2013-11-23", DateUtils.dayFormat), "210", "USD", "R&D", "Howard"));
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2013-10-28", DateUtils.dayFormat), "105", "USD", "R&D", "Howard"));
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2013-09-05", DateUtils.dayFormat), "455", "USD", "R&D", "Howard"));
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2013-07-12", DateUtils.dayFormat), "605", "USD", "R&D", "Howard"));
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2013-06-19", DateUtils.dayFormat), "205", "USD", "R&D", "Howard"));
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2013-05-01", DateUtils.dayFormat), "95", "USD", "R&D", "Howard"));
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2013-04-05", DateUtils.dayFormat), "95", "USD", "R&D", "Howard"));
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2013-02-27", DateUtils.dayFormat), "95", "USD", "R&D", "Howard"));
			entityCardSectionVO.addEntity(new ReimbursementApply(DateUtils.toDate("2013-01-08", DateUtils.dayFormat), "95", "USD", "R&D", "Howard"));

			GeneralEntityCardSectionWidget reimbursementHistoryWidget = GeneralTemplateWidgetFactory.buildGeneralEntityCardSectionWidget(entityCardSectionVO);
			leftInfoLayout.addComponent(reimbursementHistoryWidget);
			
			pageContentLayout.addComponent(leftInfoLayout);
			
			// RIGHT layout
			VerticalLayout rightInfoLayout = new VerticalLayout();
			rightInfoLayout.setWidth("300px");
			
			// create button
			VerticalLayout rightUserInfoLayout = new VerticalLayout();
			rightUserInfoLayout.setWidth("280px");
			rightUserInfoLayout.addStyleName("create-button-layout");
			createButton = GeneralTemplateWidgetFactory.buildCreateButton("Apply Reimbursement");
			createButton.setWidth("280px");
			createButton.setHeight("50px");
			rightUserInfoLayout.addComponent(createButton);
			rightInfoLayout.addComponent(rightUserInfoLayout);
			
			LinkedHashMap<String, String> propertyMap = Maps.newLinkedHashMap();
			propertyMap.put("Total Reimbursement","255000 USD");
			propertyMap.put("2014 Reimbursement","3500 USD");
			propertyMap.put("2013 Reimbursement","10800 USD");
			propertyMap.put("2012 Reimbursement","9600 USD");
			propertyMap.put("2011 Reimbursement","1300 USD");
			propertyMap.put("2010 Reimbursement","700 USD");
	
			GeneralPropertyVO generalPropertyVO = new GeneralPropertyVO("Reimbursement Statistics", propertyMap);
			GeneralPropertyWidget propertyWidget = GeneralTemplateWidgetFactory.buildGeneralPropertyWidget(generalPropertyVO, new GeneralPropertyListener() {});
			rightInfoLayout.addComponent(propertyWidget);
			

			
			pageContentLayout.addComponent(rightInfoLayout);
			this.addComponent(pageContentLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getViewerTitle() {
		return null;
	}

}
