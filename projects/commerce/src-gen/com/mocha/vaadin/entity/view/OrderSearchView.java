package com.mocha.vaadin.entity.view;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.google.common.collect.Lists;
import com.mocha.co.model.Order;

public class OrderSearchView extends SearchPanel {
	
	private List entities = Lists.newArrayList();


	public void build() {
		FieldStatus fieldStatus = null;
		fieldStatus = FieldStatus.create().setLabel("Order Totals").setPath("orderTotals").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Order Product Summary").setPath("orderProductSummary").setType("String");
		createFieldWidget(fieldStatus);
		
	}

	public Class getEntityCardClass() {
		return OrderSearchViewCard.class;
	}
	
	public String getViewerTitle() {
		return "Search Order";
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

