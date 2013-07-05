/**
 * 
 */
package com.mocha.cooperate.mobile;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.mobile.pad.PadLoginPresenter;
import com.mocha.cooperate.mobile.phone.PhoneLoginPresenter;
import com.mocha.mobile.controller.MobilePageChangeEvent;
import com.mocha.mobile.controller.MobilePageController;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class MobileContentDelegate extends VerticalLayout {

	private String type;
	private MochaEventBus eventBus;
	protected MobilePageController controller;
	
	public MobileContentDelegate(String type) {
		this.type = type;
		this.setSizeFull();
		this.eventBus = new MochaEventBus();
	}
	
	public void attach() {
		controller = new MobilePageController(eventBus, this);
		
		MobilePageChangeEvent event = new MobilePageChangeEvent();
		// check user login or not
		if(getApplication().getUser() == null) {
			if(type.equals(MobileConstants.PAD)) {
				event.setPresenter(PadLoginPresenter.class.getName());
				controller.pageChange(event);
			}
			if(type.equals(MobileConstants.PHONE)) {
				event.setPresenter(PhoneLoginPresenter.class.getName());
				controller.pageChange(event);
			}
		}

	}
	
}
