/**
 * 
 */
package com.mocha.cooperate.mobile.phone.widgets;

import com.mocha.cooperate.model.Discuss;
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
public class PhoneTopicCard extends AbstractPhoneCard  {

	public PhoneTopicCard(TimeLine timeline) {
		super(timeline);
	}
	
	public void attach() {
		Discuss discuss = timeline.getDiscuss();
		Embedded userPhoto = getUserPhoto(); 
		this.addComponent(userPhoto);
		this.setExpandRatio(userPhoto, 1.0f);
		
		VerticalLayout contentLayout = new VerticalLayout();
		Label userName = getUserName();
		contentLayout.addComponent(userName);
		
		Label title = new Label(discuss.getTitle(), Label.CONTENT_XHTML);
		
		title.setStyleName("topic-title");
		contentLayout.addComponent(title);
		
		Label content = new Label(discuss.getContent(), Label.CONTENT_XHTML);
		content.setStyleName("card-content");
		contentLayout.addComponent(content);
		// send data and reply number
		HorizontalLayout cardMsgLayout = getCreateDateAndReplyInfo(discuss.getCreateTime(),discuss.getComments().size());
		contentLayout.addComponent(cardMsgLayout);
		
		this.addComponent(contentLayout);
		this.setExpandRatio(contentLayout, 100.0f);
	}
}
