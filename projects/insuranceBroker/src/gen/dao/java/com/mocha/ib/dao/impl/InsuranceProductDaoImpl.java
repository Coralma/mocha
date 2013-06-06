package com.mocha.ib.dao.impl;
import com.mocha.ib.dao.*;
import com.mocha.ib.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * InsuranceProductDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class InsuranceProductDaoImpl extends JpaDao<InsuranceProduct> implements InsuranceProductDao {
	
	Logger log=LoggerFactory.getLogger(InsuranceProductDaoImpl.class);
	public InsuranceProductDaoImpl() {
		super();
		log.debug(""+InsuranceProductDaoImpl.class);
	}
}

