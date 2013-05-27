package com.coral.vaadin.view.template.sat.panel.impl;

import java.util.HashMap;
import java.util.List;

import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("serial")
public class DefaultSectionPanel extends AbstractViewLayout implements ISectionPanel {

//	public int column = 2;
	public String label;
//	public List<Field> fields = Lists.newArrayList();
//	public HashMap<Field, FieldStatus> fieldStatusMap = Maps.newHashMap();
	
	public DefaultSectionPanel() {
		this.setWidth("100%");
		this.setSpacing(true);
		this.addStyleName("default-section-panel");
	}
	
	public void attach() {
		initTitle();
		layout();
//		int index = 0;
//		HorizontalLayout rowLayout = null;
//		for(Field field : fields) {
//			FieldStatus status = field.getFieldStatus();
//			if(index == 0 || index == column || status.isChangeLine() ||status.isWholeRow()) {
//				rowLayout = new HorizontalLayout();
//				rowLayout.setWidth("100%");
//				rowLayout.setSpacing(true);
//				addComponent(rowLayout);
//				if(index == column || status.isChangeLine()) {
//					index = 0;
//				}
//				if(status.isWholeRow()) {
//					field.setFieldWidth("590");
//				}
//			}
//			rowLayout.addComponent(field);
//			rowLayout.setExpandRatio(field, 1.0f);
//			index++;
//		}
	}
	
	@Override
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
	
	public void addField(Field field) {
		fields.add(field);
//		fieldStatusMap.put(field, status);
	}
	
	private void initTitle() {
		if(label != null) {
			Label sectionTitle = new Label(label);
			sectionTitle.setWidth("100%");
			sectionTitle.addStyleName("section-label");
			addComponent(sectionTitle);
		}
	}
	
	public void setReadOnly(boolean readonly) {
		for(Field field : fields) {
			field.setReadOnly(readonly);
		}
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}
}
