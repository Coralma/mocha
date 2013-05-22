/**
 * 
 */
package com.coral.vaadin.widget.view.builder;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.constant.SystemConstant;
import com.coral.foundation.md.model.ViewField;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.BigDecimalField;
import com.coral.vaadin.widget.field.DateField;
import com.coral.vaadin.widget.field.StringField;
import com.coral.vaadin.widget.table.AbstractTable;
import com.coral.vaadin.widget.table.EntityTable;

/**
 * @author Coral.Ma
 *
 */
public class TypeBuilderImpl implements IFieldBuilder {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public Widget build(ViewField viewField) {
		Widget widget = null;
		String type = viewField.getProperty().getType();
		String typeBuildMethodStr = "build" + type;
		try {
			Method typeBuildMethod = this.getClass().getMethod(typeBuildMethodStr, ViewField.class);
			widget = (Widget) typeBuildMethod.invoke(this, viewField);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return widget;
	}
	
	public Widget buildString(ViewField fieldElement) {
		return new StringField(fieldElement.getProperty().getLabel());
	}
	
	public Widget buildDate(ViewField fieldElement) {
		return new DateField(fieldElement.getProperty().getLabel());
	}
	
	public Widget buildBigDecimal(ViewField fieldElement) {
		return new BigDecimalField(fieldElement.getProperty().getLabel());
	}

	public Widget buildList(ViewField fieldElement) {
		EntityTable entityTable = null;
		try {
			String style = fieldElement.getProperty().getStyle();
			String refEntityName = fieldElement.getProperty().getRef();
//			String viewClassName = SystemConstant.TCREATE_PAGE_PKG + "." + fieldElement.getProperty().getRef() + "CreateView";
//			Class<?> viewClass = Class.forName(viewClassName);
			String tableClassName = SystemConstant.TTable_PKG + "." + refEntityName + "Table";
			Class<?> tableClass = Class.forName(tableClassName);
			AbstractTable table = (AbstractTable)tableClass.getDeclaredConstructor(String.class, String.class).newInstance(style, refEntityName);
			if(table != null) {
				table.initTable(fieldElement.getProperty().getLabel());
				entityTable = table.getTable();
			}				
		} catch (Exception e) {
			log.error("The " + fieldElement.getFieldName() + " table doesn't existed. Please check the model definition.");
			e.printStackTrace();
		}
		return entityTable;
	}
}
