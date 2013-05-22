package com.coral.vaadin.view.template.sat.panel.impl;

import java.util.List;
import java.util.Map;

import com.coral.vaadin.view.template.sat.panel.ISearchPanel;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.ActionButton;
import com.coral.vaadin.widget.field.FieldStatus;
import com.coral.vaadin.widget.fields.search.StringSearchFieldWidget;
import com.vaadin.ui.VerticalLayout;

public abstract class SearchPanel extends VerticalLayout implements ISearchPanel, Viewer {

	private static final long serialVersionUID = -1159571598614146156L;
	private SearchConditionPanel conditionPanel = new SearchConditionPanel();

	public SearchPanel() {
		this.addStyleName("viewPanel");
		this.setSpacing(true);
		build();
	}
	
	public void attach() {
		this.addComponent(conditionPanel);
		for(Object entity : getEntityList()) {
			try {
				SearchEntityCard searchEntityCard = (SearchEntityCard)getEntityCardClass().newInstance();
				searchEntityCard.setEntity(entity);
				addComponent(searchEntityCard);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Field createFieldWidget(FieldStatus fieldStatus) {
		StringSearchFieldWidget stringSearchFieldWidget = new StringSearchFieldWidget(fieldStatus.getLabel(), fieldStatus.getPath());
		stringSearchFieldWidget.setFieldStatus(fieldStatus);
		addSearchField(stringSearchFieldWidget);
		return stringSearchFieldWidget;
	}
	
	public void addSearchField(Field field) {
		conditionPanel.addField(field);
	}
	
	public abstract void build();
	public abstract Class getEntityCardClass();
	public abstract List getEntityList();
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean validate(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getViewerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionButton getButton(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Widget getWidget(String widgetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Widget> getWidgets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}
