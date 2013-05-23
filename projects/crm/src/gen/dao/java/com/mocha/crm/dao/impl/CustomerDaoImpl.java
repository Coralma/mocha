package com.mocha.crm.dao.impl;
import com.mocha.crm.dao.*;
import com.mocha.crm.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * CustomerDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class CustomerDaoImpl extends JpaDao<Customer> implements CustomerDao {
	
	Logger log=LoggerFactory.getLogger(CustomerDaoImpl.class);
	public CustomerDaoImpl() {
		super();
		log.debug(""+CustomerDaoImpl.class);
	}
}

