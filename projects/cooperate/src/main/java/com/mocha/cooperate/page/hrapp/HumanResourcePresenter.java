package com.mocha.cooperate.page.hrapp;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;

public class HumanResourcePresenter extends CommonPresenter implements Presenter {

	public HumanResourcePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new HumanResourceView(eventBus);
	}
	
	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.HR;
	}

}
