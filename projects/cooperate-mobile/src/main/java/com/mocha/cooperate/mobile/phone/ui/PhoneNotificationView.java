package com.mocha.cooperate.mobile.phone.ui;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.mobile.phone.widgets.TimeLineWidget;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneCardListener;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;

public class PhoneNotificationView extends NavigationView implements MobileView {
	
	private List<NotifyLine> notifylines;
	private PhoneCardListener cardListener;
	private BasicUser currentUser;

	@Override
	public void attach() {
		super.attach();
		setCaption("Notification");
		
		TimeLineWidget timeLineWidget = new TimeLineWidget();
		timeLineWidget.setSizeFull();
		timeLineWidget.setNotifylines(notifylines);
		timeLineWidget.setCurrentUser(currentUser);
		timeLineWidget.setCardListener(cardListener);
		
		setContent(timeLineWidget);
	}


	/**
	 * @return the cardListener
	 */
	public PhoneCardListener getCardListener() {
		return cardListener;
	}


	/**
	 * @param cardListener the cardListener to set
	 */
	public void setCardListener(PhoneCardListener cardListener) {
		this.cardListener = cardListener;
	}


	/**
	 * @return the currentUser
	 */
	public BasicUser getCurrentUser() {
		return currentUser;
	}


	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(BasicUser currentUser) {
		this.currentUser = currentUser;
	}



	/**
	 * @return the notifylines
	 */
	public List<NotifyLine> getNotifylines() {
		return notifylines;
	}



	/**
	 * @param notifylines the notifylines to set
	 */
	public void setNotifylines(List<NotifyLine> notifylines) {
		this.notifylines = notifylines;
	}

}
