package com.mocha.cooperate.mobile.phone.ui;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.mocha.cooperate.model.Chat;
import com.mocha.cooperate.service.ChatService;
import com.mocha.mobile.controller.AbstractMobilePresenter;

public class PhoneMessagePresenter extends AbstractMobilePresenter {

	private ChatService chatService = new ChatService();
	
	public PhoneMessagePresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		PhoneMessageView messageView = new PhoneMessageView(eventBus);
		List<Chat> chats = chatService.loadChats(eventBus.getUser());
		messageView.setChats(chats);
		this.view = messageView;
	}
	
	@Override
	public void bind() {
		
	}


}
