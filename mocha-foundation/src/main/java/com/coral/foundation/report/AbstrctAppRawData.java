package com.coral.foundation.report;

import java.util.List;

import com.coral.foundation.security.model.ReportTable;

public abstract class AbstrctAppRawData {
	private String appName="";
	
	public AbstrctAppRawData(String appName){
		this.appName=appName;
	}
	
	public abstract  List<ReportTable> getReportTables();

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
