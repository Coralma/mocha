package com.mocha.ib.dao.impl;
import com.mocha.ib.dao.*;
import com.mocha.ib.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * PolicyDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class PolicyDaoImpl extends JpaDao<Policy> implements PolicyDao {
	
	Logger log=LoggerFactory.getLogger(PolicyDaoImpl.class);
	public PolicyDaoImpl() {
		super();
		log.debug(""+PolicyDaoImpl.class);
	}
}

