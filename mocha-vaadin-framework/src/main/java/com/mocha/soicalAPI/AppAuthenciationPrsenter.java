package com.mocha.soicalAPI;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.oauth.APIKeys;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;

public class AppAuthenciationPrsenter extends AppCommonPresenter implements Presenter {

	public AppAuthenciationPrsenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer=new AppAuthenciationViewer(eventBus.getUser(),APIKeys.facebookAPIName);
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
