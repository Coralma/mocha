package com.mocha.report;

import java.util.ArrayList;
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
import com.coral.foundation.security.basic.dao.AppReportDao;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportJoinTable;
import com.coral.foundation.security.model.ReportTable;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;


public class PreviewStep extends AbstarctReportWizardStep {

	String fieldWidth = "300px";
	private ReportModel reportModel;
	private Map<String, ReportTable> reportTables;
	private Wizard w;
	private WizardStep nStep;
	public  AppReportDao appReportDao=SpringContextUtils.getBean(AppReportDao.class);

	public PreviewStep(Wizard w) {
		this.setW(w);
		buildlistener();
	}

	@Override
	public String getCaption() {
		return "Preview Step";
	}

	@Override
	public Component getContent() {
		return buildPreviewStep();
	}

	private Component buildPreviewStep() {
		final ReportModel rm = AbstarctReportWizardStep.getUserSelectReport().get();
		AppReport appReport=new AppReport();
		if(rm!=null && rm.getAppReport()==null){
			rm.setAppReport(appReport);
		}
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(new Label("Report Review"));
		
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		layout.addComponent(formLayout);

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
		
		final TextField reportDesc = new TextField("Report Name");
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

				Label mainTableLabel = new Label("MainTable");
				layout.addComponent(mainTableLabel);

				if (reportTable
						.getType()
						.toString()
						.equals(ReportConfiguration.ReportType.MainTable
								.toString())) {

					Label mainTable = new Label(reportTable.getTableName());
					layout.addComponent(mainTable);
				}

				Label subTableLabel = new Label("SubTable");
				layout.addComponent(subTableLabel);

				if (reportTable
						.getType()
						.toString()
						.equals(ReportConfiguration.ReportType.SubTable
								.toString())) {

					Label subTable = new Label(reportTable.getTableName());
					layout.addComponent(subTable);
				}

			}
			Label queryConditions = new Label("Query Conditions");
			layout.addComponent(queryConditions);

			// build query filter conditions
			if (rm.getReportQueryFilterCondition()!=null && rm.getReportQueryFilterCondition().size() > 0) {
				for (ReportQueryFilterCondition conditions : rm.getReportQueryFilterCondition()) {
					Label reportQueryCondition = new Label(conditions.buildQueryStrings());
					layout.addComponent(reportQueryCondition);
				}
			}

			// build query order sequnces
			// TBD
		}
		return layout;
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

}
