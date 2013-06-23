package com.coral.foundation.report;

import com.coral.foundation.report.ReportConfiguration.ReportQueryFilterType;
import com.coral.foundation.security.model.ReportTable;

public class ReportQueryFilterCondition{
	
	private ReportQueryFilterType queryFilterType;
	private ReportTable reportTable;
	private String column;
	private String filterValue;
	private String queryStrings;
	
	public ReportQueryFilterCondition(){
		
	}

	public ReportQueryFilterType getQueryFilterType() {
		return queryFilterType;
	}

	public void setQueryFilterType(ReportQueryFilterType queryFilterType) {
		this.queryFilterType = queryFilterType;
	}

	public ReportTable getReportTable() {
		return reportTable;
	}

	public void setReportTable(ReportTable reportTable) {
		this.reportTable = reportTable;
	}
	
	public String getQueryStrings() {
		return queryStrings;
	}

	public void setQueryStrings(String queryStrings) {
		this.queryStrings = queryStrings;
	}
	
	public String buildQueryStrings(){
		if(getColumn()!=null&& reportTable!=null && queryFilterType!=null && filterValue!=null){
			queryStrings=reportTable.getTableName()+"."+getColumn()+" "+queryFilterType+" "+filterValue;
		}
		return queryStrings;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
}