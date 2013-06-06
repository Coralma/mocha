package com.mocha.vaadin.entity.presenter;

import com.mocha.ib.dao.*;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.vaadin.entity.view.InsCustomerServeView;
import com.mocha.ib.model.InsuranceCustomerServe;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class InsCustomerServeViewPresenter extends AppCommonPresenter implements Presenter {

	private InsuranceCustomerServeDao dao = SpringContextUtils.getBean(InsuranceCustomerServeDao.class);
	
	public InsCustomerServeViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		InsCustomerServeView newView = new InsCustomerServeView();
		Object entity = this.eventBus.getContext().get("Entity");
		if(entity != null) {
			newView.setEntity(entity);
			eventBus.resetContext();
		}
		this.viewer = newView;
	}

	@Override
	public String getPresenterName() {
		return "InsCustomerServeView";
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
	  * Save value of InsuranceCustomerServeCreateView.
	  */
	public void save() {
		InsuranceCustomerServe value = (InsuranceCustomerServe)viewer.getValue();
		if(value != null) {
			dao.persist(value);
			back();
		}
	}
	
	public void back() {
		postViewer("InsCustomerServeSearch");
	}

}

