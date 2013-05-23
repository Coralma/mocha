package com.mocha.oa.dao.impl;
import com.mocha.oa.dao.*;
import com.mocha.oa.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * LeaveDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class LeaveDaoImpl extends JpaDao<Leave> implements LeaveDao {
	
	Logger log=LoggerFactory.getLogger(LeaveDaoImpl.class);
	public LeaveDaoImpl() {
		super();
		log.debug(""+LeaveDaoImpl.class);
	}
}

