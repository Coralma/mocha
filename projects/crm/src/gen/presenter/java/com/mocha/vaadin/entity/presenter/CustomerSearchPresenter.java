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
import com.mocha.vaadin.entity.view.CustomerSearch;
import com.mocha.crm.model.Customer;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CustomerSearchPresenter extends AppCommonPresenter implements Presenter {

	private CustomerDao dao = SpringContextUtils.getBean(CustomerDao.class);
	
	public CustomerSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CustomerSearch();
		// load all data.
		List entities = dao.findAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "CustomerSearch";
	}
	
	@Override
	public void bind() {
		final CustomerSearch customerSearch = (CustomerSearch) viewer;
		customerSearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("CustomerView");
			}
		});
		customerSearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<Customer> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				customerSearch.setValue(customers);
				customerSearch.buildSearchCardPanel();
			}
		});
		customerSearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("CustomerView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("CustomerSearch");
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
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(Customer.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("name", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("district", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("contectPerson", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("mobile", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("phone", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("fax", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("email", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("accountBank", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("accountNumber", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("accountPerson", condition));
		return filterBuilder;
	}
	

}

