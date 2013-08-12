package com.coral.foundation.report;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.MochaReportDao;
import com.coral.foundation.security.basic.dao.ReportTableDao;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.MochaReport;
import com.coral.foundation.security.model.ReportJoinTable;
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.spring.bean.SpringContextUtils;

public abstract class AbstractCustomReportService implements ReportService {

	private  String defaultQueryString = "Select";

	private  String defaultMaintableString = "";

	private  String defaultSubTableString = "";

	private  String defaultOutputColumnsString = "";

	private  String defaultJoinString = "";

	private  String defaultFilterString = "where";

	private  String defalutQueryFromString = "From";

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
			if (table.getType() == ReportConfiguration.ReportType.MainTable.toString()) {
				buildOutputColumnString(table);
				buildQueryFromString(table);
				buildJoinString(table);
				for(ReportJoinTable joinTable:table.getReportJoinReportTableId()){
					buildOutputColumnString(joinTable.getReportTable());
				}
			} else if (table.getType() == ReportConfiguration.ReportType.SubTable.toString()) {
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
	public ArrayList executeMochaReportQuery() {
		// String buildQueryString = getDefaultQueryString()
		// + getDefaultOutputColumnsString() + getDefaultJoinString()
		// + getDefaultFilterString();
		// mochaReport.setReportPureQuery(buildQueryString);

		ArrayList reportResult = null;
		if (getMochaReport().getReportPureQuery() != null) {
			reportResult = mochaReportDao.executeReport(getMochaReport());
		}
		
		return reportResult;

	}

	public String getDefaultQueryString() {
		return defaultQueryString;
	}

	public void setDefaultQueryString(String defaultQueryString) {
		this.defaultQueryString = defaultQueryString;
	}

	public String getDefaultMaintableString() {
		return defaultMaintableString;
	}

	public void setDefaultMaintableString(String defaultMaintableString) {
		this.defaultMaintableString = defaultMaintableString;
	}

	public String getDefaultSubTableString() {
		return defaultSubTableString;
	}

	public void setDefaultSubTableString(String defaultSubTableString) {
		this.defaultSubTableString = defaultSubTableString;
	}

	public String getDefaultOutputColumnsString() {
		return defaultOutputColumnsString;
	}

	public void setDefaultOutputColumnsString(String defaultOutputColumnsString) {
		this.defaultOutputColumnsString = defaultOutputColumnsString;
	}

	public String getDefaultJoinString() {
		return defaultJoinString;
	}

	public void setDefaultJoinString(String defaultJoinString) {
		this.defaultJoinString = defaultJoinString;
	}

	public String getDefaultFilterString() {
		return defaultFilterString;
	}

	public void setDefaultFilterString(String defaultFilterString) {
		this.defaultFilterString = defaultFilterString;
	}

	public String getDefalutQueryFromString() {
		return defalutQueryFromString;
	}

	public void setDefalutQueryFromString(String defalutQueryFromString) {
		this.defalutQueryFromString = defalutQueryFromString;
	}

	public MochaReport getMochaReport() {
		return mochaReport;
	}

	public void setMochaReport(MochaReport mochaReport) {
		this.mochaReport = mochaReport;
	}
	

}
