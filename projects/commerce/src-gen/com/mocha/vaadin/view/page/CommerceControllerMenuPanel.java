package com.mocha.vaadin.view.page;

import com.coral.vaadin.view.template.sat.ControllerMenuPanel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
public class CommerceControllerMenuPanel extends ControllerMenuPanel {
	
	public void build() {
		try {
			// init app name title
			Label Commercetitle = createAppTitle("E-Commerce");
			addComponent(Commercetitle);
			
			MenuAction action = null;
			Label groupTitle = null;
			Button menuItem = null;
			// create the main menu item.
			action = new MenuAction();
			action.setPanel(Class.forName("com.mocha.co.presenter.CoDashboardPresenter"));
			menuItem = createMenu("DashBoard",action);
			addComponent(menuItem);
			
			// create the menu group and sub menu item.
			groupTitle = createMenuTitle("Order Management");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setView("OrderSearchView");
			menuItem = createMenu("Manage  Orders",action);
			addComponent(menuItem);
			action = new MenuAction();
			menuItem = createMenu("Manage  Logistics",action);
			addComponent(menuItem);
			action = new MenuAction();
			menuItem = createMenu("Return and  Exchange",action);
			addComponent(menuItem);
			action = new MenuAction();
			menuItem = createMenu("Manage  Customers",action);
			addComponent(menuItem);
			groupTitle = createMenuTitle("Promotion and Product");
			addComponent(groupTitle);
			action = new MenuAction();
			menuItem = createMenu("Promotion  Setting",action);
			addComponent(menuItem);
			action = new MenuAction();
			menuItem = createMenu("Product  Definition",action);
			addComponent(menuItem);
			action = new MenuAction();
			menuItem = createMenu("Supplier  Definition",action);
			addComponent(menuItem);
			groupTitle = createMenuTitle("Stock Management");
			addComponent(groupTitle);
			action = new MenuAction();
			menuItem = createMenu("Manage  Stock",action);
			addComponent(menuItem);
			groupTitle = createMenuTitle("Report Center");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setPanel(Class.forName("com.mocha.report.CrmReportPresenter"));
			menuItem = createMenu("Reports",action);
			addComponent(menuItem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

