/**
 * 
 */
package com.coral.vaadin.widget.table;

import java.util.Collection;

import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;

/**
 * @author Coral
 *
 */
public interface EntityTableAction extends ClickListener {
	
	public static String INLINE = "inline";
	public static String DEFAULT = "default";
	
	public Layout buildControlButton();
	public Component buildEntitySection(Object selectObj);
	public void onCreateItem(Collection<Object> selection);
	
	public void onUpdateItem(Collection<Object> selection);

	public void onDisplayItem(Collection<Object> selection);

	public void onDeleteItem(Collection<Object> selection);

	public void selectionChange(com.vaadin.data.Property.ValueChangeEvent event);
}
