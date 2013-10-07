package com.mocha.vaadin.entity.view;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.marketing.model.Customer;

public class CustomerView extends EntityViewPanel implements Viewer {
	
	public CustomerView() {
		super();
	}

	public void build() {
		final IViewPanel viewPanel = createViewPanel();
		ISectionPanel sectionPanel;
		FieldStatus fieldStatus;
		
		sectionPanel = createSectionPanel("companyCustInfo");
		sectionPanel.setLabel("Customer Information");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Customer Name").setPath("name").setType("String").setRequired(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Status").setPath("status").setType("String").setCodeTableName("customer-status");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("District").setPath("district").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Address").setPath("address").setType("String").setStyle("textarea").setWholeRow(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Postcode").setPath("postcode").setType("String").setChangeLine(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		sectionPanel = createSectionPanel("companyCustInfo");
		sectionPanel.setLabel("Contect Person");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Contect Person").setPath("contectPerson").setType("String").setRequired(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Mobile").setPath("mobile").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Phone").setPath("phone").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Fax").setPath("fax").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Email").setPath("email").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		sectionPanel = createSectionPanel("accountInfo");
		sectionPanel.setLabel("Company Customer Bank Account");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Account Bank").setPath("accountBank").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Account Number").setPath("accountNumber").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Account Person").setPath("accountPerson").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Account Mark").setPath("accountMark").setType("String").setStyle("textarea").setWholeRow(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		sectionPanel = createSectionPanel("custMark");
		sectionPanel.setLabel("Company Customer Mark");
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
		return Customer.class;
	}
	
	public String getViewerTitle() {
		return "Create Customer";
	}

}

