/**
 * 
 */
package com.mocha.report;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.basic.dao.AppReportDao;
import com.coral.foundation.security.basic.dao.MochaReportDao;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.MochaReport;
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.report.CrmReportViewer.ReportCardGroup;

/**
 * @author Coral
 *
 */
public class CrmReportPresenter extends CommonPresenter implements Presenter {
	
	MochaReportDao mochaReportDao=SpringContextUtils.getBean(MochaReportDao.class);

	public CrmReportPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		List<AppReport> appReports=new ArrayList<AppReport>();
		List<MochaReport> mochaReports=mochaReportDao.findByCreator(this.eventBus.getUser());
		if(mochaReports!=null){
			for(MochaReport mochaReport:mochaReports){
				appReports.add(mochaReport.getAppReport());
			}
		}
		this.viewer = new CrmReportViewer(appReports);
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
