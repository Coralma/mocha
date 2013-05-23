package com.mocha.report;

import java.util.List;

import org.vaadin.teemu.wizards.Wizard;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.google.common.collect.Lists;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class NewReportViewer extends CommonViewer implements Viewer {
	
	String fieldWidth = "300px";
	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("760px");
		layout.addStyleName("app-new-report");
		Wizard wizard = new Wizard();
		wizard.setWidth("760px");
		wizard.addStep(new ReportWizardStep("Main Table", buildMainTableStep()));
		wizard.addStep(new ReportWizardStep("Related Table", buildRelatedTableStep()));
		wizard.addStep(new ReportWizardStep("Columns Layout", new Label("Table")));
		wizard.addStep(new ReportWizardStep("Preview", new Label("Table")));
		layout.addComponent(wizard);
		this.addComponent(layout);
	}
	
	public Component buildMainTableStep() {
		VerticalLayout layout = new VerticalLayout();
		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		layout.addComponent(formLayout);
		
		TextField tableNameField = new TextField("New Table Name");
		tableNameField.setWidth(fieldWidth);
		formLayout.addComponent(tableNameField);
		
		ReportTableEditor editor = new ReportTableEditor(getDemoReportModel());
		editor.setCaption("Select Main Table");
		formLayout.addComponent(editor);

		TextArea textArea = new TextArea("Table Description");
		textArea.setWidth("600px");
		formLayout.addComponent(textArea);
		return layout;
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
		
		ReportTableEditor editor = new ReportTableEditor(getDemoReportModel());
		editor.setCaption("Select Main Table");
		formLayout.addComponent(editor);
		return layout;
	}
	
	public List<ReportModel> getDemoReportModel() {
		ReportModel[] rm = new ReportModel[]{
				new ReportModel("Customer","Customer Name", "Status", "Contect Person","Email","Phone"),
				new ReportModel("Compaign","Campaign Name", "Start Date", "End Date"),
				new ReportModel("Server","Customer Name", "Type", "Serve Date", "Result"),
		};
		return Lists.newArrayList(rm);
	}
	
	@Override
	public String getViewerTitle() {
		return "New User Defined Report";
	}
	
	
	public class ReportTableEditor extends VerticalLayout implements ValueChangeListener {
		
		private List<ReportModel> reportModels;
		private ComboBox box = new ComboBox();
		private VerticalLayout columnLayout = new VerticalLayout();
		
		public ReportTableEditor(List<ReportModel> reportModels ) {
			this.reportModels = reportModels;
		}

		public void attach() {
			box.setWidth(fieldWidth);
			box.setImmediate(true);
			BeanItemContainer<ReportModel> container = new BeanItemContainer<ReportModel>(ReportModel.class);
			container.addAll(getDemoReportModel());
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
			Label label = new Label("Please pickup below columns of the main table you selected");
			columnLayout.addComponent(label);
			ReportModel rm = (ReportModel)box.getValue();
			if(rm != null) {
				List<String> columnFields = rm.getColumnFields();
				for(String columnField : columnFields) {
					CheckBox box = new CheckBox(columnField);
					columnLayout.addComponent(box);
				}
				columnLayout.setVisible(true);
			} else {
				columnLayout.setVisible(false);
			}
		}
	}
}