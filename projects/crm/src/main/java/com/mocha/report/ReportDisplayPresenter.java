/**
 * 
 */
package com.mocha.report;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author Coral
 *
 */
public class ReportDisplayPresenter extends AppCommonPresenter implements Presenter {

	public ReportDisplayPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new ReportDisplayViewer();
	}
	
	@Override
	public void bind() {		
		ReportDisplayViewer reportDisplayViewer = (ReportDisplayViewer) viewer;
		reportDisplayViewer.getBackLink().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postCustomizeClass(CrmReportPresenter.class.getName());
			}
		});
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
