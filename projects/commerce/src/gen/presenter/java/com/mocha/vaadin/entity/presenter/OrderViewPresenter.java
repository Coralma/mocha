package com.mocha.vaadin.entity.presenter;

import com.mocha.co.dao.*;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.vaadin.entity.view.OrderView;
import com.mocha.co.model.Order;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OrderViewPresenter extends AppCommonPresenter implements Presenter {

	private OrderDao dao = SpringContextUtils.getBean(OrderDao.class);
	
	public OrderViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		OrderView newView = new OrderView();
		Object entity = this.eventBus.getContext().get("Entity");
		if(entity != null) {
			newView.setEntity(entity);
			eventBus.resetContext();
		}
		this.viewer = newView;
	}

	@Override
	public String getPresenterName() {
		return "OrderView";
	}
	
	@Override
	public void bind() {
		//TODO add and edit your action.
		Button saveButton = viewer.getButton("save");
		saveButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				save();
			}
		});
		Button backButton = viewer.getButton("back");
		backButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				back();
			}
		});
	}
	
	/**
	  * Save value of OrderCreateView.
	  */
	public void save() {
		Order value = (Order)viewer.getValue();
		if(value != null) {
			dao.persist(value);
			back();
		}
	}
	
	public void back() {
		postViewer("OrderSearchView");
	}

}

