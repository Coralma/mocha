package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * SoicalAppDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class SoicalAppDaoImpl extends JpaDao<SoicalApp> implements SoicalAppDao {
	
	Logger log=LoggerFactory.getLogger(SoicalAppDaoImpl.class);
	public SoicalAppDaoImpl() {
		super();
		log.debug(""+SoicalAppDaoImpl.class);
	}
}

