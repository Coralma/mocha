package com.mocha.vaadin.entity.presenter;

import com.mocha.crm.dao.*;
import java.util.List;
import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.vaadin.entity.view.CustomerServerSearch;
import com.mocha.crm.model.Serve;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CustomerServerSearchPresenter extends CommonPresenter implements Presenter {

	private ServeDao dao = SpringContextUtils.getBean(ServeDao.class);
	
	public CustomerServerSearchPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CustomerServerSearch();
		// load all data.
		List entities = dao.findAll();
		viewer.setValue(entities);
	}

	@Override
	public String getPresenterName() {
		return "CustomerServerSearch";
	}
	
	@Override
	public void bind() {
		//TODO add and edit your action.
	}
	

}

