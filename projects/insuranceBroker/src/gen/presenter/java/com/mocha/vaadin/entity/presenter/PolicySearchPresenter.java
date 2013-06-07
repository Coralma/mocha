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
import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel.SearchListener;
import com.coral.vaadin.widget.component.GlobleSearchWidget.GlobleSearchListener;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.vaadin.entity.view.PolicySearch;
import com.mocha.ib.model.Policy;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PolicySearchPresenter extends AppCommonPresenter implements Presenter {

	private PolicyDao dao = SpringContextUtils.getBean(PolicyDao.class);
	
	public PolicySearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new PolicySearch();
		// load all data.
		List entities = dao.findAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "PolicySearch";
	}
	
	@Override
	public void bind() {
		final PolicySearch policySearch = (PolicySearch) viewer;
		policySearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("PolicyView");
			}
		});
		policySearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<Policy> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				policySearch.setValue(customers);
				policySearch.buildSearchCardPanel();
			}
		});
		policySearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("PolicyView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("PolicySearch");
				}
			}
		});
	}
	
	public void remove(Object entity) {
		if(entity != null) {
			dao.remove(((BaseEntity)entity).getID());
		}
	}
	
	public SearchFilterBuilder buildFuzzySearch(String condition) {
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(Policy.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("customerName", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("insuranceCompany", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("category", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("insuranceProduct", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("policyNo", condition));
		return filterBuilder;
	}
	

}

