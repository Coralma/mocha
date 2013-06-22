package com.mocha.report;

import java.util.ArrayList;

public class ReportModelPool {
	
	private static  ThreadLocal<ReportModel> userSelectReport=new ThreadLocal<ReportModel>();
	
	public ReportModelPool(){
		
	}

	public static ThreadLocal<ReportModel> getUserSelectReport() {
		return userSelectReport;
	}

	public static void setUserSelectReport(ThreadLocal<ReportModel> userSelectReport) {
		ReportModelPool.userSelectReport = userSelectReport;
	}

}