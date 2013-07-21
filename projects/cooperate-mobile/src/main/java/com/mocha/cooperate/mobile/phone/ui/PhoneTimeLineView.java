package com.mocha.cooperate.mobile.phone.ui;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.mobile.phone.widgets.TimeLineWidget;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneCardListener;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;

public class PhoneTimeLineView extends NavigationView implements MobileView {

	private List<TimeLine> timelines;
	private PhoneCardListener cardListener;
	private BasicUser currentUser;
	
	@Override
	public void attach() {
		super.attach();
		setCaption("TimeLine");
		
		Button timePickupButton = new Button();
		timePickupButton.setIcon(new ThemeResource("icons/time.png"));
		this.setRightComponent(timePickupButton);
		
		TimeLineWidget timeLineWidget = new TimeLineWidget();
		timeLineWidget.setSizeFull();
		timeLineWidget.setTimelines(timelines);
		timeLineWidget.setCurrentUser(currentUser);
		timeLineWidget.setCardListener(cardListener);
		
		setContent(timeLineWidget);
	}

	/**
	 * @return the timelines
	 */
	public List<TimeLine> getTimelines() {
		return timelines;
	}

	/**
	 * @param timelines the timelines to set
	 */
	public void setTimelines(List<TimeLine> timelines) {
		this.timelines = timelines;
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

}
