package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * AppDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class AppDaoImpl extends JpaDao<App> implements AppDao {
	
	Logger log=LoggerFactory.getLogger(AppDaoImpl.class);
	public AppDaoImpl() {
		super();
		log.debug(""+AppDaoImpl.class);
	}
}

