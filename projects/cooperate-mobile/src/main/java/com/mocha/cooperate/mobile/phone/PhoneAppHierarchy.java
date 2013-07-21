package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.mobile.phone.ui.PhoneAppPresenter;
import com.mocha.cooperate.mobile.phone.ui.PhoneAppView;
import com.mocha.cooperate.mobile.phone.ui.PhoneHomePresenter;
import com.mocha.cooperate.mobile.phone.ui.PhoneHomeView;
import com.vaadin.addon.touchkit.ui.NavigationManager;

public class PhoneAppHierarchy  extends NavigationManager {

	public PhoneAppHierarchy(MochaEventBus eventBus) {
		PhoneAppPresenter appPresenter = new PhoneAppPresenter(eventBus);
		PhoneAppView appView = (PhoneAppView)appPresenter.go();
		navigateTo(appView);
	}

}
