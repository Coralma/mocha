/**
 * 
 */
package com.mocha.cooperate.mobile.phone.widgets.cards;

import com.coral.foundation.model.BaseEntity;
import com.mocha.cooperate.model.Status;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class PhoneStatusCard extends AbstractPhoneCard  {

	private Status status;
	
	public PhoneStatusCard(Status status) {
		this.status = status;
		super.createUser = status.getCreator();
	}
	
	public void attach() {
//		HorizontalLayout headLayout = new HorizontalLayout();
//		headLayout.setWidth("100%");
//		headLayout.setSpacing(true);
//		Status status = timeline.getStatus();
//		Embedded userPhoto = getUserPhoto(); 
//		headLayout.addComponent(userPhoto);
//		headLayout.setExpandRatio(userPhoto, 1.0f);
//
//		VerticalLayout userLayout = new VerticalLayout();
//		Label userName = getUserName();
//		userLayout.addComponent(userName);
//		
//		Label postData = new Label(DateUtils.date2String(status.getCreateTime(), DateUtils.shortFormat));
//		postData.setStyleName("phone-createdate-label");
//		userLayout.addComponent(postData);
//		headLayout.addComponent(userLayout);
//		headLayout.setExpandRatio(userLayout, 100.0f);
//		
//		Label replyNum = new Label("Reply(" + status.getComments().size() + ")");
//		replyNum.setWidth("60px");
//		replyNum.setStyleName("phone-reply-label");
//		headLayout.addComponent(replyNum);
//		headLayout.setExpandRatio(replyNum, 1.0f);
		
		HorizontalLayout headLayout = buildCardHead(status.getCreateTime(),status.getComments().size());
		this.addComponent(headLayout);
		
		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setMargin(false, true, true, true);
		Label content = new Label(status.getContent(), Label.CONTENT_XHTML);
		content.setStyleName("card-content");
		contentLayout.addComponent(content);
		// send data and reply number
//		HorizontalLayout cardMsgLayout = getCreateDateAndReplyInfo(status.getCreateTime(),status.getComments().size());
//		contentLayout.addComponent(cardMsgLayout);
		
		this.addComponent(contentLayout);
		
		super.addDetailButtonListener(status);
	}
	
	
}
