package com.coral.vaadin.view.template.sat.panel.impl;

import java.util.List;
import java.util.Map;

import com.coral.vaadin.view.template.sat.panel.ISearchPanel;
import com.coral.vaadin.view.template.sat.panel.impl.SearchEntityCard.SearchEntityCardListener;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.field.ActionButton;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.coral.vaadin.widget.fields.search.StringSearchFieldWidget;
import com.vaadin.ui.VerticalLayout;

public abstract class SearchPanel extends VerticalLayout implements ISearchPanel, Viewer {

	private static final long serialVersionUID = -1159571598614146156L;
	private SearchConditionPanel conditionPanel = new SearchConditionPanel();
	private SearchListener listener;
	public SearchPanel() {
		this.addStyleName("viewPanel");
		this.setSpacing(true);
		build();
	}
	
	public void attach() {
		this.addComponent(conditionPanel);
		for(final Object entity : getEntityList()) {
			try {
				SearchEntityCard searchEntityCard = (SearchEntityCard)getEntityCardClass().newInstance();
				searchEntityCard.setListener(new SearchEntityCardListener() {
					
					@Override
					public void handleAction(String name, String action) {
						if(listener != null) {
							listener.handleAction(name, action, entity);
						}
					}
				});
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
	public Result validate(String type) {
		// TODO Auto-generated method stub
		return new Result();
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

	/**
	 * @return the conditionPanel
	 */
	public SearchConditionPanel getConditionPanel() {
		return conditionPanel;
	}

	/**
	 * @return the listener
	 */
	public SearchListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(SearchListener listener) {
		this.listener = listener;
	}
	
	public interface SearchListener {
		public void handleAction(String name, String action, Object entity);
	}
}
