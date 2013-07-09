/**
 * 
 */
package com.mocha.cooperate.page.index;

import java.util.Map;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.google.common.collect.Maps;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.service.NotifyLineService;
import com.mocha.cooperate.widget.listener.IndexListener;

/**
 * @author Administrator
 *
 */
public class IndexPresenter extends CommonPresenter implements Presenter {
	
	NotifyLineService notifyService = new NotifyLineService();
	public IndexPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.currentUser = eventBus.getUser();
		this.viewer = new IndexViewer(eventBus, getNotifyMap());
	}
	
	public Map<String, Integer> getNotifyMap() {
		Map<String, Integer> notifyMap = Maps.newConcurrentMap();
		notifyMap.put(PresenterProperty.NOTIFICATION, notifyService.loadNotifyNumber(currentUser));
		return notifyMap;
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.INDEX;
	}

	@Override
	public void bind() {
		final IndexViewer indexViewer = (IndexViewer)getViewer();
		indexViewer.setIndexListener(new IndexListener() {
			@Override
			public void refreshShotCutPanel() {
//				Map<String, Integer> notifyMap = getNotifyMap();
//				if(notifyMap.get(PresenterProperty.NOTIFICATION) > 0) {
					indexViewer.getMenu().setNotifyMap(getNotifyMap());
					indexViewer.getMenu().refreshNotification();
//				}
			}
		});
	}
}
