/**
 * 
 */
package com.mocha.cooperate;

import java.util.Date;
import java.util.List;

import org.vaadin.johan.Toolbox;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.service.BasicUserService;
import com.coral.foundation.utils.DateUtils;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.listener.EnterClickListener;
import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.google.common.collect.Lists;
import com.mocha.cooperate.model.Chat;
import com.mocha.cooperate.model.ChatMessage;
import com.mocha.cooperate.service.ChatService;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Coral
 *
 */
public class ChatExtToolbar extends Toolbox {
	private ChatContent chatContent;
	private BasicUserService basicUserService = new BasicUserService();
	private ChatService chatService = new ChatService();
	private VerticalLayout layout;
	private BasicUser currentUser;
	private List<PersonInfoPanel> personInfoPanels = Lists.newArrayList();
	
	public ChatExtToolbar(BasicUser currentUser) {
		this.currentUser = currentUser;
		this.setOrientation(ORIENTATION.BOTTOM_RIGHT);
		this.setAnimationTime(400);
		this.setOverflowSize(24);
		this.setFoldOnClickOnly(true);
	}
	
	public void attach() {
		layout = new VerticalLayout();
		layout.setWidth("150px");
		layout.setHeight("300px");
		layout.setSpacing(true);

		// title
		HorizontalLayout titleLayout = new HorizontalLayout();
		titleLayout.addStyleName("chat-ext-title-layout");
		titleLayout.setWidth("100%");
		Label chatIcon = new Label();
		chatIcon.setWidth("16px");
		chatIcon.setIcon(new ThemeResource("icons/chat-ext.png"));
		titleLayout.addComponent(chatIcon);
		titleLayout.setExpandRatio(chatIcon, 1f);
		
		Label chatLabel = new Label("Chat");
		chatLabel.addStyleName("chat-ext-title");
		titleLayout.addComponent(chatLabel);
		titleLayout.setExpandRatio(chatLabel, 100f);
		layout.addComponent(titleLayout);
		
		// chat content and person list
		HorizontalLayout chatMainLayout = new HorizontalLayout();
		chatMainLayout.setSpacing(true);
		chatContent = new ChatContent();
		chatContent.setVisible(false);
		chatMainLayout.addComponent(chatContent);
		
		Panel userPanel = new Panel();
		userPanel.addStyleName(Reindeer.PANEL_LIGHT);
		userPanel.setWidth("153px");
		userPanel.setHeight("285px");
		// person list
		VerticalLayout userLayout = new VerticalLayout();
		userLayout.setWidth("130px");
		userLayout.setSpacing(true);
		List<BasicUser> users = basicUserService.findAll();
		for(BasicUser user : users) {
			PersonInfoPanel personPanel = new PersonInfoPanel(user);
			personInfoPanels.add(personPanel);
			userLayout.addComponent(personPanel);
		}
		userPanel.setContent(userLayout);
		chatMainLayout.addComponent(userPanel);
		layout.addComponent(chatMainLayout);
		
		this.addComponent(layout);
	}
	
	public class ChatContent extends VerticalLayout implements EnterClickListener {
		private Label title;
		private VerticalLayout messageLayout;
		private String contentWidth = "200px";
		private Chat chat;
		private ChatMessage lastMessage;
		private TextField input;
		
		public ChatContent() {
			this.addStyleName("chat-ext-message-panel");
			this.setWidth("202px");
			this.setHeight("280px");
		}
		
		@SuppressWarnings("serial")
		public void attach() {
			VerticalLayout replyLayout = new VerticalLayout();
			replyLayout.addStyleName("chat-ext-reply-layout");
			
			HorizontalLayout infoLayout = new HorizontalLayout();
			infoLayout.setWidth(contentWidth);
			title = new Label("Chat");
			infoLayout.addComponent(title);
			infoLayout.setExpandRatio(title, 100f);
			
			Button close = WidgetFactory.createIconButton("error.png");
			close.setWidth("16px");
			close.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					chatContent.setVisible(false);
					layout.setWidth("150px");
					ChatExtToolbar.this.requestRepaint();
				}
			});
			infoLayout.addComponent(close);
			infoLayout.setComponentAlignment(close, Alignment.MIDDLE_RIGHT);
			infoLayout.setExpandRatio(close, 1f);
			replyLayout.addComponent(infoLayout);
			
//			input = WidgetFactory.createSearchTextField("Input your message", "input", this);
			input = new TextField();
			input.setImmediate(true);
			input.setNullRepresentation("");
			input.setWidth(contentWidth);
			input.addListener(new ValueChangeListener() {
				
				@Override
				public void valueChange(ValueChangeEvent event) {
					Object msg = input.getValue();
					if(!StrUtils.isEmpty(msg)) {
						input.setValue("");
						postReply(msg.toString());	
					}
				}
			});
			replyLayout.addComponent(input);
			this.addComponent(replyLayout);
			
			Panel messagePanel = new Panel();
			messagePanel.addStyleName(Reindeer.PANEL_LIGHT);
			messagePanel.setWidth(contentWidth);
			messagePanel.setHeight("240px");
			messageLayout = new VerticalLayout();
			messagePanel.setContent(messageLayout);
			
			this.addComponent(messagePanel);
			
			Refresher refresher = new Refresher();
		    refresher.setRefreshInterval(5000);
			refresher.addListener(new RefreshListener() {
				@Override
				public void refresh(Refresher source) {
					refreashMessage();
				}
			});
			this.addComponent(refresher);
		}
		
		public void changeTitle(String username) {
			title.setValue("Chat with " + username);
		}
		
		public void buildNewOutputMessage(List<ChatMessage> newChatMsgs) {
			for(ChatMessage chatMessage : newChatMsgs) {
				Message messagePanel = new Message(chatMessage);
				messageLayout.addComponentAsFirst(messagePanel);
				lastMessage = chatMessage;
			}
		}
		
		public void buildMessageContent() {
			messageLayout.removeAllComponents();
			buildNewOutputMessage(chat.getChatMessages());
		}
		
		@Override
		public void handleEnter(String msg) {
			if(!StrUtils.isEmpty(msg)) {
				postReply(msg.toString());	
			}
		}
		
//		public Message buildMessage(BasicUser user, String msg) {
//			Message message = new Message(user, msg);
//			return message;
//		}

		/**
		 * @return the chat
		 */
		public Chat getChat() {
			return chat;
		}

		/**
		 * @param chat the chat to set
		 */
		public void setChat(Chat chat) {
			this.chat = chat;
		}

		/**
		 * @return the lastMessage
		 */
		public ChatMessage getLastMessage() {
			return lastMessage;
		}

		/**
		 * @param lastMessage the lastMessage to set
		 */
		public void setLastMessage(ChatMessage lastMessage) {
			this.lastMessage = lastMessage;
		}
	}
	
	public void postReply(String reply) {
		Chat chat = chatContent.getChat();
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setMessage(reply);
		chatMessage.setPerson(currentUser);
		chatMessage.setChat(chat);
		chatMessage = chatService.saveChatMessage(chatMessage);
		
		refreashMessage();
		// reload all new message to display
//		List<ChatMessage> newChatMsgs = chatService.loadNewMessage(chatContent.getLastMessage(), chatContent.getChat());
//		chat.getChatMessages().addAll(newChatMsgs);
//		chat = chatService.saveChat(chat);
//		chatContent.setChat(chat);
//		chatContent.buildNewOutputMessage(newChatMsgs);
	}
	
	public void refreashMessage() {
		Chat chat = chatContent.getChat();
		List<ChatMessage> newChatMsgs = chatService.loadNewMessage(chatContent.getLastMessage(), chatContent.getChat());
		chat.getChatMessages().addAll(newChatMsgs);
		chat = chatService.saveChat(chat);
		chatContent.setChat(chat);
		chatContent.buildNewOutputMessage(newChatMsgs);
	}
	
	/**
	 * Person Info of the person list panel.
	 */
	public class PersonInfoPanel extends HorizontalLayout implements LayoutClickListener {
		private BasicUser user;
		public PersonInfoPanel(BasicUser user) {
			this.user = user;
			this.setSpacing(true);
			this.addListener(this);
		}
		
		public void attach() {
			Component avatar = WidgetFactory.createSmallAvatar(user, getApplication());
			this.addComponent(avatar);
			
			Label usernameLabel = WidgetFactory.createLabel(user.getRealName());
			usernameLabel.setWidth("80px");
//			usernameLabel.addStyleName("name-label");
			this.addComponent(usernameLabel);
			this.setComponentAlignment(usernameLabel, Alignment.MIDDLE_LEFT);
		}

		@Override
		public void layoutClick(LayoutClickEvent event) {
			Chat chat = chatService.getChatByUsers(currentUser, user);
			chatContent.changeTitle(user.getRealName());
			chatContent.setVisible(true);
			chatContent.setChat(chat);
			chatContent.buildMessageContent();
			layout.setWidth("352px");
			ChatExtToolbar.this.requestRepaint();
		}
	}
	
	public class Message extends VerticalLayout {
		private BasicUser user;
		private String message;
		private ChatMessage chatMessage;
		public Message(ChatMessage chatMessage) {
			this.user = chatMessage.getPerson();
			this.message = chatMessage.getMessage();
			this.chatMessage = chatMessage;
			this.setWidth("200px");
		}
		
		public void attach() {
//			Component avatar = WidgetFactory.createMiniAvatar(user, getApplication());
			HorizontalLayout msgHeadLayout = new HorizontalLayout();
			if(user.getID().equals(currentUser.getID())) {
				msgHeadLayout.addStyleName("mymessage-style");
			} else {
				msgHeadLayout.addStyleName("othermessage-style");
			}
			msgHeadLayout.setSpacing(true);
			Label usernameLabel = new Label(user.getRealName());
			msgHeadLayout.addComponent(usernameLabel);
			
			Date messageDate = chatMessage.getCreateTime();
			double amount = DateUtils.getDayAmount(new Date(), messageDate);
			String format = "yyyy-MM-dd hh:mm:ss";
			if(amount < 0) {
				format = "hh:mm:ss";
			}
			Label dateLabel = new Label(DateUtils.date2String(messageDate, format));
			msgHeadLayout.addComponent(dateLabel);
			this.addComponent(msgHeadLayout);
			
//			VerticalLayout msgLayout = new VerticalLayout();
//			msgLayout.setWidth("180px");
			Label messageLabel = new Label(message, Label.CONTENT_XHTML);
			messageLabel.addStyleName("message-context");
//			msgLayout.addComponent(messageLabel);
			messageLabel.setWidth("180px");
			this.addComponent(messageLabel);
		}
	}
	
}
