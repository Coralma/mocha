package com.coral.foundation.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.bcel.generic.RET;
import org.hibernate.exception.ConstraintViolationException;

import com.coral.foundation.security.basic.dao.ReportJoinTableDao;
import com.coral.foundation.security.basic.dao.ReportTableDao;
import com.coral.foundation.security.basic.dao.impl.ReportTableDaoImpl;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.MochaReport;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportFilter;
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
	private MochaReport mochaReport = new MochaReport();
	private BasicUser creator;
	
	private static ArrayList<String> duplicateOuputColumns=new ArrayList<String>();

	public AppCusteomReportService(AppReport appRepor,BasicUser creator) {
		super();
		setAppReport(appReport);
		this.creator=creator;
	}
	
	
	public AppCusteomReportService(AppReport appRepor) {
		super();
		setAppReport(appReport);
	}

	@Override
	public String buildReport() {
		mochaReport.setAppReport(getAppReport());
		mochaReport.setReportName(getAppReport().getName());
		if(creator!=null){
			mochaReport.setCreator(creator);			
		}
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
		dynamicQuery.append(appReport.getReportFilters().size() == 0 ? "": getDefaultFilterString());
		dynamicQuery.append(" ");
		mochaReport.setReportPureQuery(dynamicQuery.toString());
		System.out.println("dynamicQuery is: "+dynamicQuery.toString());
		mochaReportDao.merge(mochaReport);
//		Collection reportResult = mochaReportDao.executeReport(mochaReport);
		return null;
	}

	@Override
	void buildOutputColumnString(ReportTable table) {
		List<ReportColumn> reportColumns = table.getReportColumns();
		StringBuilder outputColumnString = new StringBuilder();
		for (ReportColumn reportColumn : reportColumns) {
			if (reportColumn.getColumnUseMode().equals(
					ReportConfiguration.ReportColumnType.OutputColumn.toString())) {
				if(!duplicateOuputColumns.contains(table.getTableName()+"."+reportColumn.getColumnName())){
				duplicateOuputColumns.add(table.getTableName()+"."+reportColumn.getColumnName());
				outputColumnString.append(table.getTableName() + ".");
				outputColumnString.append(reportColumn.getColumnName());
				outputColumnString.append(" ");
				outputColumnString.append("'"+reportColumn.getColumnLabel()+"'");
				outputColumnString.append(", ");
				}
			}
		}
		setDefaultOutputColumnsString(getDefaultOutputColumnsString()
				+ outputColumnString.toString());
	}

	@Override
	void buildJoinString(ReportTable mainTable) {
		String aliname="t";
		int i=0;
		String joinType = mainTable.getJoinType().toString();
		
		ArrayList<String> subDuplicateTables=new ArrayList<String>();
		
		// support one main join with multi sub join tables
		// e.g from Table A Inner Join B on A.id=B.id
		//     Inner Join B on A.name=B.name
		//     Inner Join C on A.value=C.value
		
		// find the subTables and connect with mainTable
		for (ReportJoinTable referenceTable : mainTable.getReportJoinReportTableId()) {
			
			// get all the reference join tables;

			ReportTable subTable = referenceTable.getReportTable();
			if(!subDuplicateTables.contains(subTable.getTableName())){
			subDuplicateTables.add(subTable.getTableName());
			String joinString = getDefaultJoinString();
			String mainTableJoinColumn = "";
			String subTableJoinColumn = "";

			List<ReportColumn> mainReportColums = mainTable.getReportColumns();

			for (ReportColumn reportColumn : mainReportColums) {
				if (reportColumn.getColumnUseMode() == ReportConfiguration.ReportColumnType.JoinColumn.toString()) {
					mainTableJoinColumn = reportColumn.getColumnName();
					break;
				}
			}
			List<ReportColumn> subReportColums = subTable.getReportColumns();

			for (ReportColumn reportColumn : subReportColums) {
				if (reportColumn.getColumnUseMode() == ReportConfiguration.ReportColumnType.JoinColumn.toString()) {
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
				if(i>0){
					subTable.setTableName(subTable.getTableName()+" "+aliname+i);
				}
				joinString = joinString + " " + joinType + " "+subTable.getTableName()+" "+" on "
						+ mainTable.getTableName() + "." + mainTableJoinColumn
						+ " = " + aliname+i+ "."+ subTableJoinColumn;
			}
			setDefaultJoinString(joinString);
			i++;
		}
		}
	}

	@Override
	void buildFilterString(MochaReport table) {
		// where clause
		if(table.getAppReport().getReportFilters().size()>0){
			ReportFilter rf=table.getAppReport().getReportFilters().get(0);
			setDefaultFilterString("where "+rf.getFilterBuildString());
		}
		StringBuilder filterString = new StringBuilder("");
		if (getDefaultFilterString().equals("where")) {
			setDefaultFilterString(getDefaultFilterString() + " ");
		}
	}

	public void saveReferenceJoinTables() {
		if (appReport != null) {
			for (ReportTable reportTable : appReport.getReportTables()) {
				if (reportTable.getType() == ReportConfiguration.ReportType.SubTable.toString()) {
					reportTableDao.merge(reportTable);
				}
			}
		}
	}

	public void saveMainReportTable() {
		if (appReport != null) {
			for (ReportTable reportTable : appReport.getReportTables()) {
				if (reportTable.getType() ==ReportConfiguration.ReportType.MainTable.toString()) {
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

	public BasicUser getCreator() {
		return creator;
	}

	public void setCreator(BasicUser creator) {
		this.creator = creator;
	}

}
