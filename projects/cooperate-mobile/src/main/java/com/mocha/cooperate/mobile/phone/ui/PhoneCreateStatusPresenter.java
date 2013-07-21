package com.mocha.cooperate.mobile.phone.ui;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class PhoneCreateStatusPresenter extends AbstractMobilePresenter {
	
	public PhoneCreateStatusPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.view = new PhoneCreateStatusView();
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

}
