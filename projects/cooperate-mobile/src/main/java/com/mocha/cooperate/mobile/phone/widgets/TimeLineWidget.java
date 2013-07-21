/**
 * 
 */
package com.mocha.cooperate.mobile.phone.widgets;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneCardListener;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneStatusCard;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneTodoCard;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneTopicCard;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;

public class TimeLineWidget extends CssLayout {

	private List<TimeLine> timelines;
	private List<NotifyLine> notifylines;
	private PhoneCardListener cardListener;
	private BasicUser currentUser;
	
	public TimeLineWidget() {
		this.setWidth("100%");
	}
	
	public void attach() {
		if(timelines != null) {
			for(TimeLine timeline : timelines) {
				VerticalComponentGroup componentGroup = new VerticalComponentGroup();
				if(timeline.getStatus() != null) {
					PhoneStatusCard card = buildPhoneStatusCard(timeline.getStatus());
					componentGroup.addComponent(card);
				}
				if(timeline.getDiscuss() != null) {
					PhoneTopicCard topicCard = buildPhoneTopicCard(timeline.getDiscuss());
					componentGroup.addComponent(topicCard);
				}
				if(timeline.getTodo() != null) {
					PhoneTodoCard todoCard = buildPhoneTodoCard(timeline.getTodo());
					componentGroup.addComponent(todoCard);
				}
				this.addComponent(componentGroup);
			}
		}
		if(notifylines != null) {
			for(NotifyLine notifyLine : notifylines) {
				VerticalComponentGroup componentGroup = new VerticalComponentGroup();
				if(notifyLine.getStatus() != null) {
					PhoneStatusCard card = buildPhoneStatusCard(notifyLine.getStatus());
					componentGroup.addComponent(card);
				}
				if(notifyLine.getDiscuss() != null) {
					PhoneTopicCard topicCard = buildPhoneTopicCard(notifyLine.getDiscuss());
					componentGroup.addComponent(topicCard);
				}
				if(notifyLine.getTodo() != null) {
					PhoneTodoCard todoCard = buildPhoneTodoCard(notifyLine.getTodo());
					componentGroup.addComponent(todoCard);
				}
				this.addComponent(componentGroup);
			}
		}
	}
	
	public PhoneStatusCard buildPhoneStatusCard(Status status) {
		PhoneStatusCard card = new PhoneStatusCard(status);
		card.setCardListener(cardListener);
		return card;
	}
	
	public PhoneTopicCard buildPhoneTopicCard(Discuss topic) {
		PhoneTopicCard topicCard = new PhoneTopicCard(topic);
		topicCard.setCardListener(cardListener);
		return topicCard;
	}
	
	public PhoneTodoCard buildPhoneTodoCard(ToDo todo) {
		PhoneTodoCard todoCard = new PhoneTodoCard(todo, currentUser);
		todoCard.setCardListener(cardListener);
		return todoCard;
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
