package com.mocha.mobile.controller;

import com.coral.foundation.core.impl.MochaEventBus;

public abstract class AbstractMobilePresenter implements MobilePresenter {

	protected MochaEventBus eventBus;
	protected MobileView view;
	
	public MobileView go() {
		return view;
	}
	
	public MobileView getView() {
		return view;
	}
	
	/**
	 * @return the eventBus
	 */
	public MochaEventBus getEventBus() {
		return eventBus;
	}

	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(MochaEventBus eventBus) {
		this.eventBus = eventBus;
	}
}
