package com.mocha.vaadin.view.page;

import java.util.List;

import com.coral.vaadin.view.template.sat.FunctionMenu;
import com.coral.vaadin.view.template.sat.FunctionPanel;
import com.google.common.collect.Lists;

public class CustomerManageFunctionPanel extends FunctionPanel {
	
	@Override
	public List<FunctionMenu> getCreationFunctionMenu() {
		List<FunctionMenu> creationMenus = Lists.newArrayList();
		creationMenus.add(FunctionMenu.create().setName("Create Customer").setLabel("Create  Customer").setViewName("CustomerView"));
		creationMenus.add(FunctionMenu.create().setName("Create Customer Activity").setLabel("Create  Customer  Activity").setViewName("CustomerActivityView"));
		creationMenus.add(FunctionMenu.create().setName("Create Customer Server").setLabel("Create  Customer  Server").setViewName("CustomerServerView"));
		return creationMenus;
	}
	
	@Override
	public List<FunctionMenu> getSettingFunctionMenu() {
		return null;
	}
}

