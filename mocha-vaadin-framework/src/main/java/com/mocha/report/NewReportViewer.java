package com.mocha.report;

import org.vaadin.teemu.wizards.Wizard;

import com.coral.foundation.report.AbstrctAppRawData;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportTable;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.vaadin.ui.VerticalLayout;


public class NewReportViewer extends CommonViewer implements Viewer {
	
	String fieldWidth = "300px";
	private Wizard wizard = new Wizard();
	private ReportWizardProgressListener listener;
	private BasicUser user;
	private AbstrctAppRawData appCustomReprotRowData;
	private ReportTable editAbleReportTable;
	private MainTableStep firstStep;
	
	public NewReportViewer(BasicUser user,AbstrctAppRawData appCustomReprotRowData) {
		this.setUser(user);
		this.appCustomReprotRowData=appCustomReprotRowData;
		this.firstStep=new MainTableStep(wizard,getUser());
	}
	
	public NewReportViewer(BasicUser user,AbstrctAppRawData appCustomReprotRowData,ReportTable editAbleReportTable) {
		this.setUser(user);
		this.appCustomReprotRowData=appCustomReprotRowData;
		this.editAbleReportTable=editAbleReportTable;
		this.firstStep=new MainTableStep(wizard,getUser(),editAbleReportTable);
	}
	
	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("760px");
		layout.addStyleName("app-new-report");
		wizard.setWidth("760px");
		setAppCustomReprotRowData(getAppCustomReprotRowData());

		wizard.getBackButton().setVisible(false);		
		wizard.addStep(firstStep,"Main Table Step");
		wizard.addStep(new PreviewStep(wizard,getUser()),"Preview Step");
		wizard.setImmediate(true);
		layout.addComponent(wizard);
		this.addComponent(layout);
	}

	@Override
	public String getViewerTitle() {
		return "New User Defined Report";
	}

	public ReportWizardProgressListener getListener() {
		return listener;
	}

	public void setListener(ReportWizardProgressListener listener) {
		this.wizard.addListener(listener);
//		this.listener = listener;
	}

	public BasicUser getUser() {
		return user;
	}

	public void setUser(BasicUser user) {
		this.user = user;
	}

	public AbstrctAppRawData getAppCustomReprotRowData() {
		return appCustomReprotRowData;
	}

	public void setAppCustomReprotRowData(AbstrctAppRawData appCustomReprotRowData) {
		this.appCustomReprotRowData = appCustomReprotRowData;
	}

	public ReportTable getEditAbleReportTable() {
		return editAbleReportTable;
	}

	public void setEditAbleReportTable(ReportTable editAbleReportTable) {
		this.editAbleReportTable = editAbleReportTable;
	}


}