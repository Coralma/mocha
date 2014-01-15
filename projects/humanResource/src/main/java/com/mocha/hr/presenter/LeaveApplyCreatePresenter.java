package com.mocha.hr.presenter;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.hr.view.LeaveApplyCreateView;
import com.mocha.template.general.widget.event.GeneralAppContentChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LeaveApplyCreatePresenter extends CommonPresenter implements Presenter {

//	private LeaveApplyDao dao = SpringContextUtils.getBean(LeaveApplyDao.class);
	
	public LeaveApplyCreatePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new LeaveApplyCreateView();
	}
	
	@Override
	public void bind() {
		final LeaveApplyCreateView leaveCreateView = (LeaveApplyCreateView)this.viewer;
		leaveCreateView.getButton("save").addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
//				LeaveApply leaveApply = (LeaveApply)leaveCreateView.getEntity();
//				dao.persist(leaveApply);
				back();
			}
		});
		leaveCreateView.getButton("back").addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				back();
			}
		});
	}
	
	private void back() {
		GeneralAppContentChangeEvent mainPageEvent = new GeneralAppContentChangeEvent();
		mainPageEvent.setViewName("Leave");
		eventBus.post(mainPageEvent);
	}

	@Override
	public String getPresenterName() {
		return "LeaveCreate";
	}
}
