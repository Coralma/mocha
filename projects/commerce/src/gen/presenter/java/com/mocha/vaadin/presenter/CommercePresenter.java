package com.mocha.vaadin.presenter;

import org.vaadin.peter.contextmenu.ContextMenu.ClickEvent;
import org.vaadin.peter.contextmenu.ContextMenu.ClickListener;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.PageChangeEvent;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.view.template.sat.FunctionMenu;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.vaadin.view.page.CommerceMainPage;

public class CommercePresenter extends CommonPresenter implements Presenter, ClickListener {

	public CommercePresenter() {
		this.viewer = new CommerceMainPage();
	}
	
	@Override
	public String getPresenterName() {
		return "Commerce";
	}
	
	@Override
	public void bind() {
		CommerceMainPage page = (CommerceMainPage) viewer;
		page.getControllerMenu().setControllerMenuListener(page);
		page.getFunctionPanel().getCreateContextMenu().addListener(this);
		page.getFunctionPanel().getSettingContextMenu().addListener(this);
	}
	
	@Override
	public void contextItemClick(ClickEvent event) {
		final ContextMenuItem clickedItem = event.getClickedItem();
		CommerceMainPage oaPage = (CommerceMainPage) viewer;
		oaPage.getControllerMenu().cleanMenuStyle();
		FunctionMenu functionMenu = oaPage.getFunctionPanel().getFunctionMenu(clickedItem);
		if("exit".equals(functionMenu.getName())) {
			PageChangeEvent changeEvent = new PageChangeEvent("index");
			changeEvent.setContentPresenterName("home");
			eventBus.post(changeEvent);
		} else {
			AppContentEvent appContentEvent = new AppContentEvent();
			appContentEvent.setViewName(functionMenu.getViewName());
			appContentEvent.setCustomizeClass(functionMenu.getCustomizeClass());
			eventBus.post(appContentEvent);
		}
	}
	
	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		((CommerceMainPage) viewer).setEventBus(eventBus);
	}
}

