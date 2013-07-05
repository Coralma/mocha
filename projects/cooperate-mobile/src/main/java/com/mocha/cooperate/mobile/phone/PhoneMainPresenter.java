/**
 * 
 */
package com.mocha.cooperate.mobile.phone;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.mobile.controller.AbstractMobilePresenter;
import com.mocha.mobile.controller.MobilePresenter;

/**
 * @author Coral
 *
 */
public class PhoneMainPresenter extends AbstractMobilePresenter implements MobilePresenter {

	public PhoneMainPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.view = new PhoneMainView(eventBus);
	}
	
	@Override
	public void bind() {
		
	}

}
