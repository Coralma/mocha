package com.mocha.vaadin.view.page;

import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppMainPage;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.view.template.sat.ControllerMenuPanel.ControllerMenuListener;
import com.mocha.vaadin.entity.presenter.*;
import com.mocha.vaadin.view.page.InsuranceBrokerControllerMenuPanel;
import com.mocha.vaadin.view.page.InsuranceBrokerFunctionPanel;
public class InsuranceBrokerMainPage extends AppMainPage implements ControllerMenuListener {
	
	private InsuranceBrokerControllerMenuPanel controllerMenu = new InsuranceBrokerControllerMenuPanel();
	private InsuranceBrokerFunctionPanel functionPanel = new InsuranceBrokerFunctionPanel();
	public void attach() {
		addComponent(controllerMenu);
		addComponent(functionPanel);
		// init the main page.
		AppContentEvent event = new AppContentEvent();
		event.setCustomizeClass("com.mocha.ib.presenter.IBDashboardPresenter");
		controllerMenu.setMenuStyle(null, "com.mocha.ib.presenter.IBDashboardPresenter");
		eventBus.post(event);
		
	}
	public void showView(String viewName) {
		Presenter presenter = null;
		if("InsCustomerSearch".equals(viewName)) {
			presenter = new InsCustomerSearchPresenter(eventBus);
		}
		if("InsCustomerView".equals(viewName)) {
			presenter = new InsCustomerViewPresenter(eventBus);
		}
		if("ClaimSearch".equals(viewName)) {
			presenter = new ClaimSearchPresenter(eventBus);
		}
		if("ClaimView".equals(viewName)) {
			presenter = new ClaimViewPresenter(eventBus);
		}
		if("PolicySearch".equals(viewName)) {
			presenter = new PolicySearchPresenter(eventBus);
		}
		if("PolicyView".equals(viewName)) {
			presenter = new PolicyViewPresenter(eventBus);
		}
		if("InsCustomerServeSearch".equals(viewName)) {
			presenter = new InsCustomerServeSearchPresenter(eventBus);
		}
		if("InsCustomerServeView".equals(viewName)) {
			presenter = new InsCustomerServeViewPresenter(eventBus);
		}
		if("InsuranceCompanySearch".equals(viewName)) {
			presenter = new InsuranceCompanySearchPresenter(eventBus);
		}
		if("InsuranceCompanyView".equals(viewName)) {
			presenter = new InsuranceCompanyViewPresenter(eventBus);
		}
		if("InsuranceProductSearch".equals(viewName)) {
			presenter = new InsuranceProductSearchPresenter(eventBus);
		}
		if("InsuranceProductView".equals(viewName)) {
			presenter = new InsuranceProductViewPresenter(eventBus);
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
	public InsuranceBrokerControllerMenuPanel getControllerMenu() {
		return controllerMenu;
	}
	
	/**
	 * @return the functionPanel
	 */
	public InsuranceBrokerFunctionPanel getFunctionPanel() {
		return functionPanel;
	}
}

