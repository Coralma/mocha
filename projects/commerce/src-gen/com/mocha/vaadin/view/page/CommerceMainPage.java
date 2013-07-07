package com.mocha.vaadin.view.page;

import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppMainPage;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.foundation.report.AbstrctAppRawData;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.view.template.sat.ControllerMenuPanel.ControllerMenuListener;
import com.mocha.vaadin.entity.presenter.*;
import com.mocha.vaadin.view.page.CommerceControllerMenuPanel;
import com.mocha.vaadin.view.page.CommerceFunctionPanel;
public class CommerceMainPage extends AppMainPage implements ControllerMenuListener {
	
	private CommerceControllerMenuPanel controllerMenu = new CommerceControllerMenuPanel();
	private CommerceFunctionPanel functionPanel = new CommerceFunctionPanel();
	public void attach() {
		addComponent(controllerMenu);
		addComponent(functionPanel);
		// init the main page.
		AppContentEvent event = new AppContentEvent();
		event.setCustomizeClass("com.mocha.co.presenter.CoDashboardPresenter");
		controllerMenu.setMenuStyle(null, "com.mocha.co.presenter.CoDashboardPresenter");
		eventBus.post(event);
		
	}
	public void showView(String viewName) {
		Presenter presenter = null;
		if("CommerceCustomerSearch".equals(viewName)) {
			presenter = new CommerceCustomerSearchPresenter(eventBus);
		}
		if("CommerceCustomerView".equals(viewName)) {
			presenter = new CommerceCustomerViewPresenter(eventBus);
		}
		if("OrderSearchView".equals(viewName)) {
			presenter = new OrderSearchViewPresenter(eventBus);
		}
		if("OrderView".equals(viewName)) {
			presenter = new OrderViewPresenter(eventBus);
		}
		if("ProductSearch".equals(viewName)) {
			presenter = new ProductSearchPresenter(eventBus);
		}
		if("ProductView".equals(viewName)) {
			presenter = new ProductViewPresenter(eventBus);
		}
		if("SupplierSearch".equals(viewName)) {
			presenter = new SupplierSearchPresenter(eventBus);
		}
		if("SupplierView".equals(viewName)) {
			presenter = new SupplierViewPresenter(eventBus);
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
	public CommerceControllerMenuPanel getControllerMenu() {
		return controllerMenu;
	}
	
	/**
	 * @return the functionPanel
	 */
	public CommerceFunctionPanel getFunctionPanel() {
		return functionPanel;
	}
}

