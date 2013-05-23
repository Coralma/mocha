package com.mocha.vaadin.view.page;

import com.coral.vaadin.view.template.sat.AppMainPage;
import com.mocha.vaadin.view.page.OfficeAdminControllerMenuPanel;
import com.mocha.vaadin.view.page.OfficeAdminFunctionPanel;
public class OfficeAdminMainPage extends AppMainPage {
	
	private OfficeAdminControllerMenuPanel controllerMenu = new OfficeAdminControllerMenuPanel();
	private OfficeAdminFunctionPanel functionPanel = new OfficeAdminFunctionPanel();
	public void attach() {
		addComponent(controllerMenu);
		addComponent(functionPanel);
	}
	/**
	 * @return the controllerMenu
	 */
	public OfficeAdminControllerMenuPanel getControllerMenu() {
		return controllerMenu;
	}
	
	/**
	 * @return the functionPanel
	 */
	public OfficeAdminFunctionPanel getFunctionPanel() {
		return functionPanel;
	}
	@Override
	public void showView(String viewName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showPanel(Class customizedPresenter) {
		// TODO Auto-generated method stub
		
	}
}

