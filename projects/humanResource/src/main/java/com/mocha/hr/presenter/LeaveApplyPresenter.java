package com.mocha.hr.presenter;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.hr.view.LeaveApplyView;
import com.mocha.template.general.widget.event.GeneralAppContentChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LeaveApplyPresenter extends CommonPresenter implements Presenter {

	public LeaveApplyPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new LeaveApplyView();
	}
	
	@Override
	public void bind() {
		LeaveApplyView currentView = (LeaveApplyView) viewer;
		currentView.getCreateLeaveButton().addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				GeneralAppContentChangeEvent mainPageEvent = new GeneralAppContentChangeEvent();
				mainPageEvent.setViewName("CreateLeave");
				eventBus.post(mainPageEvent);
			}
		});
		
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
