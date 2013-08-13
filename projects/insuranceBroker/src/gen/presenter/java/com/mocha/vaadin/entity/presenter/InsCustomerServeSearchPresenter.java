package com.mocha.vaadin.entity.presenter;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.jpa.search.SearchFilter;
import com.coral.foundation.jpa.search.SearchFilterBuilder;
import com.coral.foundation.jpa.search.SearchFilterFactory;
import com.coral.foundation.persistence.BaseEntity;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel.SearchListener;
import com.coral.vaadin.widget.component.GlobleSearchWidget.GlobleSearchListener;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.ib.dao.InsuranceCustomerServeDao;
import com.mocha.ib.model.InsuranceCustomerServe;
import com.mocha.vaadin.entity.view.InsCustomerServeSearch;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class InsCustomerServeSearchPresenter extends AppCommonPresenter implements Presenter {

	private InsuranceCustomerServeDao dao = SpringContextUtils.getBean(InsuranceCustomerServeDao.class);
	
	public InsCustomerServeSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new InsCustomerServeSearch();
		// load all data.
		List entities = dao.loadAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "InsCustomerServeSearch";
	}
	
	@Override
	public void bind() {
		final InsCustomerServeSearch insCustomerServeSearch = (InsCustomerServeSearch) viewer;
		insCustomerServeSearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("InsCustomerServeView");
			}
		});
		insCustomerServeSearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<InsuranceCustomerServe> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				insCustomerServeSearch.setValue(customers);
				insCustomerServeSearch.buildSearchCardPanel();
			}
		});
		insCustomerServeSearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("InsCustomerServeView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("InsCustomerServeSearch");
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
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(InsuranceCustomerServe.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("customerName", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("type", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("date", condition));
		return filterBuilder;
	}
	

}

