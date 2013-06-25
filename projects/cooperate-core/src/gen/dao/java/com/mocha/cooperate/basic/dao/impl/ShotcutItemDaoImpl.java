package com.mocha.cooperate.basic.dao.impl;
import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ShotcutItemDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class ShotcutItemDaoImpl extends JpaDao<ShotcutItem> implements ShotcutItemDao {
	
	Logger log=LoggerFactory.getLogger(ShotcutItemDaoImpl.class);
	public ShotcutItemDaoImpl() {
		super();
		log.debug(""+ShotcutItemDaoImpl.class);
	}
}

