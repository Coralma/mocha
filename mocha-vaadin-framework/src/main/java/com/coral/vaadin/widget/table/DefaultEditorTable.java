/**
 * 
 */
package com.coral.vaadin.widget.table;

import java.util.Collection;

import com.vaadin.data.Property.ValueChangeEvent;

/**
 * @author Administrator
 *
 */
public class DefaultEditorTable extends AbstractEntityTable implements EntityTable {
	
	public void build(String label) {
		super.build(label);
		setPageLength(10);
	}

	@Override
	protected void onCreateItem(Collection<Object> selection) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onUpdateItem(Collection<Object> selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDisplayItem(Collection<Object> selection) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void selectionChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
	}

}
