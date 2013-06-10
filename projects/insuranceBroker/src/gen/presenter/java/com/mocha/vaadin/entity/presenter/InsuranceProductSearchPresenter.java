package com.mocha.vaadin.entity.presenter;

import com.mocha.ib.dao.*;
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
import com.mocha.vaadin.entity.view.InsuranceProductSearch;
import com.mocha.ib.model.InsuranceProduct;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class InsuranceProductSearchPresenter extends AppCommonPresenter implements Presenter {

	private InsuranceProductDao dao = SpringContextUtils.getBean(InsuranceProductDao.class);
	
	public InsuranceProductSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new InsuranceProductSearch();
		// load all data.
		List entities = dao.findAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "InsuranceProductSearch";
	}
	
	@Override
	public void bind() {
		final InsuranceProductSearch insuranceProductSearch = (InsuranceProductSearch) viewer;
		insuranceProductSearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("InsuranceProductView");
			}
		});
		insuranceProductSearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<InsuranceProduct> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				insuranceProductSearch.setValue(customers);
				insuranceProductSearch.buildSearchCardPanel();
			}
		});
		insuranceProductSearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("InsuranceProductView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("InsuranceProductSearch");
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
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(InsuranceProduct.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("category", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("productName", condition));
		return filterBuilder;
	}
	

}

