/**
 * 
 */
package com.mocha.cooperate.page;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.service.NotifyLineService;

/**
 * @author Administrator
 *
 */
public class NotificationPresenter extends CommonPresenter implements Presenter {

	private NotifyLineService notifyService = new NotifyLineService();
	
	public NotificationPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new NotificationViewer(eventBus);
		// make every notify as read.
		notifyService.readAllNotify(eventBus.getUser());
	}

	
	@Override
	public String getPresenterName() {
		return PresenterProperty.NOTIFICATION;
	}

	@Override
	public void bind() {
		
	}
}
