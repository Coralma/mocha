package com.mocha.vaadin.view.page;

import java.util.List;

import com.coral.vaadin.view.template.sat.FunctionMenu;
import com.coral.vaadin.view.template.sat.FunctionPanel;
import com.google.common.collect.Lists;

public class OfficeAdminFunctionPanel extends FunctionPanel {
	
	@Override
	public List<FunctionMenu> getCreationFunctionMenu() {
		List<FunctionMenu> creationMenus = Lists.newArrayList();
		creationMenus.add(FunctionMenu.create().setName("createLeave").setLabel("Create Leave").setViewName("LeaveEntityView"));
		return creationMenus;
	}
	
	@Override
	public List<FunctionMenu> getSettingFunctionMenu() {
		return null;
	}
}

