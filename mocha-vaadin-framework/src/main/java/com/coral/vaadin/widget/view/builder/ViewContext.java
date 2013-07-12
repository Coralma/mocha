/**
 * 
 */
package com.coral.vaadin.widget.view.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Property;
import com.coral.foundation.md.model.View;
import com.coral.foundation.md.model.ViewField;
import com.coral.foundation.md.model.ViewSection;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.resource.ModelCenter;
import com.google.common.collect.Maps;

/**
 * @author Administrator
 *
 */
public class ViewContext {

	public enum Action {
        CREATE, UPDATE, DISPLAY, SEARCH, SELECT, ITEM, SEARCH_LIST, SELECT_LIST, DELETE
    }
	private Map<String, ViewField> viewElements = null;
	private Action action;
	private Entity entity;
	private Object pojo;
	private View currentView;
	
	public ViewContext(String entityName) {
		this(ModelCenter.getEntity(entityName), null, Action.CREATE);
	}
	
	public ViewContext(Entity entity) {
		this(entity, null, Action.CREATE);
	}
	public ViewContext(Entity entity, Object pojo, Action action) {
		this.pojo = pojo;
		this.entity = entity;
		this.action = action;
	}
	
	public Map<String, ViewField> getViewElements() {
		if(viewElements==null) {
            viewElements = Maps.newLinkedHashMap();
            View currentView = getCurrentView();
            if(currentView != null) {
            	for(ViewSection viewSection : currentView.getSections()) {
            		if(viewSection.getViewFields() != null) {
	            		for(ViewField viewField : viewSection.getViewFields()) {
	            			viewElements.put(viewField.getFieldName(),viewField);
	            		}
            		}
            	}
            }
        }
        return viewElements;
	}
	
	public View getCurrentView() {
		if(currentView == null) {
			for(View view : entity.getViews()) {
				if(action.toString().equals(view.getType())) {
					currentView = view;
					break;
				}
			}
		}
		return currentView;
	}
	
	public ViewField getViewField(String propertyId) {
		return ModelCenter.getViewField(entity, propertyId);
	}
	
	public Property getProperty(String propertyId) {
		return ModelCenter.getProperty(entity, propertyId);
	}
	
	public Class getEntityClass() {
		return entity.getEntityType();
	}
	
	public String[] getDialogSize() {
		View view = getCurrentView();
		String sizeString = view.getDialogSize();
		String[] size = sizeString.split(",");
		return size;
		
	}
	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}
	
	public Action getAction() {
		return action;
	}
	/**
	 * @return the pojo
	 */
	public Object getPojo() {
		return pojo;
	}
}
