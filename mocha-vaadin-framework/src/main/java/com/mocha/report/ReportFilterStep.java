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
import com.mocha.report.AbstarctReportWizardStep.ReportColumnCard;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
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
		formLayout.addComponent(editor);
		return layout;
	}

	private List<ReportModel> getReportFilterModel() {
		HashSet<ReportTable> reportTables = new HashSet<ReportTable>();
		for(ReportTable r:ReportModelPool.getUserSelectReport().get().getReportTables())
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
		private GridLayout gridLayout  = new GridLayout(3, 1);
		private Label reportColumnLabel=new Label("Report Columns");
		private Label reportColumnStepDesc=new Label("Select Costom Report Columns");

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
			Label caption=new Label("Report Filter Table");
			caption.setStyleName("caption");
			this.addComponent(caption);
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
			reportColumnLabel.setStyleName("custom-report-step-column-caption");
			reportColumnLabel.setVisible(false);
			this.addComponent(reportColumnLabel);
			gridLayout.setSizeFull();
			gridLayout.setImmediate(true);
			gridLayout.addStyleName("custom-report-step-column-gridlayout");
			this.addComponent(gridLayout);
		}

		@Override
		public void valueChange(ValueChangeEvent event) {
			rm = (ReportModel) box.getValue();
			if (rm != null) {
				columnLayout.setVisible(true);
				columnLayout.setImmediate(true);
				reportColumnLabel.setVisible(true);
				columnLayout.removeAllComponents();
				gridLayout.removeAllComponents();
				gridLayout.setSpacing(true);
				gridLayout.requestRepaintAll();
				ReportTable selectReport=rm.getReportTables().iterator().next();
				rqfc.setReportTable(selectReport);
				List<ReportColumn> columnFields = selectReport.getReportColumns();
				gridLayout.setRows(columnFields.size());
				for (final ReportColumn columnField : columnFields) {
					ReportColumnCard reportColumnCard=new ReportColumnCard(columnField){
						@Override
						public void layoutClick(LayoutClickEvent event) {
							ReportColumn reportColumn=new ReportColumn();
							reportColumn.setColumnName(columnField.getColumnName());
							rqfc.setColumn(columnField.getColumnName());
						}
					};
					gridLayout.addComponent(reportColumnCard);

				}
				Label queryFilterLabel=new Label("Select Query filter condition");
				columnLayout.addComponent(queryFilterLabel);
				
				GridLayout filerValueLayout=new GridLayout(2,1);
				filerValueLayout.setSpacing(true);	
				columnLayout.addComponent(filerValueLayout);
				
				final ComboBox queryFilterBox = new ComboBox();
				queryFilterBox.setImmediate(true);
				filerValueLayout.addComponent(queryFilterBox);
				for (ReportQueryFilterType filterType : ReportConfiguration.ReportQueryFilterType.values()) {
					queryFilterBox.addItem(filterType.name().toString());
				}
				
				final TextField queryCondition = new TextField("");
				queryCondition.setWidth("250px");
				filerValueLayout.addComponent(queryCondition);
				
				Button saveFilterBtn=new Button("Save Filter");
				columnLayout.addComponent(saveFilterBtn);
				queryFilterBox.addListener(new Property.ValueChangeListener() {
					@Override
					public void valueChange(ValueChangeEvent event) {
						rqfc.setQueryFilterType(ReportConfiguration.ReportQueryFilterType.valueOf(queryFilterBox.getValue().toString()));
						
					}
				});
				saveFilterBtn.addListener(new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						Label queryFilterLabel=new Label();
						String queryFilterString="";
					
						if (queryFilterBox.getValue().toString().equals(ReportConfiguration.ReportQueryFilterType.Like)) {
							queryCondition.setValue("'" + "%"+ queryCondition.getValue() + "%" + "'");
						} else {
							queryCondition.setValue("'"
									+ queryCondition.getValue() + "'");
						}
						queryFilterString=rqfc.getReportTable().getTableName()+"."+rqfc.getColumn()+" "+
								rqfc.getQueryFilterType().getQueryFilterType().toString()+" "+queryCondition.getValue();
						rqfc.setQueryStrings(queryFilterString);
						if(rqfc.buildQueryStrings()!=null){
							ReportModelPool.getUserSelectReport().get().getReportQueryFilterCondition().add(rqfc);
						}
						System.out.println("Query Fileter String is: "+ReportModelPool.getUserSelectReport().get().
								getReportQueryFilterCondition().get(0).getQueryStrings().toString());
						queryFilterLabel.setCaption(ReportModelPool.getUserSelectReport().get().
								getReportQueryFilterCondition().get(0).getQueryStrings().toString());
						columnLayout.addComponent(queryFilterLabel);
					}
				});	
				
				
//				queryCondition.addListener(new Property.ValueChangeListener() {
//					@Override
//					public void valueChange(ValueChangeEvent event) {
//						System.out.println("user input query filter: "+queryCondition.getValue());
//						if (queryFilterBox.getValue() != null) {
//							if (queryFilterBox
//									.getValue()
//									.toString()
//									.equals(ReportConfiguration.ReportQueryFilterType.Like)) {
//								queryCondition.setValue("'" + "%"
//										+ queryCondition.getValue() + "%" + "'");
//							} else {
//								queryCondition.setValue("'"
//										+ queryCondition.getValue() + "'");
//							}
//						}
//						rqfc.setFilterValue(queryCondition.getValue().toString());
//						
//						System.out.println("System to read the report query");
//						System.out.println(rqfc.getReportTable().getTableName());
//						System.out.println(rqfc.getColumn());
//						System.out.println(rqfc.getQueryFilterType().toString());
//						System.out.println(rqfc.getFilterValue());
//						String queryFilterString=rqfc.getReportTable().getTableName()+"."+rqfc.getReportTable().getTableName()+
//								rqfc.getQueryFilterType()+rqfc.getFilterValue();
//						rqfc.setQueryStrings(queryFilterString);
//						
//						if(rqfc.buildQueryStrings()!=null){
//							AbstarctReportWizardStep.getUserSelectReport().get().getReportQueryFilterCondition().add(rqfc);
//						}
//						System.out.println("Query Fileter String is: "+AbstarctReportWizardStep.getUserSelectReport().get().getReportQueryFilterCondition().toString());
//					}
//				});
				
				
				
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
