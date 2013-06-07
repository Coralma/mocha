/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import java.util.Map;

import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.coral.vaadin.widget.fields.FieldWidget;
import com.coral.vaadin.widget.fields.search.DisplayFieldWidget;
import com.google.common.collect.Maps;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Coral
 *
 */
public abstract class SearchEntityCard extends AbstractViewLayout {

	protected Object value;
	protected String cardWidth="765px";
	protected String cardInfoWidth = "560px";
	protected Map<String, Button> actionMap = Maps.newHashMap();
	protected SearchEntityCardListener listener;
	
	public SearchEntityCard() {
		this.setWidth("765px");
		this.addStyleName(Reindeer.PANEL_LIGHT);
		this.addStyleName("entity-search-card");
		this.setMargin(true);
	}
	
	public FieldWidget createFieldWidget(FieldStatus fieldStatus) {
		DisplayFieldWidget widget = new DisplayFieldWidget(fieldStatus.getLabel());
		widget.setFieldStatus(fieldStatus);
		widget.setProperty(createFieldProperty(fieldStatus.getPath()));
		return widget;
	}
	
	public Property createFieldProperty(String propertyPath) {
		Property property = new NestedMethodProperty(getEntity(), propertyPath);
		return property;
	}
	
	public Object getEntity() {
		return value;
	}
	
	public void setEntity(Object value) {
		this.value = value;
	}
	
	public Layout getSearchIcon() {
		VerticalLayout iconLayout = new VerticalLayout();
		iconLayout.setWidth("80px");
		iconLayout.addStyleName("card-icon");
		Label icon = new Label();
		icon.setWidth("50px");
		if(getIconName() == null) {
			icon.setIcon(new ThemeResource("icons/card-default.png"));
		} else {
			String specialIcon = listener.getSpecialIcon(value);
			if(specialIcon != null) {
				icon.setIcon(new ThemeResource("icons/" + specialIcon));
			} else {
				icon.setIcon(new ThemeResource("icons/" + getIconName()));
			}
		}
		iconLayout.addComponent(icon);
		iconLayout.setComponentAlignment(icon, Alignment.MIDDLE_CENTER);
		return iconLayout;
	}
	
	public abstract String getIconName();
	
	public IActionPanel createCardActionPanel() {
		IActionPanel actionPanel = new DefaultSearchCardActionPanel();
		actionPanel.setWidth("104px");
		return actionPanel;
	}
	
	public Button createActionButton(final String name, String label, final String action) {
		Button actionButton = new Button(label);
		actionButton.setWidth("80px");
		actionButton.setData(action);
		actionButton.addStyleName(BaseTheme.BUTTON_LINK);
		actionButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(listener != null) {
					listener.handleAction(name, action);
				}
			}
		});
		actionMap.put(name, actionButton);
		return actionButton;
	}

	public Button getActionButton(String name) {
		return actionMap.get(name);
	}
	
	public interface SearchEntityCardListener {
		public void handleAction(String name, String action);
		public String getSpecialIcon(Object value);
	}

	/**
	 * @return the listener
	 */
	public SearchEntityCardListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(SearchEntityCardListener listener) {
		this.listener = listener;
	}
}
