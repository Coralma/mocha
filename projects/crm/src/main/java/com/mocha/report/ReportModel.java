package com.mocha.report;

import java.util.List;

import com.google.common.collect.Lists;

public class ReportModel {

	public String tableName;
	public List<String> columnFields = Lists.newArrayList();
	
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

}
