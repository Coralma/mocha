/**
 * 
 */
package com.coral.vaadin.widget.fields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.ui.ComboBox;


public class UnitSelectionWidget extends FieldWidget {
	
	private List<?> entities;
	private Map<Object,Object> entityMap;

	public UnitSelectionWidget(String label) {
		super(label);
	}

	@Override
	public void initField() {
		field = new ComboBox(label);
		field.addListener(this);		
	}
	
	public void setNullSelectionAllowed(boolean allow) {
		((ComboBox)field).setNullSelectionAllowed(allow);
	}
	
	public void addValueChangeListener(ValueChangeListener listener) {
		field.addListener(listener);
	}
	
	public <T> void setItems(List<T> entities, Class<T> clazz, String value) {
		this.entities = entities;
		this.entityMap = new HashMap<Object,Object>();
		((ComboBox)field).removeAllItems();
		for(T entity : entities) {
			Property valueProperty = new NestedMethodProperty(entity, value);
			Object itemValue = valueProperty.getValue();
			entityMap.put(itemValue, entity);
			((ComboBox)field).addItem(itemValue);
		}
	}
	
	public Object getSeletedItem() {
		Object value = getValue() ;
		if(value == null) {
			return null;
		} else { 
			return entityMap.get(value);
		}
	}
	
	public void setItems(String...items) {
		for(String item : items) {
			((ComboBox)field).addItem(item);
		}
	}
}
