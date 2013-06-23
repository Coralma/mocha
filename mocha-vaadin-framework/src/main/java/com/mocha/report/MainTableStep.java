package com.mocha.report;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.coral.foundation.jdbc.impl.DBToolUtil;
import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.report.ReportModel;
import com.coral.foundation.report.ReportModelPool;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportJoinTable;
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
	private static Map<String, ReportTable> reportTables;
	private Wizard w;
	private WizardStep nStep;
	private static List<ReportTable> appCustomReprotRowData;
	private static BasicUser user;
	
	
	public MainTableStep(Wizard w, BasicUser user){
		this.setW(w);
		this.listener=listener;
		this.setUser(user);
		
	}

	public MainTableStep(Wizard wizard, BasicUser user,List<ReportTable> appCustomReprotRowData) {
		this.setW(wizard);
		this.listener=listener;
		this.setUser(user);
		this.setAppCustomReprotRowData(appCustomReprotRowData);
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
		
//		DBToolUtil dbToolUtil = new DBToolUtil();
//		setReportTables(dbToolUtil.loadBasicTableInfo());
//		List<ReportTable> dbModels = new ArrayList<ReportTable>();
//		for (String key : getReportTables().keySet()) {
//			dbModels.add(getReportTables().get(key));
//		}
		reportTables=new HashMap<String,ReportTable>();
		ArrayList<ReportTable> r=new ArrayList<ReportTable>();
		for(ReportTable reportTable:getAppCustomReprotRowData()){
			for(ReportJoinTable rj:reportTable.getReportJoinReportTableId()){
				ReportColumn reportJoinColumn=new ReportColumn();
				String refernceId=reportTable.getTableName()+"_ID";
				refernceId=refernceId.trim().substring(2, refernceId.length());
				reportJoinColumn.setColumnName(refernceId);
				String subReferenceid=rj.getReportTable().getTableName()+"_ID";
				subReferenceid=subReferenceid.trim().substring(2, subReferenceid.length());
				reportJoinColumn.setReferenceTableName(rj.getReportTable().getTableName());
				reportJoinColumn.setReferenceColumnName(subReferenceid);
				reportJoinColumn.setColumnUseMode(ReportConfiguration.ReportColumnType.ForeignKeyRefernceColumn.toString());
				reportTable.getReportColumns().add(reportJoinColumn);
				reportTable.setReportTableId(null);
			}
			r.add(reportTable);
			reportTables.put(reportTable.getTableName(),reportTable);
		}
		setReportTables(reportTables);
		return getReportTableModels(r);
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

	public static BasicUser getUser() {
		return user;
	}

	public static void setUser(BasicUser user) {
		MainTableStep.user = user;
	}

	public static List<ReportTable> getAppCustomReprotRowData() {
		return appCustomReprotRowData;
	}

	public static void setAppCustomReprotRowData(
			List<ReportTable> appCustomReprotRowData) {
		MainTableStep.appCustomReprotRowData = appCustomReprotRowData;
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
			box.setItemCaptionPropertyId("tableLabel");
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
					if(columnField.getColumnLabel()!=null){						
						ReportColumnCard reportColumnCard=new ReportColumnCard(columnField){
							@Override
							public void layoutClick(LayoutClickEvent event) {
								System.out.println("user click"+columnField.getColumnName());							
								ReportColumn reportColumn=columnField;
								reportColumn.setColumnUseMode(ReportConfiguration.ReportColumnType.OutputColumn.toString());
								rm.getMainTableSelectedColumns().add(reportColumn);							
							}
						};
					gridLayout.addComponent(reportColumnCard);
					}
				}
				this.addComponent(columnLayout);				
				
				//build related table info
				relateReportTables.clear();
				mainReportTable.setType(ReportConfiguration.ReportType.MainTable.toString());
				for (ReportColumn reportColumn : mainReportTable.getReportColumns()) {
					if (reportColumn.getColumnUseMode() != null
							&& reportColumn.getColumnUseMode().equals(ReportConfiguration.ReportColumnType.ForeignKeyRefernceColumn.toString())) {
						//find reference tables first
						if (reportTables.get(reportColumn.getReferenceTableName()) != null) {
							String referenceTableName = reportTables.get(reportColumn.getReferenceTableName()).getTableName().toString();
							ReportTable reportTable=reportTables.get(referenceTableName);
							reportTable.setType(ReportConfiguration.ReportType.SubTable.toString());
							relateReportTables.add(reportTable);
						}
						
						
					}
				}
				for(ReportTable reportTable:reportTables.values()){
					for(ReportColumn reportColumn : reportTable.getReportColumns()){
						if (reportColumn.getColumnUseMode() != null
								&& reportColumn.getColumnUseMode().equals(ReportConfiguration.ReportColumnType.ForeignKeyRefernceColumn.toString())) {
							//find reference tables first
							if (reportTables.get(reportColumn.getReferenceTableName()) != null) {
								if(reportColumn.getReferenceTableName().equals(mainReportTable.getTableName())){
									String referenceTableName = reportTables.get(reportColumn.getReferenceTableName()).getTableName().toString();
									ReportTable subReportTable=reportTables.get(reportTable.getTableName());
									subReportTable.setType(ReportConfiguration.ReportType.SubTable.toString());
									relateReportTables.add(subReportTable);
								}
							}
						}
						
					}
				}
				
				rm.getReportTables().addAll(relateReportTables);
				rm.getReportTables().add(mainReportTable);
				
				// init the report model -- main table				
				if(ReportModelPool.findReportModelByCurrentUser(getUser())!=null){
					ReportModelPool.findReportModelByCurrentUser(user).getReportTables().clear();
				}
				ReportModelPool.putUserReportModel(user, rm);
								
				// auto add the step
				RelatedTableStep secondeStep = new RelatedTableStep(w,user);
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
				w.addStep(new PreviewStep(w,user),"Preview Step");
			}
		}

		private void clearMainTableReslut() {
			if(ReportModelPool.findReportModelByCurrentUser(user)!=null &&ReportModelPool.findReportModelByCurrentUser(user).getReportTables()!=null ){				
				for(Iterator<ReportTable> it=ReportModelPool.findReportModelByCurrentUser(user).getReportTables().iterator();it.hasNext();){
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
