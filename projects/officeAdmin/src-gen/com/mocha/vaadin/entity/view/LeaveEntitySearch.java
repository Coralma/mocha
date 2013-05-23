package com.mocha.vaadin.entity.view;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel;
import com.coral.vaadin.widget.field.FieldStatus;
import com.google.common.collect.Lists;
import com.mocha.oa.model.Leave;

public class LeaveEntitySearch extends SearchPanel {
	
	private List entities = Lists.newArrayList();


	public void build() {
		FieldStatus fieldStatus = null;
		fieldStatus = FieldStatus.create().setLabel("Type").setPath("type").setType("String").setRequired(true).setCodeTableName("leave_type");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Date From").setPath("dateFrom").setType("Date").setRequired(true).setChangeLine(true);
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Date To").setPath("dateTo").setType("Date").setRequired(true);
		createFieldWidget(fieldStatus);
		
	}

	public Class getEntityCardClass() {
		return LeaveEntitySearchCard.class;
	}
	
	public String getViewerTitle() {
		return "Search Leave";
	}
	
	@Override
	public List getEntityList() {
		return entities;
	}
	
	@Override
	public void setValue(Object value) {
		if(value != null) {
			entities = (List) value;
		}
	}

}

