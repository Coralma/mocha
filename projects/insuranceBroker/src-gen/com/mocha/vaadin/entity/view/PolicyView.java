package com.mocha.vaadin.entity.view;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.ib.model.Policy;

public class PolicyView extends EntityViewPanel implements Viewer {
	
	public PolicyView() {
		super();
	}

	public void build() {
		final IViewPanel viewPanel = createViewPanel();
		ISectionPanel sectionPanel;
		FieldStatus fieldStatus;
		
		sectionPanel = createSectionPanel("prdtPolicy");
		sectionPanel.setLabel("Product of Policy");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Customer").setPath("customer").setType("com.mocha.ib.model.InsuranceCustomer").setStyle("ref").setExpression("name");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Company").setPath("insuranceCompany").setType("String").setStyle("unit");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Category").setPath("category").setType("String").setStyle("unit");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Product").setPath("insuranceProduct").setType("String").setStyle("unit");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		sectionPanel = createSectionPanel("policyInfo");
		sectionPanel.setLabel("Policy Information");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Policy No").setPath("policyNo").setType("String").setChangeLine(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Effective Date").setPath("effectiveDate").setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Expiry Date").setPath("expiryDate").setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Premium").setPath("premium").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Commission").setPath("commission").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		sectionPanel = createSectionPanel("policyMark");
		sectionPanel.setLabel("Policy Mark");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Mark").setPath("mark").setType("String").setStyle("textarea").setWholeRow(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		IActionPanel actionPanel = createActionPanel();
		viewPanel.setActionPanel(actionPanel);
		actionPanel.addButton(createActionButton("save", "Save", "Save"));
		actionPanel.addButton(createActionButton("back", "Back", "Back"));
		
		addComponent(viewPanel);
	}

	public Class getEntityClass() {
		return Policy.class;
	}
	
	public String getViewerTitle() {
		return "Create Policy";
	}

}

