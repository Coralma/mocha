package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.mobile.phone.ui.PhoneMessagePresenter;
import com.mocha.cooperate.mobile.phone.ui.PhoneMessageView;
import com.vaadin.addon.touchkit.ui.NavigationManager;

public class PhoneMessageHierarchy extends NavigationManager {

	public PhoneMessageHierarchy(MochaEventBus eventBus) {
		PhoneMessagePresenter msgPresenter = new PhoneMessagePresenter(eventBus);
		PhoneMessageView msgView = (PhoneMessageView) msgPresenter.go();
		navigateTo(msgView);
	}
}
