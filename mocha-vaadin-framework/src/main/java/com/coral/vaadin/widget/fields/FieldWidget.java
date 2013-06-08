/**
 * 
 */
package com.coral.vaadin.widget.fields;

import org.apache.commons.lang.StringUtils;

import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.helper.NotificationHelper;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * @author Coral.Ma
 *
 */
public abstract class FieldWidget extends FormLayout implements ValueChangeListener,Field {

	protected String label;
	protected com.vaadin.ui.Field field;
	protected String fieldWidth = "220px";
	protected Property property;
	protected boolean required = false;
	protected FieldStatus fieldStatus;

	public FieldWidget(String label) {
		this.label = label;
		this.setMargin(false);
		this.setWidth("100%");
		this.addStyleName("field-widget");
		initField();
	}
	
	public abstract void initField();
	
	public void attach() {
		// add label
//		fieldLabel = new Label(label, Label.CONTENT_XHTML);
//		fieldLabel.setWidth(labelWidth);
//		this.addComponent(fieldLabel);
		// add field
		field.setWidth(fieldWidth);
		if(field instanceof AbstractField) {
			((AbstractField)field).setImmediate(true);
		}
		field.setPropertyDataSource(property);
		this.addComponent(field);
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		((AbstractField)field).setComponentError(null);
	}
	
	@Override
	public void addListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRequired(boolean required) {
		this.required = required;
		field.setRequired(required);
//		field.addValidator(new RegexpValidator("^(?=[^A-Za-z]+$).*[0-9].*$", "The input value must be a number."));
		field.setRequiredError(label + " can not be empty.");
	}

	@Override
	public void setError(boolean isError, String errorMsg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue() {
		return field.getValue();
	}

	@Override
	public void setValue(Object value) {
		field.setValue(value);
	}

	@Override
	public Result validate(String type) {
		Result validator = new Result();
		try {
			if(field instanceof AbstractField) {
				((AbstractField)field).setComponentError(null);
			}
			field.validate();
//			if(required) {
//				Object value = field.getValue();
//				if(StrUtils.isEmpty(value)) {
//					validator.setPass(false);
//					String errorMessage = label + " can not be empty.";
//					validator.setErrorMessage(errorMessage);
//					((AbstractField)field).setComponentError(new UserError(errorMessage));
//				}
//			}
		} catch (Exception e) {
			validator.setPass(false);
			String errorMessage = e.getMessage();
			validator.setErrorMessage(errorMessage);
			if(field instanceof AbstractField) {
				((AbstractField)field).setComponentError(new UserError(errorMessage));
			}
		}
		
		return validator;
	}
	
	public void setReadOnly(boolean readonly) {
		field.setReadOnly(readonly);
	}
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the field
	 */
	public com.vaadin.ui.Field getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(com.vaadin.ui.Field field) {
		this.field = field;
	}

	/**
	 * @return the fieldWidth
	 */
	public String getFieldWidth() {
		return fieldWidth;
	}

	/**
	 * @param fieldWidth the fieldWidth to set
	 */
	public void setFieldWidth(String fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	/**
	 * @return the property
	 */
	public Property getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(Property property) {
		this.property = property;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @return the fieldStatus
	 */
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
