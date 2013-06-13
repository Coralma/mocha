/**
 * 
 */
package com.coral.vaadin.widget.fields.search;

import java.util.LinkedHashMap;

import com.coral.foundation.security.model.CodeTable;
import com.coral.foundation.utils.CodeTableUtils;
import com.coral.vaadin.widget.fields.FieldWidget;
import com.vaadin.ui.Label;

/**
 * @author Coral
 *
 */
public class DisplayFieldWidget extends FieldWidget {

	Label displayField;
	String codeTable;
	public DisplayFieldWidget(String label) {
		super(label);
		this.addStyleName("display-field-widget");
	}

	public void initField() {
		label = label + ":";
		displayField = new Label();
		displayField.setCaption(label);
	}

	public void attach() {
		displayField.setWidth(fieldWidth);
		if(codeTable == null) {
			displayField.setPropertyDataSource(property);
		} else {
			CodeTable codeTableInstance = CodeTableUtils.get(codeTable);
			LinkedHashMap<String,String> ctMap = CodeTableUtils.parse(codeTableInstance,getApplication().getLocale().getLanguage());
			String ctValue = ctMap.get(property.getValue());
			displayField.setValue(ctValue);
		}
		this.addComponent(displayField);
	}

	/**
	 * @return the codeTable
	 */
	public String getCodeTable() {
		return codeTable;
	}

	/**
	 * @param codeTable the codeTable to set
	 */
	public void setCodeTable(String codeTable) {
		this.codeTable = codeTable;
	}
}
