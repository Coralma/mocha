package com.mocha.co.dao.impl;
import com.mocha.co.dao.*;
import com.mocha.co.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * SourceApplicationDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class SourceApplicationDaoImpl extends JpaDao<SourceApplication> implements SourceApplicationDao {
	
	Logger log=LoggerFactory.getLogger(SourceApplicationDaoImpl.class);
	public SourceApplicationDaoImpl() {
		super();
		log.debug(""+SourceApplicationDaoImpl.class);
	}
}

