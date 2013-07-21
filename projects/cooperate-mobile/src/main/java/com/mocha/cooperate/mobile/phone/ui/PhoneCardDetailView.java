package com.mocha.cooperate.mobile.phone.ui;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneStatusCard;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneTodoCard;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneTopicCard;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.ToDo;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PhoneCardDetailView extends NavigationView implements MobileView {

	private Status status;
	private Discuss discuss;
	private ToDo todo;
	private BasicUser currentUser;
	
	public PhoneCardDetailView(BasicUser currentUser) {
		this.currentUser = currentUser;
	}
	
	public void attach() {
		super.attach();
		this.setCaption("Detail");
		CssLayout layout = new CssLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		
		if(status != null) {
			VerticalComponentGroup cardGroup = new VerticalComponentGroup();
			PhoneStatusCard statusCard = new PhoneStatusCard(status);
			statusCard.setDetailCard(true);
			cardGroup.addComponent(statusCard);
			layout.addComponent(cardGroup);
			if(status.getComments().size() > 0) {
				layout.addComponent(buildReplyGroup(status.getComments()));
			}
		}
		if(discuss != null) {
			VerticalComponentGroup cardGroup = new VerticalComponentGroup();
			PhoneTopicCard topicCard = new PhoneTopicCard(discuss);
			topicCard.setDetailCard(true);
			cardGroup.addComponent(topicCard);
			layout.addComponent(cardGroup);
			if(discuss.getComments().size() > 0) {
				layout.addComponent(buildReplyGroup(discuss.getComments()));
			}
		}
		if(todo != null) {
			VerticalComponentGroup cardGroup = new VerticalComponentGroup();
			PhoneTodoCard todoCard = new PhoneTodoCard(todo,currentUser);
			todoCard.setDetailCard(true);
			cardGroup.addComponent(todoCard);
			layout.addComponent(cardGroup);
			if(todo.getComments().size() > 0) {
				layout.addComponent(buildReplyGroup(todo.getComments()));
			}
		}

		// add your reply
		VerticalComponentGroup replyGroup = new VerticalComponentGroup();
		TextField replyField = new TextField();
		replyField.setInputPrompt("Write your reply...");
		replyField.setWidth("100%");
		replyGroup.addComponent(replyField);
		
		Button replyButton = new Button("Reply");
		replyButton.setWidth("100%");
		replyGroup.addComponent(replyButton);
		
		layout.addComponent(replyGroup);
		
		this.setContent(layout);
	}
	
	public VerticalComponentGroup buildReplyGroup(List<Comment> comments) {
		VerticalComponentGroup replyGroup = new VerticalComponentGroup();
		for(Comment comment : comments) {
			VerticalLayout replyLayout = new VerticalLayout();
			Label usernameLabel = new Label(comment.getCreator().getRealName() + " : ");
			usernameLabel.addStyleName("detail-reply-username");
			replyLayout.addComponent(usernameLabel);
			Label contentLabel = new Label(comment.getContent());
			contentLabel.addStyleName("detail-reply-content");
			replyLayout.addComponent(contentLabel);
			replyGroup.addComponent(replyLayout);
		}
		return replyGroup;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the discuss
	 */
	public Discuss getDiscuss() {
		return discuss;
	}

	/**
	 * @param discuss the discuss to set
	 */
	public void setDiscuss(Discuss discuss) {
		this.discuss = discuss;
	}

	/**
	 * @return the todo
	 */
	public ToDo getTodo() {
		return todo;
	}

	/**
	 * @param todo the todo to set
	 */
	public void setTodo(ToDo todo) {
		this.todo = todo;
	}
}
