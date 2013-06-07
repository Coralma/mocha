package com.mocha.vaadin.entity.view;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.google.common.collect.Lists;
import com.mocha.ib.model.Claim;

public class ClaimSearch extends SearchPanel {
	
	private List entities = Lists.newArrayList();


	public void build() {
		FieldStatus fieldStatus = null;
		fieldStatus = FieldStatus.create().setLabel("Status").setPath("status").setType("String");
		createFieldWidget(fieldStatus);
		
	}

	public Class getEntityCardClass() {
		return ClaimSearchCard.class;
	}
	
	public String getViewerTitle() {
		return "Search Claim";
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

