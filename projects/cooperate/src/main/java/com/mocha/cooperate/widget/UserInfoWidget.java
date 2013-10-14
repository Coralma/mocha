package com.mocha.cooperate.widget;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.WidgetFactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class UserInfoWidget extends HorizontalLayout {

	
	private BasicUser user;
	
	public UserInfoWidget(BasicUser user) {
		this.user = user;
	}
	
	public void attach() {
		Component avatar = WidgetFactory.createMidAvatar(user, getApplication());
		this.addComponent(avatar);
		
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("user-info-layout");
		Label userName = new Label(user.getRealName());
		userName.addStyleName("user-name-title");
		layout.addComponent(userName);
		
		Button profile = WidgetFactory.createLink("Profile");
		layout.addComponent(profile);
		
		this.addComponent(layout);
	}
}
