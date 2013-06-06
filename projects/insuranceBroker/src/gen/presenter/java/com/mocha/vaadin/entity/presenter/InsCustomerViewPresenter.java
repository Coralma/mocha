package com.mocha.vaadin.entity.presenter;

import com.mocha.ib.dao.*;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.fields.CodeTableWidget;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.vaadin.entity.view.InsCustomerView;
import com.mocha.ib.model.InsuranceCustomer;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class InsCustomerViewPresenter extends AppCommonPresenter implements Presenter {

	private InsuranceCustomerDao dao = SpringContextUtils.getBean(InsuranceCustomerDao.class);
	
	public InsCustomerViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		InsCustomerView newView = new InsCustomerView();
		Object entity = this.eventBus.getContext().get("Entity");
		if(entity != null) {
			newView.setEntity(entity);
			eventBus.resetContext();
		}
		this.viewer = newView;
	}

	@Override
	public String getPresenterName() {
		return "InsCustomerView";
	}
	
	@Override
	public void bind() {
		initViewer((InsCustomerView) viewer);
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
	
	public void initViewer(InsCustomerView viewer) {
		CodeTableWidget ctWidget = (CodeTableWidget) viewer.getField("customerType");
		ctWidget.setValue("1");
	}
	/**
	  * Save value of InsuranceCustomerCreateView.
	  */
	public void save() {
		InsuranceCustomer value = (InsuranceCustomer)viewer.getValue();
		if(value != null) {
			dao.persist(value);
			back();
		}
	}
	
	public void back() {
		postViewer("InsCustomerSearch");
	}

}

