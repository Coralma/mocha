/**
 * 
 */
package com.coral.vaadin.widget.table;

import java.util.Collection;
import java.util.List;

import com.coral.vaadin.widget.Widget;
import com.vaadin.ui.Component;

/**
 * @author Administrator
 *
 */
@SuppressWarnings({"rawtypes"})
public interface EntityTable extends Widget {
	
	public static String INLINE="inline";
	public static String POPUP="popup";
	public static String DEFAULT="default";

	public void setViewClass(String viewClass);
	public void setDataContainer(EntityContainer dataContainer);
	public void build(String label);
	public void setValue(Object value);
	public Object getValue();
	public Collection<Object> getSelection();
	public void refreshRowCache();
	public void removeItem(Object obj);
}
