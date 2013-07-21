package com.mocha.cooperate.mobile.phone.widgets;

import com.mocha.cooperate.mobile.MobileConstants;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneCardListener;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CreateBasicPopover extends Popover implements ClickListener {

//	public Button createStatus = new Button("Create Status");
//	public Button createTopic = new Button("Create Topic");
//	public Button createTodo = new Button("Create Todo");
	
	private NavigationButton createStatus = new NavigationButton("Create Status");
	private NavigationButton createTopic = new NavigationButton("Create Topic");
	private NavigationButton createTodo = new NavigationButton("Create Todo");
	private PhoneCardListener cardListener;
	
	public CreateBasicPopover(PhoneCardListener cardListener) {
		this.cardListener = cardListener;
		this.setCaption("Post your idea");
		this.setWidth("80%");
		this.setClosable(true);
		this.setModal(true);
		this.center();
	}
	
	public void attach() {
		VerticalComponentGroup group = new VerticalComponentGroup();
		createStatus.setWidth("100%");
		createStatus.addListener(this);
		group.addComponent(createStatus);
		createTopic.setWidth("100%");
		createTopic.addListener(this);
		group.addComponent(createTopic);
		createTodo.setWidth("100%");
		createTodo.addListener(this);
		group.addComponent(createTodo);
		this.setContent(group);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Button clickedBtn =event.getButton();
		if(createStatus.equals(clickedBtn)) {
			cardListener.createBasic(MobileConstants.STATUS);
		} else if(createTopic.equals(clickedBtn)) {
			cardListener.createBasic(MobileConstants.TOPIC);
		} else if(createTodo.equals(clickedBtn)) {
			cardListener.createBasic(MobileConstants.TODO);
		}
		this.close();
	}
}
