package com.mocha.cooperate.mobile.phone;

import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button.ClickListener;

public class PhoneCreateStatusView extends NavigationView implements MobileView, ClickListener {

	private Button backButton = new Button("Back");
	private Button postButton = new Button("Post");
	private TextArea textArea = new TextArea();
	
	public PhoneCreateStatusView() {
		setLeftComponent(backButton);
		setRightComponent(postButton);
	}
	
	@Override
	public void attach() {
		super.attach();
		setCaption("New Status");
		
		CssLayout newStatusLayout = new CssLayout();
		newStatusLayout.setSizeFull();
		VerticalComponentGroup componentGroup = new VerticalComponentGroup();
		componentGroup.setWidth("100%");
		textArea.setWidth("100%");
		textArea.setInputPrompt("Update your status");
		componentGroup.addComponent(textArea);
		
		newStatusLayout.addComponent(componentGroup);
		setContent(newStatusLayout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
}
