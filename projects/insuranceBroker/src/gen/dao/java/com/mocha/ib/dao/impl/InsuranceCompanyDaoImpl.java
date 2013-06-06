package com.mocha.ib.dao.impl;
import com.mocha.ib.dao.*;
import com.mocha.ib.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * InsuranceCompanyDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class InsuranceCompanyDaoImpl extends JpaDao<InsuranceCompany> implements InsuranceCompanyDao {
	
	Logger log=LoggerFactory.getLogger(InsuranceCompanyDaoImpl.class);
	public InsuranceCompanyDaoImpl() {
		super();
		log.debug(""+InsuranceCompanyDaoImpl.class);
	}
}

