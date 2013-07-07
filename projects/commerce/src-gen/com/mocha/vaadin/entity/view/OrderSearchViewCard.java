package com.mocha.vaadin.entity.view;
import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.DefaultSectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.SearchEntityCard;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.co.model.Order;

public class OrderSearchViewCard extends SearchEntityCard {
	

	public void attach() {
		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setWidth(cardWidth);
		this.addComponent(hlayout);
		
		Layout icon = getSearchIcon();
		hlayout.addComponent(icon);
		hlayout.setComponentAlignment(icon,Alignment.MIDDLE_CENTER);
		
		ISectionPanel sectionPanel = new DefaultSectionPanel();
		sectionPanel.setWidth(cardInfoWidth);
		sectionPanel.setSpacing(false);
		
		FieldStatus fieldStatus = null;
		fieldStatus = FieldStatus.create().setLabel("Customer Name").setPath("customer.name").setType("com.mocha.co.model.CommerceCustomer");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Source").setPath("customer.source").setType("com.mocha.co.model.CommerceCustomer");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Purchase Date").setPath("purchaseDate").setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Ship Date").setPath("exceptedShipDate").setType("Date");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Total").setPath("orderTotals").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Order Product Summary").setPath("orderProductSummary").setType("String").setWholeRow(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		hlayout.addComponent(sectionPanel);
		hlayout.setComponentAlignment(sectionPanel,Alignment.TOP_LEFT);
		
		IActionPanel actionPanel = createCardActionPanel();
		actionPanel.addButton(createActionButton("view", "View", "View"));		
		hlayout.addComponent(actionPanel);
		hlayout.setComponentAlignment(actionPanel, Alignment.MIDDLE_LEFT);
	}

	@Override
	public String getIconName() {
		return "card-ebay.png";
		
	}

}

