/**
 * 
 */
package com.coral.vaadin.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.Menu;
import com.coral.foundation.security.CommonSecurityManager;
import com.coral.vaadin.configuration.MemoryPropertyConfiguration;
import com.coral.vaadin.controller.PageChangeEvent;
import com.coral.vaadin.controller.PageController;
import com.coral.vaadin.logging.LoggerConfiguration;
import com.coral.vaadin.resource.AppResource;
import com.coral.vaadin.resource.ModelCenter;
import com.vaadin.Application;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Administrator
 * 
 */
public abstract class CommonApplication extends Application {

	protected List<Mocha> CORALS;
	protected MochaEventBus eventBus;

	protected static Logger logger = LoggerFactory.getLogger(CommonApplication.class);

	private String configFileName = "system.properties";

	@Override
	public void init() {
		initConfig();
		setTheme(getTheme());
		Window mainWindow = new Window(getWindowTitle());
		mainWindow.setWidth("100%");
		setMainWindow(mainWindow);
		eventBus = new MochaEventBus();
		// init security manager
		CommonSecurityManager commonSecuirtyManager = new CommonSecurityManager();
		initPage();
	}

	private void initConfig() {
		// load config properties
		MemoryPropertyConfiguration configuration = new MemoryPropertyConfiguration();
		String logStorage = configuration.findConfiguration("log.storage");
		LoggerConfiguration congfiguration = new LoggerConfiguration(logStorage);
		congfiguration.doConfigure(logStorage);

	}

	public void initModel(String MODEL_XML) {
		try {
			CORALS = ModelCenter.getModel(MODEL_XML);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getWindowTitle() {
		return "Coral Application";
	}

	public String getTheme() {
		return "coral";
	}

	public void initPage() {

		VerticalLayout main = new VerticalLayout();
		main.setWidth("100%");
		VerticalLayout page = new VerticalLayout();
		page.setStyleName("c-app");
		page.setWidth("980px");
		// page.addComponent(getWebHead());
		// page.addComponent(getMenuBar());
		VerticalLayout mainPage = getMainPage();
		PageController controller = new PageController(mainPage, eventBus);
		controller.pageChange(new PageChangeEvent(getHomePage()));
		page.addComponent(mainPage);

		// TODO page controller
		page.addComponent(getWebFoot());
		main.addComponent(page);
		main.setComponentAlignment(page, Alignment.TOP_CENTER);
		getMainWindow().setContent(main);
	}

	public VerticalLayout getMainPage() {
		VerticalLayout show = new VerticalLayout();
		show.setWidth("100%");
		show.setMargin(true);
		return show;
	}

	public Layout getWebHead() {
		HorizontalLayout head = new HorizontalLayout();
		head.setWidth("100%");
		head.setHeight("70");
		Label logo = new Label();
		logo.setIcon(AppResource.LOGO);
		head.addComponent(logo);

		HorizontalLayout logInfo = new HorizontalLayout();
		logInfo.setSpacing(true);
		logInfo.addComponent(new Label("Welcome User:"));
		logInfo.addComponent(new Label(" | "));
		Button settingBtn = new Button("Setting");
		settingBtn.setStyleName(BaseTheme.BUTTON_LINK);
		logInfo.addComponent(settingBtn);
		logInfo.addComponent(new Label(" | "));
		logInfo.setMargin(true, true, false, false);
		Button logoutBtn = new Button("Logout");
		logoutBtn.setStyleName(BaseTheme.BUTTON_LINK);
		logInfo.addComponent(logoutBtn);
		head.addComponent(logInfo);
		head.setComponentAlignment(logInfo, Alignment.TOP_RIGHT);
		return head;
	}

	public MenuBar getMenuBar() {
		final MenuBar menubar = new MenuBar();
		menubar.setWidth("100%");
		for (Mocha coral : CORALS) {
			List<Menu> menuList = coral.getMenuList();
			if (menuList != null) {
				for (Menu menu : menuList) {
					addMenuItem(menu, menubar, null);
				}
			}
		}
		return menubar;
	}

	public MenuBar.MenuItem addMenuItem(Menu menu, MenuBar menuBar, MenuBar.MenuItem parentItem) {
		MenuBar.MenuItem menuItem = null;

		if (menuBar != null) {
			menuItem = menuBar.addItem(menu.getLabel(), null, null);
		}
		else if (parentItem != null) {
			menuItem = parentItem.addItem(menu.getLabel(), null, null);
		}

		for (Menu subMenu : menu.getSubMenus()) {
			addMenuItem(subMenu, null, menuItem);
		}

		if (menu.getSubMenus().size() == 0) {
			menuItem.setCommand(addCommand(menu));
		}
		return menuItem;
	}

	public MenuBar.Command addCommand(final Menu menu) {
		MenuBar.Command command = null;
		command = new MenuBar.Command() {
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				PageChangeEvent event = new PageChangeEvent();
				event.setPresenterName(menu.getPage());
				eventBus.post(event);
			}
		};
		return command;
	}

	public abstract String getHomePage();

	public Layout getWebFoot() {
		VerticalLayout foot = new VerticalLayout();
		foot.setWidth("100%");
		foot.setHeight("80");
		return foot;
	}
}
