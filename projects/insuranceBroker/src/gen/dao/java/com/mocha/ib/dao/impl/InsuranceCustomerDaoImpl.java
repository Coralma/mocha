package com.mocha.ib.dao.impl;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.jpa.impl.JpaDao;
import com.mocha.ib.dao.InsuranceCustomerDao;
import com.mocha.ib.model.InsuranceCustomer;

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
	
	public InsuranceCustomer previous(Long id) {
		Query query = entityManager.createQuery("from InsuranceCustomer where insuranceCustomerId<:id order by insuranceCustomerId desc ",InsuranceCustomer.class);
		query.setParameter("id", id);
		query.setFirstResult(1);
		query.setMaxResults(1);
		List<InsuranceCustomer> customers = query.getResultList();
		if(customers.size() > 0) {
			return customers.get(0);
		}
		return null;
	}
	
	public InsuranceCustomer next(Long id) {
		Query query = entityManager.createQuery("from InsuranceCustomer where insuranceCustomerId>:id",InsuranceCustomer.class);
		query.setParameter("id", id);
		query.setFirstResult(1);
		query.setMaxResults(1);
		List<InsuranceCustomer> customers = query.getResultList();
		if(customers.size() > 0) {
			return customers.get(0);
		}
		return null;
	}
}

