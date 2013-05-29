package com.mocha.vaadin.entity.presenter;

import com.mocha.crm.dao.*;
import java.util.List;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.model.BaseEntity;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel.SearchListener;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.vaadin.entity.view.CustomerServerSearch;
import com.mocha.crm.model.Serve;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CustomerServerSearchPresenter extends AppCommonPresenter implements Presenter {

	private ServeDao dao = SpringContextUtils.getBean(ServeDao.class);
	
	public CustomerServerSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CustomerServerSearch();
		// load all data.
		List entities = dao.findAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "CustomerServerSearch";
	}
	
	@Override
	public void bind() {
		CustomerServerSearch customerServerSearch = (CustomerServerSearch) viewer;
		customerServerSearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("CustomerServerView");
			}
		});
		customerServerSearch.setListener(new SearchListener() {
			@Override
			public void handleAction(String name, String action, Object entity) {
				if("Edit".equals(action)) {
					postViewer("CustomerServerView",entity);
				} else if("Delete".equals(action)) {
					remove(entity);
					postViewer("CustomerServerSearch");
				}
			}
		});
	}
	
	public void remove(Object entity) {
		if(entity != null) {
			dao.remove(((BaseEntity)entity).getID());
		}
	}
	

}

