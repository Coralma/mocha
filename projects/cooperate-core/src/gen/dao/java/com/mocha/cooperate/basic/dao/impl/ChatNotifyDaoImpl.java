package com.mocha.cooperate.basic.dao.impl;
import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ChatNotifyDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ChatNotifyDaoImpl extends JpaDao<ChatNotify> implements ChatNotifyDao {
	
	Logger log=LoggerFactory.getLogger(ChatNotifyDaoImpl.class);
	public ChatNotifyDaoImpl() {
		super();
		log.debug(""+ChatNotifyDaoImpl.class);
	}
}

