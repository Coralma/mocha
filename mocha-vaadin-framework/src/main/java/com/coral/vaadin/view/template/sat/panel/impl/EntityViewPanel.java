/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import java.util.List;
import java.util.Map;

import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.ActionButton;
import com.coral.vaadin.widget.fields.CodeTableWidget;
import com.coral.vaadin.widget.fields.DateFieldWidget;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.coral.vaadin.widget.fields.FieldWidget;
import com.coral.vaadin.widget.fields.LongFieldWidget;
import com.coral.vaadin.widget.fields.OptionGroupWidget;
import com.coral.vaadin.widget.fields.StringAreaFieldWidget;
import com.coral.vaadin.widget.fields.StringFieldWidget;
import com.coral.vaadin.widget.helper.NotificationHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral.Ma
 *
 */
public abstract class EntityViewPanel extends VerticalLayout {

	public Object entity;
	public Class entityClass;
	public Map<String, FieldWidget> fieldMap = Maps.newHashMap();
	public Map<String, Button> actionMap = Maps.newHashMap();
	protected IViewPanel viewPanel;
	
	public EntityViewPanel() {
		this.addStyleName("entity-view-panel");
	}
	
	public void attach() {
		if(entity == null) {
			try {
				entity = getEntityClass().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		build();
	}
	
	/**
	 * Build the content of view.
	 */
	public abstract void build();

	public IViewPanel createViewPanel() {
		if(viewPanel == null) {
			viewPanel = new ViewPanel();
		}
		return viewPanel;
	}
	
	public ISectionPanel createSectionPanel() {
		ISectionPanel sectionPanel = new DefaultSectionPanel();
		return sectionPanel;
	}
	
	public FieldWidget createFieldWidget(FieldStatus fieldStatus) {
		FieldWidget field = null;
		String label = fieldStatus.getLabel();
		if("String".equals(fieldStatus.getType())) {
			if("textarea".equals(fieldStatus.getStyle())) {
				field = new StringAreaFieldWidget(label);
			} else if(fieldStatus.getCodeTableName() != null) {
				if("radio".equals(fieldStatus.getStyle())) {
					field = new OptionGroupWidget(label,fieldStatus.getCodeTableName());	
				} else {
					field = new CodeTableWidget(label,fieldStatus.getCodeTableName());
				}
			} else {
				field = new StringFieldWidget(label);
			}
		} else if("Date".equals(fieldStatus.getType())) {
			field = new DateFieldWidget(label);
		} else if("Long".equals(fieldStatus.getType())) {
			field = new LongFieldWidget(label);
		}
		field.setProperty(createFieldProperty(fieldStatus.getPath()));
		field.setFieldStatus(fieldStatus);
		field.setRequired(fieldStatus.isRequired());
		fieldMap.put(fieldStatus.getPath(), field);
		return field;
	}
	
	public Property createFieldProperty(String propertyPath) {
		Property property = new NestedMethodProperty(getEntity(), propertyPath);
		return property;
	}
	
	public IActionPanel createActionPanel() {
		IActionPanel buttonLayout = new DefaultActionPanel();
		return buttonLayout;
	}
	
	public Button createActionButton(String name, String label, String action) {
		Button actionButton = new NativeButton(label);
		actionButton.addStyleName("mocha-button");
		actionButton.setWidth("100px");
		actionButton.setData(action);
		actionMap.put(name, actionButton);
		return actionButton;
	}

	/**
	 * @return the entity
	 */
	public Object getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Object entity) {
		this.entity = entity;
	}

	/**
	 * @return the entityClass
	 */
	public Class getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}
	
	/**
	 * Return the specified field for presenter to add business logic event. 
	 * @param fieldName
	 * @return FieldWidget 
	 */
	public FieldWidget getField(String fieldName) {
		return fieldMap.get(fieldName);
	}
	
	/**
	 * Return the specified action for presenter to add action event.
	 * @param actionName
	 * @return Button
	 */
	public Button getAction(String actionName) {
		return actionMap.get(actionName);
	}
	
	
	public Object getValue() {
		List<Result> errors = Lists.newArrayList();
		errors = viewPanel.validate();
		if(errors.size() == 0) {
			return entity;
		} else {
			getWindow().showNotification(NotificationHelper.getErrorNotification(errors));
		}
		return null;
	}
	
	public Button getButton(String actionName) {
		return actionMap.get(actionName);
	}
	
	public void setReadOnly(boolean readonly) {
		viewPanel.setReadOnly(readonly);
	}
	
	
	
	
	
	public String getViewerTitle() {
		return null;
	}




	public void setValue(Object value) {
		
	}


	public Result validate(String type) {
		return new Result();
	}


	public Widget getWidget(String widgetName) {
		return null;
	}


	public Map<String, Widget> getWidgets() {
		return null;
	}
}
