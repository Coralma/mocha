package com.mocha.soicalAPI;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.basic.dao.LinkedinConnectionNetworkUpdateDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class SoicalConnectionProfileViewPresenter extends AppCommonPresenter implements Presenter {

	private LinkedinConnection linkedConn;

	private LinkedinConnectionNetworkUpdateDao dao = SpringContextUtils.getBean(LinkedinConnectionNetworkUpdateDao.class);

	public SoicalConnectionProfileViewPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		linkedConn = (LinkedinConnection) eventBus.getContext().get("linkedConn");
		List<LinkedinConnectionNetworkUpdate> updates = dao.findUpdateByConnection(linkedConn);
		linkedConn.setLinkedinConnectionNetworkUpdate(updates);
		this.viewer = new SoicalConnectionProfileViewer(linkedConn);
	}

	@Override
	public void bind() {
		// syncStatusBtn

		final SoicalConnectionProfileViewer viewer = (SoicalConnectionProfileViewer) this.viewer;
		viewer.getSyncStatusBtn().addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				System.out.println("Update connection " + linkedConn.getFirstName() + " now");
				List<LinkedinConnectionNetworkUpdate> updates = dao.findUpdateByConnection(linkedConn);
				viewer.getLinkedConn().setLinkedinConnectionNetworkUpdate(updates);
				viewer.requestRepaintAll();
				viewer.build();
			}
		});

	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
