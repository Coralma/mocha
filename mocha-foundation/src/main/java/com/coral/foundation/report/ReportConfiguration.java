package com.coral.foundation.report;

public class ReportConfiguration {
	
	
	
	public  enum ReportColumnType {
		OutputColumn("1"), JoinColumn("2"),ForeignKeyRefernceColumn("3");

		private String columnType;

		ReportColumnType(String columnType) {
			this.columnType = columnType;
		}

		public String getColumnType() {
			return columnType;
		}

		public void setColumnType(String columnType) {
			this.columnType = columnType;
		}
	}

	public enum ReportType {
		MainTable("1"), SubTable("1");

		private String tableType;

		ReportType(String tableType) {
			this.tableType = tableType;
		}

		public String getTableType() {
			return tableType;
		}

		public void setTableType(String tableType) {
			this.tableType = tableType;
		}
	}
	
	public enum ReportQueryFilterType{
		Larger(">"),Equal("="),Smaller("<"),LargerOrEqual(">="),SmallerOrEqual("<=");
		
		private String queryFilterType;
		
		ReportQueryFilterType(String queryFilterType){
			this.setQueryFilterType(queryFilterType);
		}

		public String getQueryFilterType() {
			return queryFilterType;
		}

		public void setQueryFilterType(String queryFilterType) {
			this.queryFilterType = queryFilterType;
		}	
	}
	
	public enum QueryOrder{
		Asc("asc"),Desc("desc");
		
		private String queryOrderSequnce;
		
		QueryOrder(String queryOrderSequnce){
			this.setQueryOrderSequnce(queryOrderSequnce);
		}

		public String getQueryOrderSequnce() {
			return queryOrderSequnce;
		}

		public void setQueryOrderSequnce(String queryOrderSequnce) {
			this.queryOrderSequnce = queryOrderSequnce;
		}
	}

}
