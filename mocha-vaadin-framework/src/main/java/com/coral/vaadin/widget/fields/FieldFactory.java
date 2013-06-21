/**
 * 
 */
package com.coral.vaadin.widget.fields;

import java.util.Map;

import com.coral.vaadin.widget.fields.search.DisplayFieldWidget;
import com.google.common.collect.Maps;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;

/**
 * @author Coral
 *
 */
public class FieldFactory {
	
	public Map<String, FieldWidget> fieldMap = Maps.newHashMap();
	public Object entity;
	public boolean readonly;
	
	public FieldFactory(Object entity, boolean readonly) {
		this.entity = entity;
		this.readonly = readonly;
	}

	public FieldWidget create(FieldStatus fieldStatus) {
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
	
	public FieldWidget createDisplay(FieldStatus fieldStatus) {
		DisplayFieldWidget widget = new DisplayFieldWidget(fieldStatus.getLabel());
		widget.setFieldStatus(fieldStatus);
		widget.setProperty(createFieldProperty(fieldStatus.getPath()));
		widget.setCodeTable(fieldStatus.getCodeTableName());
		fieldMap.put(fieldStatus.getPath(), widget);
		return widget;
	}
	
	private Property createFieldProperty(String propertyPath) {
		Property property = new NestedMethodProperty(getEntity(), propertyPath);
		return property;
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
	 * @return the readonly
	 */
	public boolean isReadonly() {
		return readonly;
	}

	/**
	 * @param readonly the readonly to set
	 */
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
}
