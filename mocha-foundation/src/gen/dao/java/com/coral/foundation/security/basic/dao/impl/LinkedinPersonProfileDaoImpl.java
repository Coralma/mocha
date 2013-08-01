package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * LinkedinPersonProfileDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class LinkedinPersonProfileDaoImpl extends JpaDao<LinkedinPersonProfile> implements LinkedinPersonProfileDao {
	
	Logger log=LoggerFactory.getLogger(LinkedinPersonProfileDaoImpl.class);
	public LinkedinPersonProfileDaoImpl() {
		super();
		log.debug(""+LinkedinPersonProfileDaoImpl.class);
	}
}

