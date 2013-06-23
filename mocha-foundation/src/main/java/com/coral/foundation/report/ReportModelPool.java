package com.coral.foundation.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;

public class ReportModelPool {
	
	private static  ConcurrentHashMap<String,ReportModel> userSelectReport=new ConcurrentHashMap<String,ReportModel>() ;
	
	private static BasicUser basicUser;
	
	private ReportModel reportModel;
	
	public ReportModelPool(BasicUser basicUser,ReportModel reportModel){
		this.setBasicUser(basicUser);
		initUserSessionReportModel(basicUser,reportModel);
	}

	private static void initUserSessionReportModel(BasicUser basicUser,ReportModel reportModel) {
		userSelectReport.put(basicUser.getUserName(),reportModel);
	}

	public static ConcurrentHashMap<String,ReportModel> getUserSelectReport() {
		if(!userSelectReport.containsKey(getBasicUser().getUserName())){
			initUserSessionReportModel(getBasicUser(),new ReportModel("", ""));
		}
		return userSelectReport;
	}
	
	public static void putUserReportModel(BasicUser basicUser,ReportModel reportModel){
//			findReportModelByCurrentUser(basicUser);
			userSelectReport.put(basicUser.getUserName(),reportModel);
	}
	
	
	public static ReportModel findReportModelByCurrentUser(BasicUser baiscUser){
		if(getUserSelectReport().containsKey(baiscUser.getUserName())){
			System.out.println("current reportModel has value: "+ReportModelPool.getUserSelectReport().get(baiscUser.getUserName()).getReportTables().size());
			return ReportModelPool.getUserSelectReport().get(baiscUser.getUserName());
		}
		return null;
		
	}

	public static void setUserSelectReport(ConcurrentHashMap<String,ReportModel> userSelectReport) {
		ReportModelPool.userSelectReport = userSelectReport;
	}

	public ReportModel getReportModel() {
		return reportModel;
	}

	public void setReportModel(ReportModel reportModel) {
		this.reportModel = reportModel;
	}

	public static BasicUser getBasicUser() {
		return basicUser;
	}

	public static void setBasicUser(BasicUser basicUser) {
		ReportModelPool.basicUser = basicUser;
	}

	


}