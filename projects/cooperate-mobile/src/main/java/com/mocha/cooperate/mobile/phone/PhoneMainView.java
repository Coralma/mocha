/**
 * 
 */
package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TabSheet.Tab;


public class PhoneMainView extends TabBarView implements MobileView {

	public PhoneMainView(MochaEventBus eventBus) {
		PhoneHomePresenter homePresenter = new PhoneHomePresenter(eventBus);
		homePresenter.bind();
		PhoneHomeView homeView = (PhoneHomeView) homePresenter.go();
		Tab addTab = addTab(homeView);
		addTab.setIcon(new ThemeResource("linegraphics/home.png"));
		addTab.setCaption("Home");

		addTab = addTab(new SettingView());
		addTab.setIcon(new ThemeResource("linegraphics/world.png"));
		addTab.setCaption("TimeLine");

		addTab = addTab(new SettingView());
		addTab.setIcon(new ThemeResource("linegraphics/bird.png"));
		addTab.setCaption("Apps");

		SettingView settings = new SettingView();
		addTab = addTab(settings);
		addTab.setIcon(new ThemeResource("linegraphics/tools.png"));
		addTab.setCaption("Settings");

		/*
		 * Make settings view as the default. This would not be best option for
		 * a real application, but it also serves as our demos welcome page.
		 */
		setSelectedTab(homeView);

	}
}
