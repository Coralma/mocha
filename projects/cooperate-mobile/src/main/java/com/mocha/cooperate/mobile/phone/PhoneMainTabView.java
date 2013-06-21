/**
 * 
 */
package com.mocha.cooperate.mobile.phone;

import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TabSheet.Tab;


public class PhoneMainTabView extends TabBarView implements MobileView {

	public PhoneMainTabView() {
		/*
		 * Populate main views
		 */
		Tab addTab = addTab(new SettingView());
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
		setSelectedTab(settings);

	}
}
