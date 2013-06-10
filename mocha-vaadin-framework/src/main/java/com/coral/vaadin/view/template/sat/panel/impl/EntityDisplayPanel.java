/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.coral.vaadin.widget.fields.FieldWidget;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral.Ma
 *
 */
public abstract class EntityDisplayPanel extends EntityViewPanel {
	
	public EntityDisplayListener listener;
	
	public EntityDisplayPanel() {
		super();
		this.removeStyleName("entity-view-panel");
	}
	
	/**
	 * Build the content of view.
	 */
	public abstract void build();
	
	public FieldWidget createFieldWidget(FieldStatus fieldStatus) {
		FieldWidget field = super.createFieldWidget(fieldStatus);
		field.setReadOnly(true);
		return field;
	}
	
	public ISectionPanel createListSectionPanel(String sectionName) {
		ListSectionPanel listSectionPanel = new ListSectionPanel(sectionName);
		sectionMap.put(sectionName, listSectionPanel);
		return listSectionPanel;
	}
	
	public class DisplayNavigatorPanel extends HorizontalLayout implements ClickListener {
		
		private Button createButton = WidgetFactory.createLink("Back","back",this);
		private Button editButton = WidgetFactory.createLink("Edit","edit",this);
		private Button deleteButton = WidgetFactory.createLink("Delete","delete",this);
		private Button quickActionsButton = WidgetFactory.createLink("Quick Actions","actions",this);
		
		private Button leftButton = WidgetFactory.createIconButton("arrow-left.png","pre",this);
		private Button rightButton = WidgetFactory.createIconButton("arrow-collapse.png","next",this);
		
		public DisplayNavigatorPanel() {
			this.setWidth("765px");
			this.addStyleName("controller-panel");
			build();
		}
		
		public void build() {
			ToolbarAdvance toolbar = new ToolbarAdvance();
			toolbar.setToolbarWidth("760px");
			createButton.setIcon(new ThemeResource("icons/back.png"));
			toolbar.addLeftComponent(createButton);
			toolbar.addLeftComponent(editButton);
			toolbar.addLeftComponent(deleteButton);
			toolbar.addLeftComponent(quickActionsButton);
			
			toolbar.setNeedRightSeperate(false);
			toolbar.setRightAlignment(Alignment.MIDDLE_RIGHT);
			toolbar.addRightComponent(leftButton);
			toolbar.addRightComponent(rightButton);
			this.addComponent(toolbar);
		}
		

		@Override
		public void buttonClick(ClickEvent event) {
			Object actionData = event.getButton().getData();
			if("back".equals(actionData)) {
				listener.back();
			} else if("edit".equals(actionData)) {
				listener.edit(getEntity());
			} else if("delete".equals(actionData)) {
				listener.delete(getEntity());
			} else if("pre".equals(actionData)) {
				listener.previous(getEntity());
			} else if("next".equals(actionData)) {
				listener.next(getEntity());
			}
		}
	}
	
	public interface EntityDisplayListener {
		public void back();
		public void edit(Object value);
		public void delete(Object value);
		public void previous(Object value);
		public void next(Object value);
	}

	/**
	 * @return the listener
	 */
	public EntityDisplayListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(EntityDisplayListener listener) {
		this.listener = listener;
	}
}
