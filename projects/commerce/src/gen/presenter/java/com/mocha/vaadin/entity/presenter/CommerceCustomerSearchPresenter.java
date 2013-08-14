package com.mocha.vaadin.entity.presenter;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.jpa.search.SearchFilter;
import com.coral.foundation.jpa.search.SearchFilterBuilder;
import com.coral.foundation.jpa.search.SearchFilterFactory;
import com.coral.foundation.persistence.BaseEntity;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel.SearchListener;
import com.coral.vaadin.widget.component.GlobleSearchWidget.GlobleSearchListener;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.co.dao.CommerceCustomerDao;
import com.mocha.co.model.CommerceCustomer;
import com.mocha.vaadin.entity.view.CommerceCustomerSearch;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CommerceCustomerSearchPresenter extends AppCommonPresenter implements Presenter {

	private CommerceCustomerDao dao = SpringContextUtils.getBean(CommerceCustomerDao.class);
	
	public CommerceCustomerSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CommerceCustomerSearch();
		// load all data.
		List entities = dao.loadAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "CommerceCustomerSearch";
	}
	
	@Override
	public void bind() {
		final CommerceCustomerSearch commerceCustomerSearch = (CommerceCustomerSearch) viewer;
		commerceCustomerSearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("CommerceCustomerView");
			}
		});
		commerceCustomerSearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<CommerceCustomer> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				commerceCustomerSearch.setValue(customers);
				commerceCustomerSearch.buildSearchCardPanel();
			}
		});
		commerceCustomerSearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("CommerceCustomerView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("CommerceCustomerSearch");
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
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(CommerceCustomer.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("customerType", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("status", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("name", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("district", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("contectPerson", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("mobile", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("phone", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("fax", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("email", condition));
		return filterBuilder;
	}
	

}

