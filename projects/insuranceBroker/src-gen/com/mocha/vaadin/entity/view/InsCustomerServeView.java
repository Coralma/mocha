package com.mocha.vaadin.entity.view;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.ib.model.InsuranceCustomerServe;

public class InsCustomerServeView extends EntityViewPanel implements Viewer {
	
	public InsCustomerServeView() {
		super();
	}

	public void build() {
		final IViewPanel viewPanel = createViewPanel();
		ISectionPanel sectionPanel;
		FieldStatus fieldStatus;
		
		sectionPanel = createSectionPanel("CustomerServerInfo");
		sectionPanel.setLabel("Customer Server");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Customer").setPath("customer").setType("com.mocha.ib.model.InsuranceCustomer").setRequired(true).setStyle("ref").setExpression("name");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Type").setPath("type").setType("String").setCodeTableName("serve-type").setChangeLine(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Priority").setPath("priority").setType("String").setCodeTableName("serve-priority");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Date").setPath("date").setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Status").setPath("status").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Mark").setPath("mark").setType("String").setStyle("textarea").setWholeRow(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		sectionPanel = createSectionPanel("activityFeedback");
		sectionPanel.setLabel("Feedback");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Result").setPath("result").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Feedback").setPath("feedback").setType("String").setStyle("textarea").setWholeRow(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		IActionPanel actionPanel = createActionPanel();
		viewPanel.setActionPanel(actionPanel);
		actionPanel.addButton(createActionButton("save", "Save", "Save"));
		actionPanel.addButton(createActionButton("back", "Back", "Back"));
		
		addComponent(viewPanel);
	}

	public Class getEntityClass() {
		return InsuranceCustomerServe.class;
	}
	
	public String getViewerTitle() {
		return "Create Insurance Customer Serve";
	}

}

