package com.mocha.template.general.widget;

import com.coral.vaadin.widget.WidgetFactory;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.mocha.template.general.widget.listener.GeneralSectionListener;
import com.mocha.template.general.widget.vo.GeneralSectionContentVO;
import com.mocha.template.general.widget.vo.GeneralSectionMessageVO;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class GeneralSectionWidget extends AbstractGeneralWidget {
	
//	protected String left_layout_width = GeneralTemplateCst.left_layout_width;
//	protected String left_content_width = GeneralTemplateCst.left_content_width;
	protected String label_height = "48px";
	
	private GeneralSectionContentVO sectionContent;
	private GeneralSectionListener sectionListener;
	

	public GeneralSectionWidget(GeneralSectionContentVO sectionContent) {
		this.sectionContent = sectionContent;
	}

	public void attach() {
		VerticalLayout sectionCaption = buildSectionHead(sectionContent.getSectionLabel());
		this.addComponent(sectionCaption);
		
		// app section content - message center content.
		if(sectionContent.getMessages().size() > 0) {
			VerticalLayout messageContentLayout = new VerticalLayout();
			messageContentLayout.addStyleName("app-section-content");
			for(GeneralSectionMessageVO message : sectionContent.getMessages()) {
				Button messageLink =  WidgetFactory.createLink(message.getLabel());
				messageLink.setData(message.getAction());
				messageContentLayout.addComponent(messageLink);
			}
			this.addComponent(messageContentLayout);
		}
	}

	/**
	 * @return the sectionListener
	 */
	public GeneralSectionListener getSectionListener() {
		return sectionListener;
	}

	/**
	 * @param sectionListener the sectionListener to set
	 */
	public void setSectionListener(GeneralSectionListener sectionListener) {
		this.sectionListener = sectionListener;
	}
	
}
