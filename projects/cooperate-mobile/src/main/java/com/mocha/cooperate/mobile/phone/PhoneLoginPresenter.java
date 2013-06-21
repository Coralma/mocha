package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.mobile.controller.AbstractMobilePresenter;
import com.mocha.mobile.controller.MobilePresenter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PhoneLoginPresenter extends AbstractMobilePresenter implements MobilePresenter {

	public PhoneLoginPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.view = new PhoneLoginView();
	}
	
	public void bind() {
		PhoneLoginView loginView = (PhoneLoginView)getView();
		loginView.getLoginButton().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
			}
		});
	}
}
