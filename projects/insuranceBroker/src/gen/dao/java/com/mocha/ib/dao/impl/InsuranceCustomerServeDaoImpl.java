package com.mocha.ib.dao.impl;
import com.mocha.ib.dao.*;
import com.mocha.ib.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * InsuranceCustomerServeDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class InsuranceCustomerServeDaoImpl extends JpaDao<InsuranceCustomerServe> implements InsuranceCustomerServeDao {
	
	Logger log=LoggerFactory.getLogger(InsuranceCustomerServeDaoImpl.class);
	public InsuranceCustomerServeDaoImpl() {
		super();
		log.debug(""+InsuranceCustomerServeDaoImpl.class);
	}
}

