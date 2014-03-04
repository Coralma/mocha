package com.mocha.vaadin.presenter;

import org.vaadin.peter.contextmenu.ContextMenu.ClickEvent;
import org.vaadin.peter.contextmenu.ContextMenu.ClickListener;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;
import com.coral.vaadin.controller.PageChangeEvent;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.view.template.sat.FunctionMenu;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.vaadin.view.page.OfficeAdminMainPage;

public class OfficeAdminPresenter extends CommonPresenter implements Presenter, ClickListener {

	public OfficeAdminPresenter() {
		this.viewer = new OfficeAdminMainPage();
	}
	
	@Override
	public String getPresenterName() {
		return "OfficeAdmin";
	}
	
	@Override
	public void bind() {
		OfficeAdminMainPage page = (OfficeAdminMainPage) viewer;
		page.getControllerMenu().setControllerMenuListener(page);
		page.getFunctionPanel().getCreateContextMenu().addListener(this);
		page.getFunctionPanel().getSettingContextMenu().addListener(this);
	}
	
	@Override
	public void contextItemClick(ClickEvent event) {
		final ContextMenuItem clickedItem = event.getClickedItem();
		OfficeAdminMainPage oaPage = (OfficeAdminMainPage) viewer;
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
		((OfficeAdminMainPage) viewer).setEventBus(eventBus);
	}
}

