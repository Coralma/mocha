package com.mocha.cooperate.mobile.pad;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.mobile.controller.AbstractMobilePresenter;
import com.mocha.mobile.controller.MobilePresenter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PadLoginPresenter extends AbstractMobilePresenter implements MobilePresenter {

	public PadLoginPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.view = new PadLoginView();
	}
	
	public void bind() {
		PadLoginView loginView = (PadLoginView)getView();
		loginView.getLoginButton().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				
			}
		});
	}
}
