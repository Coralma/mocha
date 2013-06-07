package com.coral.foundation.report;

import java.util.List;

import com.coral.foundation.security.basic.dao.ReportTableDao;
import com.coral.foundation.security.basic.dao.impl.ReportTableDaoImpl;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.MochaReport;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;

/*
 * Here is one table sturure
 * 
 * Main Table
 * 
 * 
 * */

public class AppCusteomReportService extends AbstractCustomReportService {

	private AppReport appReport;

	public AppCusteomReportService(AppReport appReport) {
		super();
		setAppReport(appReport);
	}

	@Override
	public String buildReport() {
		MochaReport mochaReport = new MochaReport();
		mochaReport.setAppReport(getAppReport());
		setMochaReport(mochaReport);
		validateReportTemplate(mochaReport);
		return null;
	}

	@Override
	void buildOutputColumnString(ReportTable table) {
		List<ReportColumn> reportColumns = table.getReportColumns();
		StringBuilder outputColumnString = new StringBuilder(
				table.getTableName());
		for (ReportColumn reportColumn : reportColumns) {
			outputColumnString.append(reportColumn.getColumnName());
			outputColumnString.append(", ");
		}
		setDefaultOutputColumnsString(getDefaultOutputColumnsString()
				+ outputColumnString.toString());
	}

	@Override
	void buildJoinString(ReportTable mainTable) {
		String joinType = mainTable.getJoinType().toString();
		ReportTableDao reportTableDao = new ReportTableDaoImpl();
		// find the subTables and connect with mainTable
		for (Long subReprotTableId : mainTable.getJoniReportTableId()) {
			ReportTable subTable = reportTableDao.findById(subReprotTableId);
			String joinString = getDefaultJoinString();
			if (joinString == "") {
				// build maincolumn here
				joinString = joinString + joinType + ""
						+ mainTable.getReportJoinColumn().getColumnName()
						+ " = "
						+ subTable.getReportJoinColumn().getColumnName();
			} else {
				joinString = joinString + " and " + joinType + ""
						+ mainTable.getReportJoinColumn().getColumnName()
						+ " = "
						+ subTable.getReportJoinColumn().getColumnName();
			}
			setDefaultJoinString(joinString);
		}
	}

	@Override
	void buildFilterString(MochaReport table) {
		// where clause
		StringBuilder filterString = new StringBuilder("");
		if (getDefaultFilterString().equals("where")) {
			setDefaultFilterString(getDefaultFilterString() + " ");
		}
	}

	public void saveAllReportTable() {
		if (appReport != null) {
			for (ReportTable reportTable : appReport.getReportTables()) {
				reportTableDao.persist(reportTable);
				// if (reportTable.getType() == "2") {
				// }
			}
		}
	}

	public AppReport getAppReport() {
		return appReport;
	}

	public void setAppReport(AppReport appReport) {
		this.appReport = appReport;
	}

}
