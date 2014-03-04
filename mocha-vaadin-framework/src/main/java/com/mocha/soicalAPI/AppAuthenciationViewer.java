package com.mocha.soicalAPI;

import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;

public class AppAuthenciationViewer extends EntityViewPanel implements Viewer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BasicUser user;
	private String socialAppName;
	private final Refresher refresher = new Refresher();
	
	public AppAuthenciationViewer(BasicUser user, String socialAppName) {
		this.user = user;
		this.socialAppName = socialAppName;
	}

	@Override
	public void build() {
		getWindow().addWindow(new AppAuthenciateWindow(user, socialAppName));
//		refresher.setRefreshInterval(5000);
//		refresher.addListener(new RefreshListener() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void refresh(Refresher source) {
//				getWindow().requestRepaintAll();
//			}
//		});
//		addComponent(refresher);
	}
}
