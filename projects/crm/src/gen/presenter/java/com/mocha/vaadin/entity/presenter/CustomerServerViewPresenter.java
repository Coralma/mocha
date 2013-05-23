package com.mocha.vaadin.entity.presenter;

import com.mocha.crm.dao.*;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.vaadin.entity.view.CustomerServerView;
import com.mocha.crm.model.Serve;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CustomerServerViewPresenter extends CommonPresenter implements Presenter {

	private ServeDao dao = SpringContextUtils.getBean(ServeDao.class);
	
	public CustomerServerViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CustomerServerView();
	}

	@Override
	public String getPresenterName() {
		return "CustomerServerView";
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
				//TODO add action content.
			}
		});
	}
	
	/**
	  * Save value of ServeCreateView.
	  */
	public void save() {
		Serve value = (Serve)viewer.getValue();
		if(value != null) {
			dao.persist(value);
			//back();
		}
	}
	
	public void back() {
	//	if(eventBus != null) {
	//		eventBus.post(new PageChangeEvent(""));
	//	}
	}

}

