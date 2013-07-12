package com.mocha.cooperate.basic.dao.impl;
import java.math.BigInteger;
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

	@Override
	public Chat getChatByUsers(BasicUser currentUser, BasicUser anotherUser) {
		Query query = entityManager.createNativeQuery("select t.CHAT_ID commonChartID from t_chat_person t " +
				"inner join t_chat tc on t.CHAT_ID=tc.CHAT_ID " +
				"where t.person_BASIC_USER_ID in ("+ currentUser.getID() +") and exists " +
				"(select 1 from t_chat_person tt where tt.person_BASIC_USER_ID in ("+anotherUser.getID()+") and tt.chat_id=t.CHAT_ID) " +
				"and tc.PERSON_NUMBER=2 group by t.CHAT_ID");
//		query.setParameter("personNumber", Long.valueOf(users.length));
//		for(int i=0; i<users.length;i++) {
//			query.setParameter("user" + i, users[i]);	
//		}
//		Chat chat = (Chat)query.getSingleResult();
		List chats = query.getResultList();
		if(chats.size() > 0) {
			BigInteger chatId = (BigInteger)chats.get(0);
			return findById(chatId.longValue());
		} else {
			return null;
		}
	}
}

