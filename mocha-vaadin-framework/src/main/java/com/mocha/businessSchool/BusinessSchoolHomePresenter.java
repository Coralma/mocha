package com.mocha.businessSchool;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.AppContentEvent;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.mocha.soicalAPI.IbFollowedMembersViewer;
import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class BusinessSchoolHomePresenter extends AppCommonPresenter implements Presenter {

	public BusinessSchoolHomePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new BusinessSchoolHomeViewer();
	}

	@Override
	public void bind() {
		BusinessSchoolHomeViewer view = (BusinessSchoolHomeViewer) this.viewer;
		// view.getStartBtn().addListener(new ClickListener() {
		// /**
		// *
		// */
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public void buttonClick(ClickEvent event) {
		// AppContentEvent appChangeEvent = new AppContentEvent();
		// appChangeEvent.setCustomizeClass("com.mocha.soicalAPI.IbEntireConnectionProfilePresnter");
		// eventBus.post(appChangeEvent);
		// }
		// });

		view.getProfileBussiness().getStartBtn().addListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				System.out.println("run profile app");

				AppContentEvent appChangeEvent = new AppContentEvent();
				appChangeEvent.setCustomizeClass("com.mocha.soicalAPI.IbEntireConnectionProfilePresnter");
				eventBus.post(appChangeEvent);
			}

		});
	}

	@Override
	public String getPresenterName() {

		return null;
	}

}
