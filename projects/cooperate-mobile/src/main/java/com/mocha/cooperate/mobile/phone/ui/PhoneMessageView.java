package com.mocha.cooperate.mobile.phone.ui;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.cooperate.model.Chat;
import com.mocha.cooperate.service.ChatService;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;

public class PhoneMessageView extends NavigationView implements MobileView, ClickListener {

	private NavigationButton notifyButton = new NavigationButton();
	private Button newChat = new Button();
	private MochaEventBus eventBus;
	private List<Chat> chats;
	private ChatService chatService = new ChatService();
	private BasicUser currentUser;
	
	public PhoneMessageView(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.currentUser = eventBus.getUser();
		newChat.setIcon(new ThemeResource("icons/add-chat.png"));
		setRightComponent(newChat);
	}

	@Override
	public void attach() {
		super.attach();
		setCaption("Message");
		
		CssLayout newTopicLayout = new CssLayout();
		newTopicLayout.setSizeFull();
		newTopicLayout.setMargin(true);
		
		VerticalComponentGroup group = new VerticalComponentGroup();
		group.setMargin(true);
		notifyButton.setWidth("100%");
		notifyButton.addListener(this);
		notifyButton.setIcon(new ThemeResource("icons/at-icon.png"));
		notifyButton.setCaption("My Notification");
		group.addComponent(notifyButton);
		
		newTopicLayout.addComponent(group);
		
		VerticalComponentGroup chatGroup = new VerticalComponentGroup();
		chatGroup.setCaption("My Chat");
		for(Chat chat : chats) {
			NavigationButton chatButton = new NavigationButton();
			chatButton.addListener(this);
			chatButton.setData(chat);
			chatButton.setCaption(chatService.getChatTitle(chat, currentUser));
			BasicUser person = chatService.getChatPerson(chat, currentUser);
			Resource resource;
			if(person != null) {
				resource = PageBuildHelper.getUserPhotoResource(person.getUserPhoto(), this.getApplication());
			} else {
				resource = new ThemeResource("icons/group_avatar.png");
			}
			chatButton.setIcon(resource);
			chatGroup.addComponent(chatButton);
		}
		
		newTopicLayout.addComponent(chatGroup);
		this.setContent(newTopicLayout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Button clickedButton = event.getButton();
		Object chat = clickedButton.getData();
		if(notifyButton.equals(clickedButton)) {
			PhoneNotificationPresenter notificationPresenter = new PhoneNotificationPresenter(eventBus);
			this.getNavigationManager().navigateTo(notificationPresenter.go());
		}
		if(chat != null && chat instanceof Chat) {
			this.getNavigationManager().navigateTo(new WorkingView());
		}
	}

	/**
	 * @return the chats
	 */
	public List<Chat> getChats() {
		return chats;
	}

	/**
	 * @param chats the chats to set
	 */
	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}
	
}
