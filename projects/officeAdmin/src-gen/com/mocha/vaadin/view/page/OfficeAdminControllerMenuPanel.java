package com.mocha.vaadin.view.page;

import com.coral.vaadin.view.template.sat.ControllerMenuPanel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
public class OfficeAdminControllerMenuPanel extends ControllerMenuPanel {
	
	public void build() {
		try {
			// init app name title
			Label OfficeAdmintitle = createAppTitle("OfficeAdmin");
			addComponent(OfficeAdmintitle);
			
			MenuAction action = null;
			Label groupTitle = null;
			Button menuItem = null;
			// create the main menu item.
			action = new MenuAction();
			action.setView("LeaveEntitySearch");
			menuItem = createMenu("Home",action);
			addComponent(menuItem);
			
			// create the menu group and sub menu item.
			groupTitle = createMenuTitle("Leave Management");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setView("LeaveEntityView");
			menuItem = createMenu("Leave Apply",action);
			addComponent(menuItem);
			action = new MenuAction();
			action.setPanel(Class.forName("com.mocha.oa.page.OfficeAdminIndexPresenter"));
			menuItem = createMenu("Leave Query",action);
			addComponent(menuItem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

