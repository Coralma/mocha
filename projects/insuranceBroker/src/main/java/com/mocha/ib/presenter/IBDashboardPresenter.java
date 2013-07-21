/**
 * 
 */
package com.mocha.ib.presenter;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;

/**
 * @author Coral
 *
 */
public class IBDashboardPresenter extends CommonPresenter implements Presenter {

	public IBDashboardPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new IBDashboardView(eventBus.getUser());
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
