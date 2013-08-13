package com.mocha.cooperate.mobile.phone.ui;

import java.util.Date;
import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.persistence.BaseEntity;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneCardListener;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class PhoneTimeLinePresenter extends AbstractMobilePresenter {

	private TimeLineService timelineService = new TimeLineService(); 
	public PhoneTimeLinePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		List<TimeLine> timelines = timelineService.loadTimeLineByUser(eventBus.getUser(), new Date(), 0);
		PhoneTimeLineView timelineView = new PhoneTimeLineView();
		timelineView.setTimelines(timelines);
		timelineView.setCurrentUser(eventBus.getUser());
		this.view = timelineView;
	}
	
	@Override
	public void bind() {
		final PhoneTimeLineView timelineView = (PhoneTimeLineView) getView();
		timelineView.setCardListener(new PhoneCardListener() {

			@Override
			public void cardClick(BaseEntity entity) {
				eventBus.resetContext();
				if(entity instanceof Status) {
					eventBus.put(Status.class.getName(), entity);
				} else if(entity instanceof Discuss) {
					eventBus.put(Discuss.class.getName(), entity);
				} else if(entity instanceof ToDo) {
					eventBus.put(ToDo.class.getName(), entity);
				}
				PhoneCardDetailPresenter detailPresenter = new PhoneCardDetailPresenter(eventBus);
				timelineView.getNavigationManager().navigateTo(detailPresenter.go());				
			}

			@Override
			public void createBasic(String category) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}


}
