/**
 * 
 */
package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.mobile.phone.ui.PhoneHomePresenter;
import com.mocha.cooperate.mobile.phone.ui.PhoneHomeView;
import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * @author Coral
 *
 */
public class PhoneHomeHierarchy extends NavigationManager {

	public PhoneHomeHierarchy(MochaEventBus eventBus) {
		PhoneHomePresenter homePresenter = new PhoneHomePresenter(eventBus);
		PhoneHomeView homeView = (PhoneHomeView) homePresenter.go();
		navigateTo(homeView);
	}
}
