/**
 * 
 */
package com.mocha.cooperate.mobile.phone.widgets;

import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class PhoneStatusCard extends AbstractPhoneCard  {

	public PhoneStatusCard(TimeLine timeline) {
		super(timeline);
	}
	
	public void attach() {
		Status status = timeline.getStatus();
		Embedded userPhoto = getUserPhoto(); 
		this.addComponent(userPhoto);
		this.setExpandRatio(userPhoto, 1.0f);
		
		VerticalLayout contentLayout = new VerticalLayout();
		Label userName = getUserName();
		contentLayout.addComponent(userName);
		
		Label content = new Label(status.getContent(), Label.CONTENT_XHTML);
		content.setStyleName("card-content");
		contentLayout.addComponent(content);
		// send data and reply number
		HorizontalLayout cardMsgLayout = getCreateDateAndReplyInfo(status.getCreateTime(),status.getComments().size());
		contentLayout.addComponent(cardMsgLayout);
		
		this.addComponent(contentLayout);
		this.setExpandRatio(contentLayout, 100.0f);
	}
}
