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
import com.mocha.co.dao.CommerceProductDao;
import com.mocha.co.model.CommerceProduct;
import com.mocha.vaadin.entity.view.ProductSearch;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ProductSearchPresenter extends AppCommonPresenter implements Presenter {

	private CommerceProductDao dao = SpringContextUtils.getBean(CommerceProductDao.class);
	
	public ProductSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new ProductSearch();
		// load all data.
		List entities = dao.loadAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "ProductSearch";
	}
	
	@Override
	public void bind() {
		final ProductSearch productSearch = (ProductSearch) viewer;
		productSearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("ProductView");
			}
		});
		productSearch.getConditionPanel().getGlobleSearchWidget().setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				List<CommerceProduct> customers = dao.fuzzySearch(buildFuzzySearch(condition));
				productSearch.setValue(customers);
				productSearch.buildSearchCardPanel();
			}
		});
		productSearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("ProductView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("ProductSearch");
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
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(CommerceProduct.class);
		filterBuilder.getSearchFilters().add(SearchFilter.like("productName", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("brand", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("color", condition));
		filterBuilder.getSearchFilters().add(SearchFilter.like("salePrice", condition));
		return filterBuilder;
	}
	

}

