package com.mocha.cooperate.basic.dao.impl;
import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * StatusDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class StatusDaoImpl extends JpaDao<Status> implements StatusDao {
	
	Logger log=LoggerFactory.getLogger(StatusDaoImpl.class);
	public StatusDaoImpl() {
		super();
		log.debug(""+StatusDaoImpl.class);
	}
}

