package com.mocha.oa;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.PageChangeEvent;
import com.coral.vaadin.view.template.sat.AppMainPage;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.ActionButton;
import com.google.common.eventbus.Subscribe;
import com.mocha.oa.fwk.OfficeAdminControllerMenuPanel;
import com.mocha.oa.fwk.FunctionPanel;

/**
 * 
 * @author Coral.Ma
 *
 */
public class OfficeAdminMainPage extends AppMainPage implements Viewer {
	
	public MochaEventBus eventBus;
	private OfficeAdminControllerMenuPanel controllerMenu = new OfficeAdminControllerMenuPanel();
	private FunctionPanel functionPanel = new FunctionPanel();
	
	public OfficeAdminMainPage(MochaEventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public void attach() {
		addComponent(controllerMenu);
		addComponent(functionPanel);
	}
	
	public String indexPage() {
		return "OfficeAdminIndex";
	}
	
	@Subscribe
	public void viewChange(PageChangeEvent event) {
		
	}
	
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validate(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionButton getButton(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget getWidget(String widgetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Widget> getWidgets() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the controllerMenu
	 */
	public OfficeAdminControllerMenuPanel getControllerMenu() {
		return controllerMenu;
	}

	/**
	 * @param controllerMenu the controllerMenu to set
	 */
	public void setControllerMenu(OfficeAdminControllerMenuPanel controllerMenu) {
		this.controllerMenu = controllerMenu;
	}

	/**
	 * @return the functionPanel
	 */
	public FunctionPanel getFunctionPanel() {
		return functionPanel;
	}

	/**
	 * @param functionPanel the functionPanel to set
	 */
	public void setFunctionPanel(FunctionPanel functionPanel) {
		this.functionPanel = functionPanel;
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
