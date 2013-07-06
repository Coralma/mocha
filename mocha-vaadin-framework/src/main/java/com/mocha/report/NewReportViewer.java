package com.mocha.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.coral.foundation.jdbc.impl.DBToolUtil;
import com.coral.foundation.report.AbstrctAppRawData;
import com.coral.foundation.report.AppCusteomReportService;
import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.report.ReportConfiguration.ReportQueryFilterType;
import com.coral.foundation.report.ReportConfiguration.ReportType;
import com.coral.foundation.report.ReportModel;
import com.coral.foundation.report.ReportModelPool;
import com.coral.foundation.report.ReportQueryFilterCondition;
import com.coral.foundation.security.CommonWebSessionManager;
import com.coral.foundation.security.model.Account;
import com.coral.foundation.security.model.AppReport;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.common.collect.Lists;
import com.vaadin.data.Container.PropertySetChangeEvent;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import javax.persistence.*;


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