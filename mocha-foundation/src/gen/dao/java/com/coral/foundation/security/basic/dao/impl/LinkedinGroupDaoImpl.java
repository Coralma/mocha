package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * LinkedinGroupDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class LinkedinGroupDaoImpl extends JpaDao<LinkedinGroup> implements LinkedinGroupDao {
	
	Logger log=LoggerFactory.getLogger(LinkedinGroupDaoImpl.class);
	public LinkedinGroupDaoImpl() {
		super();
		log.debug(""+LinkedinGroupDaoImpl.class);
	}
}

