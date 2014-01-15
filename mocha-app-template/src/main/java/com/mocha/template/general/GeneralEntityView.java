package com.mocha.template.general;

import java.util.Map;

import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.fields.CodeTableWidget;
import com.coral.vaadin.widget.fields.DateFieldWidget;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.coral.vaadin.widget.fields.FieldWidget;
import com.coral.vaadin.widget.fields.LongFieldWidget;
import com.coral.vaadin.widget.fields.OptionGroupWidget;
import com.coral.vaadin.widget.fields.ReferenceSelectionWidget;
import com.coral.vaadin.widget.fields.StringAreaFieldWidget;
import com.coral.vaadin.widget.fields.StringFieldWidget;
import com.coral.vaadin.widget.fields.UnitSelectionWidget;
import com.google.common.collect.Maps;
import com.mocha.template.IAppActionSection;
import com.mocha.template.IAppSection;
import com.mocha.template.IAppView;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

public abstract class GeneralEntityView extends VerticalLayout {

	public Object entity;
	public Class entityClass;
	public Map<String, FieldWidget> fieldMap = Maps.newHashMap();
	public Map<String, Button> actionMap = Maps.newHashMap();
	
	public void attach() {
		if(entity == null) {
			try {
				entity = getEntityClass().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		HorizontalLayout layout = new HorizontalLayout();
		
		IAppView appView = buildEditView();
		layout.addComponent(appView);
		
		Layout controlLayout = buildControlView();
		if(controlLayout != null) {
			layout.addComponent(controlLayout);
		}
		this.addComponent(layout);
	}

	/**
	 * Build the content of view.
	 */
	public abstract IAppView buildEditView();
	
	public abstract Layout buildControlView();

	public IAppView createGeneralAppView() {
		GeneralAppView viewPanel = new GeneralAppView();
		return viewPanel;
	}

	public IAppSection createGeneralAppSection(String label) {
		GeneralAppSection appSection = new GeneralAppSection(label);
		return appSection;
	}
	
	public IAppActionSection createGeneralAppActionSection() {
		GeneralAppActionSection actionSection = new GeneralAppActionSection();
		return actionSection;
	}
	
	public Button createActionButton(String name, String label, String action) {
		Button actionButton = new NativeButton(label);
		actionButton.addStyleName("action-button");
		actionButton.setWidth("120px");
		actionButton.setData(action);
		actionMap.put(name, actionButton);
		return actionButton;
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
			} else if("unit".equals(fieldStatus.getStyle())) {
				field = new UnitSelectionWidget(label);
			} else {
				field = new StringFieldWidget(label);
			}
		} else if("Date".equals(fieldStatus.getType())) {
			field = new DateFieldWidget(label);
		} else if("Long".equals(fieldStatus.getType())) {
			field = new LongFieldWidget(label);
		} else {
			if("ref".equals(fieldStatus.getStyle())) {
				field = new ReferenceSelectionWidget(label, fieldStatus.getType(), fieldStatus.getExpression());
			}
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
	
	public Object getValue() {
		return entity;
	}

	public void setValue(Object value) {
		this.entity = value;
	}

	public Result validate(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Button getButton(String actionName) {
		return actionMap.get(actionName);
	}

	public Widget getWidget(String widgetName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Widget> getWidgets() {
		// TODO Auto-generated method stub
		return null;
	}
}
