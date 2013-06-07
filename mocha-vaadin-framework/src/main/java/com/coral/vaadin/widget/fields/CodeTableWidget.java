/**
 * 
 */
package com.coral.vaadin.widget.fields;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.coral.foundation.security.model.CodeTable;
import com.coral.foundation.utils.CodeTableUtils;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComboBox;

/**
 * @author Coral.Ma
 *
 */
public class CodeTableWidget extends FieldWidget {

	private String codeTableName;
//	public CodeTableWidget(String label, List<String> datas) {
//		super(label);
//		setItems(datas);
//	}
	
	public CodeTableWidget(String label, String codeTableName) {
		super(label);
		this.codeTableName = codeTableName;
	}
	
	public void attach() {
		super.attach();
		CodeTable codeTable = CodeTableUtils.get(codeTableName);
		setItems(CodeTableUtils.parse(codeTable,getApplication().getLocale().getLanguage()));
	}
	
	public void setItems(LinkedHashMap<String,String> datas) {
		if(datas != null) {
			for(Map.Entry<String,String> data : datas.entrySet()) {
				((ComboBox)field).addItem(data.getKey());
				((ComboBox)field).setItemCaption(data.getKey(), data.getValue());
			}
		}
	}
	
	public void initField() {
		field = new ComboBox(label);
		field.addListener(this);
	}

	public void setNullSelectionAllowed(boolean allow) {
		((ComboBox)field).setNullSelectionAllowed(allow);
	}
	
	public void addListener(ValueChangeListener listener) {
		field.addListener(listener);
	}
}
