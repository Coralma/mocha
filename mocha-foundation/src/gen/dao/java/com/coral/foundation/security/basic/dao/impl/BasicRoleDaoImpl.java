package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * BasicRoleDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class BasicRoleDaoImpl extends JpaDao<BasicRole> implements BasicRoleDao {
	
	Logger log=LoggerFactory.getLogger(BasicRoleDaoImpl.class);
	public BasicRoleDaoImpl() {
		super();
		log.debug(""+BasicRoleDaoImpl.class);
	}
}

