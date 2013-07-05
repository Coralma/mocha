package com.mocha.co.dao.impl;
import com.mocha.co.dao.*;
import com.mocha.co.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * OrderDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class OrderDaoImpl extends JpaDao<Order> implements OrderDao {
	
	Logger log=LoggerFactory.getLogger(OrderDaoImpl.class);
	public OrderDaoImpl() {
		super();
		log.debug(""+OrderDaoImpl.class);
	}
}

