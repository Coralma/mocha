/**
 * 
 */
package com.mocha.cooperate.mobile.phone.widgets.cards;

import java.util.Date;

import com.coral.foundation.model.BaseEntity;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.DateUtils;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public abstract class AbstractPhoneCard extends VerticalLayout {

	protected BasicUser createUser;
	protected PhoneCardListener cardListener;
	protected NavigationButton detailButton = new NavigationButton();
	protected boolean detailCard = false;
	
	public AbstractPhoneCard() {
		this.setWidth("100%");
		this.setSpacing(true);
		this.addStyleName("phone-card-layout");
//		this.createUser = timeline.getCreator();
	}
	
	public HorizontalLayout buildCardHead(Date createDate, int commentSize) {
		HorizontalLayout headLayout = new HorizontalLayout();
		headLayout.addStyleName("card-head");
		headLayout.setWidth("100%");
		headLayout.setSpacing(true);

		Embedded userPhoto = getUserPhoto(); 
		headLayout.addComponent(userPhoto);
		headLayout.setExpandRatio(userPhoto, 1.0f);

		VerticalLayout userLayout = new VerticalLayout();
		Label userName = getUserName();
		userLayout.addComponent(userName);
		
		HorizontalLayout infoLayout = new HorizontalLayout();
		Label postData = new Label(DateUtils.date2String(createDate, DateUtils.shortFormat));
		postData.setStyleName("phone-createdate-label");
		infoLayout.addComponent(postData);
		
		Label replyNum = new Label("Reply(" + commentSize + ")");
		replyNum.setWidth("60px");
		replyNum.setStyleName("phone-reply-label");
		infoLayout.addComponent(replyNum);
		
		userLayout.addComponent(infoLayout);
		headLayout.addComponent(userLayout);
		headLayout.setExpandRatio(userLayout, 100.0f);
		
		if(!detailCard) {
			detailButton.setCaption("Detail");
			detailButton.setWidth("60px");
			headLayout.addComponent(detailButton);
			headLayout.setExpandRatio(detailButton, 1.0f);
		}
		return headLayout;
	}
	
	public void addDetailButtonListener(final BaseEntity baseEntity) {
		if(!detailCard) {
			detailButton.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					cardListener.cardClick(baseEntity);
				}
			});
		}
	}
	
//	public HorizontalLayout getCreateDateAndReplyInfo(Date createDate, int commentSize) {
//		HorizontalLayout cardMsgLayout = new HorizontalLayout();
//		cardMsgLayout.setWidth("100%");
//		Label postData = new Label(DateUtils.date2String(createDate, DateUtils.shortFormat));
//		postData.setStyleName("phone-createdate-label");
//		cardMsgLayout.addComponent(postData);
//		Label replyNum = new Label("Reply(" + commentSize + ")");
//		replyNum.setWidth("100%");
//		replyNum.setStyleName("phone-reply-label");
//		cardMsgLayout.addComponent(replyNum);
//		return cardMsgLayout;
//	}
	
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

//	@Override
//	public void layoutClick(LayoutClickEvent event) {
//		cardListener.cardClick(timeline);
//	}

	/**
	 * @param cardListener the cardListener to set
	 */
	public void setCardListener(PhoneCardListener cardListener) {
		this.cardListener = cardListener;
	}

	/**
	 * @return the detailCard
	 */
	public boolean isDetailCard() {
		return detailCard;
	}

	/**
	 * @param detailCard the detailCard to set
	 */
	public void setDetailCard(boolean detailCard) {
		this.detailCard = detailCard;
	}
}
