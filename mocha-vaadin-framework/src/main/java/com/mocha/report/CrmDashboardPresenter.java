/**
 * 
 */
package com.mocha.report;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;

/**
 * @author Coral
 *
 */
public class CrmDashboardPresenter extends CommonPresenter implements Presenter {

	public CrmDashboardPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CrmDashboardViewer();
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
