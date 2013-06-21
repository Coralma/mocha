package com.mocha.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.report.ReportConfiguration.ReportQueryFilterType;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;
import com.google.common.collect.Lists;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ReportFilterStep extends AbstarctReportWizardStep {

	String fieldWidth = "300px";
	private ReportModel reportModel;
	private Map<String, ReportTable> reportTables;
	private Wizard w;
	final ReportQueryFilterCondition rqfc=new ReportQueryFilterCondition();
	
	public ReportFilterStep(Wizard w) {
		this.w = w;
		
		w.addListener(new WizardProgressListener() {
			
			@Override
			public void wizardCompleted(WizardCompletedEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void wizardCancelled(WizardCancelledEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void stepSetChanged(WizardStepSetChangedEvent event) {
				
			}
			
			@Override
			public void activeStepChanged(WizardStepActivationEvent event) {
				
			}
		});
	}

	@Override
	void buildlistener() {
	}

	@Override
	public String getCaption() {
		return "Report Filter Step";
	}

	@Override
	public Component getContent() {
		return buildReportQueryFilterStep();
	}

	private Component buildReportQueryFilterStep() {
		VerticalLayout layout = new VerticalLayout();
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		formLayout.setReadOnly(true);
		layout.addComponent(formLayout);
		ReportTableEditor editor = new ReportTableEditor(
				getReportFilterModel(), "Report Filters", true);
		editor.setCaption("Select Table To Filter");
		formLayout.addComponent(editor);
		return layout;
	}

	private List<ReportModel> getReportFilterModel() {
		HashSet<ReportTable> reportTables = new HashSet<ReportTable>();
		for(ReportTable r:AbstarctReportWizardStep.getUserSelectReport().get().getReportTables())
		{
				if(r!=null){					
					reportTables.add(r);
				}	
		}
		return getReportTableModels(Lists.newArrayList(reportTables));
	}

	public class ReportTableEditor extends VerticalLayout
			implements
				ValueChangeListener {

		private static final long serialVersionUID = 1L;
		private List<ReportModel> reportModels;
		private ComboBox box = new ComboBox();
		private VerticalLayout columnLayout = new VerticalLayout();
		private String stepType;
		private boolean queryFilterFlg = false;
		private List<String> queryFilters = new ArrayList<String>();
		private ReportModel rm;
		final StringBuilder queryFilter = new StringBuilder();

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
				columnLayout.setVisible(true);
				columnLayout.setImmediate(true);
				ReportTable selectReport=rm.getReportTables().iterator().next();
				rqfc.setReportTable(selectReport);
				List<ReportColumn> columnFields = selectReport.getReportColumns();
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
							rqfc.setColumn(checkBox.getCaption());
						}
					});
				}
				final ComboBox queryFilterBox = new ComboBox(
						"Select one query filter condition");
				queryFilterBox.setImmediate(true);

				for (ReportQueryFilterType filterType : ReportConfiguration.ReportQueryFilterType
						.values()) {
					queryFilterBox.addItem(filterType.name().toString());
				}

				
				columnLayout.addComponent(queryFilterBox);
				final TextField queryCondition = new TextField("Filter Values");
				queryCondition.addListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(ValueChangeEvent event) {
						rqfc.setFilterValue(queryCondition.getValue().toString());
						System.out.println("System to read the report query");
						System.out.println(rqfc.getReportTable().getTableName());
						System.out.println(rqfc.getColumn());
						System.out.println(rqfc.getQueryFilterType().toString());
						System.out.println(rqfc.getFilterValue());
						if(rqfc.buildQueryStrings()!=null){
							AbstarctReportWizardStep.getUserSelectReport().get().getReportQueryFilterCondition().add(rqfc);
						}
						System.out.println(rqfc.buildQueryStrings());
						
					}
				});
				
				queryFilterBox.addListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(ValueChangeEvent event) {
						rqfc.setQueryFilterType(ReportConfiguration.ReportQueryFilterType.valueOf(queryFilterBox.getValue().toString()));
						columnLayout.addComponent(queryCondition);
					}
				});
				
				this.addComponent(columnLayout);

				RelatedTableStep secondeStep = new RelatedTableStep(w);
				boolean removeStepflg = false;
				for (WizardStep step : w.getSteps()) {
					if (step.getCaption().equals(secondeStep.getCaption())) {
						removeStepflg = true;
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
