/**
 * 
 */
package com.coral.vaadin.view.template.sat;

import java.util.List;
import java.util.Map;

import org.vaadin.peter.contextmenu.ContextMenu;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;

import com.coral.vaadin.widget.Viewer;
import com.google.common.collect.Maps;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral.Ma
 *
 */
@SuppressWarnings("serial")
public abstract class FunctionPanel extends VerticalLayout {

	protected String contentWidth = "770px";
	public VerticalLayout headLayout = new VerticalLayout();
	public VerticalLayout contentLayout = new VerticalLayout();
	protected ContextMenu createContextMenu = new ContextMenu();
	protected ContextMenu settingContextMenu = new ContextMenu();
	protected Map<String, ContextMenuItem> contextMenuMap = Maps.newHashMap();
	protected Map<String, FunctionMenu> contextMenuCache = Maps.newHashMap();
	protected FunctionControllerPanel controllerPanel = new FunctionControllerPanel();
	public Viewer currentViewer;
	
	public FunctionPanel() {
		this.setWidth(contentWidth);
		this.addStyleName("app-function-main");
		this.headLayout.setWidth(contentWidth);
		this.contentLayout.setWidth(contentWidth);
		this.contentLayout.addStyleName("function-content-layout");
	}
	
	public void attach() {
//		FunctionControllerPanel controllerPanel = new FunctionControllerPanel();
		headLayout.addComponent(controllerPanel);
		addComponent(headLayout);
		setExpandRatio(headLayout, 1);
//		contentLayout.setMargin(true);
		contentLayout.setSizeFull();
		addComponent(contentLayout);
		setExpandRatio(contentLayout, 100);
	}
	
	public void setContent(Viewer viewer) {
		controllerPanel.setVisible(true);
		controllerPanel.setTitle(viewer.getViewerTitle());
		contentLayout.removeAllComponents();
		contentLayout.addComponent(viewer);
		this.currentViewer = viewer;
	}
	
	public void setFullContent(Viewer viewer) {
		controllerPanel.setVisible(false);
//		controllerPanel.setTitle(viewer.getViewerTitle());
		contentLayout.removeAllComponents();
		contentLayout.addComponent(viewer);
		this.currentViewer = viewer;
	}
	
	public void changeToEntityEditTitlePanel(String title) {
//		headLayout.removeAllComponents();
//		EntityEditTitlePanel titlePanel = new EntityEditTitlePanel(title);
//		headLayout.addComponent(titlePanel);
	}
	
//	public void changeToFunctionControllerPanel() {
//		headLayout.removeAllComponents();
//		headLayout.addComponent(controllerPanel);
//	}

	public class FunctionControllerPanel extends HorizontalLayout {
		private String entityName = "Home";
		private Label titleLabel;
		
		public FunctionControllerPanel() {
			this.setWidth("765px");
			this.addStyleName("controller-panel");
			build();
		}
		
		public void build() {
			Button createButton = new Button("Create");
			createButton.addStyleName(BaseTheme.BUTTON_LINK);
			createButton.addStyleName("controller-head");
			createButton.setWidth("70px");
			bindingCreationContentMenu(createButton, getCreationFunctionMenu());
			
			titleLabel = new Label(entityName);
			titleLabel.addStyleName("entity-edit-title");
			titleLabel.setWidth("596px");
			
			Button toolButton = new Button("Setting");
			toolButton.addStyleName(BaseTheme.BUTTON_LINK);
			toolButton.addStyleName("tools-head");
			toolButton.setWidth("70px");
			bindingSettingContentMenu(toolButton, getSettingFunctionMenu());
			this.addComponent(createButton);
			this.addComponent(titleLabel);
			this.addComponent(toolButton);
		}
		
		public void setTitle(String title) {
			if(title == null) {
				titleLabel.setValue("");
			} else {
				titleLabel.setValue(title);
			}
		}
	}
	
	public void bindingCreationContentMenu(Button funButton, List<FunctionMenu> functionMenus) {
		if(functionMenus == null) {
			return;
		}
		for(FunctionMenu functionMenu : functionMenus) {
			ContextMenuItem item = createContextMenu.addItem(functionMenu.getLabel());
			contextMenuMap.put(functionMenu.getName(), item);
			contextMenuCache.put(functionMenu.getName(), functionMenu);
		}
		funButton.addListener(new ClickListener() {
            public void buttonClick(final com.vaadin.ui.Button.ClickEvent event) {
                // put menu at bottom left of button
            	createContextMenu.show(event.getClientX() - event.getRelativeX(),
                        event.getClientY() - event.getRelativeY() + 36);
            }
        });
		this.addComponent(createContextMenu);
	}
	
	public void bindingSettingContentMenu(Button funButton, List<FunctionMenu> functionMenus) {
		if(functionMenus == null) {
			return;
		}
		for(FunctionMenu functionMenu : functionMenus) {
			ContextMenuItem item = settingContextMenu.addItem(functionMenu.getLabel());
			item.setSeparatorVisible(functionMenu.isSeparator());
			contextMenuMap.put(functionMenu.getName(), item);
			contextMenuCache.put(functionMenu.getName(), functionMenu);
		}
		funButton.addListener(new ClickListener() {
            public void buttonClick(final com.vaadin.ui.Button.ClickEvent event) {
                // put menu at bottom left of button
            	settingContextMenu.show(event.getClientX() - event.getRelativeX() - 82,
                        event.getClientY() - event.getRelativeY() + 36);
            }
        });
		this.addComponent(settingContextMenu);
	}
	
	public abstract List<FunctionMenu> getCreationFunctionMenu();
	public abstract List<FunctionMenu> getSettingFunctionMenu();
	

	/**
	 * @return the createContextMenu
	 */
	public ContextMenu getCreateContextMenu() {
		return createContextMenu;
	}

	/**
	 * @return the settingContextMenu
	 */
	public ContextMenu getSettingContextMenu() {
		return settingContextMenu;
	}
	
	public ContextMenuItem getContextMenuItem(String name) {
		return contextMenuMap.get(name);
	}
	
	public FunctionMenu getFunctionMenu(ContextMenuItem contextMenuItem) {
		return contextMenuCache.get(contextMenuItem.getName());
	}
}