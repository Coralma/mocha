package com.mocha.vaadin.view.page;

import com.coral.vaadin.view.template.sat.ControllerMenuPanel;
import com.coral.vaadin.view.template.sat.ControllerMenuPanel.MenuAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
public class InsuranceBrokerControllerMenuPanel extends ControllerMenuPanel {
	
	public void build() {
		try {
			// init app name title
			Label InsuranceBrokertitle = createAppTitle("Insurance Broker");
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
			menuItem = createMenu("Customer  Manage",action);
			addComponent(menuItem);
			action = new MenuAction();
			action.setView("InsCustomerServeSearch");
			menuItem = createMenu("Customer  Serve",action);
			addComponent(menuItem);
			groupTitle = createMenuTitle("Policy Management");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setView("PolicySearch");
			menuItem = createMenu("Policy  Manage",action);
			addComponent(menuItem);
			action = new MenuAction();
			action.setView("ClaimSearch");
			menuItem = createMenu("Cliam  Register",action);
			addComponent(menuItem);
			groupTitle = createMenuTitle("Company and Product");
			addComponent(groupTitle);
			action = new MenuAction();
			action.setView("InsuranceCompanySearch");
			menuItem = createMenu("Cooperated  Company",action);
			addComponent(menuItem);
			action = new MenuAction();
			action.setView("InsuranceProductSearch");
			menuItem = createMenu("Product  Management",action);
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

