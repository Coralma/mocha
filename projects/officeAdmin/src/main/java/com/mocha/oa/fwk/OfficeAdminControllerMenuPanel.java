/**
 * 
 */
package com.mocha.oa.fwk;

import com.coral.vaadin.view.template.sat.ControllerMenuPanel;
import com.mocha.oa.page.OfficeAdminIndexPresenter;
import com.mocha.oa.page.OfficeAdminSearchPresenter;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

/**
 * @author Coral.Ma
 *
 */
public class OfficeAdminControllerMenuPanel extends ControllerMenuPanel {

	
//	String[] g1 = new String[]{"Enable & Disable","Style Names","Setting Text","Mouse Events"};
//	String[] g2 = new String[]{"Field Interactions","Client-only Tabs"};
	
//	public App getApp() {
//		App app = new App();
//		app.setName("Office Admin");
//		AppMenu appMenu = new AppMenu();
//		appMenu.setMenuName("Intro");
//		app.getAppMenus().add(appMenu);
//		
//		AppMenuGroup appMenuGroup = new AppMenuGroup();
//		appMenuGroup.setTitle("Feature");
//		for(String g : g1) {
//			AppMenu subAppMenu = new AppMenu();
//			subAppMenu.setMenuName(g);
//			appMenuGroup.getAppMenus().add(subAppMenu);
//		}
//		app.getAppMenuGroups().add(appMenuGroup);
//		
//		AppMenuGroup appMenuGroup1 = new AppMenuGroup();
//		appMenuGroup1.setTitle("Examples");
//		for(String g : g2) {
//			AppMenu subAppMenu = new AppMenu();
//			subAppMenu.setMenuName(g);
//			appMenuGroup1.getAppMenus().add(subAppMenu);
//		}
//		app.getAppMenuGroups().add(appMenuGroup1);
//		return app;
//	}
	
	public void build() {
		Label appTitle = createAppTitle("Office Admin");
		addComponent(appTitle);
		
		MenuAction action = new MenuAction();
		action.setPanel(OfficeAdminSearchPresenter.class);

		Button introTitle = createMenu("Intro",action);
		addComponent(introTitle);
		
		Label menuTitle = createMenuTitle("Feature");
		addComponent(menuTitle);
		
		action = new MenuAction();
		action.setPanel(OfficeAdminIndexPresenter.class);
		
		Button subMenu = createMenu("Enable & Disable",action);
		addComponent(subMenu);
		Button subMenu2 = createMenu("Style Names",action);
		addComponent(subMenu2);
		Button subMenu3 = createMenu("Setting Text",action);
		addComponent(subMenu3);
		Button subMenu4 = createMenu("Mouse Events",action);
		addComponent(subMenu4);
		
		Label menuTitle2 = createMenuTitle("Examples");
		addComponent(menuTitle2);
		
		Button subMenu5 = createMenu("Field Interactions",action);
		addComponent(subMenu5);
		Button subMenu6 = createMenu("Client-only Tabs",action);
		addComponent(subMenu6);
		
		addComponent(seperateLine());
		Button bth = createBackHomeMenu();
		addComponent(bth);
		
		addComponent(endSpace());
	}
}
