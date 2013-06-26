package com.coral.foundation.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.coral.foundation.report.ReportConfiguration.QueryOrder;
import com.coral.foundation.report.ReportConfiguration.ReportQueryFilterType;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;
import com.google.common.collect.Lists;

public class ReportModel {

	public String tableName;
	private String tableLabel;
	public List<String> columnFields = Lists.newArrayList();
	private HashSet<ReportTable> reportTables=new HashSet<ReportTable>();
	private List<ReportQueryFilterCondition> reportQueryFilterCondition=new ArrayList<ReportQueryFilterCondition>();
	private List<ReportQueryOrders> orderConditions=new ArrayList<ReportQueryOrders>();
	private HashSet<ReportColumn> mainTableSelectedColumns=new HashSet<ReportColumn>();
	private HashSet<ReportColumn> subTableSelectedColumns=new HashSet<ReportColumn>();
	private AppReport appReport=new AppReport();
	private static AbstrctAppRawData appRawRata;
	
	private StringBuilder reportQueryJoinStrings=new StringBuilder();
	
	
	public ReportModel(String tableName,String tableLabel, String... fields){
		this.tableName = tableName;
		this.tableLabel=tableLabel;
		for(String f : fields) {
			columnFields.add(f);
		}
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the columnFields
	 */
	public List<String> getColumnFields() {
		return columnFields;
	}
	/**
	 * @param columnFields the columnFields to set
	 */
	public void setColumnFields(List<String> columnFields) {
		this.columnFields = columnFields;
	}
	
	public List<ReportQueryOrders> getOrderConditions() {
		return orderConditions;
	}
	public void setOrderConditions(List<ReportQueryOrders> orderConditions) {
		this.orderConditions = orderConditions;
	}


	public List<ReportQueryFilterCondition> getReportQueryFilterCondition() {
		return reportQueryFilterCondition;
	}
	public void setReportQueryFilterCondition(
			List<ReportQueryFilterCondition> reportQueryFilterCondition) {
		this.reportQueryFilterCondition = reportQueryFilterCondition;
	}

	public HashSet<ReportTable> getReportTables() {
		return reportTables;
	}
	public void setReportTables(HashSet<ReportTable> reportTables) {
		this.reportTables = reportTables;
	}


	public AppReport getAppReport() {
		return appReport;
	}
	public void setAppReport(AppReport appReport) {
		this.appReport = appReport;
	}


	public HashSet<ReportColumn> getMainTableSelectedColumns() {
		return mainTableSelectedColumns;
	}
	public void setMainTableSelectedColumns(HashSet<ReportColumn> mainTableSelectedColumns) {
		this.mainTableSelectedColumns = mainTableSelectedColumns;
	}


	public HashSet<ReportColumn> getSubTableSelectedColumns() {
		return subTableSelectedColumns;
	}
	public void setSubTableSelectedColumns(HashSet<ReportColumn> subTableSelectedColumns) {
		this.subTableSelectedColumns = subTableSelectedColumns;
	}


	public String getTableLabel() {
		return tableLabel;
	}
	public void setTableLabel(String tableLabel) {
		this.tableLabel = tableLabel;
	}

	public AbstrctAppRawData getAppRawRata() {
		return appRawRata;
	}
	public void setAppRawRata(AbstrctAppRawData appRawRata) {
		this.appRawRata = appRawRata;
	}

	class ReportQueryOrders{
		
		private QueryOrder queryOrder;
		
		private ReportTable reportTable;
		
		public ReportQueryOrders(){
			
		}

		public QueryOrder getQueryOrder() {
			return queryOrder;
		}

		public void setQueryOrder(QueryOrder queryOrder) {
			this.queryOrder = queryOrder;
		}

		public ReportTable getReportTable() {
			return reportTable;
		}

		public void setReportTable(ReportTable reportTable) {
			this.reportTable = reportTable;
		}
		
	}


}
