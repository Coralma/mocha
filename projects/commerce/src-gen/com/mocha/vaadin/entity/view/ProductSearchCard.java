package com.mocha.vaadin.entity.view;
import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.DefaultSectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.SearchEntityCard;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.co.model.CommerceProduct;

public class ProductSearchCard extends SearchEntityCard {
	

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
		fieldStatus = FieldStatus.create().setLabel("Product Name").setPath("productName").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Product Code").setPath("productCode").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Brand").setPath("brand").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Unit").setPath("unit").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Spec").setPath("spec").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Color").setPath("color").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Sale Price").setPath("salePrice").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		hlayout.addComponent(sectionPanel);
		hlayout.setComponentAlignment(sectionPanel,Alignment.TOP_LEFT);
		
		IActionPanel actionPanel = createCardActionPanel();
		actionPanel.addButton(createActionButton("edit", "Edit", "Edit"));		
		actionPanel.addButton(createActionButton("delete", "Delete", "Delete"));		
		hlayout.addComponent(actionPanel);
		hlayout.setComponentAlignment(actionPanel, Alignment.MIDDLE_LEFT);
	}

	@Override
	public String getIconName() {
		return "card-cust.png";
		
	}

}

