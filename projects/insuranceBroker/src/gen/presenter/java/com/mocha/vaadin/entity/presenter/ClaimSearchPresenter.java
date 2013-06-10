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
import com.mocha.vaadin.entity.view.ClaimSearch;
import com.mocha.ib.model.Claim;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ClaimSearchPresenter extends AppCommonPresenter implements Presenter {

	private ClaimDao dao = SpringContextUtils.getBean(ClaimDao.class);
	
	public ClaimSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new ClaimSearch();
		// load all data.
		List entities = dao.findAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "ClaimSearch";
	}
	
	@Override
	public void bind() {
		final ClaimSearch claimSearch = (ClaimSearch) viewer;
		claimSearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("ClaimView");
			}
		});
		claimSearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<Claim> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				claimSearch.setValue(customers);
				claimSearch.buildSearchCardPanel();
			}
		});
		claimSearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("ClaimView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("ClaimSearch");
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
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(Claim.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("policyNo", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("status", condition));
		return filterBuilder;
	}
	

}

