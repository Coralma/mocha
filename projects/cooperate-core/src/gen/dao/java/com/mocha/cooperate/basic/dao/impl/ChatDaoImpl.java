package com.mocha.cooperate.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.security.model.BasicUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ChatDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ChatDaoImpl extends JpaDao<Chat> implements ChatDao {
	
	Logger log=LoggerFactory.getLogger(ChatDaoImpl.class);
	public ChatDaoImpl() {
		super();
		log.debug(""+ChatDaoImpl.class);
	}
	
	public List<Chat> loadChats(BasicUser currentUser) {
		Query query = entityManager.createQuery("from Chat t where EXISTS (SELECT i FROM t.chatPersons i WHERE i.person =:currentUser)", Chat.class);
		query.setParameter("currentUser", currentUser);
		List<Chat> chats = query.getResultList();
		return chats;
	}
}

