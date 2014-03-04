package com.mocha.cooperate.mobile.phone.ui;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.persistence.BaseEntity;
import com.mocha.cooperate.mobile.phone.widgets.cards.PhoneCardListener;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.service.NotifyLineService;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class PhoneNotificationPresenter extends AbstractMobilePresenter {

	private NotifyLineService notifyService = new NotifyLineService();
	
	public PhoneNotificationPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		PhoneNotificationView timeLineView = new PhoneNotificationView();
		List<NotifyLine> notifylines = notifyService.loadNotifyLine(eventBus.getUser(),10);
		timeLineView.setNotifylines(notifylines);
		timeLineView.setCurrentUser(eventBus.getUser());
		this.view = timeLineView;
	}

	@Override
	public void bind() {
		final PhoneNotificationView timeLineView = (PhoneNotificationView) getView();
		timeLineView.setCardListener(new PhoneCardListener() {

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
				timeLineView.getNavigationManager().navigateTo(detailPresenter.go());				
			}

			@Override
			public void createBasic(String category) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}


}
