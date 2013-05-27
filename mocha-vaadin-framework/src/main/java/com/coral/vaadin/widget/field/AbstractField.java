/**
 * 
 */
package com.coral.vaadin.widget.field;

import java.util.ArrayList;
import java.util.List;

import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.coral.vaadin.widget.validate.RequiredValidator;
import com.coral.vaadin.widget.validate.Validator;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * @author Coral
 *
 */
public abstract class AbstractField extends HorizontalLayout implements ValueChangeListener {

	
	protected Label fieldLabel;
	protected String label;
	protected String labelWidth = "100";
	protected String fieldWidth = "220";
	protected String largeFieldWidth = "690";
	protected com.vaadin.ui.Field field;
	protected Object data;
	protected List<Validator> validators = new ArrayList<Validator>();
	protected FieldStatus fieldStatus;
	
	public AbstractField() {
		
	}
	public AbstractField(String label) {
		this.label = label;
		if(label != null) {
			fieldLabel = new Label(label+":",Label.CONTENT_XHTML);
			fieldLabel.setWidth(labelWidth);
			addComponent(fieldLabel);
			setComponentAlignment(fieldLabel, Alignment.TOP_LEFT);
		}
	}
	
	public void addListener(ValueChangeListener listener) {
		field.addListener(listener);
	}

	/** field validate */
	public Result validate(String type) {
		Result result = new Result();
		for(Validator validator : validators) {
			boolean isPass = validator.validate(type);
			if(!isPass) {
				result.setPass(false);
				return result;
			}
		}
		return result;
	}
	
	public List<Widget> getSubFields() {
	    return null;
	}
	
	public void setRequired(boolean required) {
//		field.setRequired(required);
//		fieldLabel.setCaption(label+"<span class=\"v-required-field-indicator\">*</span>");
		if(required) {
			fieldLabel.setValue(label+"<span class=\"v-required-field-indicator\">*</span>:");
			validators.add(new RequiredValidator(getField()));
		}
	}
	
	 public void valueChange(Property.ValueChangeEvent event) {
		 validate(Validator.ONBLUR);
	 }
	
	public abstract Field getField();
	
	public void setFieldWidth(String width) {
		if(width != null) {
			field.setWidth(width);
		}
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	public void detach() {
		field.detach();
	}
	
//	public void setError(boolean isError, String errorMsg) {
//		if(isError) {
//			field.addStyleName("c-field-error");
//		} else {
//			field.removeStyleName("c-field-error");
//		}
//	}
	
	public FieldStatus getFieldStatus() {
		return fieldStatus;
	}

	/**
	 * @param fieldStatus the fieldStatus to set
	 */
	public void setFieldStatus(FieldStatus fieldStatus) {
		this.fieldStatus = fieldStatus;
	} 
}
