package com.mocha.co.dao.impl;
import com.mocha.co.dao.*;
import com.mocha.co.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * CommerceProductDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class CommerceProductDaoImpl extends JpaDao<CommerceProduct> implements CommerceProductDao {
	
	Logger log=LoggerFactory.getLogger(CommerceProductDaoImpl.class);
	public CommerceProductDaoImpl() {
		super();
		log.debug(""+CommerceProductDaoImpl.class);
	}
}

