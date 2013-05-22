package com.mocha.cooperate.basic.dao.impl;
import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ShotcutDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ShotcutDaoImpl extends JpaDao<Shotcut> implements ShotcutDao {
	
	Logger log=LoggerFactory.getLogger(ShotcutDaoImpl.class);
	public ShotcutDaoImpl() {
		super();
		log.debug(""+ShotcutDaoImpl.class);
	}
}

