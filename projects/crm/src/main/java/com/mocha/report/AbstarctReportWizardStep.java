package com.mocha.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vaadin.teemu.wizards.event.WizardProgressListener;

import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;
import com.google.common.collect.Lists;
import com.vaadin.ui.Component;

public abstract class AbstarctReportWizardStep extends ReportWizardStep {
	
	String caption;
	Component content=getContent();
	boolean advance = true;
	boolean back = true;
	WizardProgressListener listener;
	
	static Map<String, ReportTable> reportTables;
	static ReportTable mainReportTable;
	static List<ReportTable> relateReportTables = new ArrayList<ReportTable>();
	protected static List<ReportModel> finalReportModels = new ArrayList<ReportModel>();
	ReportQueryFilterCondition queryFilterCondition= new ReportQueryFilterCondition();
	
	private static ThreadLocal<ReportModel> userSelectReport=new ThreadLocal<ReportModel>();
	
	public AbstarctReportWizardStep(){
		super();
	}
	
	public AbstarctReportWizardStep(String caption, Component content) {
		super(caption, content);
		buildlistener();
	}
	
	abstract void buildlistener();
	
	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public Component getContent() {
		return content;
	}

	@Override
	public boolean onAdvance() {
		return advance;
	}

	@Override
	public boolean onBack() {
		return advance;
	}
	
	
	protected List<ReportModel> getReportTableModels(List<ReportTable> reportTables) {
		ArrayList<ReportModel> rm = new ArrayList<ReportModel>();
		if (reportTables!=null) {
			for (ReportTable reportTable : reportTables) {
				ArrayList<ReportColumn> reportColumns = new ArrayList<ReportColumn>();
				String[] reportColumnNames = new String[reportTable.getReportColumns().size()];
				int i = 0;
				for (ReportColumn reportColumn : reportTable.getReportColumns()) {
					reportColumns.add(reportColumn);
					reportColumnNames[i] = reportColumn.getColumnName();
					i++;
				}
				ReportModel reportModel = new ReportModel(
						reportTable.getTableName(), reportColumnNames);
				reportModel.getReportTables().add(reportTable);
				rm.add(reportModel);
			}
			return Lists.newArrayList(rm);
		}
		return null;
	}
	public static ThreadLocal<ReportModel> getUserSelectReport() {
		return userSelectReport;
	}

	public static void setUserSelectReport(ThreadLocal<ReportModel> userSelectReport) {
		AbstarctReportWizardStep.userSelectReport = userSelectReport;
	}




}
