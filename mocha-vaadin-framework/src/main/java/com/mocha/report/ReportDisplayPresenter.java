/**
 * 
 */
package com.mocha.report;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.report.AbstrctAppRawData;
import com.coral.foundation.report.AppCusteomReportService;
import com.coral.foundation.security.basic.dao.MochaReportDao;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.MochaReport;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author Coral
 *
 */
public class ReportDisplayPresenter extends AppCommonPresenter
		implements
			Presenter {

	private Long reportId;
	private MochaReportDao mochaReportDao = SpringContextUtils
			.getBean(MochaReportDao.class);
	private MochaReport mochaReport;
	
	private static AbstrctAppRawData appCustomReprotRowData;

	public ReportDisplayPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		setAppCustomReprotRowData((AbstrctAppRawData) eventBus.getContext().get("appCustomReprotRowData"));
		ReportDisplayViewer newReportViewer = new ReportDisplayViewer();
		Object reportId = this.eventBus.getContext().get("reportID");
		mochaReport = mochaReportDao
				.load(Long.valueOf(reportId.toString()));
		if (mochaReport != null) {
			AppReport appReport = new AppReport();
			AppCusteomReportService appCustomReportService = new AppCusteomReportService(
					appReport);
			appCustomReportService.setMochaReport(mochaReport);
			ArrayList<String[]> reportResultSet = appCustomReportService.executeMochaReportQuery();
			IndexedContainer contactContainer = new IndexedContainer();
			String[] columnNames = (String[]) reportResultSet.get(0);

			for (String columnName : columnNames) {
				contactContainer.addContainerProperty(columnName, String.class,
						"");
			}
			for (int i = 1; i < reportResultSet.size(); i++) {
				String[] columnValues = (String[]) reportResultSet.get(i);
				Item item = contactContainer.addItem(i);
				for (int j = 0; j < columnValues.length; j++) {
					item.getItemProperty(columnNames[j]).setValue(
							columnValues[j]);
				}
			}
			newReportViewer.setContactContainer(contactContainer);
		}

		if (reportId != null) {
			mochaReport = mochaReportDao.load(Long.valueOf(reportId
					.toString()));
			newReportViewer.setReportId(Long.valueOf(reportId.toString()));
			eventBus.resetContext();
		}

		this.viewer = newReportViewer;
	}

	@Override
	public void bind() {
		ReportDisplayViewer reportDisplayViewer = (ReportDisplayViewer) viewer;

		reportDisplayViewer.getBackLink().addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				postCustomizeClass(CrmReportPresenter.class.getName());
			}
		});
		
		reportDisplayViewer.getEditButton().addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				
				postCustomizeClass(NewReportPresenter.class.getName(),mochaReport,getAppCustomReprotRowData());
			}
		});
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public static AbstrctAppRawData getAppCustomReprotRowData() {
		return appCustomReprotRowData;
	}

	public static void setAppCustomReprotRowData(AbstrctAppRawData appCustomReprotRowData) {
		ReportDisplayPresenter.appCustomReprotRowData = appCustomReprotRowData;
	}

}
