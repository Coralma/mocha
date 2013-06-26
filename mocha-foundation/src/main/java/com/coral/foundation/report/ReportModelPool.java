package com.coral.foundation.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;

public class ReportModelPool {
	
	private static  ConcurrentHashMap<String,ReportModel> userSelectReport=new ConcurrentHashMap<String,ReportModel>() ;
	private BasicUser basicUser;
	
	private ReportModel reportModel;
	
	public ReportModelPool(BasicUser basicUser,ReportModel reportModel){
		setBasicUser(basicUser);
		initUserSessionReportModel(basicUser,reportModel);
	}
		
	public static ReportModelPool initInstance(BasicUser bUser,ReportModel rm){
		if(findReportModelByCurrentUser(bUser)==null){
			return new ReportModelPool(bUser,rm);
		}
		return null;
	}

	private static void initUserSessionReportModel(BasicUser basicUser,ReportModel reportModel) {
		userSelectReport.put(basicUser.getUserName(),reportModel);
	}

	private static ConcurrentHashMap<String,ReportModel> getUserSelectReport(BasicUser user) {
		if(!userSelectReport.containsKey(user.getUserName())){
//			initUserSessionReportModel(user,new ReportModel("", ""));
			return null;
		}
		return userSelectReport;
	}
	
	public static void putUserReportModel(BasicUser basicUser,ReportModel reportModel){
//			findReportModelByCurrentUser(basicUser);
			userSelectReport.put(basicUser.getUserName(),reportModel);
	}
	
	
	public static ReportModel findReportModelByCurrentUser(BasicUser baiscUser){
		
		if(getUserSelectReport(baiscUser)==null){
			return null;
		}
		
		if(getUserSelectReport(baiscUser).containsKey(baiscUser.getUserName())){
//			System.out.println("current reportModel has value: "+ReportModelPool.getUserSelectReport().get(baiscUser.getUserName()).getAppRawRata().getReportTables().size());
			ReportModel rm=ReportModelPool.getUserSelectReport(baiscUser).get(baiscUser.getUserName()); 
			return rm;
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
	public static void init(BasicUser user, ReportModel reportModel) {
		initUserSessionReportModel(user,reportModel);
	}

	public BasicUser getBasicUser() {
		return basicUser;
	}

	public void setBasicUser(BasicUser basicUser) {
		this.basicUser = basicUser;
	}

	public static void clearReportModelByUser(BasicUser user) {
		if(getUserSelectReport(user)!=null){
			ReportModel oldRm=getUserSelectReport(user).get(user.getUserName());			
			getUserSelectReport(user).remove(user.getUserName());
			ReportModel newRm=new ReportModel("","");
			newRm.setAppRawRata(oldRm.getAppRawRata());
			putUserReportModel(user, newRm);
		}
	}

	


}