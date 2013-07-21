package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.mobile.phone.ui.PhoneTimeLinePresenter;
import com.mocha.cooperate.mobile.phone.ui.PhoneTimeLineView;
import com.vaadin.addon.touchkit.ui.NavigationManager;

public class PhoneTimeLineHierarchy extends NavigationManager {

	public PhoneTimeLineHierarchy(MochaEventBus eventBus) {
		PhoneTimeLinePresenter timelinePresenter = new PhoneTimeLinePresenter(eventBus);
		PhoneTimeLineView timelineView = (PhoneTimeLineView) timelinePresenter.go();
		navigateTo(timelineView);
	}

}
