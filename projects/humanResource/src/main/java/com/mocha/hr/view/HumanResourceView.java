package com.mocha.hr.view;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.Viewer;
import com.mocha.hr.presenter.LeaveApplyCreatePresenter;
import com.mocha.hr.presenter.LeaveApplyPresenter;
import com.mocha.hr.presenter.MyHrPresenter;
import com.mocha.hr.service.PresenterFactory;
import com.mocha.template.general.GeneralAppFramework;
import com.mocha.template.general.widget.event.GeneralAppContentChangeEvent;
import com.mocha.template.general.widget.vo.GeneralHeadVO;
import com.mocha.template.general.widget.vo.GeneralMenuVO;

public class HumanResourceView extends GeneralAppFramework implements Viewer {
	
	public HumanResourceView() {
		super();
		layout.addStyleName("general-app");
	}

	public void setEventBus(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.currentUser = eventBus.getUser();
		this.eventBus.register(this);
	}

	@Override
	public Presenter loadPresenter(MochaEventBus eventBus,
			GeneralAppContentChangeEvent event) {
		String viewName = event.getViewName();
		Presenter presenter = PresenterFactory.getFactory(viewName, eventBus);
//		if(viewName.equals("My HR")) {
//			presenter = new MyHrPresenter(eventBus);
//		} else if(viewName.equals("Leave")) {
//			presenter = new LeaveApplyPresenter(eventBus);
//		} else if(viewName.equals("CreateLeave")) {
//			presenter = new LeaveApplyCreatePresenter(eventBus);
//		}
		return presenter;
	}

	@Override
	public void defaultPresenter() {
		GeneralAppContentChangeEvent mainPageEvent = new GeneralAppContentChangeEvent();
		mainPageEvent.setViewName("My HR");
		eventBus.post(mainPageEvent);
	}

	@Override
	public GeneralHeadVO loadGeneralHead() {
		GeneralHeadVO generalHead = new GeneralHeadVO();
		generalHead.setAppName("Human Resource");
		generalHead.addMenu(new GeneralMenuVO("My HR"));
		generalHead.addMenu(new GeneralMenuVO("Leave"));
		generalHead.addMenu(new GeneralMenuVO("Timesheet"));
		generalHead.addMenu(new GeneralMenuVO("Travel"));
		generalHead.addMenu(new GeneralMenuVO("Reimbursement"));
		generalHead.addMenu(new GeneralMenuVO("Interview"));
		return generalHead;
	}
	
	@Override
	public String getViewerTitle() {
		return null;
	}

}
