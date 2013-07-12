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
import com.mocha.vaadin.entity.view.OrderSearchView;
import com.mocha.co.model.Order;
import com.mocha.co.service.OrderService;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OrderSearchViewPresenter extends AppCommonPresenter implements Presenter {

	private OrderDao dao = SpringContextUtils.getBean(OrderDao.class);
	
	public OrderSearchViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new OrderSearchView();
		// load all data.
//		List entities = dao.findAll();
		OrderService orderService = new OrderService(eventBus.getUser());
		List entities = orderService.loadEbayOrders();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "OrderSearchView";
	}
	
	@Override
	public void bind() {
		final OrderSearchView orderSearchView = (OrderSearchView) viewer;
		orderSearchView.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("OrderView");
			}
		});
		orderSearchView.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<Order> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				orderSearchView.setValue(customers);
				orderSearchView.buildSearchCardPanel();
			}
		});
		orderSearchView.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("OrderView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("OrderSearchView");
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
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(Order.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("orderTotals", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("orderProductSummary", condition));
		return filterBuilder;
	}
	

}

