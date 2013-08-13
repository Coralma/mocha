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
import com.mocha.ib.dao.InsuranceCustomerDao;
import com.mocha.ib.model.InsuranceCustomer;
import com.mocha.vaadin.entity.view.InsCustomerSearch;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class InsCustomerSearchPresenter extends AppCommonPresenter implements Presenter {

	private InsuranceCustomerDao dao = SpringContextUtils.getBean(InsuranceCustomerDao.class);
	
	public InsCustomerSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new InsCustomerSearch(eventBus.getUser()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getCardSpecialIcon(Object value) {
				InsuranceCustomer customer = (InsuranceCustomer)value;
				if("1".equals(customer.getCustomerType())) {
					return "card-com.png";
				} else {
					return "card-indi.png";
				}
			}
		};
		// load all data.
		List entities = dao.loadAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "InsCustomerSearch";
	}
	
	@Override
	public void bind() {
		final InsCustomerSearch insCustomerSearch = (InsCustomerSearch) viewer;
		insCustomerSearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("InsCustomerView");
			}
		});
		insCustomerSearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<InsuranceCustomer> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				insCustomerSearch.setValue(customers);
				insCustomerSearch.buildSearchCardPanel();
			}
		});
		insCustomerSearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("InsCustomerView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("InsCustomerSearch");
				} else if("View".equals(action)) {
					eventBus.put("Entity", entity);
					postCustomizeClass(InsCustomerDisplayPresenter.class.getName());
				}
			}

			@Override
			public void cardClick(Object value) {
				eventBus.put("Entity", value);
				postCustomizeClass(InsCustomerDisplayPresenter.class.getName());
				
			}
		});
	}
	
	public void remove(Object entity) {
		if(entity != null) {
			dao.remove(((BaseEntity)entity).getID());
		}
	}
	
	public SearchFilterBuilder buildFuzzySearch(String condition) {
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(InsuranceCustomer.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("customerType", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("status", condition));
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

