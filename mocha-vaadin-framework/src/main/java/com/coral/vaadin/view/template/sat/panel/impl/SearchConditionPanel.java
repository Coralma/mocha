/**
 * 
 */
package com.coral.vaadin.view.template.sat.panel.impl;

import com.coral.vaadin.view.template.sat.panel.ISearchConditionPanel;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.WidgetFactory;
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

	protected GlobleSearchWidget globleSearchWidget = new GlobleSearchWidget();
	public Button searchButton = WidgetFactory.createButton("Search");
	public Button globleButton = WidgetFactory.createLink("Globle");
	private String panelWidth="765px";
	private Button createBtn = WidgetFactory.createButton("Create");
//	private Button importBtn = WidgetFactory.createButton("Import");
//	private Button exportBtn = WidgetFactory.createButton("Export");
	
	public SearchConditionPanel() {
		this.setSpacing(false);
		this.setWidth(panelWidth);
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
		
		HorizontalLayout searcLayout = new HorizontalLayout();
		searcLayout.setWidth(panelWidth);

		HorizontalLayout conditionLayout = new HorizontalLayout();
		conditionLayout.setSpacing(true);
		
		conditionLayout.addComponent(globleSearchWidget);
		
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
		searcLayout.addComponent(conditionLayout);
		
		// create button
		HorizontalLayout btnLayout = new HorizontalLayout();
		btnLayout.setStyleName("search-create-btn");
		btnLayout.addComponent(createBtn);
//		btnLayout.addComponent(importBtn);
//		btnLayout.addComponent(exportBtn);
		searcLayout.addComponent(btnLayout);
		searcLayout.setComponentAlignment(btnLayout, Alignment.MIDDLE_RIGHT);
		
		this.addComponent(searcLayout);
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

	/**
	 * @return the globleButton
	 */
	public Button getGlobleButton() {
		return globleButton;
	}

	/**
	 * @return the createBtn
	 */
	public Button getCreateBtn() {
		return createBtn;
	}

	/**
	 * @return the globleSearchWidget
	 */
	public GlobleSearchWidget getGlobleSearchWidget() {
		return globleSearchWidget;
	}
}
