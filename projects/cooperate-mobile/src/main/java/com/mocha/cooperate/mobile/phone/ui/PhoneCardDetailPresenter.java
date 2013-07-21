package com.mocha.cooperate.mobile.phone.ui;


import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.ToDo;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class PhoneCardDetailPresenter extends AbstractMobilePresenter {
	
	public PhoneCardDetailPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		PhoneCardDetailView detailView = new PhoneCardDetailView(eventBus.getUser());
		detailView.setStatus((Status) eventBus.getContext().get(Status.class.getName()));
		detailView.setDiscuss((Discuss)eventBus.getContext().get(Discuss.class.getName()));
		detailView.setTodo((ToDo)eventBus.getContext().get(ToDo.class.getName()));
		this.view = detailView;
	}

	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

}
