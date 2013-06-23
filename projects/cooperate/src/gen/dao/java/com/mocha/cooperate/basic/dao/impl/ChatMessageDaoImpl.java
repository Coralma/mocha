package com.mocha.cooperate.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ChatMessageDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ChatMessageDaoImpl extends JpaDao<ChatMessage> implements ChatMessageDao {
	
	Logger log=LoggerFactory.getLogger(ChatMessageDaoImpl.class);
	public ChatMessageDaoImpl() {
		super();
		log.debug(""+ChatMessageDaoImpl.class);
	}

	@Override
	public List<ChatMessage> loadNewMessage(ChatMessage lastMessage,
			Chat currentChat) {
		Long messageId = new Long(0);
		if(lastMessage != null) {
			messageId = lastMessage.getID();
		}
		Query query = entityManager.createQuery("from ChatMessage where chatMessageId > :chatMessageId and chat=:chat",ChatMessage.class);
		query.setParameter("chatMessageId", messageId);
		query.setParameter("chat", currentChat);
		List<ChatMessage> chatMessages = query.getResultList();
		return chatMessages;
	}
}

