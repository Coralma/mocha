package com.mocha.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;
import com.coral.foundation.report.AppCusteomReportService;
import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.report.ReportConfiguration.ReportColumnType;
import com.coral.foundation.report.ReportModel;
import com.coral.foundation.report.ReportModelPool;
import com.coral.foundation.security.basic.dao.AppReportDao;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportJoinTable;
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.mocha.report.AbstarctReportWizardStep.ReportColumnCard;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;


public class PreviewStep extends AbstarctReportWizardStep {

	String fieldWidth = "300px";
	private ReportModel reportModel;
	private Map<String, ReportTable> reportTables;
	private Wizard w;
	private WizardStep nStep;
	public  AppReportDao appReportDao=SpringContextUtils.getBean(AppReportDao.class);
	private GridLayout mainTableLayout;
	private GridLayout subTablesLayout;
	private ReportModel rm;
	private VerticalLayout layout;
	private BasicUser user;
	
	public PreviewStep(Wizard w,BasicUser user) {
		this.setW(w);
		this.user=user;
		buildlistener();
	}

	@Override
	public String getCaption() {
		return "Preview Step";
	}

	@Override
	public Component getContent() {
		if(user!=null){
			
			return buildPreviewStep();
		}
		return new Label("");
	}

	private Component buildPreviewStep() {
		layout= new VerticalLayout();
		rm = ReportModelPool.findReportModelByCurrentUser(user);
		AppReport appReport=new AppReport();
		if(rm!=null && rm.getAppReport()==null){
			rm.setAppReport(appReport);
		}
		mainTableLayout=new GridLayout(3,1);
		subTablesLayout=new GridLayout(3,1);
		Label stepCaption=new Label("Report Review");
		stepCaption.addStyleName("custom-report-step-column-caption");
		getLayout().addComponent(stepCaption);
		
		
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		getLayout().addComponent(formLayout);

		final TextField reportName = new TextField("Report Name");
		reportName.addListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(rm.getAppReport()!=null){
					rm.getAppReport().setName(reportName.getValue().toString());
				}
			}
		});
		reportName.setWidth(fieldWidth);
		formLayout.addComponent(reportName);
		
		final TextField reportDesc = new TextField("Report Descritpion");
		reportDesc.setWidth("300px");
		reportDesc.addListener(new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(rm.getAppReport()!=null){
				rm.getAppReport().setDescription(reportDesc.getValue().toString());
				}
			}
		});
		reportDesc.setWidth(fieldWidth);
		formLayout.addComponent(reportDesc);
		
		if (rm!=null && rm.getReportTables() != null) {
			// build query tables
			for (ReportTable reportTable : rm.getReportTables()) {
				if (reportTable.getType().toString().equals(ReportConfiguration.ReportType.MainTable.toString())) {
					buildTableInfo(reportTable);
				}
				if (reportTable.getType().toString().equals(ReportConfiguration.ReportType.SubTable.toString())) {
					buildTableInfo(reportTable);
				}
			}
//			Label queryConditions = new Label("Filter and Conditions");
//			layout.addComponent(queryConditions);

			// build query filter conditions
			if (rm.getReportQueryFilterCondition()!=null && rm.getReportQueryFilterCondition().size() > 0) {
				for (com.coral.foundation.report.ReportQueryFilterCondition conditions : rm.getReportQueryFilterCondition()) {
					Label reportQueryCondition = new Label(conditions.buildQueryStrings());
					getLayout().addComponent(reportQueryCondition);
				}
			}
			//build query order sequnces
			// TBD
		}
		return getLayout();
	}

	private void buildTableInfo(ReportTable reportTable) {		
		HashSet<ReportColumn> outputReportColumns=null;
		mainTableLayout.addStyleName("custom-report-step-caption");
		subTablesLayout.addStyleName("custom-report-step-caption");
		
		//main table layout
		if(reportTable.getType().equals(ReportConfiguration.ReportType.MainTable.toString()) && rm.getMainTableSelectedColumns().size()>0){
			Label mainTableLabelName=new Label("Main Table");
			mainTableLabelName.setStyleName("custom-report-step-column-caption");
			getLayout().addComponent(mainTableLabelName);
			
			Label mainTableName=new Label(reportTable.getTableLabel());
			getLayout().addComponent(mainTableName);
			
			outputReportColumns=rm.getMainTableSelectedColumns();
			mainTableLayout.removeAllComponents();
			mainTableLayout.setSpacing(true);
			mainTableLayout.requestRepaintAll();
			mainTableLayout.setRows(outputReportColumns.size());
			for (final ReportColumn columnField : outputReportColumns) {
				if(columnField.getColumnLabel()!=null){
				ReportColumnCard reportColumnCard=new ReportColumnCard(columnField);
				mainTableLayout.addComponent(reportColumnCard);
				}
			}
			getLayout().addComponent(mainTableLayout);
		}
		
		//sub table layout
		if(reportTable.getType().equals(ReportConfiguration.ReportType.SubTable.toString()) && rm.getSubTableSelectedColumns().size()>0){
			Label subTableLabelName=new Label("Related Table");
			subTableLabelName.setStyleName("custom-report-step-column-caption");
			getLayout().addComponent(subTableLabelName);
			
			Label subTableName=new Label(reportTable.getTableLabel());
			getLayout().addComponent(subTableName);
			
			outputReportColumns=rm.getSubTableSelectedColumns();
			subTablesLayout.removeAllComponents();
			subTablesLayout.setSpacing(true);
			subTablesLayout.requestRepaintAll();
			subTablesLayout.setRows(outputReportColumns.size());
			for (final ReportColumn columnField : outputReportColumns) {
				if(columnField.getColumnLabel()!=null){
					ReportColumnCard reportColumnCard=new ReportColumnCard(columnField);
					subTablesLayout.addComponent(reportColumnCard);					
				}
			}
			getLayout().addComponent(subTablesLayout);
		}
		
	}

	@Override
	void buildlistener() {
		
	}

	public Wizard getW() {
		return w;
	}

	public void setW(Wizard w) {
		this.w = w;
	}

	public VerticalLayout getLayout() {
		return layout;
	}

	public void setLayout(VerticalLayout layout) {
		this.layout = layout;
	}

}
