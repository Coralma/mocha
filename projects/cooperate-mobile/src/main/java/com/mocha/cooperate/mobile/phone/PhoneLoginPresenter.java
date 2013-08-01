package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.CommonSecurityManager;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.mobile.controller.AbstractMobilePresenter;
import com.mocha.mobile.controller.MobilePageChangeEvent;
import com.mocha.mobile.controller.MobilePresenter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PhoneLoginPresenter extends AbstractMobilePresenter implements MobilePresenter {

	private CommonSecurityManager commonSecuirtyManager;
	
	public PhoneLoginPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.view = new PhoneLoginView();
		commonSecuirtyManager = new CommonSecurityManager();
		commonSecuirtyManager.build();
	}

	public void bind() {
		final PhoneLoginView loginView = (PhoneLoginView) getView();
		loginView.getLoginButton().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String inputUserName = loginView.getUsername().getValue().toString();
				String inputPassword = loginView.getPassword().getValue().toString();
				// BasicUser user = CommonSecurityManager.build().login(inputUserName, inputPassword);
				BasicUser user = commonSecuirtyManager.login(inputUserName, inputPassword);
				if (user == null) {
					System.out.println("Login Failed!");
					return;
				}
				// loginView.getApplication().setUser(user);
				eventBus.setUser(user);
				MobilePageChangeEvent pageEvent = new MobilePageChangeEvent();
				pageEvent.setPresenter(PhoneMainPresenter.class.getName());
				eventBus.post(pageEvent);
			}
		});
	}
}
