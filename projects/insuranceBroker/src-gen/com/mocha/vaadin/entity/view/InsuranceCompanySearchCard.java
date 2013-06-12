package com.mocha.vaadin.entity.view;
import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.DefaultSectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.SearchEntityCard;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.ib.model.InsuranceCompany;

public class InsuranceCompanySearchCard extends SearchEntityCard {
	

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
		fieldStatus = FieldStatus.create().setLabel("Company Name").setPath("companyName").setType("String");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Level").setPath("level").setType("String").setCodeTableName("company-level");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Description").setPath("description").setType("String").setWholeRow(true);
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
		return "card-company.png";
		
	}

}

