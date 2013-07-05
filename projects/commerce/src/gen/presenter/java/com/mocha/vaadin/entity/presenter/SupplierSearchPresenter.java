package com.mocha.vaadin.entity.presenter;

import com.mocha.co.dao.*;
import java.util.List;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.model.BaseEntity;
import com.coral.foundation.jpa.search.SearchFilter;
import com.coral.foundation.jpa.search.SearchFilterBuilder;
import com.coral.foundation.jpa.search.SearchFilterFactory;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel.SearchListener;
import com.coral.vaadin.widget.component.GlobleSearchWidget.GlobleSearchListener;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.vaadin.entity.view.SupplierSearch;
import com.mocha.co.model.Supplier;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class SupplierSearchPresenter extends AppCommonPresenter implements Presenter {

	private SupplierDao dao = SpringContextUtils.getBean(SupplierDao.class);
	
	public SupplierSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new SupplierSearch();
		// load all data.
		List entities = dao.findAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "SupplierSearch";
	}
	
	@Override
	public void bind() {
		final SupplierSearch supplierSearch = (SupplierSearch) viewer;
		supplierSearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("SupplierView");
			}
		});
		supplierSearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<Supplier> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				supplierSearch.setValue(customers);
				supplierSearch.buildSearchCardPanel();
			}
		});
		supplierSearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("SupplierView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("SupplierSearch");
				}
			}
			
			@Override
			public void cardClick(Object value) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public void remove(Object entity) {
		if(entity != null) {
			dao.remove(((BaseEntity)entity).getID());
		}
	}
	
	public SearchFilterBuilder buildFuzzySearch(String condition) {
		if(StrUtils.isEmpty(condition)) {
			return null;
		}
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(Supplier.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("supplierName", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("contectPerson", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("email", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("mobile", condition));
		return filterBuilder;
	}
	

}

