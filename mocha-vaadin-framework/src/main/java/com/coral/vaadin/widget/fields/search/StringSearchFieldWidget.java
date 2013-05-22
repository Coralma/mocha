/**
 * 
 */
package com.coral.vaadin.widget.fields.search;

import com.coral.foundation.jpa.search.SearchFilter;
import com.vaadin.ui.TextField;

/**
 * @author Coral
 *
 */
public class StringSearchFieldWidget extends SearchFieldWidget {

	public StringSearchFieldWidget(String label, String propertyName) {
		super(label);
	}

	@Override
	public void initField() {
		field = new TextField(label);
		((TextField)field).setNullRepresentation("");
		((TextField)field).setNullSettingAllowed(true);
	}

	@Override
	public Object getValue() {
		Object value = field.getValue();
		if(value != null) {
			return SearchFilter.eq(propertyName, value);
		}
		return null;
	}

}
