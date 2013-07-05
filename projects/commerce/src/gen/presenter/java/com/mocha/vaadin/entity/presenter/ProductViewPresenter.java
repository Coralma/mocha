package com.mocha.vaadin.entity.presenter;

import com.mocha.co.dao.*;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.vaadin.entity.view.ProductView;
import com.mocha.co.model.CommerceProduct;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ProductViewPresenter extends AppCommonPresenter implements Presenter {

	private CommerceProductDao dao = SpringContextUtils.getBean(CommerceProductDao.class);
	
	public ProductViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		ProductView newView = new ProductView();
		Object entity = this.eventBus.getContext().get("Entity");
		if(entity != null) {
			newView.setEntity(entity);
			eventBus.resetContext();
		}
		this.viewer = newView;
	}

	@Override
	public String getPresenterName() {
		return "ProductView";
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
	  * Save value of CommerceProductCreateView.
	  */
	public void save() {
		CommerceProduct value = (CommerceProduct)viewer.getValue();
		if(value != null) {
			dao.persist(value);
			back();
		}
	}
	
	public void back() {
		postViewer("ProductSearch");
	}

}

