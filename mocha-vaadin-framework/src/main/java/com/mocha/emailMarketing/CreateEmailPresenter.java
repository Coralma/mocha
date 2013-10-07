package com.mocha.emailMarketing;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;

public class CreateEmailPresenter extends AppCommonPresenter implements Presenter {

	public CreateEmailPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.viewer = new CreateEmailViewer();
	}

	@Override
	public void bind() {
		final CreateEmailViewer emailViewer = (CreateEmailViewer) viewer;
		// emailViewer.attach();
		emailViewer.setImmediate(true);
		emailViewer.getCreateEmailBtn().addListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				emailViewer.buildEmailStepsLayou();
				emailViewer.requestRepaint();
			}
		});
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
