package com.mocha.oa;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.constant.VaadinAppConstant;
import com.google.common.eventbus.EventBus;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class OfficeAdminApplication extends Application {
	private static final long serialVersionUID = 5003822334158074903L;
	private MochaEventBus mochaEventBus = new MochaEventBus();
	
	@Override
	public void init() {
		setLocale(((WebApplicationContext)getContext()).getBrowser().getLocale());
		setTheme("cooperate");
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		Window mainWindow = new Window();
		mainWindow.setContent(layout);
		setMainWindow(mainWindow);
		
		VerticalLayout mainPage = new VerticalLayout();
		mainPage.setWidth(VaadinAppConstant.DEFAULT_WINDOW_SIZE);
		
//		mainPage.addComponent(new Label("Vaadin Application Component"));
		OfficeAdminMainPage page = new OfficeAdminMainPage(mochaEventBus);
		mainPage.addComponent(page);
		
		layout.addComponent(mainPage);
		layout.setComponentAlignment(mainPage, Alignment.TOP_CENTER);
	}
}
