package com.mocha.hr.view;

import java.util.List;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.google.common.collect.Lists;
import com.mocha.template.general.AbstractGeneralViewer;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.mocha.template.general.utils.GeneralTemplateWidgetFactory;
import com.mocha.template.general.widget.GeneralSectionWidget;
import com.mocha.template.general.widget.vo.GeneralSectionContentVO;
import com.mocha.template.general.widget.vo.GeneralSectionMessageVO;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;

public class TimeSheetView extends AbstractGeneralViewer implements Viewer {
	
	protected String right_section_width= GeneralTemplateCst.right_section_width;
	public InlineDateField datetime;
	public Button createButton;
	
	public TimeSheetView() {
		
	}
	
	public void attach() {
		HorizontalLayout pageContentLayout = new HorizontalLayout();
		pageContentLayout.setWidth(total_width);
		pageContentLayout.addStyleName("page-content");

		// left info panel
		VerticalLayout leftInfoLayout = new VerticalLayout();
		leftInfoLayout.setWidth("290px");
		
		VerticalLayout captionLayout = new VerticalLayout();
		captionLayout.setMargin(false);
		captionLayout.setSpacing(true);
		captionLayout.setWidth(right_section_width);
		captionLayout.addStyleName("left-widget-layout");
		
		datetime = new InlineDateField();
		datetime.setWidth("280px");
		datetime.setValue(new java.util.Date());
		datetime.setResolution(InlineDateField.RESOLUTION_DAY);
        datetime.setImmediate(true);
        captionLayout.addComponent(datetime);
		leftInfoLayout.addComponent(captionLayout);
		
		// button of today timesheet.
		VerticalLayout rightUserInfoLayout = new VerticalLayout();
		rightUserInfoLayout.setWidth("280px");
		rightUserInfoLayout.addStyleName("left-widget-layout");
		createButton = GeneralTemplateWidgetFactory.buildCreateButton("Today Timesheet");
		createButton.setWidth("240px");
		createButton.setHeight("45px");
		rightUserInfoLayout.addComponent(createButton);
		rightUserInfoLayout.setComponentAlignment(createButton, Alignment.MIDDLE_LEFT);
		leftInfoLayout.addComponent(rightUserInfoLayout);
		
		pageContentLayout.addComponent(leftInfoLayout);
		
		// RIGHT layout
		VerticalLayout rightInfoLayout = new VerticalLayout();
		rightInfoLayout.setWidth("750px");
		
		GeneralSectionContentVO sectionContent = new GeneralSectionContentVO();
		sectionContent.setSectionLabel("Timesheet Message");
		GeneralSectionWidget sectionWidget = GeneralTemplateWidgetFactory.buildGeneralSectionWidget(sectionContent);
		rightInfoLayout.addComponent(sectionWidget);
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.addStyleName("right-widget-layout");
		RichTextArea textArea = new RichTextArea();
		textArea.setWidth("660px");
		textArea.setHeight("300px");
		layout.addComponent(textArea);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		Button saveButton = WidgetFactory.createButton("Save");
		Button submitButton = WidgetFactory.createButton("Save & Submit");
		buttonLayout.addComponent(saveButton);
		buttonLayout.addComponent(submitButton);
		layout.addComponent(buttonLayout);
		layout.setComponentAlignment(buttonLayout, Alignment.BOTTOM_LEFT);
		rightInfoLayout.addComponent(layout);
		
		GeneralSectionContentVO historySectionContent = new GeneralSectionContentVO();
		historySectionContent.setSectionLabel("Total Timesheet History");
		historySectionContent.setMessages(getMessageData());
		GeneralSectionWidget historySectionWidget = GeneralTemplateWidgetFactory.buildGeneralSectionWidget(historySectionContent);
		rightInfoLayout.addComponent(historySectionWidget);
		
		pageContentLayout.addComponent(rightInfoLayout);
		this.addComponent(pageContentLayout);
	}
	
	public List<GeneralSectionMessageVO> getMessageData() {
		List<GeneralSectionMessageVO> messages = Lists.newArrayList();
		messages.add(new GeneralSectionMessageVO("[2014-01-10] Communicate with ABC Company for the project requirements."));
		messages.add(new GeneralSectionMessageVO("[2014-01-09] Developer the general template for the next generation system."));
		messages.add(new GeneralSectionMessageVO("[2014-01-08] Design the general template for the next generation system."));
		messages.add(new GeneralSectionMessageVO("[2014-01-07] Design the UI display of the general template."));
		messages.add(new GeneralSectionMessageVO("[2014-01-06] Testing the project of Human Resource for the UI integration."));
		messages.add(new GeneralSectionMessageVO("[2014-01-03] Developer the mobile client of mocha platform"));
		return messages;
	}

	@Override
	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
