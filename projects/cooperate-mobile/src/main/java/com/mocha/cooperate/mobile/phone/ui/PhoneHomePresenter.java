package com.mocha.cooperate.mobile.phone.ui;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.persistence.BaseEntity;
import com.mocha.cooperate.mobile.MobileConstants;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneCardListener;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.mobile.controller.AbstractMobilePresenter;
import com.mocha.mobile.controller.MobileView;

public class PhoneHomePresenter extends AbstractMobilePresenter {

	protected TimeLineService timeLineService = new TimeLineService();
	
	public PhoneHomePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		PhoneHomeView homeview = new PhoneHomeView(eventBus);
		List<TimeLine> timelines = timeLineService.loadTimeLine(eventBus.getUser(), 0);
		homeview.setTimelines(timelines);
		this.view = homeview;
	}

	@Override
	public void bind() {
		final PhoneHomeView homeview = (PhoneHomeView)getView();
		homeview.setCardListener(new PhoneCardListener() {
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
				homeview.getNavigationManager().navigateTo(detailPresenter.go());
			}
			
			@Override
			public void createBasic(String category) {
				MobileView view = null;
				if(MobileConstants.STATUS.equals(category)) {
					PhoneCreateStatusPresenter statusPresenter = new PhoneCreateStatusPresenter(eventBus);
					view = statusPresenter.go();
				} else if(MobileConstants.TOPIC.equals(category)) {
					PhoneCreateTopicPresenter statusPresenter = new PhoneCreateTopicPresenter(eventBus);
					view = statusPresenter.go();
				} else if(MobileConstants.TODO.equals(category)) {
					PhoneCreateTodoPresenter statusPresenter = new PhoneCreateTodoPresenter(eventBus);
					view = statusPresenter.go();
				}
				homeview.navigateTo(view);
			}
		});
	}

}
