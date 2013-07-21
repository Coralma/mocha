package com.mocha.vaadin.view.page;

import com.coral.vaadin.view.template.sat.ControllerMenuPanel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
public class InsuranceBrokerControllerMenuPanel extends ControllerMenuPanel {
	
	public void build() {
		try {
			// init app name title
			Label InsuranceBrokertitle = createAppTitle("Financial Advisor");
			addComponent(InsuranceBrokertitle);
			
			MenuAction action = null;
			Label groupTitle = null;
			Button menuItem = null;
			// create the main menu item.
			action = new MenuAction();
			action.setPanel(Class.forName("com.mocha.ib.presenter.IBDashboardPresenter"));
			menuItem = createMenu("Home",action);
			addComponent(menuItem);
			
			// create the menu group and sub menu item.
			groupTitle = createMenuTitle("Customer Management");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setView("InsCustomerSearch");
			menuItem = createMenu("Manage  Customers",action);
			addComponent(menuItem);
			groupTitle = createMenuTitle("Company and Product");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setView("InsuranceCompanySearch");
			menuItem = createMenu("Cooperating  Companies",action);
			addComponent(menuItem);
			action = new MenuAction();
			action.setView("InsuranceProductSearch");
			menuItem = createMenu("Products and  Services",action);
			addComponent(menuItem);
			groupTitle = createMenuTitle("Report Center");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setPanel(Class.forName("com.mocha.report.CrmReportPresenter"));
			menuItem = createMenu("Reports",action);
			addComponent(menuItem);
			action = new MenuAction();
			action.setPanel(Class.forName("com.mocha.soicalAPI.LinkedinLoginPresnter"));
			menuItem = createMenu("Login With LinkedIn",action);
			addComponent(menuItem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

