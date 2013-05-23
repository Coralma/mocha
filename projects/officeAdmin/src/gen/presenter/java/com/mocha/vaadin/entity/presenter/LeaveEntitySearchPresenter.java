package com.mocha.vaadin.entity.presenter;

import com.mocha.oa.dao.*;
import java.util.List;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.vaadin.entity.view.LeaveEntitySearch;
import com.mocha.oa.model.Leave;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LeaveEntitySearchPresenter extends CommonPresenter implements Presenter {

	private LeaveDao dao = SpringContextUtils.getBean(LeaveDao.class);
	
	public LeaveEntitySearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new LeaveEntitySearch();
		// load all data.
		List entities = dao.findAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "LeaveEntitySearch";
	}
	
	@Override
	public void bind() {
		//TODO add and edit your action.
	}
	

}

