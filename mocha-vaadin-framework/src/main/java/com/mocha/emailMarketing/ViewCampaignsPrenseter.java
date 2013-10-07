package com.mocha.emailMarketing;

import java.util.List;

import org.apache.http.protocol.HttpService;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.edm.mailgun.EDMTracking;
import com.coral.foundation.security.basic.dao.CampaingnsDao;
import com.coral.foundation.security.model.Campaingns;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.AppCommonPresenter;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Button.ClickEvent;

public class ViewCampaignsPrenseter extends AppCommonPresenter implements Presenter {

	private MochaEventBus eventBus;
	private List<Campaingns> campaingnsList;
	private CampaingnsDao campaingnsDao = SpringContextUtils.getBean(CampaingnsDao.class);

	public ViewCampaignsPrenseter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		campaingnsList = campaingnsDao.findAll();
		this.viewer = new ViewCampaignsViewer(campaingnsList);
	}

	@Override
	public void bind() {

		ViewCampaignsViewer viewCampaignsViewer = (ViewCampaignsViewer) this.viewer;

		NativeButton updateBtn = viewCampaignsViewer.getUpdateBtn();
		updateBtn.addListener(new ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EDMTracking emTrakcing = new EDMTracking();
				emTrakcing.execute();
			}
		});
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
