package com.mocha.vaadin.view.page;

import org.vaadin.peter.contextmenu.ContextMenu.ClickEvent;
import org.vaadin.peter.contextmenu.ContextMenu.ClickListener;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.ControllerMenuPanel.ControllerMenuListener;
import com.coral.vaadin.view.template.sat.FunctionMenu;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.vaadin.entity.presenter.LeaveEntitySearchPresenter;
import com.mocha.vaadin.entity.presenter.LeaveEntityViewPresenter;

public class OfficeAdminPresenter extends CommonPresenter implements Presenter,ControllerMenuListener,ClickListener {

	public OfficeAdminPresenter() {
		this.viewer = new OfficeAdminMainPage();
	}
	
	@Override
	public String getPresenterName() {
		return "OfficeAdmin";
	}
	
	@Override
	public void bind() {
		final OfficeAdminMainPage oaPage = (OfficeAdminMainPage) viewer;
		oaPage.getControllerMenu().setControllerMenuListener(this);
		oaPage.getFunctionPanel().getCreateContextMenu().addListener(this);
		oaPage.getFunctionPanel().getSettingContextMenu().addListener(this);
	}

	@Override
	public void showView(String viewName) {
		Presenter presenter = null;
		if("LeaveEntityView".equals(viewName)) {
			presenter = new LeaveEntityViewPresenter(eventBus);
		} else if("LeaveEntitySearch".equals(viewName)) {
			presenter = new LeaveEntitySearchPresenter(eventBus);
		}
		if(presenter == null) {
			System.out.println(viewName + " doesn't exist.");
		}
		OfficeAdminMainPage oaPage = (OfficeAdminMainPage) viewer;
		oaPage.getFunctionPanel().setContent(presenter.go());
		presenter.bind();
	}
	
	@Override
	public void showPanel(Class panel) {
		try {
			Presenter presenter = (Presenter)panel.getConstructor(MochaEventBus.class).newInstance(eventBus);
//			oaPage.getFunctionPanel().changeToEntityEditTitlePanel(presenter.getPresenterName());
			OfficeAdminMainPage oaPage = (OfficeAdminMainPage) viewer;
			oaPage.getFunctionPanel().setContent(presenter.go());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showPanel(String panelClassName) {
		try {
			showPanel(Class.forName(panelClassName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextItemClick(ClickEvent event) {
		final ContextMenuItem clickedItem = event.getClickedItem();
		OfficeAdminMainPage oaPage = (OfficeAdminMainPage) viewer;
		FunctionMenu functionMenu = oaPage.getFunctionPanel().getFunctionMenu(clickedItem);
		if(functionMenu.getViewName() != null) {
			showView(functionMenu.getViewName());
		} else if(functionMenu.getCustomizeClass() != null) {
			showPanel(functionMenu.getCustomizeClass());
		}
	}
}

