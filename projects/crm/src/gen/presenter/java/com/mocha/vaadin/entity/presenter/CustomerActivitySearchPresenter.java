package com.mocha.vaadin.entity.presenter;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.model.BaseEntity;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.panel.impl.SearchPanel.SearchListener;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.crm.dao.CampaignDao;
import com.mocha.vaadin.entity.view.CustomerActivitySearch;
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
		CustomerActivitySearch customerActivitySearch = (CustomerActivitySearch) viewer;
		customerActivitySearch.getConditionPanel().getCreateBtn().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				postViewer("CustomerActivityView");
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
}

