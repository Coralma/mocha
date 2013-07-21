package com.mocha.cooperate.mobile.phone.ui;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class PhoneCreateTodoPresenter extends AbstractMobilePresenter {

	public PhoneCreateTodoPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.view = new PhoneCreateTodoView(eventBus.getUser());
	}
	
	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

}