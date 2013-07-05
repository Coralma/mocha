/**
 * 
 */
package com.mocha.cooperate.mobile.phone.widgets;

import java.util.Date;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.DateUtils;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * @author Coral
 *
 */
public abstract class AbstractPhoneCard extends HorizontalLayout implements LayoutClickListener {

	protected TimeLine timeline;
	protected BasicUser createUser;
	protected PhoneCardListener cardListener;
	
	public AbstractPhoneCard(TimeLine timeline) {
		this.setWidth("100%");
		this.setSpacing(true);
		this.addStyleName("phone-card-layout");
		this.timeline = timeline;
		this.createUser = timeline.getCreator();
	}
	
	public HorizontalLayout getCreateDateAndReplyInfo(Date createDate, int commentSize) {
		HorizontalLayout cardMsgLayout = new HorizontalLayout();
		cardMsgLayout.setWidth("100%");
		Label postData = new Label(DateUtils.date2String(createDate, DateUtils.shortFormat));
		postData.setStyleName("phone-createdate-label");
		cardMsgLayout.addComponent(postData);
		Label replyNum = new Label("Reply(" + commentSize + ")");
		replyNum.setWidth("100%");
		replyNum.setStyleName("phone-reply-label");
		cardMsgLayout.addComponent(replyNum);
		return cardMsgLayout;
	}
	
	public Embedded getUserPhoto() {
		String url = createUser.getUserPhoto();
		Embedded userPhoto = PageBuildHelper.buildUserPhoto(url, getApplication());
		userPhoto.addStyleName("card-photo");
		userPhoto.setWidth("40px");
		return userPhoto; 
	}
	
	public Label getUserName() {
		Label userName = new Label(createUser.getRealName());
		userName.setStyleName("card-name");
		return userName;
	}

	@Override
	public void layoutClick(LayoutClickEvent event) {
		cardListener.cardClick(timeline);
	}

	/**
	 * @param cardListener the cardListener to set
	 */
	public void setCardListener(PhoneCardListener cardListener) {
		this.cardListener = cardListener;
	}
}
