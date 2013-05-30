package com.coral.vaadin.view.template.sat;

import java.util.List;

import com.google.common.collect.Lists;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;

public abstract class ControllerMenuPanel extends VerticalLayout implements ClickListener {

	private ControllerMenuListener controllerMenuListener;
	protected List<NativeButton> menus = Lists.newArrayList();
	
	public ControllerMenuPanel() {
		this.setWidth("200px");
		this.addStyleName("controller-menu");
		build();
	}

	public abstract void build();
	
	public void attach() {
//		NativeButton defaultSelected = menus.get(0);
//		buttonClick(defaultSelected.new ClickEvent(defaultSelected));
	}
	
	public Label createAppTitle(String title) {
		Label appTitle = new Label(title);
		appTitle.addStyleName("app-title");
		return appTitle;
	}
	
	public Label createMenuTitle(String title) {
		Label menuTitle = new Label(title);
		menuTitle.addStyleName("menu-group-title");
		return menuTitle;
	}
	
	public NativeButton createMenu(String caption, MenuAction menuAction) {
		NativeButton menu = new NativeButton(caption);
		menu.setWidth("100%");
		menu.setData(menuAction);
		menu.addListener(this);
		menu.setStyleName("menu-clicked-item");
		menus.add(menu);
		return menu;
	}
	
	public NativeButton createBackHomeMenu() {
		NativeButton menu = new NativeButton("Back to Home");
		menu.setWidth("100%");
		menu.setData("Home");
//		menu.addListener(this);
		menu.setStyleName("menu-clicked-item");
		return menu;
	}
	
	public Label seperateLine() {
		Label seperate = new Label("<hr />", Label.CONTENT_XHTML);
		return seperate;
	}
	
	public ComponentContainer endSpace() {
		VerticalLayout layout = new VerticalLayout();
		for(int i=0; i<3; i++) {
			layout.addComponent(space());
		}
		return layout;
	}
	
	public Label space() {
		Label space = new Label("<br />", Label.CONTENT_XHTML);
		return space;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		NativeButton clickedButton = (NativeButton)event.getButton();
		MenuAction action = (MenuAction)clickedButton.getData();
		if("view".equals(action.getType())) {
			controllerMenuListener.showView(action.getView());
		} else if("panel".equals(action.getType())) {
			controllerMenuListener.showPanel(action.getPanel());
		}
		setMenuStyle(action.getView(), action.getPanel());
//		for(NativeButton menu : menus) {
//			if(menu.equals(clickedButton)) {
//				menu.setStyleName("menu-clicked-item-selected");
//			} else {
//				menu.setStyleName("menu-clicked-item");
//			}
//			menu.requestRepaint();
//		}
	}
	
	public void cleanMenuStyle() {
		for(NativeButton menu : menus) {
			menu.setStyleName("menu-clicked-item");
			menu.requestRepaint();
		}
	}
	
	public void setMenuStyle(String view, String panel) {
		try {
			setMenuStyle(view, Class.forName(panel));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void setMenuStyle(String view, Class panel) {
		for(NativeButton menu : menus) {
			MenuAction menuAction = (MenuAction) menu.getData();
			if((view != null && view.equals(menuAction.getView())) || (panel != null && panel.equals(menuAction.getPanel()))) {
				menu.setStyleName("menu-clicked-item-selected");
			} else {
				menu.setStyleName("menu-clicked-item");
			}
			menu.requestRepaint();
		}
	}

	/**
	 * @param controllerMenuListener the controllerMenuListener to set
	 */
	public void setControllerMenuListener(
			ControllerMenuListener controllerMenuListener) {
		this.controllerMenuListener = controllerMenuListener;
	}

	public interface ControllerMenuListener {
		public void showView(String view);
		public void showPanel(Class Panel);
	}

	public class MenuAction {
		String type;
		String view;
		Class panel;

		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getView() {
			return view;
		}
		public void setView(String view) {
			this.view = view;
			this.type="view";
		}
		public Class getPanel() {
			return panel;
		}
		public void setPanel(Class panel) {
			this.panel = panel;
			this.type="panel";
		}
	}
}
