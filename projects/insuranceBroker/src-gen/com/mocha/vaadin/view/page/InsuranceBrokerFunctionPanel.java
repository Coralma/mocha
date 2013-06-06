package com.mocha.vaadin.view.page;

import java.util.List;

import com.coral.vaadin.view.template.sat.FunctionMenu;
import com.coral.vaadin.view.template.sat.FunctionPanel;
import com.google.common.collect.Lists;

public class InsuranceBrokerFunctionPanel extends FunctionPanel {
	
	@Override
	public List<FunctionMenu> getCreationFunctionMenu() {
		List<FunctionMenu> creationMenus = Lists.newArrayList();
		creationMenus.add(FunctionMenu.create().setName("Create Customer").setLabel("Create  Customer").setViewName("InsCustomerView"));
		creationMenus.add(FunctionMenu.create().setName("Create Policy").setLabel("Create  Policy").setViewName("PolicyView"));
		creationMenus.add(FunctionMenu.create().setName("Create Cliam").setLabel("Create  Cliam").setViewName("ClaimView"));
		creationMenus.add(FunctionMenu.create().setName("Create Serve").setLabel("Create  Serve").setViewName("InsCustomerServeView"));
		return creationMenus;
	}
	
	@Override
	public List<FunctionMenu> getSettingFunctionMenu() {
		return null;
	}
}

