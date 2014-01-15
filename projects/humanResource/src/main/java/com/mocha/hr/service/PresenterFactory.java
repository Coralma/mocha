package com.mocha.hr.service;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.mocha.hr.presenter.InterviewPresenter;
import com.mocha.hr.presenter.LeaveApplyCreatePresenter;
import com.mocha.hr.presenter.LeaveApplyPresenter;
import com.mocha.hr.presenter.MyHrPresenter;
import com.mocha.hr.presenter.ReimbursementPresenter;
import com.mocha.hr.presenter.TimeSheetPresenter;
import com.mocha.hr.presenter.TravelPresenter;

public class PresenterFactory {
	
	public static Presenter getFactory(String viewName, MochaEventBus eventBus) {
		Presenter presenter = null; 
		if(viewName.equals("My HR")) {
			presenter = new MyHrPresenter(eventBus);
		} else if(viewName.equals("Leave")) {
			presenter = new LeaveApplyPresenter(eventBus);
		} else if(viewName.equals("CreateLeave")) {
			presenter = new LeaveApplyCreatePresenter(eventBus);
		} else if(viewName.equals(HumanResourceCst.TRAVEL)) {
			presenter = new TravelPresenter(eventBus);
		} else if(viewName.equals(HumanResourceCst.REIMBURSEMENT)) {
			presenter = new ReimbursementPresenter(eventBus);
		} else if(viewName.equals(HumanResourceCst.TIMESHEET)) {
			presenter = new TimeSheetPresenter(eventBus);
		} else if(viewName.equals(HumanResourceCst.INTERVIEW)) {
			presenter = new InterviewPresenter(eventBus);
		}
		return presenter;
	}
}
