package com.mocha.template.general.widget;

import java.util.Map;

import com.coral.vaadin.widget.WidgetFactory;
import com.google.common.collect.Maps;
import com.mocha.template.general.utils.GeneralTemplateCst;
import com.mocha.template.general.widget.listener.GeneralNavigationListener;
import com.mocha.template.general.widget.vo.GeneralHeadVO;
import com.mocha.template.general.widget.vo.GeneralMenuVO;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class GeneralNavigationWidget extends HorizontalLayout {

	public String total_width = GeneralTemplateCst.total_width;
	public String app_name_width = GeneralTemplateCst.app_name_width;
	public String menu_width = GeneralTemplateCst.menu_width;
	
	private Map<String, Button> menuMap = Maps.newHashMap();
	private GeneralHeadVO generalHead;
	private GeneralNavigationListener listener;
	
	public GeneralNavigationWidget(GeneralHeadVO generalHead) {
		this.generalHead = generalHead;
	}
	
	public GeneralNavigationWidget(GeneralHeadVO generalHead, GeneralNavigationListener listener) {
		this.generalHead = generalHead;
		this.listener = listener;
	}
	
	/**
	 * Head of app. The display sample is like below.
	 * appName   menu1  menu2  menu3  menu4  menu5
	 */
	public void attach() {
		this.addStyleName("app-head-layout");
		this.setWidth(total_width);

		HorizontalLayout appNameLayout = new HorizontalLayout();
		// set the app name
		Label appName = WidgetFactory.createLabel(generalHead.getAppName());
		appNameLayout.addComponent(appName);
		appNameLayout.addStyleName("app-name");
		
		appName.setWidth(app_name_width);
		appNameLayout.setWidth(app_name_width);
		this.addComponent(appNameLayout);
		this.setComponentAlignment(appNameLayout, Alignment.MIDDLE_LEFT);

		HorizontalLayout menuLayout = new HorizontalLayout();
		menuLayout.setWidth(menu_width);
		HorizontalLayout menus = new HorizontalLayout();
		menus.addStyleName("app-menu-layout");
		menus.setMargin(true);
		menus.setSpacing(true);
		
		for(GeneralMenuVO menu : generalHead.getMenus()) {
			Button menuLink =  WidgetFactory.createNativeButton(menu.getLabel());
//			menuLink.addListener(listener);
			menuLink.setData(menu.getAction());
			menuMap.put(menu.getName(), menuLink);
			menus.addComponent(menuLink);
		}
		
		menuLayout.addComponent(menus);
		this.addComponent(menuLayout);
		this.setComponentAlignment(menuLayout, Alignment.MIDDLE_LEFT);
	}

	/**
	 * @return the listener
	 */
	public GeneralNavigationListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(GeneralNavigationListener listener) {
		this.listener = listener;
		for(Button menuLink : menuMap.values()) {
			menuLink.addListener(listener);
		}
	}
}
