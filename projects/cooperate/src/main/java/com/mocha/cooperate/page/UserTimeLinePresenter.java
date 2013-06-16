/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.Date;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.cooperate.widget.listener.TimeOfLineListener;

/**
 * @author Coral
 *
 */
public class UserTimeLinePresenter extends CommonPresenter implements Presenter {

	private BasicUser selectedUser;
	
	public UserTimeLinePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		selectedUser = (BasicUser) eventBus.getContext().get("selectedUser");
		if(selectedUser == null) {
			selectedUser = eventBus.getUser();
		}
		TimeLineService timeLineService = new TimeLineService();
		Date oldestDate = timeLineService.queryOldestData(selectedUser);
		
		this.viewer = new UserTimeLineViewer(selectedUser, oldestDate, eventBus);
		eventBus.resetContext();
	}
	
	@Override
	public void bind() {
		final UserTimeLineViewer timeLineViewer = (UserTimeLineViewer) this.viewer;
		timeLineViewer.setTimeOfLineListener(new TimeOfLineListener() {
			
			@Override
			public void handleDateChoose(Date date) {
				timeLineViewer.buildTimelinePanel(date);
			}
		});
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.TIMELINE;
	}
}
