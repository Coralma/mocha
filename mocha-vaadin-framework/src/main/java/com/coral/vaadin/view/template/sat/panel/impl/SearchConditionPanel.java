/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import com.coral.vaadin.view.template.sat.panel.ISearchConditionPanel;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.component.GlobleSearchWidget;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral
 *
 */
public class SearchConditionPanel extends AbstractViewLayout implements ISearchConditionPanel {

	public Button searchButton = new Button("Search");
	public Button globleButton = new Button("Globle");
	
	public SearchConditionPanel() {
		this.setSpacing(false);
		this.setWidth("765px");
		this.addStyleName("search-condition-layout");
	}
	
	public void addField(Field field) {
		fields.add(field);
	}
	
	public void attach() {
		globleSearchPanel();
//		layout();
//		HorizontalLayout buttonPanel = buildButtonPanel();
//		this.addComponent(buttonPanel);
//		this.setComponentAlignment(buttonPanel, Alignment.MIDDLE_RIGHT);
	}
	
	public void globleSearchPanel() {
		this.removeAllComponents();
		HorizontalLayout conditionLayout = new HorizontalLayout();
		conditionLayout.setSpacing(true);
		GlobleSearchWidget searchTextField = new GlobleSearchWidget();
		conditionLayout.addComponent(searchTextField);
		
		Button advanceBtn = new Button("Advance");
		advanceBtn.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				advanceSearchPanel();
			}
		});
		advanceBtn.addStyleName(BaseTheme.BUTTON_LINK);
		conditionLayout.addComponent(advanceBtn);
		conditionLayout.setComponentAlignment(advanceBtn,Alignment.MIDDLE_CENTER);
		this.addComponent(conditionLayout);
	}
	
	public void advanceSearchPanel() {
		this.removeAllComponents();
		layout();
		HorizontalLayout buttonPanel = buildButtonPanel();
		this.addComponent(buttonPanel);
		this.setComponentAlignment(buttonPanel, Alignment.MIDDLE_RIGHT);
	}
	
	public HorizontalLayout buildButtonPanel() {
		globleButton.addStyleName(BaseTheme.BUTTON_LINK);
		HorizontalLayout buttonPanel = new HorizontalLayout();
		buttonPanel.addStyleName("advance-search-button-panel");
		buttonPanel.setSpacing(true);
		buttonPanel.addComponent(searchButton);
		buttonPanel.addComponent(globleButton);
		globleButton.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				globleSearchPanel();				
			}
		});
		buttonPanel.setComponentAlignment(globleButton, Alignment.MIDDLE_CENTER);
		return buttonPanel;
	}
}
