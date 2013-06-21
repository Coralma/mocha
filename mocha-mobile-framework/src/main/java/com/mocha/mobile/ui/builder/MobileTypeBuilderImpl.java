/**
 * 
 */
package com.mocha.mobile.ui.builder;

import java.lang.reflect.Method;

import com.coral.foundation.md.model.ViewField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

/**
 * @author Administrator
 *
 */
public class MobileTypeBuilderImpl implements IMobileFieldBuilder {

	@Override
	public Field build(ViewField viewField) {
		String type = viewField.getProperty().getType();
		String typeBuildMethodStr = "build" + type;
		try {
			Method typeBuildMethod = this.getClass().getMethod(typeBuildMethodStr, ViewField.class);
			return (Field)typeBuildMethod.invoke(this, viewField);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Field buildString(ViewField viewField) {
		Field field = new TextField(viewField.getFieldName()); 
        ((TextField) field).setNullRepresentation("");
		return field;
	}
	
	public Field buildDate(ViewField fieldElement) {
		DateField dateField = new DateField(fieldElement.getFieldName());
		return dateField;
	}
	
	public Field buildBigDecimal(ViewField fieldElement) {
		Field field = new TextField(fieldElement.getFieldName());
		((TextField) field).setNullRepresentation("");
		return field;
	}

}
