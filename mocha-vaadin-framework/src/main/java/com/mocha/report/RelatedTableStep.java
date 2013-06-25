package com.mocha.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardProgressListener;

import com.coral.foundation.report.ReportConfiguration;
import com.coral.foundation.report.ReportModel;
import com.coral.foundation.report.ReportModelPool;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.ReportColumn;
import com.coral.foundation.security.model.ReportTable;
import com.google.common.collect.Lists;
import com.mocha.report.AbstarctReportWizardStep.ReportColumnCard;
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

public class RelatedTableStep extends AbstarctReportWizardStep{
	
	String fieldWidth = "300px";
	ArrayList<ReportTable> relateReportTables;
	private Wizard w;
	private WizardStep nStep;
	private WizardStep pStep;
	private BasicUser user;
	
	public RelatedTableStep(Wizard w,BasicUser user) {
		this.w=w;
		nStep=new ReportFilterStep(w,user);
//		pStep = new MainTableStep(w,user);
		this.user=user;
		getContent();
	}

	@Override
	public String getCaption() {
		return "Related Table Step";
	}
	
	@Override
	void buildlistener() {
		
	}

	@Override
	public Component getContent() {
		System.out.println();
		try {
			if(user!=null && ReportModelPool.findReportModelByCurrentUser(user)!=null){
				removeDuplciateRelateResult();
				return buildRelatedTableStep();				
			}
			return new Label("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Component buildRelatedTableStep() throws Exception {
		VerticalLayout layout = new VerticalLayout();
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		formLayout.setReadOnly(true);
		layout.addComponent(formLayout);
		
		if(ReportModelPool.findReportModelByCurrentUser(user)==null){
			throw new Exception("Error occurs when initialing the related data");
		}
		relateReportTables=new ArrayList<ReportTable>();
		for(ReportTable r:ReportModelPool.findReportModelByCurrentUser(user).getReportTables())
		{
			if(r.getType().toString().equals(ReportConfiguration.ReportType.SubTable.toString())){
				if(relateReportTables!=null){					
					relateReportTables.add(r);
				}
			}
				
		}
		ReportTableEditor editor = new ReportTableEditor(getRelateTableModel(),ReportConfiguration.ReportType.SubTable.toString());
		formLayout.addComponent(editor);
		return layout;
	}
	
	private List<ReportModel> getRelateTableModel() {
		return getReportTableModels(relateReportTables);
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
			Label caption=new Label("Select Reference Table");
			caption.setStyleName("caption");
			this.addComponent(caption);
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
			reportColumnLabel.setVisible(false);
			reportColumnStepDesc.setStyleName("custom-report-step-column-desc");
			reportColumnStepDesc.setVisible(false);
			this.addComponent(reportColumnLabel);
			this.addComponent(reportColumnStepDesc);
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
				reportColumnStepDesc.setVisible(true);
				List<ReportColumn> columnFields = rm.getReportTables().iterator().next().getReportColumns();
				gridLayout.removeAllComponents();
				gridLayout.requestRepaintAll();
				gridLayout.setRows(columnFields.size());
				gridLayout.setSpacing(true);
				
				for (final ReportColumn columnField : columnFields) {
					if(columnField.getColumnLabel()!=null){
					ReportColumnCard reportColumnCard=new ReportColumnCard(columnField){
						@Override
						public void layoutClick(LayoutClickEvent event) {
							if(clearDuplicateRelateColumnResult(columnField)){
								System.out.println("User click related column: "+columnField.getColumnLabel());
								ReportColumn reportColumn=new ReportColumn();
								reportColumn.setColumnName(columnField.getColumnName());
								reportColumn.setColumnLabel(columnField.getColumnLabel());
								reportColumn.setColumnUseMode(ReportConfiguration.ReportColumnType.OutputColumn.toString());
								if(ReportModelPool.findReportModelByCurrentUser(user).getSubTableSelectedColumns()==null){
									HashSet<ReportColumn> reportColumns=new HashSet<ReportColumn>();
									reportColumns.add(reportColumn);
									ReportModelPool.findReportModelByCurrentUser(user).setSubTableSelectedColumns(reportColumns);
								}else{
									ReportModelPool.findReportModelByCurrentUser(user).getSubTableSelectedColumns().add(reportColumn);
								}
							}
						}
					};
						gridLayout.addComponent(reportColumnCard);
					}

				}
				this.addComponent(columnLayout);
				ReportTable selectSubReportTable=rm.getReportTables().iterator().next();
				ReportModelPool.findReportModelByCurrentUser(user).getReportTables().add(selectSubReportTable);
				boolean removeStepflg = false;
				for (WizardStep step : w.getSteps()) {
					if (step.getCaption() != null) {
						if (step.getCaption().equals("Report Filter Step")) {
							removeStepflg = true;
						}
					}
				}
				if(removeStepflg){
					w.removeStep("Report Filter Step");
				}
				w.removeStep("Preview Step");
				w.addStep(new ReportFilterStep(w,user), "Report Filter Step");
				w.addStep(new PreviewStep(w,user),"Preview Step");
				
			}
		}

		public List<ReportModel> getReportModels() {
			return reportModels;
		}

		public void setReportModels(List<ReportModel> reportModels) {
			this.reportModels = reportModels;
		}
		
		public boolean clearDuplicateRelateColumnResult(ReportColumn reportColumn) {
			for(ReportColumn sReportColumn:ReportModelPool.findReportModelByCurrentUser(user).getSubTableSelectedColumns()){
				if(sReportColumn.getColumnUseMode().equals(ReportConfiguration.ReportColumnType.OutputColumn.toString())){
					if(sReportColumn.getColumnName().equals(reportColumn.getColumnName())&&
							sReportColumn.getColumnLabel().equals(reportColumn.getColumnLabel())){
						return false;
					}
				}
			}
			return true;
		}
	}

	private void removeDuplciateRelateResult() {
		
	}

	

}
