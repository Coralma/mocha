package com.mocha.cooperate.mobile.phone;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.mobile.phone.widgets.PhoneCardListener;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class PhoneHomePresenter extends AbstractMobilePresenter {

	protected TimeLineService timeLineService = new TimeLineService();
	
	public PhoneHomePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		PhoneHomeView homeview = new PhoneHomeView();
		List<TimeLine> timelines = timeLineService.loadTimeLine(eventBus.getUser(), 0);
		homeview.setTimelines(timelines);
		this.view = homeview;
	}

	@Override
	public void bind() {
		final PhoneHomeView homeview = (PhoneHomeView)getView();
		homeview.setCardListener(new PhoneCardListener() {
			@Override
			public void cardClick(TimeLine timeline) {
				
			}
		});
	}

}
