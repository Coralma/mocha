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

import com.coral.foundation.jdbc.impl.DBToolUtil;
import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;
import com.google.common.collect.Lists;
import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
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

		ReportTableEditor editor = new ReportTableEditor(
				getMainTableStepModel(),
				ReportConfiguration.ReportType.MainTable.toString());
		formLayout.addComponent(editor);

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
		private GridLayout gridLayout  = new GridLayout(3, 1);
		private Label reportColumnLabel=new Label("Main Table Columns");
		private Label reportColumnStepDesc=new Label("Select Custom Report Columns");
		private GridLayout reportColmnDesc=new GridLayout(2,1);
		
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
			this.addStyleName("custom-report-step-caption");
			Label mainTableCaption=new Label("Main Table");
			Label mainTableDesc=new Label("Select Custom Report Table");
			mainTableDesc.addStyleName("custom-report-step-column-desc");
			GridLayout reportTableDesc=new GridLayout(2,1);
			reportTableDesc.setSpacing(true);
			reportTableDesc.addComponent(mainTableCaption);
			reportTableDesc.addComponent(mainTableDesc);
			this.addComponent(reportTableDesc);
			box.setWidth(fieldWidth);
			box.setImmediate(true);
			box.addStyleName("custom-report-step-box");
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
			reportColumnLabel.setStyleName("custom-report-step-column-caption");
			reportColumnStepDesc.setStyleName("custom-report-step-column-desc");
			reportTableDesc.setSpacing(true);
			reportColmnDesc.addComponent(reportColumnLabel);
			reportColmnDesc.addComponent(reportColumnStepDesc);
			gridLayout.setSizeFull();
			gridLayout.setImmediate(true);
			gridLayout.addStyleName("custom-report-step-column-gridlayout");
			reportColmnDesc.setSpacing(true);
		}

		@Override
		public void valueChange(ValueChangeEvent event) {
			rm = (ReportModel) box.getValue();
			
			clearMainTableReslut();
			
			if (rm != null) {
				columnLayout.removeAllComponents();
				columnLayout.addComponent(reportColmnDesc);
				columnLayout.setVisible(true);
				columnLayout.setImmediate(true);
				columnLayout.addComponent(gridLayout);
				//build main table info
				mainReportTable=rm.getReportTables().iterator().next();
				columnLayout.setVisible(true);
				columnLayout.setImmediate(true);
				List<ReportColumn> columnFields =mainReportTable.getReportColumns();
				gridLayout.removeAllComponents();
				gridLayout.setSpacing(true);
				gridLayout.requestRepaintAll();
				gridLayout.setRows(columnFields.size());
				for (final ReportColumn columnField : columnFields) {
					ReportColumnCard reportColumnCard=new ReportColumnCard(columnField){
						@Override
						public void layoutClick(LayoutClickEvent event) {
							System.out.println("user click"+columnField.getColumnName());							
							ReportColumn reportColumn=columnField;
							rm.getMainTableSelectedColumns().add(reportColumn);							
						}
					};
					gridLayout.addComponent(reportColumnCard);
				}
//				for (ReportColumn columnField : columnFields) {
//					final CheckBox checkBox = new CheckBox(columnField.getColumnName());
//					gridLayout.addComponent(checkBox);
//					checkBox.addListener(new ClickListener() {
//						/**
//						 * 
//						 */
//						private static final long serialVersionUID = 1L;
//
//						@Override
//						public void buttonClick(ClickEvent event) {
//							ReportColumn reportColumn=new ReportColumn();
//							reportColumn.setColumnName(checkBox.getCaption().toString());
//							rm.getMainTableSelectedColumns().put(mainReportTable.getTableName(),reportColumn);
//						}
//					});
//				}
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
				if(ReportModelPool.getUserSelectReport().get()!=null){
					ReportModelPool.getUserSelectReport().get().getReportTables().clear();
				}
				ReportModelPool.getUserSelectReport().set(rm);
				
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

		private void clearMainTableReslut() {
			if(ReportModelPool.getUserSelectReport().get()!=null &&ReportModelPool.getUserSelectReport().get().getReportTables()!=null ){				
				for(Iterator<ReportTable> it=ReportModelPool.getUserSelectReport().get().getReportTables().iterator();it.hasNext();){
					ReportTable rt=it.next();
					if(rt.getType().equals(ReportConfiguration.ReportType.MainTable.toString())){
						it.remove();
					}
				}
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
