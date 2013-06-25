package com.mocha.cooperate.basic.dao.impl;
import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * NotifyLineDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class NotifyLineDaoImpl extends JpaDao<NotifyLine> implements NotifyLineDao {
	
	Logger log=LoggerFactory.getLogger(NotifyLineDaoImpl.class);
	public NotifyLineDaoImpl() {
		super();
		log.debug(""+NotifyLineDaoImpl.class);
	}
}

