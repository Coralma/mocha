package com.mocha.cooperate.mobile.phone.ui;

import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;

public class SettingView extends NavigationView implements MobileView {
	
	@Override
	public void attach() {
		super.attach();
		setCaption("Setting");
		
		CssLayout content = new CssLayout();
		content.setWidth("100%");
		
		VerticalComponentGroup componentGroup = new VerticalComponentGroup();
		NavigationButton accountManageButton = new NavigationButton("Account Manage"); 
		componentGroup.addComponent(accountManageButton);
		
		NavigationButton feedbackButton = new NavigationButton("Feed Back"); 
		componentGroup.addComponent(feedbackButton);
		
		NavigationButton aboutUsButton = new NavigationButton("About Us"); 
		componentGroup.addComponent(aboutUsButton);
		
		Button logoutButton = new Button("Logout Mocha Platform");
		componentGroup.addComponent(logoutButton);
		
		content.addComponent(componentGroup);
		
		
		
		setContent(content);
	}

}
