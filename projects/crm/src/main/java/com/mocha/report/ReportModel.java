package com.mocha.report;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.report.ReportConfiguration.QueryOrder;
import com.coral.foundation.report.ReportConfiguration.ReportQueryFilterType;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;
import com.google.common.collect.Lists;

public class ReportModel {

	public String tableName;
	public List<String> columnFields = Lists.newArrayList();
	private List<ReportTable> reportTables=new ArrayList<ReportTable>();
	private List<ReportQueryFilterCondition> reportQueryFilterCondition=new ArrayList<ReportQueryFilterCondition>();
	private List<ReportQueryOrders> orderConditions=new ArrayList<ReportQueryOrders>();
	private List<ReportColumn> selectedColumns=new ArrayList<ReportColumn>();
	
	public ReportModel(String tableName, String... fields){
		this.tableName = tableName;
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
	
	
	public List<ReportTable> getReportTables() {
		return reportTables;
	}
	public void setReportTables(List<ReportTable> reportTables) {
		this.reportTables = reportTables;
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


	public List<ReportColumn> getSelectedColumns() {
		return selectedColumns;
	}
	public void setSelectedColumns(List<ReportColumn> selectedColumns) {
		this.selectedColumns = selectedColumns;
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
