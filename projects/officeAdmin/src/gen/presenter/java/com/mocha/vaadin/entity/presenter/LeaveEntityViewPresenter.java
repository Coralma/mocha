package com.mocha.vaadin.entity.presenter;

import com.mocha.oa.dao.*;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.vaadin.entity.view.LeaveEntityView;
import com.mocha.oa.model.Leave;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LeaveEntityViewPresenter extends CommonPresenter implements Presenter {

	private LeaveDao dao = SpringContextUtils.getBean(LeaveDao.class);
	
	public LeaveEntityViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new LeaveEntityView();
	}

	@Override
	public String getPresenterName() {
		return "LeaveEntityView";
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
	  * Save value of LeaveCreateView.
	  */
	public void save() {
		Leave value = (Leave)viewer.getValue();
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

