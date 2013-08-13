package com.mocha.crm.dao;

import com.mocha.crm.model.*;
import com.coral.foundation.persistence.BaseDao;

/**
  * CustomerDao is a auto Generated class. Please don't modify it.
  */
public class CustomerDao extends BaseDao<Customer> {
	
	@Override
	public Class<Customer> getEntityClass() {
		return Customer.class;
	}
}

