/**
 * 
 */
package com.coral.vaadin.widget.table;

import com.vaadin.data.Container;

/**
 * @author Coral
 *
 */
public interface EntityContainer extends Container.Indexed {

	public Object[] getVisibleColumns();
	public String[] getColumnHeaders();
}
