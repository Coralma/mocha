package com.mocha.vaadin.view.page;

import java.util.List;

import com.coral.vaadin.view.template.sat.FunctionMenu;
import com.coral.vaadin.view.template.sat.FunctionPanel;
import com.google.common.collect.Lists;

public class CommerceFunctionPanel extends FunctionPanel {
	
	@Override
	public List<FunctionMenu> getCreationFunctionMenu() {
		List<FunctionMenu> creationMenus = Lists.newArrayList();
		creationMenus.add(FunctionMenu.create().setName("Create Order").setLabel("Create  Order"));
		creationMenus.add(FunctionMenu.create().setName("Create Customer").setLabel("Create  Customer"));
		creationMenus.add(FunctionMenu.create().setName("Create Supplier").setLabel("Create  Supplier"));
		creationMenus.add(FunctionMenu.create().setName("Create Product").setLabel("Create  Product"));
		creationMenus.add(FunctionMenu.create().setName("Create Product Incomings").setLabel("Create  Product  Incomings"));
		creationMenus.add(FunctionMenu.create().setName("Create Product Outgoings").setLabel("Create  Product  Outgoings"));
		return creationMenus;
	}
	
	@Override
	public List<FunctionMenu> getSettingFunctionMenu() {
		List<FunctionMenu> settingMenus = Lists.newArrayList();
		settingMenus.add(FunctionMenu.create().setName("Data Batch Import").setLabel("Data  Batch  Import"));
		settingMenus.add(FunctionMenu.create().setName("Data Batch Export").setLabel("Data  Batch  Export"));
		settingMenus.add(FunctionMenu.create().setName("exit").setLabel("Back to Homepage"));
		return settingMenus;
	}
}

