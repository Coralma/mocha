package com.mocha.vaadin.entity.view;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.co.model.Order;

public class OrderView extends EntityViewPanel implements Viewer {
	
	public OrderView() {
		super();
	}

	public void build() {
		final IViewPanel viewPanel = createViewPanel();
		ISectionPanel sectionPanel;
		FieldStatus fieldStatus;
		
		sectionPanel = createSectionPanel("customerStatus");
		sectionPanel.setLabel("Customer Status");
		viewPanel.addSection(sectionPanel);
		
		fieldStatus = FieldStatus.create().setLabel("Customer").setPath("customer").setType("com.mocha.co.model.CommerceCustomer").setStyle("ref").setExpression("name");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Status").setPath("status").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Purchase Date").setPath("purchaseDate").setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Excepted Ship Date").setPath("exceptedShipDate").setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Order Totals").setPath("orderTotals").setType("String").setChangeLine(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		IActionPanel actionPanel = createActionPanel();
		viewPanel.setActionPanel(actionPanel);
		actionPanel.addButton(createActionButton("save", "Save", "Save"));
		actionPanel.addButton(createActionButton("back", "Back", "Back"));
		
		addComponent(viewPanel);
	}

	public Class getEntityClass() {
		return Order.class;
	}
	
	public String getViewerTitle() {
		return "Create Order";
	}

}

