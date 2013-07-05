package com.mocha.co.dao.impl;
import com.mocha.co.dao.*;
import com.mocha.co.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * PromotionDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class PromotionDaoImpl extends JpaDao<Promotion> implements PromotionDao {
	
	Logger log=LoggerFactory.getLogger(PromotionDaoImpl.class);
	public PromotionDaoImpl() {
		super();
		log.debug(""+PromotionDaoImpl.class);
	}
}

