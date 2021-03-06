/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import java.util.List;

import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.google.common.collect.Lists;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public abstract class AbstractViewLayout extends VerticalLayout {

	public int column = 2;
	public List<Field> fields = Lists.newArrayList();
	
	public void layout() {
		int index = 0;
		HorizontalLayout rowLayout = null;
		for(Field field : fields) {
			FieldStatus status = field.getFieldStatus();
			if(index == 0 || index == column || status.isChangeLine() ||status.isWholeRow()) {
				rowLayout = new HorizontalLayout();
				rowLayout.setWidth("100%");
				rowLayout.setSpacing(true);
				addComponent(rowLayout);
				if(index == column || status.isChangeLine()) {
					index = 0;
				}
				if(status.isWholeRow()) {
					field.setFieldWidth(getWholeRowWidth());
				}
			}
			rowLayout.addComponent(field);
			rowLayout.setExpandRatio(field, 1.0f);
			index++;
		}
	}
	
	public List<Result> validate() {
		List<Result> errorResultList = Lists.newArrayList(); 
		boolean valid = true;
		for(Field field : fields) {
			Result result = field.validate(null);
			boolean fieldValidate = result.isPass();
			if(fieldValidate == false) {
				errorResultList.add(result);
			}
		}
		return errorResultList;
	}
	
	public String getWholeRowWidth() {
		return "610px";
	}
	
	public void addField(Field field) {
		fields.add(field);
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
}
