package com.mocha.vaadin.view.page;

import com.coral.vaadin.view.template.sat.ControllerMenuPanel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
public class CustomerManageControllerMenuPanel extends ControllerMenuPanel {
	
	public void build() {
		try {
			// init app name title
			Label CustomerManagetitle = createAppTitle("Customer Manage");
			addComponent(CustomerManagetitle);
			
			MenuAction action = null;
			Label groupTitle = null;
			Button menuItem = null;
			// create the main menu item.
			action = new MenuAction();
			action.setPanel(Class.forName("com.mocha.report.CrmDashboardPresenter"));
			menuItem = createMenu("Home",action);
			addComponent(menuItem);
			
			// create the menu group and sub menu item.
			groupTitle = createMenuTitle("Customer Management");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setView("CustomerSearch");
			menuItem = createMenu("Customer",action);
			addComponent(menuItem);
			groupTitle = createMenuTitle("Customer Event");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setView("CustomerActivitySearch");
			menuItem = createMenu("Campaign",action);
			addComponent(menuItem);
			action = new MenuAction();
			action.setView("CustomerServerSearch");
			menuItem = createMenu("Customer  Serve",action);
			addComponent(menuItem);
			groupTitle = createMenuTitle("Report Center");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setPanel(Class.forName("com.mocha.report.CrmReportPresenter"));
			menuItem = createMenu("Customer  Report",action);
			addComponent(menuItem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

