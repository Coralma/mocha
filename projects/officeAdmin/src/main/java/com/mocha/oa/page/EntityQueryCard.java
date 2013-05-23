/**
 * 
 */
package com.mocha.oa.page;

import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.DefaultSearchCardActionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.DefaultSectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.SearchEntityCard;
import com.coral.vaadin.widget.field.FieldStatus;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

/**
 * @author Coral
 *
 */
public class EntityQueryCard extends SearchEntityCard {
	
	public void build() {
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
		
		fieldStatus = FieldStatus.create().setLabel("Type").setPath("type").setType("String").setRequired(true).setCodeTableName("leave_type");
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Date From").setPath("dateFrom").setType("Date").setRequired(true).setChangeLine(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Date To").setPath("dateTo").setType("Date").setRequired(true);
		sectionPanel.addField(createFieldWidget(fieldStatus));
		
		fieldStatus = FieldStatus.create().setLabel("Duration").setPath("duration").setType("Long").setRequired(true).setChangeLine(true);
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
		return null;
	}

}
