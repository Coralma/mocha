/**
 * 
 */
package com.mocha.report;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.view.CommonPresenter;

/**
 * @author Coral
 *
 */
public class CrmReportPresenter extends CommonPresenter implements Presenter {

	public CrmReportPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CrmReportViewer();
	}

	@Override
	public void bind() {
		CrmReportViewer crmReportViewer = (CrmReportViewer)viewer;
		crmReportViewer.setListener(new ReportCategoryListener() {
			
			@Override
			public void createReport() {
				AppContentEvent appContentEvent = new AppContentEvent();
				appContentEvent.setCustomizeClass("com.mocha.report.NewReportPresenter");
				eventBus.post(appContentEvent);
			}

			@Override
			public void showReport(Long reportID) {
				AppContentEvent appContentEvent = new AppContentEvent();
				appContentEvent.setCustomizeClass("com.mocha.report.ReportDisplayPresenter");
//				appContentEvent.setReportId(reportID);
				eventBus.getContext().put("reportID",reportID);
				eventBus.post(appContentEvent);
			}
		});
	}

	@Override
	public String getPresenterName() {
		return null;
	}

}
