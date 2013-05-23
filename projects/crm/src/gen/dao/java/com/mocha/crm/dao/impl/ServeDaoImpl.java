package com.mocha.crm.dao.impl;
import com.mocha.crm.dao.*;
import com.mocha.crm.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ServeDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ServeDaoImpl extends JpaDao<Serve> implements ServeDao {
	
	Logger log=LoggerFactory.getLogger(ServeDaoImpl.class);
	public ServeDaoImpl() {
		super();
		log.debug(""+ServeDaoImpl.class);
	}
}

