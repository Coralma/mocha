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
		creationMenus.add(FunctionMenu.create().setName("Create Investment").setLabel("Create  Investment"));
		creationMenus.add(FunctionMenu.create().setName("Create Mortgage").setLabel("Create  Mortgage"));
		return creationMenus;
	}
	
	@Override
	public List<FunctionMenu> getSettingFunctionMenu() {
		List<FunctionMenu> settingMenus = Lists.newArrayList();
		settingMenus.add(FunctionMenu.create().setName("Batch Import").setLabel("Batch  Import"));
		settingMenus.add(FunctionMenu.create().setName("Batch Export").setLabel("Batch  Export"));
		settingMenus.add(FunctionMenu.create().setName("exit").setLabel("Back to Homepage"));
		return settingMenus;
	}
}

