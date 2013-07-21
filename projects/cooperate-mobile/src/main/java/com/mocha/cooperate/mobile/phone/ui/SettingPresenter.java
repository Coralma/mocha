package com.mocha.cooperate.mobile.phone.ui;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class SettingPresenter extends AbstractMobilePresenter {

	public SettingPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.view = new SettingView();
	}
	
	@Override
	public void bind() {
		
	}


}
