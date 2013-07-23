package com.mocha.vaadin.entity.view;

import java.util.List;

import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.google.common.collect.Lists;
import com.mocha.ib.model.InsuranceCustomer;

public class InsCustomerSearch extends SearchPanel {
	
	private  BasicUser user;
	
	private List entities = Lists.newArrayList();

	private BasicUserDao buDao=SpringContextUtils.getBean(BasicUserDao.class);

	public InsCustomerSearch(BasicUser user) {
		this.setUser(user);
	}
	
	@Override
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
		
		fieldStatus = FieldStatus.create().setLabel("Account Bank").setPath("accountBank").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Account Number").setPath("accountNumber").setType("String");
		createFieldWidget(fieldStatus);
		
		fieldStatus = FieldStatus.create().setLabel("Account Person").setPath("accountPerson").setType("String");
		createFieldWidget(fieldStatus);
		
	}
	
	public Class getEntityCardClass() {
		return InsCustomerSearchCard.class;
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

	public BasicUser getUser() {
		return user;
	}

	public void setUser(BasicUser user) {
		this.user = user;
	}


}

