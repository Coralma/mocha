package com.mocha.cooperate.mobile.phone.ui;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class PhoneAppPresenter extends AbstractMobilePresenter {

	public PhoneAppPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.view = new PhoneAppView();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

}

