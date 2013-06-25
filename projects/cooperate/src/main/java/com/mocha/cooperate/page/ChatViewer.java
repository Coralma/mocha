package com.mocha.cooperate.page;

import java.util.List;
import java.util.Set;

import org.vaadin.hene.expandingtextarea.ExpandingTextArea;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.view.CommonViewer;
import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.google.common.collect.Lists;
import com.mocha.cooperate.model.Chat;
import com.mocha.cooperate.model.ChatMessage;
import com.mocha.cooperate.page.event.ChatListener;
import com.mocha.cooperate.service.ChatService;
import com.mocha.cooperate.widget.CheckBoxPrepose;
import com.mocha.cooperate.widget.NotifyTokenField;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class ChatViewer extends CommonViewer implements Viewer, ClickListener {

	private ChatService chatService = new ChatService();
	private ExpandingTextArea inputArea = new ExpandingTextArea();
	private Label chatPersonsTitle;
//	private Button addFButton = WidgetFactory.createLink("Add Files");
	private Button replyButton = WidgetFactory.createButton("Reply", "reply", this);
	
	private VerticalLayout outputMessageLayout = new VerticalLayout();
	private VerticalLayout personsListLayout = new VerticalLayout();
	private BasicUser currentUser;
	private String personWidth = "236px";
	private ChatListener listener;
	private List<Chat> chats;
	private List<PersonPanel> personPanels;
	private Chat currentChat;
	private ChatMessage lastMessage;
	private MochaEventBus eventBus;
	
	public ChatViewer(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		this.currentUser = eventBus.getUser();
	}
	
	public void attach() {
		super.attach();
		
		ToolbarAdvance toolbar = new ToolbarAdvance();
		toolbar.addStyleName("chat-persons-title");
		chatPersonsTitle = WidgetFactory.createLabel("Conversation");
		toolbar.addLeftComponent(chatPersonsTitle);
		toolbar.setNeedRightSeperate(false);
		Button newChat = WidgetFactory.createButton("New Chat", "new", this);
		Button newTodo = WidgetFactory.createButton("New Todo", "todo", this);
		toolbar.addRightComponent(newChat);
		toolbar.addRightComponent(newTodo);
		this.addComponent(toolbar);
		
		if(chats.size() > 0) {
			currentChat = chats.get(0);
			this.setChatPersonsTitle(chatService.getChatTitle(currentChat, currentUser));
		}
		// chat main content for all.
		HorizontalLayout chatContentLayout = new HorizontalLayout();
		chatContentLayout.setWidth("766");
		chatContentLayout.addStyleName("chat-content");
		
		// input and output message panel
		VerticalLayout chatIOLayout = new VerticalLayout();
		chatIOLayout.setWidth("530px");
		chatIOLayout.addStyleName("chat-io");
		VerticalLayout inputLayout = new VerticalLayout();
		inputLayout.addStyleName("chat-input");
		inputArea.setWidth("510px");
		inputAreaEnable(false);
		inputLayout.addComponent(inputArea);
		
		HorizontalLayout inputControl = new HorizontalLayout();
		inputControl.addStyleName("chat-control");
		inputControl.setWidth("512px");
//		inputControl.addComponent(addFButton);
//		inputControl.setComponentAlignment(addFButton, Alignment.MIDDLE_LEFT);
		
		HorizontalLayout postControl = new HorizontalLayout();
		postControl.setSpacing(true);
		CheckBoxPrepose checkBoxPrepose = new CheckBoxPrepose("Press Enter to send");
		postControl.addComponent(checkBoxPrepose);
		postControl.setComponentAlignment(checkBoxPrepose, Alignment.MIDDLE_CENTER);
		postControl.addComponent(replyButton);
		inputControl.addComponent(postControl);
		inputControl.setComponentAlignment(postControl, Alignment.MIDDLE_RIGHT);
		inputLayout.addComponent(inputControl);
		chatIOLayout.addComponent(inputLayout);
		
		outputMessageLayout.setWidth("500px");
		outputMessageLayout.setSpacing(true);
		outputMessageLayout.addStyleName("chat-message-layout");
		buildOutputMessage();
		chatIOLayout.addComponent(outputMessageLayout);
		
		chatContentLayout.addComponent(chatIOLayout);
		
		// person list panel
		VerticalLayout personsLayout = new VerticalLayout();
		personsLayout.setWidth(personWidth);
		personsLayout.addStyleName("chat-persons");
		VerticalLayout personsTitleLayout = new VerticalLayout();
		personsTitleLayout.addStyleName("title-layout");
		personsTitleLayout.addComponent(new Label("My Conversation"));
		personsLayout.addComponent(personsTitleLayout);
		
		personsListLayout.setWidth(personWidth);
		buildPersons();
		personsLayout.addComponent(personsListLayout);
		
		chatContentLayout.addComponent(personsLayout);
		this.addComponent(chatContentLayout);
		
		bind();
	}
	
	public void bind() {
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		Object data = event.getButton().getData();
		if("new".equals(data)) {
			NewChatWindow newChatWindow = new NewChatWindow();
			getWindow().addWindow(newChatWindow);
		} else if("reply".equals(data)) {
			String value = (String)inputArea.getValue();
			if(!StrUtils.isEmpty(value)) {
				inputArea.setValue("");
				listener.postMessage(value);
			}
		} else if("todo".equals(data)) {
			ToDoEditorWindowPresenter todoWindow = new ToDoEditorWindowPresenter(eventBus);
			todoWindow.bind();
			getWindow().addWindow(todoWindow.getWindow());
		}
	}
	
	public void buildOutputMessage() {
		outputMessageLayout.removeAllComponents();
		if(currentChat != null) {
			Refresher refresher = new Refresher();
		    refresher.setRefreshInterval(3000);
			refresher.addListener(new RefreshListener() {
				@Override
				public void refresh(Refresher source) {
					listener.refreashMessage();
				}
			});
			outputMessageLayout.addComponent(refresher);
			inputArea.setEnabled(true);
			buildNewOutputMessage(currentChat.getChatMessages());
		}
	}
	
	public void buildNewOutputMessage(List<ChatMessage> chatMessages) {
		int msgsize =chatMessages.size();
		if(msgsize > 0) {
			lastMessage = chatMessages.get(msgsize - 1);
		}
		for(ChatMessage msg : chatMessages) {
			BasicUser person = msg.getPerson();
			if(person.getID().equals(currentUser.getID())) {
				outputMessageLayout.addComponentAsFirst(new MyReply(msg));
			} else {
				OtherReply otherReply = new OtherReply(msg, person);
				outputMessageLayout.addComponentAsFirst(otherReply);
				outputMessageLayout.setComponentAlignment(otherReply, Alignment.MIDDLE_RIGHT);
			}
		}
	}
	
	public void inputAreaEnable(boolean enabled) {
		inputArea.setEnabled(enabled);
	}
	
	/** build the persons*/
	public void buildPersons() {
		personsListLayout.removeAllComponents();
		personPanels = Lists.newArrayList();
		for(int i=0; i < chats.size(); i++) {
			Chat chat = chats.get(i);
			PersonPanel personPanel = new PersonPanel(chat);
			if(i == 0) {
				personPanel = new PersonPanel(chat, true);
			}
			personPanels.add(personPanel);
			personsListLayout.addComponent(personPanel);
		}
	}
	
	@Override
	public String getViewerTitle() {
		return "Chat";
	}
	
	public class MyReply extends HorizontalLayout {
		String message;
		public MyReply(ChatMessage msg) {
			this.addStyleName("chat-message");
			this.message = msg.getMessage();
		}
		
		public void attach() {
			Component avatar = WidgetFactory.createMidAvatar(currentUser, getApplication());
			this.addComponent(avatar);
			VerticalLayout contentLayout = new VerticalLayout();
			contentLayout.addStyleName("my-message");
			
			VerticalLayout arrowLayout = new VerticalLayout();
			arrowLayout.addStyleName("my-message-arrow");
			arrowLayout.setWidth("11px");
			Label arrowLabel = new Label();
			arrowLabel.setWidth("11px");
			arrowLabel.setIcon(new ThemeResource("icons/left-bubble.png"));
			arrowLayout.addComponent(arrowLabel);
			this.addComponent(arrowLayout);
			
			Label contentLabel = new Label(message, Label.CONTENT_XHTML);
			if(message.length() > 60) {
				contentLayout.setWidth("360px");
				contentLabel.setWidth("350px");
			}
			contentLayout.addComponent(contentLabel);
			this.addComponent(contentLayout);
		}
	}
	
	public class OtherReply extends HorizontalLayout {
		String message;
		BasicUser user;
		public OtherReply(ChatMessage msg, BasicUser user) {
			this.message = msg.getMessage();
			this.user = user;
			this.addStyleName("other-chat-message");
		}
		
		public void attach() {
			VerticalLayout contentLayout = new VerticalLayout();
			contentLayout.addStyleName("other-message");
			Label contentLabel = new Label(message, Label.CONTENT_XHTML);
			if(message.length() > 60) {
				contentLayout.setWidth("360px");
				contentLabel.setWidth("350px");
			}
			contentLayout.addComponent(contentLabel);
			this.addComponent(contentLayout);
			
			VerticalLayout arrowLayout = new VerticalLayout();
			arrowLayout.addStyleName("my-message-arrow");
			arrowLayout.setWidth("11px");
			Label arrowLabel = new Label();
			arrowLabel.setWidth("11px");
			arrowLabel.setIcon(new ThemeResource("icons/right-bubble.png"));
			arrowLayout.addComponent(arrowLabel);
			this.addComponent(arrowLayout);
			
			Component avatar = WidgetFactory.createMidAvatar(user, getApplication());
			this.addComponent(avatar);
		}
	}

	public class PersonPanel extends HorizontalLayout implements LayoutClickListener {
		private Chat chat;
		private String chatTitle;
		public PersonPanel(Chat chat) {
			this(chat, false);
		}
		
		public PersonPanel(Chat chat, boolean selected) {
			this.chat = chat;
			this.setWidth("216px");
			this.addListener(this);
			this.chatTitle = chatService.getChatTitle(chat, currentUser);
			if(selected) {
				this.setStyleName("chat-person-selected");
			} else {
				this.addStyleName("chat-person");
			}
		}
		
		public void attach() {
			String avatarUrl = chatService.getAvatar(chat, currentUser);
			BasicUser sender = chatService.getChatPerson(chat, currentUser);
			Component avatar = null;
			if(avatarUrl != null) {
				avatar = WidgetFactory.createSmallAvatar(avatarUrl, getApplication());
			} else {
				avatar = WidgetFactory.createSmallAvatar(sender, getApplication());
			}
			this.addComponent(avatar);
			
			Label usernameLabel = WidgetFactory.createLabel(chatTitle);
			usernameLabel.addStyleName("name-label");
			usernameLabel.setWidth("159px");
			this.addComponent(usernameLabel);
			this.setComponentAlignment(usernameLabel, Alignment.MIDDLE_LEFT);
		}

		@Override
		public void layoutClick(LayoutClickEvent event) {
			if(!currentChat.equals(this.chat)) {
				changePersonPanelStyle(chat);
//				for(PersonPanel personPanel : personPanels) {
//					if(personPanel.equals(this)) {
//						personPanel.setStyleName("chat-person-selected");
//					} else {
//						personPanel.setStyleName("chat-person");
//					}
//				}
				
			}
		}

		/**
		 * @return the chat
		 */
		public Chat getChat() {
			return chat;
		}

		/**
		 * @return the chatTitle
		 */
		public String getChatTitle() {
			return chatTitle;
		}
	}
	
	public void changePersonPanelStyle(Chat chat) {
		for(PersonPanel personPanel : personPanels) {
			if(personPanel.getChat().getID().equals(chat.getID())) {
				personPanel.setStyleName("chat-person-selected");
				setChatPersonsTitle(personPanel.getChatTitle());
			} else {
				personPanel.setStyleName("chat-person");
			}
		}
		listener.selectChat(chat);
	}
	
	private void setChatPersonsTitle(String chatTitle) {
		chatPersonsTitle.setValue("Conversation with " + chatTitle);
	}
	
	public class NewChatWindow extends Window {
		
		private NotifyTokenField tokenField;
		private Button createButton = WidgetFactory.createButton("Create Chat");
		private Button cancelButton = WidgetFactory.createButton("Cancel");
		
		public NewChatWindow() {
			super();
			this.setCaption("New Conversation with:");
			this.center();
			this.addStyleName("mocha-app");
			this.setWidth("500px");
			this.setClosable(true);
			this.setResizeLazy(true);
			this.setResizable(false);
			this.setModal(true);
			this.addStyleName(Reindeer.WINDOW_LIGHT);
		}
		
		public void attach() {
			VerticalLayout layout = new VerticalLayout();
			layout.setWidth("480px");
			tokenField = new NotifyTokenField();
			tokenField.setInputPrompt("Type the name you want to chat with");
			tokenField.setWidth("300px");
			layout.addComponent(tokenField);
			
			HorizontalLayout buttonLayout = new HorizontalLayout();
			buttonLayout.addStyleName("newchat-buttons");
			buttonLayout.setSpacing(true);
			buttonLayout.addComponent(createButton);
			buttonLayout.addComponent(cancelButton);
			layout.addComponent(buttonLayout);
			layout.setComponentAlignment(buttonLayout, Alignment.MIDDLE_RIGHT);
			this.addComponent(layout);
			
			bind();
		}
		
		public void bind() {
			createButton.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					// create chat
					Chat chat = chatService.createChat((Set<BasicUser>)tokenField.getValue(), currentUser);
					listener.saveChat(chat);
					inputAreaEnable(true);
					// close window
					NewChatWindow.this.close();
				}
			});
			cancelButton.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					NewChatWindow.this.close();
				}
			});
		}
	}

	/**
	 * @return the listener
	 */
	public ChatListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(ChatListener listener) {
		this.listener = listener;
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

	/**
	 * @return the currentChat
	 */
	public Chat getCurrentChat() {
		return currentChat;
	}

	/**
	 * @param currentChat the currentChat to set
	 */
	public void setCurrentChat(Chat currentChat) {
		this.currentChat = currentChat;
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
