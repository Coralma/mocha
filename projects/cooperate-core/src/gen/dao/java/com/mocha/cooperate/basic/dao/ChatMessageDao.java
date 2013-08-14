package com.mocha.cooperate.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.mocha.cooperate.model.Chat;
import com.mocha.cooperate.model.ChatMessage;

/**
  * ChatMessageDao is a auto Generated class. Please don't modify it.
  */
public class ChatMessageDao extends BaseDao<ChatMessage> {
	
	@Override
	public Class<ChatMessage> getEntityClass() {
		return ChatMessage.class;
	}
	
	public List<ChatMessage> loadNewMessage(ChatMessage lastMessage,
			Chat currentChat) {
		Long messageId = new Long(0);
		if(lastMessage != null) {
			messageId = lastMessage.getID();
		}
		Query query = getEntityManager().createQuery("from ChatMessage c where c.chatMessageId > :chatMessageId and c.chat=:chat",ChatMessage.class);
		query.setParameter("chatMessageId", messageId);
		query.setParameter("chat", currentChat);
		List<ChatMessage> chatMessages = query.getResultList();
		return chatMessages;
	}
}

