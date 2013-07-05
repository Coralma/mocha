package com.mocha.cooperate.mobile.phone;

import java.util.List;

import com.mocha.cooperate.mobile.phone.widgets.PhoneCardListener;
import com.mocha.cooperate.mobile.phone.widgets.PhoneStatusCard;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.CssLayout;

public class PhoneHomeView extends NavigationView implements MobileView {

	private List<TimeLine> timelines;
	private PhoneCardListener cardListener;
	
	public PhoneHomeView() {
		
	}
	
	@Override
	public void attach() {
		super.attach();
		setCaption("My Feed");
		
		CssLayout content = new CssLayout();
		content.addStyleName("phone-home-layout");
		content.setSizeFull();
		
		for(TimeLine timeline : timelines) {
			PhoneStatusCard card = new PhoneStatusCard(timeline);
			card.setCardListener(cardListener);
			content.addComponent(card);
		}
		setContent(content);
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
	 * @param cardListener the cardListener to set
	 */
	public void setCardListener(PhoneCardListener cardListener) {
		this.cardListener = cardListener;
	}
}
