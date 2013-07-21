package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.mobile.phone.ui.PhoneTimeLinePresenter;
import com.mocha.cooperate.mobile.phone.ui.PhoneTimeLineView;
import com.mocha.cooperate.mobile.phone.ui.SettingPresenter;
import com.mocha.cooperate.mobile.phone.ui.SettingView;
import com.vaadin.addon.touchkit.ui.NavigationManager;

public class PhoneSettingHierarchy extends NavigationManager {

	public PhoneSettingHierarchy(MochaEventBus eventBus) {
		SettingPresenter settingPresenter = new SettingPresenter(eventBus);
		SettingView settings = (SettingView) settingPresenter.go();
		navigateTo(settings);
	}

}
