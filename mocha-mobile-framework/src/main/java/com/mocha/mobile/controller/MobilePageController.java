/**
 * 
 */
package com.mocha.mobile.controller;

import com.coral.foundation.core.impl.MochaEventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.ui.ComponentContainer;

/**
 * @author Coral
 *
 */
public class MobilePageController {
	
	private ComponentContainer mainPage;
	private MochaEventBus eventBus;
	private MobileView currentView;

	public MobilePageController(MochaEventBus eventBus, ComponentContainer mainPage) {
		this.eventBus = eventBus;
		this.mainPage = mainPage;
		this.eventBus.register(this);
	}
	
	@Subscribe
	public void pageChange(MobilePageChangeEvent event) {
		try {
			String presenter = event.getPresenter();
			MobilePresenter mobilePresenter = (MobilePresenter) Class.forName(presenter).getConstructor(MochaEventBus.class).newInstance(eventBus);
			MobileView mobileView = mobilePresenter.go();
			if(currentView != null) {
				mainPage.replaceComponent(currentView, mobileView);
			} else {
				mainPage.addComponent(mobileView);
			}
			mobilePresenter.bind();
			currentView = mobileView;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
