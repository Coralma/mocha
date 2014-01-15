package com.mocha.hr.presenter;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.hr.view.HumanResourceView;
import com.mocha.template.general.widget.GeneralNavigationWidget;
import com.mocha.template.general.widget.event.GeneralAppContentChangeEvent;
import com.mocha.template.general.widget.listener.GeneralNavigationListener;
import com.vaadin.ui.Button.ClickEvent;

public class HumanResourcePresenter extends CommonPresenter implements Presenter {

	public HumanResourcePresenter() {
		this.viewer = new HumanResourceView();
	}
	
	@Override
	public void bind() {
		HumanResourceView hrView = (HumanResourceView) viewer;
		GeneralNavigationWidget navigationWidget = hrView.getNavigationWidget();
		navigationWidget.setListener(new GeneralNavigationListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				GeneralAppContentChangeEvent mainPageEvent = new GeneralAppContentChangeEvent();
				mainPageEvent.setViewName(event.getButton().getData().toString());
				eventBus.post(mainPageEvent);
			}
		});
	}

	@Override
	public String getPresenterName() {
//		return PresenterProperty.HR;
		return "HR";
	}

	
	/**
	 * @param eventBus the eventBus to set
	 */
	public void setEventBus(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		((HumanResourceView) viewer).setEventBus(eventBus);
	}
}
