package com.mocha.ib.dao.impl;
import com.mocha.ib.dao.*;
import com.mocha.ib.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * InsuranceCustomerDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class InsuranceCustomerDaoImpl extends JpaDao<InsuranceCustomer> implements InsuranceCustomerDao {
	
	Logger log=LoggerFactory.getLogger(InsuranceCustomerDaoImpl.class);
	public InsuranceCustomerDaoImpl() {
		super();
		log.debug(""+InsuranceCustomerDaoImpl.class);
	}
}

