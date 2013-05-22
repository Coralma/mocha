/**
 * 
 */
package com.coral.vaadin.widget.view.builder;

import com.coral.foundation.md.model.Property;
import com.coral.foundation.md.model.ViewField;
import com.coral.vaadin.util.CodeTableFactory;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.CodeTableField;
import com.coral.vaadin.widget.field.StringAreaField;
import com.coral.vaadin.widget.field.StringPasswordField;
import com.coral.vaadin.widget.field.StringRichField;
import com.coral.vaadin.widget.field.StringUploadField;

/**
 * @author Coral.Ma
 *
 */
public class StyleBuilderImpl implements IFieldBuilder {

	public Widget build(ViewField viewField) {
		Widget widget = null;
		String style = viewField.getProperty().getStyle();
		if(style == null) {
			return null;
		} else if(style.startsWith("area")) {
			widget = buildLarge(viewField);
		} else if(style.startsWith("code")) {
			widget = buildCode(viewField);
		} else if(style.startsWith("rich")) {
			widget = buildRich(viewField);
		} else if(style.startsWith("upload")) {
			widget = buildUpload(viewField);
		} else if(style.startsWith("password")) {
			widget = buildPassword(viewField);
		}
		return widget;
	}

	public Widget buildLarge(ViewField viewField) {
		Property dataProperty = viewField.getProperty();
		String style = dataProperty.getStyle();
		StringAreaField widget = new StringAreaField(dataProperty.getLabel());
		if(style.length() > 5) {
			String largeWidth = style.substring(5);
			((Field)widget).setFieldWidth(largeWidth);
		}
		return widget;
	}
	
	public Widget buildRich(ViewField viewField) {
		Property dataProperty = viewField.getProperty();
		String style = dataProperty.getStyle();
		StringRichField widget = new StringRichField(dataProperty.getLabel());
		if(style.length() > 5) {
			String largeWidth = style.substring(5);
			((Field)widget).setFieldWidth(largeWidth);
		}
		return widget;
	}
	
	public Widget buildCode(ViewField viewField) {
		Property dataProperty = viewField.getProperty();
		String style = dataProperty.getStyle();
		String label = dataProperty.getLabel();
		String codeTableName = style.substring(5);
		String[] datas = CodeTableFactory.getFactory().getCodeTable(codeTableName);
		CodeTableField widget = new CodeTableField(label, datas);
		return widget;
	}
	
	public Widget buildPassword(ViewField viewField) {
		return new StringPasswordField(viewField.getProperty().getLabel());
	}
	
	public Widget buildUpload(ViewField viewField) {
		return new StringUploadField(viewField.getProperty().getLabel());
	}
}
