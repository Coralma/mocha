package com.mocha.vaadin.view.page;

import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppMainPage;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.view.template.sat.ControllerMenuPanel.ControllerMenuListener;
import com.mocha.report.CrmDashboardPresenter;
import com.mocha.vaadin.entity.presenter.*;
import com.mocha.vaadin.view.page.CustomerManageControllerMenuPanel;
import com.mocha.vaadin.view.page.CustomerManageFunctionPanel;
public class CustomerManageMainPage extends AppMainPage implements ControllerMenuListener {
	
	private CustomerManageControllerMenuPanel controllerMenu = new CustomerManageControllerMenuPanel();
	private CustomerManageFunctionPanel functionPanel = new CustomerManageFunctionPanel();
	public void attach() {
		addComponent(controllerMenu);
		addComponent(functionPanel);
		// init the main page.
		AppContentEvent event = new AppContentEvent();
		event.setCustomizeClass(CrmDashboardPresenter.class.getName());
		eventBus.post(event);
		controllerMenu.setMenuStyle(null, CrmDashboardPresenter.class.getName());
	}
	public void showView(String viewName) {
		Presenter presenter = null;
		if("CustomerSearch".equals(viewName)) {
			presenter = new CustomerSearchPresenter(eventBus);
		}
		if("CustomerView".equals(viewName)) {
			presenter = new CustomerViewPresenter(eventBus);
		}
		if("CustomerActivitySearch".equals(viewName)) {
			presenter = new CustomerActivitySearchPresenter(eventBus);
		}
		if("CustomerActivityView".equals(viewName)) {
			presenter = new CustomerActivityViewPresenter(eventBus);
		}
		if("CustomerServerSearch".equals(viewName)) {
			presenter = new CustomerServerSearchPresenter(eventBus);
		}
		if("CustomerServerView".equals(viewName)) {
			presenter = new CustomerServerViewPresenter(eventBus);
		}
		if(presenter == null) {
			throw new RuntimeException(this.getClass() + "show view : " + viewName + " doesn't exist.");
		}
		functionPanel.setContent(presenter.go());
		presenter.bind();
	}
	
	public void showPanel(Class customizedPresenter) {
		try {
			Presenter presenter = (Presenter) customizedPresenter.getConstructor(MochaEventBus.class).newInstance(eventBus);
			if(presenter.isFullSize()) {
				functionPanel.setFullContent(presenter.go());
			} else {
				functionPanel.setContent(presenter.go());
			}
			presenter.bind();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(this.getClass() + "Show Customized Class : " + customizedPresenter + " error.");
		}
	}
	/**
	 * @return the controllerMenu
	 */
	public CustomerManageControllerMenuPanel getControllerMenu() {
		return controllerMenu;
	}
	
	/**
	 * @return the functionPanel
	 */
	public CustomerManageFunctionPanel getFunctionPanel() {
		return functionPanel;
	}
}

