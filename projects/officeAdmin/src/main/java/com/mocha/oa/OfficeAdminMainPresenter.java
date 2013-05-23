package com.mocha.oa;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.view.template.sat.ControllerMenuPanel.ControllerMenuListener;
import com.coral.vaadin.widget.view.CommonPresenter;

public class OfficeAdminMainPresenter extends CommonPresenter implements Presenter {
	
	public OfficeAdminMainPresenter() {
		this.viewer = new OfficeAdminMainPage(eventBus);
		bind();
	}

	@Override
	public String getPresenterName() {	
		return "OfficeAdminIndex";
	}

	@Override
	public void bind() {
		final OfficeAdminMainPage oaPage = (OfficeAdminMainPage) viewer;
		oaPage.getControllerMenu().setControllerMenuListener(new ControllerMenuListener() {
			
			@Override
			public void showView(String view) {
				
			}
			
			@Override
			public void showPanel(Class panel) {
				try {
					Presenter presenter = (Presenter)panel.getConstructor(MochaEventBus.class).newInstance(eventBus);
//					oaPage.getFunctionPanel().changeToEntityEditTitlePanel(presenter.getPresenterName());
					oaPage.getFunctionPanel().setContent(presenter.go());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
