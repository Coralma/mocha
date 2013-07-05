package com.mocha.co.dao.impl;
import com.mocha.co.dao.*;
import com.mocha.co.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * StockProductDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class StockProductDaoImpl extends JpaDao<StockProduct> implements StockProductDao {
	
	Logger log=LoggerFactory.getLogger(StockProductDaoImpl.class);
	public StockProductDaoImpl() {
		super();
		log.debug(""+StockProductDaoImpl.class);
	}
}

