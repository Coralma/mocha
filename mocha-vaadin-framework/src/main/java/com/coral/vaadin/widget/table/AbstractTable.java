/**
 * 
 */
package com.coral.vaadin.widget.table;

import org.vaadin.addon.customfield.CustomField;

import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Widget;



/**
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class AbstractTable extends CustomField implements Widget {
	
	protected EntityTable table;
	protected Class viewClass;
	protected Object data;
	
	public AbstractTable(String viewClass) {
		this(EntityTable.POPUP, viewClass);
	}
	
	public AbstractTable(String type, String viewClass) {
		if(EntityTable.POPUP.equals(type)) {
			table = new PopupEditorTable();
		} else if(EntityTable.INLINE.equals(type)) {
			table = new InlineEditorTable();
		} else {
			table = new DefaultEditorTable();
		}
		table.setViewClass(viewClass);
	}

	public void initTable(String label) {
		table.build(label);
	}
	
	public EntityTable getTable() {
		return table;
	}

	public Object getValue() {
		return table.getValue();
	}

	public void setValue(Object value) {
		table.setValue(value);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public void refreshRowCache() {
		table.refreshRowCache();
	}

	public Result validate(String type) {
		return new Result();
	}

	@Override
	public Class<?> getType() {
		return viewClass;
	}

}
