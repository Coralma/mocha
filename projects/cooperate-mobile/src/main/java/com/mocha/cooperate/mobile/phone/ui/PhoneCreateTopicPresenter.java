package com.mocha.cooperate.mobile.phone.ui;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class PhoneCreateTopicPresenter extends AbstractMobilePresenter {

	public PhoneCreateTopicPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.view = new PhoneCreateTopicView();
	}
	
	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

}