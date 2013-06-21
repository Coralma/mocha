package com.mocha.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.coral.foundation.jdbc.impl.DBToolUtil;
import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;
import com.google.common.collect.Lists;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;


public class MainTableStep extends AbstarctReportWizardStep {

	String fieldWidth = "300px";
	private ReportModel reportModel;
	private Map<String, ReportTable> reportTables;
	private Wizard w;
	private WizardStep nStep;
	
	
	public MainTableStep(Wizard w){
		this.setW(w);
		this.listener=listener;
	}

	@Override
	public String getCaption() {
		return "Main Table Step";
	}

	public void doWork() {

	}

	public ReportModel getReportModel() {
		return reportModel;
	}

	public void setReportModel(ReportModel reportModel) {
		this.reportModel = reportModel;
	}

	@Override
	public Component getContent() {
		return buildMainTableStep();
	}

	public Component buildMainTableStep() {
		VerticalLayout layout = new VerticalLayout();
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		layout.addComponent(formLayout);

		TextField tableNameField = new TextField("New Table Name");
		tableNameField.setWidth(fieldWidth);
		formLayout.addComponent(tableNameField);

		ReportTableEditor editor = new ReportTableEditor(
				getMainTableStepModel(),
				ReportConfiguration.ReportType.MainTable.toString());
		editor.setCaption("Select Main Table");
		formLayout.addComponent(editor);

		TextArea textArea = new TextArea("Table Description");
		textArea.setWidth("600px");
		formLayout.addComponent(textArea);
		return layout;
	}

	// get db entity models
	private List<ReportModel> getMainTableStepModel() {
		DBToolUtil dbToolUtil = new DBToolUtil();
		setReportTables(dbToolUtil.loadBasicTableInfo());
		
		List<ReportTable> dbModels = new ArrayList<ReportTable>();
		for (String key : getReportTables().keySet()) {
			dbModels.add(getReportTables().get(key));
		}
		return getReportTableModels(dbModels);
	}

	

	@Override
	void buildlistener() {
		this.listener = new WizardProgressListener() {

			@Override
			public void activeStepChanged(WizardStepActivationEvent event) {
				w=event.getWizard();
			}

			@Override
			public void stepSetChanged(WizardStepSetChangedEvent event) {
				w=event.getWizard();
			}

			@Override
			public void wizardCompleted(WizardCompletedEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void wizardCancelled(WizardCancelledEvent event) {
				// TODO Auto-generated method stub

			}

		};
		this.listener = listener;
	}

	public Map<String, ReportTable> getReportTables() {
		return reportTables;
	}

	public void setReportTables(Map<String, ReportTable> reportTables) {
		this.reportTables = reportTables;
	}

	public Wizard getW() {
		return w;
	}

	public void setW(Wizard w) {
		this.w = w;
	}

	public class ReportTableEditor extends VerticalLayout implements ValueChangeListener {

		private static final long serialVersionUID = 1L;
		private List<ReportModel> reportModels;
		private ComboBox box = new ComboBox();
		private VerticalLayout columnLayout = new VerticalLayout();
		private String stepType;
		private boolean queryFilterFlg = false;
		private List<String> queryFilters = new ArrayList<String>();
		private ReportModel rm;
		
		
		public ReportTableEditor(List<ReportModel> reportModels, String stepType) {
			this.setReportModels(reportModels);
			this.stepType = stepType;
		}

		public ReportTableEditor(List<ReportModel> reportModels,
				String stepType, boolean queryFilterFlg) {
			this.setReportModels(reportModels);
			this.stepType = stepType;
			this.queryFilterFlg = queryFilterFlg;
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
			rm = (ReportModel) box.getValue();
			if (rm != null) {
				//build main table info
				columnLayout.removeAllComponents();
				mainReportTable=rm.getReportTables().iterator().next();
				columnLayout.setVisible(true);
				columnLayout.setImmediate(true);
				List<ReportColumn> columnFields =mainReportTable.getReportColumns();
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
							rm.getMainTableSelectedColumns().put(mainReportTable.getTableName(),reportColumn);
						}
					});
				}
				this.addComponent(columnLayout);
				
				//build related table info
				relateReportTables.clear();
				mainReportTable.setType(ReportConfiguration.ReportType.MainTable.toString());
				for (ReportColumn reportColumn : mainReportTable.getReportColumns()) {
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
				rm.getReportTables().addAll(relateReportTables);
				rm.getReportTables().add(mainReportTable);
				
				// init the report model -- main table				
				if(AbstarctReportWizardStep.getUserSelectReport().get()!=null){
					AbstarctReportWizardStep.getUserSelectReport().set(null);
				}
				AbstarctReportWizardStep.getUserSelectReport().set(rm);
				
				// auto add the step
				RelatedTableStep secondeStep = new RelatedTableStep(w);
				boolean removeStepflg = false;
				for (WizardStep step : w.getSteps()) {
					if (step.getCaption().equals(secondeStep.getCaption())) {
						removeStepflg = true;
					}
				}
				if (removeStepflg) {
					w.removeStep(secondeStep.getCaption());
					w.removeStep("Preview Step");
				} else {
					w.removeStep("Preview Step");
				}
				w.addStep(secondeStep, "Related Table Step");
				w.addStep(new PreviewStep(w),"Preview Step");
			}
		}

		public List<ReportModel> getReportModels() {
			return reportModels;
		}

		public void setReportModels(List<ReportModel> reportModels) {
			this.reportModels = reportModels;
		}
	}

}
