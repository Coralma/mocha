package com.mocha.vaadin.entity.view;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.google.common.collect.Lists;
import com.mocha.co.model.CommerceCustomer;

public class CommerceCustomerSearch extends SearchPanel {
	
	private List entities = Lists.newArrayList();


	public void build() {
		FieldStatus fieldStatus = null;
		fieldStatus = FieldStatus.create().setLabel("Customer Type").setPath("customerType").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Status").setPath("status").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Name").setPath("name").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("District").setPath("district").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Contect Person").setPath("contectPerson").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Mobile").setPath("mobile").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Phone").setPath("phone").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Fax").setPath("fax").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Email").setPath("email").setType("String");
		createFieldWidget(fieldStatus);
		
	}

	public Class getEntityCardClass() {
		return CommerceCustomerSearchCard.class;
	}
	
	public String getViewerTitle() {
		return "Search Customer";
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

