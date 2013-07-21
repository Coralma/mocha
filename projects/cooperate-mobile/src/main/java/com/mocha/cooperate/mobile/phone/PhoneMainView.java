/**
 * 
 */
package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.mobile.phone.ui.SettingPresenter;
import com.mocha.cooperate.mobile.phone.ui.SettingView;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.TabSheet.Tab;


public class PhoneMainView extends TabBarView implements MobileView {

	private PhoneHomeHierarchy homeHierarchy;
	private PhoneAppHierarchy appHierarchy;
	private PhoneMessageHierarchy messageHierarchy;
	private PhoneTimeLineHierarchy timelineHierarchy;
	private PhoneSettingHierarchy settingHierarchy;

	public PhoneMainView(MochaEventBus eventBus) {
//		PhoneHomePresenter homePresenter = new PhoneHomePresenter(eventBus);
		
//		PhoneHomeView homeView = (PhoneHomeView) homePresenter.go();
		homeHierarchy = new PhoneHomeHierarchy(eventBus); 
		Tab addTab = addTab(homeHierarchy);
		addTab.setIcon(new ThemeResource("linegraphics/home.png"));
		addTab.setCaption("Home");

//		PhoneMessagePresenter messagePresenter = new PhoneMessagePresenter(eventBus);
//		PhoneMessageView messageView = (PhoneMessageView) messagePresenter.go();
		messageHierarchy = new PhoneMessageHierarchy(eventBus);
		addTab = addTab(messageHierarchy);
		addTab.setIcon(new ThemeResource("linegraphics/mail.png"));
		addTab.setCaption("Message");
		
//		PhoneTimeLinePresenter timeLinePresenter = new PhoneTimeLinePresenter(eventBus);
//		PhoneTimeLineView timeLineView = (PhoneTimeLineView) timeLinePresenter.go();
		timelineHierarchy = new PhoneTimeLineHierarchy(eventBus);
		addTab = addTab(timelineHierarchy);
		addTab.setIcon(new ThemeResource("linegraphics/world.png"));
		addTab.setCaption("TimeLine");

//		PhoneAppPresenter appPresenter = new PhoneAppPresenter(eventBus);
//		PhoneAppView appView = (PhoneAppView)appPresenter.go();
		appHierarchy = new PhoneAppHierarchy(eventBus);
		addTab = addTab(appHierarchy);
		addTab.setIcon(new ThemeResource("linegraphics/bird.png"));
		addTab.setCaption("Apps");

//		SettingPresenter settingPresenter = new SettingPresenter(eventBus);
//		SettingView settings = (SettingView) settingPresenter.go();
		settingHierarchy = new PhoneSettingHierarchy(eventBus);
		addTab = addTab(settingHierarchy);
		addTab.setIcon(new ThemeResource("linegraphics/tools.png"));
		addTab.setCaption("Settings");

		setSelectedTab(homeHierarchy);

	}
}
