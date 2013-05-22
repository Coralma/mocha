/**
 * 
 */
package com.coral.vaadin.widget.fields;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.OptionGroup;

/**
 * @author Coral
 *
 */
public class OptionGroupWidget extends CodeTableWidget{

//	public OptionGroupWidget(String label, List<String> datas) {
//		super(label, datas);
//	}
	
	public OptionGroupWidget(String label, String codeTableName) {
		super(label, codeTableName);
	}
	
//	public void setItems(List<String> datas) {
//		if(datas != null) {
//			for(String data : datas) {
//				((OptionGroup)field).addItem(data);
//			}
//		}
//		
//	}
	public void setItems(LinkedHashMap<String,String> datas) {
		if(datas != null) {
			for(Map.Entry<String,String> data : datas.entrySet()) {
				((OptionGroup)field).addItem(data.getKey());
				((OptionGroup)field).setItemCaption(data.getKey(), data.getValue());
			}
		}
	}
	
	public void attach() {
		super.attach();
		((OptionGroup)field).select(((OptionGroup)field).getContainerDataSource().getItemIds().iterator().next());
	}
	
	public void initField() {
		field = new OptionGroup(label);
		field.addStyleName("horizontal");
		field.addListener(this);
	}
}
