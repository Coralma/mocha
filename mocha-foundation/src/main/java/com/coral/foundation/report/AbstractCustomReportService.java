package com.coral.foundation.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.MochaReportDao;
import com.coral.foundation.security.basic.dao.ReportTableDao;
import com.coral.foundation.security.basic.dao.impl.ReportTableDaoImpl;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.MochaReport;
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.spring.bean.SpringContextUtils;

public abstract class AbstractCustomReportService implements ReportService {

	private static String defaultQueryString = "Select";

	private static String defaultMaintableString = "";

	private static String defaultSubTableString = "";

	private static String defaultOutputColumnsString = "";

	private static String defaultJoinString = "";

	private static String defaultFilterString = "where";
	
	private static String defalutQueryFromString="From";

	public MochaReportDao mochaReportDao = SpringContextUtils
			.getBean(MochaReportDao.class);

	public ReportTableDao reportTableDao = SpringContextUtils
			.getBean(ReportTableDao.class);

	private MochaReport mochaReport;

	public AbstractCustomReportService() {

	}

	@Override
	public void validateReportTemplate(MochaReport mochaReport) {
		AppReport appReport = mochaReport.getAppReport();
		// first get the related tables first
		List<ReportTable> reportTables = appReport.getReportTables();

		for (ReportTable table : reportTables) {
			if (table.getType() == "1") {
				buildOutputColumnString(table);
				buildQueryFromString(table);
				buildJoinString(table);
			} else if (table.getType() == "2") {
				buildOutputColumnString(table);
			}
		}
		buildFilterString(mochaReport);
	}

	abstract void buildOutputColumnString(ReportTable table);
	abstract void buildQueryFromString(ReportTable table);
	abstract void buildJoinString(ReportTable table);
	abstract void buildFilterString(MochaReport table);

	@Override
	public void loadReportTemplate(MochaReport mochaReport) {

	}

	@Override
	public Collection executeMochaReportQuery() {
		String buildQueryString = getDefaultQueryString()
				+ getDefaultOutputColumnsString() + getDefaultJoinString()
				+ getDefaultFilterString();
		mochaReport.setReportPureQuery(buildQueryString);
		Collection reportResult = mochaReportDao.executeReport(mochaReport);
		return reportResult;

	}
	public static String getDefaultQueryString() {
		return defaultQueryString;
	}

	public static void setDefaultQueryString(String defaultQueryString) {
		AbstractCustomReportService.defaultQueryString = defaultQueryString;
	}

	public static String getDefaultMaintableString() {
		return defaultMaintableString;
	}

	public static void setDefaultMaintableString(String defaultMaintableString) {
		AbstractCustomReportService.defaultMaintableString = defaultMaintableString;
	}

	public static String getDefaultSubTableString() {
		return defaultSubTableString;
	}

	public static void setDefaultSubTableString(String defaultSubTableString) {
		AbstractCustomReportService.defaultSubTableString = defaultSubTableString;
	}

	public static String getDefaultOutputColumnsString() {
		return defaultOutputColumnsString;
	}

	public static void setDefaultOutputColumnsString(
			String defaultOutputColumnsString) {
		AbstractCustomReportService.defaultOutputColumnsString = defaultOutputColumnsString;
	}

	public static String getDefaultJoinString() {
		return defaultJoinString;
	}

	public static void setDefaultJoinString(String defaultJoinString) {
		AbstractCustomReportService.defaultJoinString = defaultJoinString;
	}

	public static String getDefaultFilterString() {
		return defaultFilterString;
	}

	public static void setDefaultFilterString(String defaultFilterString) {
		AbstractCustomReportService.defaultFilterString = defaultFilterString;
	}

	public MochaReport getMochaReport() {
		return mochaReport;
	}

	public void setMochaReport(MochaReport mochaReport) {
		this.mochaReport = mochaReport;
	}

	public static String getDefalutQueryFromString() {
		return defalutQueryFromString;
	}

	public static void setDefalutQueryFromString(String defalutQueryFromString) {
		AbstractCustomReportService.defalutQueryFromString = defalutQueryFromString;
	}

}
