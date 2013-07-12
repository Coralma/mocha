package com.coral.vaadin.app;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.vaadin.johan.Toolbox;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.md.model.Menu;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.controller.PageChangeEvent;
import com.coral.vaadin.controller.PageController;
import com.coral.vaadin.resource.AppResource;
import com.coral.vaadin.resource.ModelCenter;
import com.github.wolfie.sessionguard.SessionGuard;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

public abstract class AbstractMainPage extends Panel {

	protected String pageWidth = "980px";
	protected String contentWidth = "1000px";
	protected MochaEventBus eventBus = new MochaEventBus();
	protected PageController controller;
	protected VerticalLayout mainContent;
	protected Toolbox toolbox;
	protected Message message;
	protected HttpServletResponse response; 
	
	public AbstractMainPage() {
		super();
		setSizeFull();
		setStyleName("mocha-app");
	}
	
	public void attach() {
		initContent();
	}
	
	public void initContent() {
		message = new Message(getApplication().getLocale());
		mainContent = new VerticalLayout();
		mainContent.addStyleName("mocha-page");
		mainContent.setWidth(contentWidth);
		
		eventBus.setUser((BasicUser)getApplication().getUser());
		controller = new PageController(mainContent, eventBus);
		// draw the page first to make the component tree generate.
		setContent(draw());
		// turn to index;
		PageChangeEvent indexEvent = indexPage();
		controller.pageChange(indexEvent);
	}
	
	public SessionGuard buildSessionGuard() {
		// session manage
		final SessionGuard sessionGuard = new SessionGuard();
		sessionGuard.setWidth("100%");
	    sessionGuard.setKeepalive(true);
	    return sessionGuard;
	}
	
	public ComponentContainer draw() {
		VerticalLayout backgroundLayout = new VerticalLayout();
		backgroundLayout.addStyleName("main-background");
		backgroundLayout.setWidth("100%");
		
		Layout head = getWebHead();
		backgroundLayout.addComponent(head);
		backgroundLayout.setComponentAlignment(head, Alignment.TOP_CENTER);

		backgroundLayout.addComponent(mainContent);
		backgroundLayout.setComponentAlignment(mainContent, Alignment.TOP_CENTER);
		
		Layout foot = getWebFoot();
		backgroundLayout.addComponent(foot);
		backgroundLayout.setComponentAlignment(foot,Alignment.TOP_CENTER);
		
		backgroundLayout.addComponent(buildSessionGuard());
//		backgroundLayout.addComponent(buildToolbox());
		return backgroundLayout;
	}
	
//	public Toolbox buildToolbox() {
//		ChatExtToolbar toolbar = new ChatExtToolbar(eventBus.getUser());
//		return toolbar;
//		toolbox = new Toolbox();
//		toolbox.setOrientation(ORIENTATION.BOTTOM_RIGHT);
//		toolbox.setAnimationTime(400);
//		toolbox.setOverflowSize(24);
//		toolbox.setFoldOnClickOnly(true);
//		
//		final VerticalLayout layout = new VerticalLayout();
//		
//		HorizontalLayout titleLayout = new HorizontalLayout();
//		titleLayout.addStyleName("chat-ext-title-layout");
//		titleLayout.setWidth("100%");
//		Label chatIcon = new Label();
//		chatIcon.setWidth("16px");
//		chatIcon.setIcon(new ThemeResource("icons/chat-ext.png"));
//		titleLayout.addComponent(chatIcon);
//		titleLayout.setExpandRatio(chatIcon, 1f);
//		
//		Label chatLabel = new Label("Chat");
//		chatLabel.addStyleName("chat-ext-title");
//		titleLayout.addComponent(chatLabel);
//		titleLayout.setExpandRatio(chatLabel, 100f);
//		
//		layout.addComponent(titleLayout);
//		layout.setWidth("200px");
//		
//		layout.setHeight("300px");
//		layout.setSpacing(true);
//		layout.addComponent(new Button("New Chat"));
//		layout.addComponent(new Button("Notification"));
//		layout.addComponent(new Button("Todo"));
//		
//		toolbox.addComponent(layout);
//		
//		return toolbox;
//	}

	public abstract PageChangeEvent indexPage();

	public Layout getWebFoot() {
		VerticalLayout foot = new VerticalLayout();
		foot.addStyleName("mocha-app-foot");
		foot.setWidth("100%");
		foot.setHeight("200px");

		VerticalLayout footContent = new VerticalLayout();
		footContent.setWidth(pageWidth);
		foot.addComponent(footContent);
		
		return foot;
	}
	
	public Layout getWebHead() {
		VerticalLayout head = new VerticalLayout();
		head.setSizeFull();
		
		HorizontalLayout headContent = new HorizontalLayout();
		headContent.setWidth(pageWidth);
		headContent.setHeight("70px");
		Label logo = new Label();
		logo.setWidth("131px");
		logo.setIcon(AppResource.LOGO);
		headContent.addComponent(logo);
		headContent.setComponentAlignment(logo, Alignment.BOTTOM_LEFT);

		HorizontalLayout logInfo = new HorizontalLayout();
		logInfo.setSpacing(true);
		logInfo.addComponent(new Label("Welcome User: Angela"));
		logInfo.addComponent(new Label(" | "));
		Button settingBtn = new Button("Setting");
		settingBtn.setStyleName(BaseTheme.BUTTON_LINK);
		logInfo.addComponent(settingBtn);
		logInfo.addComponent(new Label(" | "));
		logInfo.setMargin(true, true, false, false);
		Button logoutBtn = new Button("Logout");
		logoutBtn.setStyleName(BaseTheme.BUTTON_LINK);
		logInfo.addComponent(logoutBtn);
		headContent.addComponent(logInfo);
		headContent.setComponentAlignment(logInfo, Alignment.TOP_RIGHT);
		
		head.addComponent(headContent);
		head.setComponentAlignment(headContent, Alignment.TOP_CENTER);
		head.addComponent(getMenuBar());
		return head;
	}
	

	/**
	 * Menubar init
	 * @return
	 */
	public MenuBar getMenuBar() {
		final MenuBar menubar = new MenuBar();
		menubar.setWidth("100%");
		for (Mocha coral : ModelCenter.getModel()) {
			List<Menu> menuList = coral.getMenuList();
			if (menuList != null) {
				for (Menu menu : menuList) {
					addMenuItem(menu, menubar, null);
				}
			}
		}
		return menubar;
	}

	public MenuBar.MenuItem addMenuItem(Menu menu, MenuBar menuBar,
			MenuBar.MenuItem parentItem) {
		MenuBar.MenuItem menuItem = null;

		if (menuBar != null) {
			menuItem = menuBar.addItem(menu.getLabel(), null, null);
		} else if (parentItem != null) {
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

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
