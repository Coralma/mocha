package com.mocha.cooperate.mobile.phone.ui;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.mobile.phone.widgets.CreateBasicPopover;
import com.mocha.cooperate.mobile.phone.widgets.GlobleQueryPopover;
import com.mocha.cooperate.mobile.phone.widgets.TimeLineWidget;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneCardListener;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneStatusCard;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneTodoCard;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneTopicCard;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

public class PhoneHomeView extends NavigationView implements MobileView, ClickListener {

	private List<TimeLine> timelines;
	private PhoneCardListener cardListener;
	private Button createButton = new Button();
	private Button queryButton = new Button();
	private BasicUser currentUser;
	private MochaEventBus eventBus;
	
	public PhoneHomeView(MochaEventBus eventBus) {
		this.currentUser = eventBus.getUser();
		this.eventBus = eventBus;
		createButton.setIcon(new ThemeResource("icons/new.png"));
		createButton.addListener(this);
		this.setLeftComponent(createButton);
		
		queryButton.setIcon(new ThemeResource("icons/query.png"));
		queryButton.addListener(this);
		this.setRightComponent(queryButton);
//		getNavigationManager().navigateTo(c);
	}
	
	@Override
	public void attach() {
		super.attach();
		setCaption("My Feed");
		
//		CssLayout content = new CssLayout();
//		content.setSizeFull();
//		
//		for(TimeLine timeline : timelines) {
//			VerticalComponentGroup componentGroup = new VerticalComponentGroup();
//			if(timeline.getStatus() != null) {
//				PhoneStatusCard card = new PhoneStatusCard(timeline);
//				card.setCardListener(cardListener);
//				componentGroup.addComponent(card);
//			}
//			if(timeline.getDiscuss() != null) {
//				PhoneTopicCard topicCard = new PhoneTopicCard(timeline);
//				topicCard.setCardListener(cardListener);
//				componentGroup.addComponent(topicCard);
//			}
//			if(timeline.getTodo() != null) {
//				PhoneTodoCard todoCard = new PhoneTodoCard(timeline, currentUser);
//				todoCard.setCardListener(cardListener);
//				componentGroup.addComponent(todoCard);
//			}
//			content.addComponent(componentGroup);
//		}
		TimeLineWidget timeLineWidget = new TimeLineWidget();
		timeLineWidget.setSizeFull();
		timeLineWidget.setTimelines(timelines);
		timeLineWidget.setCurrentUser(currentUser);
		timeLineWidget.setCardListener(cardListener);
		
		setContent(timeLineWidget);
	}
	

	@Override
	public void buttonClick(ClickEvent event) {
		if(createButton.equals(event.getButton())) {
			CreateBasicPopover createBasicPopover = new CreateBasicPopover(cardListener);
			createBasicPopover.showRelativeTo(createButton);
		}
		if(queryButton.equals(event.getButton())) {
			GlobleQueryPopover queryPopover = new GlobleQueryPopover();
			queryPopover.showRelativeTo(queryButton);
		}
	}
	
	public void navigateTo(Component navigationView) {
		getNavigationManager().navigateTo(navigationView);
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
