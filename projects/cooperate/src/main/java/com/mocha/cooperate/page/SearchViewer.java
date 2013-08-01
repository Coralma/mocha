package com.mocha.cooperate.page;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.component.SearchTextField;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.widget.PagingVerticalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class SearchViewer extends CommonViewer implements Viewer {

	private PagingVerticalLayout cooperateLayout;
	private PagingVerticalLayout financialAdvisorLayout;
	private PagingVerticalLayout ecommerceLayout;
	
	private String pageWidth = SystemProperty.content_page_width;
	private String searchText;
	
	public SearchViewer(String searchText) {
		this.searchText = searchText;
	}
	
	@Override
	public void attach() {
		super.attach();
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("search-condition-panel");
		layout.setWidth(pageWidth);
		
		SearchTextField searchTextField = new SearchTextField();
		searchTextField.setWidth(SystemProperty.content_widget_width);
		layout.addComponent(searchTextField);
		
		this.addComponent(layout);
		
		TabSheet tabSheet = new TabSheet();
		tabSheet.addStyleName(Reindeer.TABSHEET_MINIMAL);
		tabSheet.setWidth(pageWidth);
		
		cooperateLayout = new PagingVerticalLayout();
		financialAdvisorLayout = new PagingVerticalLayout();
		ecommerceLayout = new PagingVerticalLayout();
		
		tabSheet.addTab(cooperateLayout,"Cooperation");
		tabSheet.addTab(financialAdvisorLayout,"Financial Advisor");
		tabSheet.addTab(ecommerceLayout,"ECommerce");
		
		this.addComponent(tabSheet);
	}

	@Override
	public String getViewerTitle() {
		return "Search";
	}

}
