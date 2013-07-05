package com.mocha.co.dao.impl;
import com.mocha.co.dao.*;
import com.mocha.co.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * SupplierDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class SupplierDaoImpl extends JpaDao<Supplier> implements SupplierDao {
	
	Logger log=LoggerFactory.getLogger(SupplierDaoImpl.class);
	public SupplierDaoImpl() {
		super();
		log.debug(""+SupplierDaoImpl.class);
	}
}

