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
	private Map<String,Object> entityMap = new HashMap<String,Object>();

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
	
	public void addListener(ValueChangeListener listener) {
		field.addListener(listener);
	}
	
	public <T> void setItems(List<T> entities, Class<T> clazz, String value) {
		this.entities = entities;
		for(T entity : entities) {
			Property valueProperty = new NestedMethodProperty(entity, value);
			Object itemValue = valueProperty.getValue();
			entityMap.put(key, value)
			((ComboBox)field).addItem(itemValue);
		}
	}
	
	public void setItems(String...items) {
		for(String item : items) {
			((ComboBox)field).addItem(item);
		}
	}
}
