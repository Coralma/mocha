package com.mocha.ib.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.ib.model.InsuranceCustomer;

/**
  * InsuranceCustomerDao is a auto Generated class. Please don't modify it.
  */
public class InsuranceCustomerDao extends BaseDao<InsuranceCustomer> {
	
	@Override
	public Class<InsuranceCustomer> getEntityClass() {
		return InsuranceCustomer.class;
	}
	

	public InsuranceCustomer previous(Long id) {
		Query query = getEntityManager().createQuery("from InsuranceCustomer where insuranceCustomerId<:id order by insuranceCustomerId desc ",InsuranceCustomer.class);
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
		Query query = getEntityManager().createQuery("from InsuranceCustomer where insuranceCustomerId>:id",InsuranceCustomer.class);
		query.setParameter("id", id);
		query.setFirstResult(1);
		query.setMaxResults(1);
		List<InsuranceCustomer> customers = query.getResultList();
		if(customers.size() > 0) {
			return customers.get(0);
		}
		return null;
	}
	
	public InsuranceCustomer findCustomerByUser(BasicUser customerUser) {
		Query query = getEntityManager().createQuery("from InsuranceCustomer where referUser=:referUser",InsuranceCustomer.class);
		query.setParameter("referUser", customerUser);
		List<InsuranceCustomer> customers = query.getResultList();
		if(customers.size() > 0) {
			return customers.get(0);
		}
		return null;
	}
}

