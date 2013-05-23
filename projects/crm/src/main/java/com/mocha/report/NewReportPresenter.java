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
public class NewReportPresenter extends CommonPresenter implements Presenter {

	public NewReportPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new NewReportViewer();
	}

	@Override
	public void bind() {
		
	}

	@Override
	public String getPresenterName() {
		return null;
	}

}
