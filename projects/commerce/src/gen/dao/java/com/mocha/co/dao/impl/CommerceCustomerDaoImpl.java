package com.mocha.co.dao.impl;
import com.mocha.co.dao.*;
import com.mocha.co.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * CommerceCustomerDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class CommerceCustomerDaoImpl extends JpaDao<CommerceCustomer> implements CommerceCustomerDao {
	
	Logger log=LoggerFactory.getLogger(CommerceCustomerDaoImpl.class);
	public CommerceCustomerDaoImpl() {
		super();
		log.debug(""+CommerceCustomerDaoImpl.class);
	}
}

