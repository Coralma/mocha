/**
 * 
 */
package com.coral.vaadin.widget;

import java.util.List;
import java.util.Map;

import com.coral.vaadin.widget.field.ActionButton;
import com.coral.vaadin.widget.listener.PresenterListener;
import com.coral.vaadin.widget.table.AbstractTable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;

/**
 * @author Coral
 *
 */
public interface Viewer extends ComponentContainer, Widget {
	
	public static String TCreate = "TCreate";
	public static String TUpdate = "TUpdate";
	public static String TDisplay = "TDisplay";
	public static String TSearch = "TSearch";
	public static String TTable = "TTable";
	
	public String getViewerTitle();
	public void attach();
//	public Object getValue();
//	public void setValue(Object value);
//	public void setListener(PresenterListener listener);
	public Button getButton(String actionName);
	public Widget getWidget(String widgetName);
	public Map<String, Widget> getWidgets();
//	public List<Widget> getWidgets();
//	public List<AbstractTable> getTables();
//	public List<ActionButton> getButtons();
}
