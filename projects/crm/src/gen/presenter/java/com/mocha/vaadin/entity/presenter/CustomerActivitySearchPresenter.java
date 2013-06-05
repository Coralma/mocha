package com.mocha.vaadin.entity.presenter;

import com.mocha.crm.dao.*;
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
import com.mocha.vaadin.entity.view.CustomerActivitySearch;
import com.mocha.crm.model.Campaign;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CustomerActivitySearchPresenter extends AppCommonPresenter implements Presenter {

	private CampaignDao dao = SpringContextUtils.getBean(CampaignDao.class);
	
	public CustomerActivitySearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CustomerActivitySearch();
		// load all data.
		List entities = dao.findAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "CustomerActivitySearch";
	}
	
	@Override
	public void bind() {
		final CustomerActivitySearch customerActivitySearch = (CustomerActivitySearch) viewer;
		customerActivitySearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("CustomerActivityView");
			}
		});
		customerActivitySearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<Campaign> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				customerActivitySearch.setValue(customers);
				customerActivitySearch.buildSearchCardPanel();
			}
		});
		customerActivitySearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("CustomerActivityView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("CustomerActivitySearch");
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
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(Campaign.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("campaignName", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("type", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("startDate", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("endDate", condition));
		return filterBuilder;
	}
	

}

