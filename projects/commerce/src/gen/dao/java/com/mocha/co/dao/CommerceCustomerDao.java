package com.mocha.co.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.co.model.CommerceCustomer;

/**
  * CommerceCustomerDao is a auto Generated class. Please don't modify it.
  */
public class CommerceCustomerDao extends BaseDao<CommerceCustomer> {
	
	@Override
	public Class<CommerceCustomer> getEntityClass() {
		return CommerceCustomer.class;
	}
	
	public CommerceCustomer findCCByUser(BasicUser basicUser) {
		try {
			Query query = getEntityManager().createQuery(
					"from CommerceCustomer t where t.referUser =:referUser",
					CommerceCustomer.class);
			query.setParameter("referUser", basicUser);
			CommerceCustomer ccs = (CommerceCustomer) query.getSingleResult();
			return ccs;
		} catch (NoResultException e) {
			return null;
		}
	}
}

