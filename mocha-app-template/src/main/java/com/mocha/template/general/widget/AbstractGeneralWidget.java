/**
 * 
 */
package com.mocha.template.general.widget;

import com.coral.vaadin.widget.WidgetFactory;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AbstractGeneralWidget extends VerticalLayout {
	
	protected String left_layout_width = GeneralTemplateCst.left_layout_width;
	protected String left_content_width = GeneralTemplateCst.left_content_width;
	protected String label_height = "48px";

	public VerticalLayout buildSectionHead(String caption) {
		VerticalLayout msgCenterLayout = new VerticalLayout();
		msgCenterLayout.setWidth(left_layout_width);

		VerticalLayout msgCenterCaptionLayout = new VerticalLayout();
		msgCenterCaptionLayout.setWidth(left_content_width);
		msgCenterCaptionLayout.addStyleName("caption-layout");
		msgCenterCaptionLayout.setHeight(label_height);

		// app section caption - message center.
		Label messageCenterCaption = WidgetFactory.createLabel(caption);
		messageCenterCaption.setWidth(left_content_width);
		messageCenterCaption.addStyleName("app-section-caption");
		msgCenterCaptionLayout.addComponent(messageCenterCaption);
		msgCenterCaptionLayout.setComponentAlignment(messageCenterCaption, Alignment.MIDDLE_LEFT);
		msgCenterLayout.addComponent(msgCenterCaptionLayout);
		return msgCenterLayout;
	}
	
	public void setWidgetWidth(String layoutWidth, String contentWidth) {
		this.left_layout_width = layoutWidth;
		this.left_content_width= contentWidth;
	}
}
