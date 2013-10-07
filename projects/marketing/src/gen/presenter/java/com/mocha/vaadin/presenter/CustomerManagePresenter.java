package com.mocha.vaadin.presenter;

import org.vaadin.peter.contextmenu.ContextMenu.ClickEvent;
import org.vaadin.peter.contextmenu.ContextMenu.ClickListener;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.view.template.sat.FunctionMenu;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.vaadin.view.page.CustomerManageMainPage;

public class CustomerManagePresenter extends CommonPresenter implements Presenter, ClickListener  {

	public CustomerManagePresenter() {
		this.viewer = new CustomerManageMainPage();
	}
	
	@Override
	public String getPresenterName() {
		return "CustomerManage";
	}
	
	@Override
	public void bind() {
		CustomerManageMainPage page = (CustomerManageMainPage) viewer;
		page.getControllerMenu().setControllerMenuListener(page);
		page.getFunctionPanel().getCreateContextMenu().addListener(this);
		page.getFunctionPanel().getSettingContextMenu().addListener(this);
	}

	@Override
	public void contextItemClick(ClickEvent event) {
		final ContextMenuItem clickedItem = event.getClickedItem();
		CustomerManageMainPage oaPage = (CustomerManageMainPage) viewer;
		oaPage.getControllerMenu().cleanMenuStyle();
		FunctionMenu functionMenu = oaPage.getFunctionPanel().getFunctionMenu(clickedItem);
		AppContentEvent appContentEvent = new AppContentEvent();
		appContentEvent.setViewName(functionMenu.getViewName());
		appContentEvent.setCustomizeClass(functionMenu.getCustomizeClass());
		eventBus.post(appContentEvent);
	}
	
	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		((CustomerManageMainPage) viewer).setEventBus(eventBus);
	}
}

