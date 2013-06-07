package com.mocha.ib.dao.impl;
import com.mocha.ib.dao.*;
import com.mocha.ib.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ClaimDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ClaimDaoImpl extends JpaDao<Claim> implements ClaimDao {
	
	Logger log=LoggerFactory.getLogger(ClaimDaoImpl.class);
	public ClaimDaoImpl() {
		super();
		log.debug(""+ClaimDaoImpl.class);
	}
}

