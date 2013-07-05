package com.mocha.vaadin.entity.presenter;

import com.mocha.co.dao.*;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.vaadin.entity.view.SupplierView;
import com.mocha.co.model.Supplier;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class SupplierViewPresenter extends AppCommonPresenter implements Presenter {

	private SupplierDao dao = SpringContextUtils.getBean(SupplierDao.class);
	
	public SupplierViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		SupplierView newView = new SupplierView();
		Object entity = this.eventBus.getContext().get("Entity");
		if(entity != null) {
			newView.setEntity(entity);
			eventBus.resetContext();
		}
		this.viewer = newView;
	}

	@Override
	public String getPresenterName() {
		return "SupplierView";
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
	  * Save value of SupplierCreateView.
	  */
	public void save() {
		Supplier value = (Supplier)viewer.getValue();
		if(value != null) {
			dao.persist(value);
			back();
		}
	}
	
	public void back() {
		postViewer("SupplierSearch");
	}

}

