/**
 * 
 */
package com.mocha.cooperate.page;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;

/**
 * @author Administrator
 *
 */
public class NotificationPresenter extends CommonPresenter implements Presenter {

	public NotificationPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new NotificationViewer();
	}

	
	@Override
	public String getPresenterName() {
		return PresenterProperty.NOTIFICATION;
	}

	@Override
	public void bind() {
		
	}
}
