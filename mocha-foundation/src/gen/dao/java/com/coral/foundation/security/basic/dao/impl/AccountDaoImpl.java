package com.coral.foundation.security.basic.dao.impl;
import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * AccountDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class AccountDaoImpl extends JpaDao<Account> implements AccountDao {
	
	Logger log=LoggerFactory.getLogger(AccountDaoImpl.class);
	public AccountDaoImpl() {
		super();
		log.debug(""+AccountDaoImpl.class);
	}
}

