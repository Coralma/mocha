package com.mocha.cooperate.mobile.phone.widgets;

import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;

public class CreateMessagePopover extends Popover {

	public Button createStatus = new Button("Create Status");
	public Button createTopic = new Button("Create Topic");
	public Button createTodo = new Button("Create Todo");
	
	public CreateMessagePopover() {
		this.setWidth("100%");
	}
	
	public void attach() {
		CssLayout createBtnLayout = new CssLayout();
		createBtnLayout.setMargin(true);
		createStatus.setWidth("100%");
		createBtnLayout.addComponent(createStatus);
		createTopic.setWidth("100%");
		createBtnLayout.addComponent(createTopic);
		createTodo.setWidth("100%");
		createBtnLayout.addComponent(createTodo);
		this.setContent(createBtnLayout);
	}
}
