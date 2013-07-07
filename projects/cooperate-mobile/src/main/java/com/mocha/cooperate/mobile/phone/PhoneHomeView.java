package com.mocha.cooperate.mobile.phone;

import java.util.List;

import com.mocha.cooperate.mobile.phone.widgets.CreateMessagePopover;
import com.mocha.cooperate.mobile.phone.widgets.PhoneCardListener;
import com.mocha.cooperate.mobile.phone.widgets.PhoneStatusCard;
import com.mocha.cooperate.mobile.phone.widgets.PhoneTopicCard;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;

public class PhoneHomeView extends NavigationView implements MobileView, ClickListener {

	private List<TimeLine> timelines;
	private PhoneCardListener cardListener;
	private Button createButton = new Button();

	public PhoneHomeView() {
		createButton.setIcon(new ThemeResource("linegraphics/plus.png"));
		createButton.addListener(this);
		this.setRightComponent(createButton);
	}
	
	@Override
	public void attach() {
		super.attach();
		setCaption("My Feed");
		
		CssLayout content = new CssLayout();
		content.addStyleName("phone-home-layout");
		content.setSizeFull();
		
		for(TimeLine timeline : timelines) {
			if(timeline.getStatus() != null) {
				PhoneStatusCard card = new PhoneStatusCard(timeline);
				card.setCardListener(cardListener);
				content.addComponent(card);
			}
			if(timeline.getDiscuss() != null) {
				PhoneTopicCard topicCard = new PhoneTopicCard(timeline);
				topicCard.setCardListener(cardListener);
				content.addComponent(topicCard);
			}
		}
		setContent(content);
	}
	

	@Override
	public void buttonClick(ClickEvent event) {
		this.getWindow().addWindow(new CreateMessagePopover());
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
