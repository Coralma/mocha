package com.mocha.vaadin.entity.presenter;

import com.mocha.crm.dao.*;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.vaadin.entity.view.CustomerView;
import com.mocha.crm.model.Customer;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CustomerViewPresenter extends AppCommonPresenter implements Presenter {

	private CustomerDao dao = SpringContextUtils.getBean(CustomerDao.class);
	
	public CustomerViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		CustomerView newView = new CustomerView();
		Object entity = this.eventBus.getContext().get("reportID");
		if(entity != null) {
			newView.setEntity(entity);
			eventBus.resetContext();
		}
		this.viewer = newView;
	}

	@Override
	public String getPresenterName() {
		return "CustomerView";
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
	  * Save value of CustomerCreateView.
	  */
	public void save() {
		Customer value = (Customer)viewer.getValue();
		if(value != null) {
			dao.persist(value);
			back();
		}
	}
	
	public void back() {
		postViewer("CustomerSearch");
	}

}

