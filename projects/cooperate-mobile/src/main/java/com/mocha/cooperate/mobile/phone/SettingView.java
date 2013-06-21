package com.mocha.cooperate.mobile.phone;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SettingView extends NavigationView {
	
	@Override
	public void attach() {
		super.attach();
		setCaption("Home");
		
		CssLayout content = new CssLayout();
		content.setWidth("100%");
		
		VerticalComponentGroup componentGroup = new VerticalComponentGroup();
		componentGroup.setCaption("Coral:");
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(new Label("Title of Discuss topic"));
		layout.addComponent(new Label("Vornitologist is an example app for Vaadin TouchKit. Birds are real (data from Wikipedia and birdlife.fi) but observation backend produces just some random dummy data."));
		componentGroup.addComponent(layout);
		content.addComponent(componentGroup);

		componentGroup = new VerticalComponentGroup();
		componentGroup.setCaption("Vance:");
		componentGroup.addComponent(new Label("The source code for this app (maven built project) is publicly available via SVN or it can be browsed online. There is also a tutorial to help exploring how this application is built."));
		content.addComponent(componentGroup);
		
		setContent(content);
	}

}
