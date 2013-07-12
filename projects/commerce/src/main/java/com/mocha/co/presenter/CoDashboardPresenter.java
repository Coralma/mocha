/**
 * 
 */
package com.mocha.co.presenter;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;

/**
 * @author Coral
 *
 */
public class CoDashboardPresenter extends CommonPresenter implements Presenter {

	public CoDashboardPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CoDashboardView(eventBus.getUser());
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return "Home";
	}

	@Override
	public boolean isFullSize() {
		return false;
	}

}
