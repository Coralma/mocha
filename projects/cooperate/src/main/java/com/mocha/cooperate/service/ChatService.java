/**
 * 
 */
package com.mocha.cooperate.service;

import java.util.List;
import java.util.Set;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mocha.cooperate.basic.dao.ChatDao;
import com.mocha.cooperate.basic.dao.ChatMessageDao;
import com.mocha.cooperate.model.Chat;
import com.mocha.cooperate.model.ChatMessage;
import com.mocha.cooperate.model.ChatPerson;

/**
 * @author Coral
 *
 */
public class ChatService {

	private ChatDao chatDao = SpringContextUtils.getBean(ChatDao.class);
	private ChatMessageDao chatMessageDao = SpringContextUtils.getBean(ChatMessageDao.class);
	
	public ChatMessage saveChatMessage(ChatMessage chatMessage) {
		return chatMessageDao.merge(chatMessage);
	}
	public Chat saveChat(Chat chat) {
		return chatDao.merge(chat);
	}
	
	public List<Chat> loadChats(BasicUser currentUser) {
		return chatDao.loadChats(currentUser);
	}
	
	public Chat getChatByUsers(BasicUser currentUser, BasicUser anotherUser) {
		Chat chat = chatDao.getChatByUsers(currentUser, anotherUser);
		if(chat == null) {
			chat = saveChat(createChat(Sets.newHashSet(anotherUser),currentUser));
		}
		return chat;
	}
	
	public List<ChatMessage> loadNewMessage(ChatMessage lastMessage, Chat currentChat) {
		return chatMessageDao.loadNewMessage(lastMessage, currentChat);
	}
	
	public Chat createChat(Set<BasicUser> persons, BasicUser currentUser) {
		List<ChatPerson> chatPersons = Lists.newArrayList();
		boolean isChooseCurrentUser = false;
		for(BasicUser person : persons) {
			if(person.getID().equals(currentUser)) {
				isChooseCurrentUser = true;
			}
			ChatPerson chatPerson = new ChatPerson();
			chatPerson.setPerson(person);
			chatPersons.add(chatPerson);
		}
		if(!isChooseCurrentUser) {
			ChatPerson currentPerson = new ChatPerson();
			currentPerson.setLeader(new Long(1));
			currentPerson.setPerson(currentUser);
			chatPersons.add(0, currentPerson);
		}
		Chat chat = new Chat();
		chat.setChatPersons(chatPersons);
		chat.setPersonNumber(new Long(chatPersons.size()));
		
		return chat;
	}
	
	public String getAvatar(Chat chat, BasicUser currentUser) {
		String url = null;
		List<ChatPerson> chatPersons = chat.getChatPersons();
		if(chatPersons.size() > 2) {
			url = "icons/group_avatar.png";
		}
		return url;
	}
	
	public BasicUser getChatPerson(Chat chat, BasicUser currentUser) {
		BasicUser sender = null;
		List<ChatPerson> chatPersons = chat.getChatPersons();
		if(chatPersons.size() == 2) {
			for(ChatPerson person : chatPersons) {
				if(person.getPerson().getID() != currentUser.getID()) {
					sender = person.getPerson(); 
					break;
				}
			}
		}
		return sender;
	}
	
	public String getChatTitle(Chat chat, BasicUser currentUser) {
		String title = "";
		List<ChatPerson> chatPersons = chat.getChatPersons();
		if(chatPersons.size() == 2) {
			for(ChatPerson person : chatPersons) {
				if(person.getPerson().getID() != currentUser.getID()) {
					title = person.getPerson().getRealName();
					break;
				}
			}
		} else if(chatPersons.size() == 3) {
			String seperate = " and ";
			for(ChatPerson person : chatPersons) {
				if(person.getPerson().getID() != currentUser.getID()) {
					title += person.getPerson().getRealName() + seperate;
				}
			}
			title = title.substring(0, title.lastIndexOf(seperate));
		} else if(chatPersons.size() > 3) {
			String seperate = ", ";
			for(ChatPerson person : chatPersons) {
				if(person.getPerson().getID() != currentUser.getID()) {
					title += person.getPerson().getRealName() + seperate;
				}
			}
			title = title.substring(0, title.lastIndexOf(seperate));
		}
		return title;
	}
}
