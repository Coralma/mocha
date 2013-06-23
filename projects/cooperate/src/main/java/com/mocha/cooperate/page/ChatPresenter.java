package com.mocha.cooperate.page;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.model.Chat;
import com.mocha.cooperate.model.ChatMessage;
import com.mocha.cooperate.page.event.ChatListener;
import com.mocha.cooperate.service.ChatService;

public class ChatPresenter extends CommonPresenter implements Presenter {

	private ChatService chatService = new ChatService();
	
	public ChatPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.currentUser = eventBus.getUser();
		this.viewer = new ChatViewer(currentUser);
		loadChats();
	}
	
	public void loadChats() {
		List<Chat> chats = chatService.loadChats(currentUser);
		((ChatViewer) getViewer()).setChats(chats);
	}

	@Override
	public void bind() {
		final ChatViewer chatViewer = (ChatViewer) getViewer();
		chatViewer.setListener(new ChatListener() {
			
			@Override
			public void saveChat(Chat chat) {
				chat = chatService.saveChat(chat);
				chatViewer.setCurrentChat(chat);
				loadChats();
				chatViewer.buildPersons();
			}

			@Override
			public void postMessage(String message) {
				Chat chat = chatViewer.getCurrentChat();
				ChatMessage chatMessage = new ChatMessage();
				chatMessage.setMessage(message);
				chatMessage.setPerson(currentUser);
				chatMessage.setChat(chat);
				chatMessage = chatService.saveChatMessage(chatMessage);
				// reload all new message to display
				List<ChatMessage> newChatMsgs = chatService.loadNewMessage(chatViewer.getLastMessage(), chatViewer.getCurrentChat());
				chat.getChatMessages().addAll(newChatMsgs);
				chat = chatService.saveChat(chat);
				chatViewer.setCurrentChat(chat);
				chatViewer.buildNewOutputMessage(newChatMsgs);
			}
			
			@Override
			public void refreashMessage() {
				Chat chat = chatViewer.getCurrentChat();
				List<ChatMessage> newChatMsgs = chatService.loadNewMessage(chatViewer.getLastMessage(), chatViewer.getCurrentChat());
				chat.getChatMessages().addAll(newChatMsgs);
				chatViewer.setCurrentChat(chat);
				chatViewer.buildNewOutputMessage(newChatMsgs);
			}

			@Override
			public void selectChat(Chat chat) {
				chatViewer.setCurrentChat(chat);
				chatViewer.buildOutputMessage();
				
			}
		});
	}

	@Override
	public String getPresenterName() {
		// TODO Auto-generated method stub
		return null;
	}

}
