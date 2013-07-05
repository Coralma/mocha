package com.mocha.co.dao.impl;
import com.mocha.co.dao.*;
import com.mocha.co.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * OrderProductDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class OrderProductDaoImpl extends JpaDao<OrderProduct> implements OrderProductDao {
	
	Logger log=LoggerFactory.getLogger(OrderProductDaoImpl.class);
	public OrderProductDaoImpl() {
		super();
		log.debug(""+OrderProductDaoImpl.class);
	}
}

