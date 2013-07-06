package com.mocha.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vaadin.teemu.wizards.event.WizardProgressListener;

import com.coral.foundation.report.ReportModel;
import com.coral.foundation.report.ReportQueryFilterCondition;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;
import com.google.common.collect.Lists;
import com.vaadin.Application;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstarctReportWizardStep extends ReportWizardStep {
	
	String caption;
	boolean advance = true;
	boolean back = true;
	protected WizardProgressListener listener;
	BasicUser basicUser;
	Component content;
	protected ReportTable defaultReportTable;
	
//	 Map<String, ReportTable> reportTables;
//	 ReportTable mainReportTable;
//	 List<ReportTable> relateReportTables = new ArrayList<ReportTable>();
//	protected List<ReportModel> finalReportModels = new ArrayList<ReportModel>();
//	ReportQueryFilterCondition queryFilterCondition= new ReportQueryFilterCondition();
	
//	private static ArrayList<ReportModel> userSelectReport=new ArrayList<ReportModel>();
	
	public AbstarctReportWizardStep(){
		super();
//		content=getContent();
	}
	
	public AbstarctReportWizardStep(String caption, Component content) {
		super(caption, content);
		buildlistener();
//		content=getContent();
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
				ReportModel reportModel = new ReportModel(reportTable.getTableName(),
						reportTable.getTableLabel(), reportColumnNames);
				reportModel.getReportTables().add(reportTable);
				rm.add(reportModel);
			}
			return Lists.newArrayList(rm);
		}
		return null;
	}
	
	
//	public static ThreadLocal<ReportModel> getUserSelectReport(){
//		return userSelectReport;			
//	}
//
//	public static void setUserSelectReport(ThreadLocal<ReportModel> userSelectReport) {
//		AbstarctReportWizardStep.userSelectReport = userSelectReport;
//	}

//	public static ArrayList<ReportModel> getUserSelectReport() {
//		if(getUserSelectReport()==null){
//			
//		}
//		return userSelectReport;
//	}
//
//	public static void setUserSelectReport(ArrayList<ReportModel> userSelectReport) {
//		AbstarctReportWizardStep.userSelectReport = userSelectReport;
//	}

	public WizardProgressListener getListener() {
		return listener;
	}

	public void setListener(WizardProgressListener listener) {
		this.listener = listener;
	}


	public ReportTable getDefaultReportTable() {
		return defaultReportTable;
	}

	public void setDefaultReportTable(ReportTable defaultReportTable) {
		this.defaultReportTable = defaultReportTable;
	}


	public class ReportColumnCard extends VerticalLayout implements LayoutClickListener {
		/**
		 * 
		 */
		private String name;
		private String desc;
		private String width = "150px";
		private static final long serialVersionUID = 1L;
		VerticalLayout veriLayout=new VerticalLayout();
		private ReportColumn reportColumn;
		private CheckBox checkBox=new CheckBox();
		
		ReportColumnCard(ReportColumn reportColumn){
			this.setReportColumn(reportColumn);
			this.name= reportColumn.getColumnLabel();
			this.addStyleName("custom-report-column-card");
			this.setWidth(width);
			this.setHeight("20px");
//			this.reportColumnButton.setCaption(this.name);
//			this.reportColumnButton.addStyleName("custom-report-column-card-button");
			this.addListener(this);
			this.getCheckBox().setCaption(this.name);
		}

		public void attach() {
			this.addComponent(getCheckBox());
		}
		
		@Override
		public void layoutClick(LayoutClickEvent event) {
			
		}


		public ReportColumn getReportColumn() {
			return reportColumn;
		}


		public void setReportColumn(ReportColumn reportColumn) {
			this.reportColumn = reportColumn;
		}

		public CheckBox getCheckBox() {
			return checkBox;
		}

		public void setCheckBox(CheckBox checkBox) {
			this.checkBox = checkBox;
		}

		
	}


}
