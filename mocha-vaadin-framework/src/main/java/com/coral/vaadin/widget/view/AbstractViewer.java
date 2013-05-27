/**
 * 
 */
package com.coral.vaadin.widget.view;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.coral.vaadin.util.VaadinDataBinding;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.ActionButton;
import com.google.common.collect.Maps;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 *
 */
public abstract class AbstractViewer extends VerticalLayout implements Viewer {

	private static final long serialVersionUID = 3787027121440221046L;
	protected LinkedHashMap<String, Widget> widgets = Maps.newLinkedHashMap();
	protected Map<String, ActionButton> buttons = Maps.newConcurrentMap();

	public ActionButton buildButton(String action, String data) {
		ActionButton actionButton = new ActionButton(action,data);
		buttons.put(data, actionButton);
		return actionButton;
	}
	
	public ActionButton getButton(String action) {
		return buttons.get(action);
	}
	
	public Map<String, Widget> getWidgets() {
		return widgets;
	}
	
	public Widget getWidget(String widgetName) {
		return widgets.get(widgetName);
	}

	public <T> T getEntityValue(Class<T> t) {
		T pojo = null;
		try {
			pojo = getEntityValue(t.newInstance(),t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pojo;
	}
	
	public <T> T getEntityValue(T obj, Class<T> t) {
		Collection<Widget> widgetCollection = widgets.values();
		for(Widget Widget : widgetCollection) {
			Result result = Widget.validate("COMMIT");
			if(!result.isPass()) {
				return null;
			}
		}
		if(obj == null) {
			try {
				obj = t.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (T) VaadinDataBinding.bindingData(widgetCollection, obj, t);
	}
	
	public void setEntityValue(Object value) {
		if(value == null) return;
		detachWidgets();
		VaadinDataBinding.bindingToFields(value, widgets.values());
	}
	
	public void detachWidgets() {
		for(Widget widget : widgets.values()) {
			widget.detach();
		}
	}
	
}