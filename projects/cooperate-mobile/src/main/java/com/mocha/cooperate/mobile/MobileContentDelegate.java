/**
 * 
 */
package com.mocha.cooperate.mobile;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.mobile.pad.PadLoginView;
import com.mocha.cooperate.mobile.phone.PhoneLoginView;
import com.mocha.cooperate.mobile.phone.PhoneMainTabView;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class MobileContentDelegate extends VerticalLayout {

	private String type;
	private MochaEventBus eventBus;
	
	public MobileContentDelegate(String type) {
		this.type = type;
		this.setSizeFull();
		this.eventBus = new MochaEventBus();
	}
	
	public void attach() {
		this.removeAllComponents();
		// check user login or not
		if(getApplication().getUser() == null) {
			if(type.equals(MobileConstants.PAD)) {
				this.addComponent(new PadLoginView());
			}
			if(type.equals(MobileConstants.PHONE)) {
				this.addComponent(new PhoneLoginView());
//				this.addComponent(new PhoneMainTabView());
			}
		}
//		else {
//			if(type.equals(MobileConstants.PAD)) {
//				this.addComponent(new PadLoginView());
//			}
//			if(type.equals(MobileConstants.PHONE)) {
//				this.addComponent(new PhoneMainTabView());
//			}
//		}
	}
	
	
}
