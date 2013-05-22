/**
 * 
 */
package com.coral.vaadin.widget.field;

import java.util.Date;
import java.util.List;

import com.coral.vaadin.widget.Field;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.ErrorMessage;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.terminal.UserError;
import com.vaadin.terminal.Paintable.RepaintRequestListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;
import com.vaadin.ui.AbstractComponent.ComponentErrorEvent;

/**
 * @author Administrator
 *
 */
public class CodeTableField extends AbstractField implements Field {

//	Select codeTable = new Select();
//	protected String fieldWidth = "230";
	
//	public CodeTableField(String label, List<String> datas) {
//		super(label);
//		field = new ComboBox();
//		((ComboBox)field).setImmediate(true);
//		addListener(this);
//		setCodeTableData(datas);
//		field.setWidth(fieldWidth);
//		addComponent(field);
//	}
	
	public CodeTableField(String label, String[] datas) {
		super(label);
		field = new ComboBox();
		((ComboBox)field).setImmediate(true);
		addListener(this);
		setCodeTableData(datas);
		field.setWidth(fieldWidth);
		addComponent(field);
	}
	
	public void setCodeTableData(List<String> datas) {
		if(datas != null) {
			for(String data : datas) {
				((Select)field).addItem(data);
			}
		}
	}
	
	public void setCodeTableData(String[] datas) {
		if(datas != null) {
			for(String data : datas) {
				((Select)field).addItem(data);
			}
		}
	}
	
	public void setError(boolean isError, String errorMsg) {
		if(isError) {
			((ComboBox)field).setComponentError(new UserError(errorMsg));
		} else {
			((ComboBox)field).setComponentError(null);
		}
	}

	@Override
	public Object getValue() {
		Object value = field.getValue();
		if(value == null) {
			return null;
		}
		return field.getValue().toString();
	}

	@Override
	public void setValue(Object value) {
		field.setValue(value);
	}



	@Override
	public Field getField() {
		return this;
	}
}
