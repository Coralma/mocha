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
import com.coral.foundation.report.AppCusteomReportService;
import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.report.ReportConfiguration.ReportQueryFilterType;
import com.coral.foundation.report.ReportConfiguration.ReportType;
import com.coral.foundation.security.model.Account;
import com.coral.foundation.security.model.AppReport;
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

	static Map<String, ReportTable> reportTables;
	static ReportTable mainReportTable;
	static List<ReportTable> relateReportTables = new ArrayList<ReportTable>();
	private List<ReportModel> finalReportModels = new ArrayList<ReportModel>();
	final ReportQueryFilterCondition queryFilterCondition= new ReportQueryFilterCondition();


	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("760px");
		layout.addStyleName("app-new-report");
		wizard.setWidth("760px");

		ReportWizardStep mainTableStep = new ReportWizardStep("Main Table",
				buildMainTableStep());
		ReportWizardStep relatedTableStep = new ReportWizardStep(
				"Related Table", buildRelatedTableStep());
		ReportWizardStep reportQueryFilterLayout = new ReportWizardStep("Report Filters",
				buildReportQueryFilterStep());
		final ReportWizardStep preview = new ReportWizardStep("Preview",
				buildPreviewStep());
		preview.advance=true;
		
		wizard.addStep(mainTableStep);
		wizard.addStep(relatedTableStep);
		wizard.addStep(reportQueryFilterLayout);
		wizard.addStep(preview);
		wizard.setImmediate(true);
		
		wizard.addListener(new WizardProgressListener(){

			@Override
			public void activeStepChanged(WizardStepActivationEvent event) {
				
			}

			@Override
			public void stepSetChanged(WizardStepSetChangedEvent event) {
				
			}

			@Override
			public void wizardCompleted(WizardCompletedEvent event) {
				AppReport appReport = new AppReport();
				AppCusteomReportService appCustomReportService = new AppCusteomReportService(
						appReport);
				
				
			}

			@Override
			public void wizardCancelled(WizardCancelledEvent event) {
				
			}
			
		});

		layout.addComponent(wizard);
		this.addComponent(layout);
	}

	private Component buildPreviewStep() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(new Label("Report Review"));
		
		for (ReportModel reportModel : finalReportModels) {
			// build query tables
			for (ReportTable reportTable : reportModel.getReportTables()) {
				
				Label mainTableLabel=new Label("MainTable");
				layout.addComponent(mainTableLabel);
				
				if (reportTable.getType().toString()
						.equals(ReportConfiguration.ReportType.MainTable.toString())) {
					
					Label mainTable=new Label(reportTable.getTableName());
					layout.addComponent(mainTable);
				}
				
				Label subTableLabel=new Label("SubTable");
				layout.addComponent(subTableLabel);
				
				if (reportTable.getType().toString()
						.equals(ReportConfiguration.ReportType.SubTable.toString())) {
					
					
					Label subTable=new Label(reportTable.getTableName());
					layout.addComponent(subTable);
				}	
			
			}
			Label queryConditions=new Label("Query Conditions");
			layout.addComponent(queryConditions);
			
			//build query filter conditions
			if (reportModel.getReportQueryFilterCondition().size() > 0) {
				for (ReportQueryFilterCondition conditions : reportModel.getReportQueryFilterCondition()) {
					Label reportQueryCondition=new Label(conditions.buildQueryStrings());
					layout.addComponent(reportQueryCondition);
				}
			}
			
			//build query order sequnces
			//TBD
		}
		return layout;
	}

	private Component buildReportQueryFilterStep() {
		VerticalLayout layout = new VerticalLayout();
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		formLayout.setReadOnly(true);
		layout.addComponent(formLayout);
		ReportTableEditor editor = new ReportTableEditor(getReportFilterModel(),"Report Filters",true);
		editor.setCaption("Select Main Table");
		
		formLayout.addComponent(editor);
		return layout;
	}

	private List<ReportModel> getReportFilterModel() {
		List<ReportTable> reportTables = new ArrayList<ReportTable>();
		for (ReportModel reportModel : finalReportModels) {
			for (ReportTable reportTable : reportModel.getReportTables()) {
				reportTables.add(reportTable);
			}
		}
		return getReportTableModels(reportTables);
	}

	public Component buildMainTableStep() {
		VerticalLayout layout = new VerticalLayout();
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		layout.addComponent(formLayout);

		TextField tableNameField = new TextField("New Table Name");
		tableNameField.setWidth(fieldWidth);
		formLayout.addComponent(tableNameField);

		ReportTableEditor editor = new ReportTableEditor(getMainTableStepModel(),ReportConfiguration.ReportType.MainTable.toString());
		editor.setCaption("Select Main Table");
		formLayout.addComponent(editor);

		TextArea textArea = new TextArea("Table Description");
		textArea.setWidth("600px");
		formLayout.addComponent(textArea);
		return layout;
	}

	//get db entity models
	private List<ReportModel> getMainTableStepModel() {
//		Map<String, ReportTable> returnTables = new ConcurrentHashMap<String, ReportTable>();
		DBToolUtil dbToolUtil = new DBToolUtil();
		reportTables = dbToolUtil.loadBasicTableInfo();
		List<ReportTable> dbModels=new ArrayList<ReportTable>();
		for(String key:reportTables.keySet()){
			dbModels.add(reportTables.get(key));
		}
		return getReportTableModels(dbModels);
	}
	

	public Component buildRelatedTableStep() {
		VerticalLayout layout = new VerticalLayout();
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		formLayout.setReadOnly(true);
		layout.addComponent(formLayout);

		TextField tableNameField = new TextField("New Table Name");
		tableNameField.setReadOnly(true);
		tableNameField.setWidth(fieldWidth);
		formLayout.addComponent(tableNameField);

		TextArea textArea = new TextArea("Table Description");
		textArea.setReadOnly(true);
		textArea.setWidth("600px");
		formLayout.addComponent(textArea);

		ReportTableEditor editor = new ReportTableEditor(getRelateTableModel(),
				ReportConfiguration.ReportType.SubTable.toString());
		editor.setCaption("Select Main Table");
		formLayout.addComponent(editor);
		return layout;
	}

	
	private List<ReportModel> getRelateTableModel() {
		return getReportTableModels(relateReportTables);
	}

	private List<ReportModel> getReportTableModels(List<ReportTable> reportTables) {
		ArrayList<ReportModel> rm = new ArrayList<ReportModel>();
		for (ReportTable reportTable:reportTables) {
			ArrayList<ReportColumn> reportColumns = new ArrayList<ReportColumn>();
			String[] reportColumnNames = new String[reportTable.getReportColumns().size()];
			int i = 0;
			for (ReportColumn reportColumn : reportTable.getReportColumns()) {
				reportColumns.add(reportColumn);
				reportColumnNames[i] = reportColumn.getColumnName();
				i++;
			}
			ReportModel reportModel = new ReportModel(reportTable.getTableName(),reportColumnNames);
			reportModel.getReportTables().add(reportTable);
			rm.add(reportModel);
		}
		return Lists.newArrayList(rm);
	}


	public String getTableName(Class entityClass) {
		String tableName = null;
		if (entityClass.getAnnotation(Table.class) != null) {
			Table table = (Table) entityClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		return tableName;
	}

	@Override
	public String getViewerTitle() {
		return "New User Defined Report";
	}

	public class ReportTableEditor extends VerticalLayout
			implements
				ValueChangeListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private List<ReportModel> reportModels;
		private ComboBox box = new ComboBox();
		private VerticalLayout columnLayout = new VerticalLayout();
		private String stepType;
		private boolean queryFilterFlg=false;
		private List<String> queryFilters=new ArrayList<String>();
		

		public ReportTableEditor(List<ReportModel> reportModels, String stepType) {
			this.setReportModels(reportModels);
			this.stepType = stepType;
		}
		
		public ReportTableEditor(List<ReportModel> reportModels, String stepType,boolean queryFilterFlg) {
			this.setReportModels(reportModels);
			this.stepType = stepType;
			this.queryFilterFlg=queryFilterFlg;
		}

		public void attach() {
			box.setWidth(fieldWidth);
			box.setImmediate(true);
			BeanItemContainer<ReportModel> container = new BeanItemContainer<ReportModel>(
					ReportModel.class);
			container.addAll(getReportModels());
			box.setContainerDataSource(container);
			box.setItemCaptionPropertyId("tableName");
			box.addListener(this);
			this.addComponent(box);
			columnLayout.setSpacing(true);
			columnLayout.setVisible(false);
			this.addComponent(columnLayout);
		}

		@Override
		public void valueChange(ValueChangeEvent event) {
			columnLayout.removeAllComponents();
			Label label = new Label(
					"Please pickup below columns of the main table you selected");
			columnLayout.addComponent(label);
			
			List<ReportColumn> selectReprotColumn=new ArrayList<ReportColumn>();
			
			final ReportModel rm = (ReportModel) box.getValue();
			final StringBuilder queryFilter=new StringBuilder();
			if (rm != null) {
				List<ReportColumn> columnFields = rm.getReportTables().get(0).getReportColumns();
				
				for (ReportColumn columnField : columnFields) {
					final CheckBox checkBox = new CheckBox(columnField.getColumnName());
					columnLayout.addComponent(checkBox);
					checkBox.addListener(new ClickListener() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							ReportColumn reportColumn=new ReportColumn();
							reportColumn.setColumnName(checkBox.getCaption().toString());
							rm.getSelectedColumns().add(reportColumn);	
							if(stepType.equals("Report Filters")){
								queryFilter.append(rm.getTableName()+"."+checkBox.getCaption());
								System.out.println("User filter condition is: " +queryFilter.toString());
								finalReportModels.get(0).getReportQueryFilterCondition().get(0).setReportTable(rm.getReportTables().get(0));
								finalReportModels.get(0).getReportQueryFilterCondition().get(0).setColumn(reportColumn.getColumnName());
							}
							else{
								
							}
						}
					});
				}
				
				if (queryFilterFlg) {
					final ComboBox queryFilterBox = new ComboBox("Select one query filter condition");
					queryFilterBox.setImmediate(true);					
					
					for (ReportQueryFilterType filterType : ReportConfiguration.ReportQueryFilterType.values()) {
						queryFilterBox.addItem(filterType.name().toString());
					}
					
					queryFilterBox.addListener(new Property.ValueChangeListener() {
						@Override
						public void valueChange(ValueChangeEvent event) {
							
							queryFilter.append(" "+queryFilterBox.getValue().toString());
							ReportQueryFilterType queryFilterType=ReportConfiguration.ReportQueryFilterType.valueOf(queryFilterBox.getValue().toString());
							queryFilterCondition.setQueryFilterType(queryFilterType);
							System.out.println("User filter condition is: " +queryFilter.toString());
							
							finalReportModels.get(0).getReportQueryFilterCondition().get(0).setQueryFilterType(queryFilterType);
//							refreshWizard(new ReportWizardStep("Report Filters",buildReportQueryFilterStep()));
						}
					});
					
					columnLayout.addComponent(queryFilterBox);
					final TextField queryCondition=new TextField();
					queryCondition.addListener(new Property.ValueChangeListener() {
						
						@Override
						public void valueChange(ValueChangeEvent event) {
							queryFilter.append(queryCondition.getValue().toString());
							System.out.println("User filter condition is: " +queryFilter.toString());
							queryFilterCondition.setFilterValue(queryCondition.getValue().toString());
							finalReportModels.get(0).getReportQueryFilterCondition().get(0).setFilterValue(queryCondition.getValue().toString());
//							refreshWizard(new ReportWizardStep("Report Filters",buildReportQueryFilterStep()));
						}
					});
					
					
					
					columnLayout.addComponent(queryCondition);	
				}
				columnLayout.setVisible(true);
				
			} else {
				columnLayout.setVisible(false);
			}
			try {
				if (stepType.equals(ReportConfiguration.ReportType.MainTable.toString())) {
					
//					mainReportTable = findTableByReportType(rm,ReportType.MainTable).get(0);
					
					mainReportTable=rm.getReportTables().get(0);
					
					System.out.println("user selected table: "+ mainReportTable.getTableName());

					// if mainReportTable has been selected, start to build the
					// reference tables
					relateReportTables.clear();
					mainReportTable.setType(ReportConfiguration.ReportType.MainTable.toString());
//					relateReportTables.add(mainReportTable);					
					for (ReportColumn reportColumn : mainReportTable
							.getReportColumns()) {
						if (reportColumn.getColumnUseMode() != null
								&& reportColumn.getColumnUseMode().equals(ReportConfiguration.ReportColumnType.ForeignKeyRefernceColumn.toString())) {
							if (reportTables.get(reportColumn.getReferenceTableName()) != null) {
								String referenceTableName = reportTables.get(reportColumn.getReferenceTableName()).getTableName().toString();
								ReportTable reportTable=reportTables.get(referenceTableName);
								reportTable.setType(ReportConfiguration.ReportType.SubTable.toString());
								relateReportTables.add(reportTable);
							}
						}
					}
					
					// change relate table step content
					// wizard.requestRepaintAll();
					refreshWizard(new ReportWizardStep("Related Table",buildRelatedTableStep()));
					rm.getReportTables().add(mainReportTable);
					finalReportModels.add(rm);
					
				} else if (stepType.equals(ReportConfiguration.ReportType.SubTable.toString())) {

//					finalReportQueryTables.put(rm.getReportTable().getTableName(), rm.getReportTable());
					
					for(ReportTable reportTable:relateReportTables){
						finalReportModels.get(0).getReportTables().add(reportTable);					
					}
					
					finalReportModels.get(0).getReportTables().add(rm.getReportTables().get(0));
					
					refreshWizard(new ReportWizardStep("Report Filters",buildReportQueryFilterStep()));
					
				}else if(stepType.equals("Report Filters")){
					
					finalReportModels.get(0).getReportQueryFilterCondition().add(queryFilterCondition);
					
//					reportModel.getReportQueryFilterCondition().add(queryFilterCondition);
					
					refreshWizard(new ReportWizardStep("Preview",buildPreviewStep()));
					
				} else if (stepType.equals("Preview")){
					System.out.println("Preview");
				}
					
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Errors occurs when selecting report on step "+ stepType.toString() + " page");
			}
		}

		public List<ReportModel> getReportModels() {
			return reportModels;
		}

		public void setReportModels(List<ReportModel> reportModels) {
			this.reportModels = reportModels;
		}
	}

	private void refreshWizard(ReportWizardStep reportWizardStep) {

		ArrayList<WizardStep> wizardSteps = Lists.newArrayList(wizard
				.getSteps());

		for (int i = 0; i < wizardSteps.size(); i++) {
			if (wizardSteps.get(i).getCaption()
					.equals(reportWizardStep.getCaption())) {
				// first remove all the steps after "related table"
				for (int j = i; j < wizardSteps.size(); j++) {
					wizard.removeStep(wizardSteps.get(j));
				}
				// add "new step"--related table
				wizard.addStep(reportWizardStep);
				// add remaining steps
				for (int h = i + 1; h < wizardSteps.size(); h++) {
					wizard.addStep(wizardSteps.get(h));
				}
			}
		}
		
	}
	
	private List<ReportTable> findTableByReportType(ReportModel reportModels,ReportType reportType){
		List<ReportTable> foundTables=new ArrayList<ReportTable>();
		for(ReportTable reportTable:reportModels.getReportTables()){
			if(reportTable.getType()!=null && reportTable.getType().equals(reportType)){
				foundTables.add(reportTable);
			}
		}
		return foundTables;
	}

}