package com.coral.foundation.report;

import java.util.List;

import org.apache.bcel.generic.RET;
import org.hibernate.exception.ConstraintViolationException;

import com.coral.foundation.security.basic.dao.ReportJoinTableDao;
import com.coral.foundation.security.basic.dao.ReportTableDao;
import com.coral.foundation.security.basic.dao.impl.ReportTableDaoImpl;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.MochaReport;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportJoinTable;
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.spring.bean.SpringContextUtils;

/*
 * Here is one table sturure
 * 
 * Main Table
 * 
 * 
 * */

public class AppCusteomReportService extends AbstractCustomReportService {

	private ReportTableDao reportTableDao = SpringContextUtils
			.getBean(ReportTableDao.class);

	private ReportJoinTableDao reportJoinTableDao = SpringContextUtils
			.getBean(ReportJoinTableDao.class);

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

		StringBuilder dynamicQuery = new StringBuilder();
		dynamicQuery.append(getDefaultQueryString());
		dynamicQuery.append(" ");
		dynamicQuery.append(getDefaultOutputColumnsString().substring(0,
				getDefaultOutputColumnsString().length() - 2));
		dynamicQuery.append(" ");
		dynamicQuery.append(getDefalutQueryFromString());
		dynamicQuery.append(" ");
		dynamicQuery.append(getDefaultJoinString());
		dynamicQuery.append(" ");
		dynamicQuery.append(appReport.getReportFilters().size()==0
				? ""
				: getDefaultFilterString());
		dynamicQuery.append(" ");
		mochaReport.setReportPureQuery(dynamicQuery.toString());
		mochaReportDao.merge(mochaReport);
		mochaReportDao.executeReport(mochaReport);

		return null;
	}

	@Override
	void buildOutputColumnString(ReportTable table) {
		List<ReportColumn> reportColumns = table.getReportColumns();
		StringBuilder outputColumnString = new StringBuilder();
		for (ReportColumn reportColumn : reportColumns) {
			outputColumnString.append(table.getTableName() + ".");
			outputColumnString.append(reportColumn.getColumnName());
			outputColumnString.append(", ");
		}
		setDefaultOutputColumnsString(getDefaultOutputColumnsString()
				+ outputColumnString.toString());
	}

	@Override
	void buildJoinString(ReportTable mainTable) {
		String joinType = mainTable.getJoinType().toString();
		// find the subTables and connect with mainTable
		for (ReportJoinTable referenceTable : mainTable
				.getReportJoinReportTableId()) {

			// get all the reference join tables;

			ReportTable subTable = referenceTable.getReportTable();
			String joinString = getDefaultJoinString();
			String mainTableJoinColumn = "";
			String subTableJoinColumn = "";

			List<ReportColumn> mainReportColums = mainTable.getReportColumns();

			for (ReportColumn reportColumn : mainReportColums) {
				if (reportColumn.getColumnUseMode() == "2") {
					mainTableJoinColumn = reportColumn.getColumnName();
					break;
				}
			}

			List<ReportColumn> subReportColums = subTable.getReportColumns();

			for (ReportColumn reportColumn : subReportColums) {
				if (reportColumn.getColumnUseMode() == "2") {
					subTableJoinColumn = reportColumn.getColumnName();
					break;
				}
			}
			if (joinString == "") {
				// build maincolumn here

				joinString = joinString
						+ joinType
						+ " "
						// + mainTable.getTableName() + "." +
						// mainTableJoinColumn
						+ subTable.getTableName() + " " + "on" + " "
						+ mainTable.getTableName() + "." + mainTableJoinColumn
						+ " = " + subTable.getTableName() + "."
						+ subTableJoinColumn;
			} else {
				joinString = joinString + " and " + joinType + ""
						+ mainTable.getTableName() + "." + mainTableJoinColumn
						+ " = " + subTable.getTableName() + "."
						+ subTableJoinColumn;
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

	public void saveReferenceJoinTables() {
		if (appReport != null) {
			for (ReportTable reportTable : appReport.getReportTables()) {
				if (reportTable.getType() == "2") {
					reportTableDao.merge(reportTable);
				}
			}
		}
	}

	public void saveMainReportTable() {
		if (appReport != null) {
			for (ReportTable reportTable : appReport.getReportTables()) {
				if (reportTable.getType() == "1") {
					reportTableDao.merge(reportTable);
				}
			}
		}
	}

	public AppReport getAppReport() {
		return appReport;
	}

	public void setAppReport(AppReport appReport) {
		this.appReport = appReport;
	}

	@Override
	void buildQueryFromString(ReportTable reportTable) {
		setDefalutQueryFromString(getDefalutQueryFromString() + " "
				+ reportTable.getTableName());

	}

}
