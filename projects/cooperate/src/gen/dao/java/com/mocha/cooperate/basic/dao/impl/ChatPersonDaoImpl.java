package com.mocha.cooperate.basic.dao.impl;
import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ChatPersonDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ChatPersonDaoImpl extends JpaDao<ChatPerson> implements ChatPersonDao {
	
	Logger log=LoggerFactory.getLogger(ChatPersonDaoImpl.class);
	public ChatPersonDaoImpl() {
		super();
		log.debug(""+ChatPersonDaoImpl.class);
	}
}

