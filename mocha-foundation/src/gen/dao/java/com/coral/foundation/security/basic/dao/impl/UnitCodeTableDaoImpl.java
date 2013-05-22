package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * UnitCodeTableDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class UnitCodeTableDaoImpl extends JpaDao<UnitCodeTable> implements UnitCodeTableDao {
	
	Logger log=LoggerFactory.getLogger(UnitCodeTableDaoImpl.class);
	public UnitCodeTableDaoImpl() {
		super();
		log.debug(""+UnitCodeTableDaoImpl.class);
	}
}

