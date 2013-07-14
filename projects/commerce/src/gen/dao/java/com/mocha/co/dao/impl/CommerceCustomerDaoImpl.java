package com.mocha.co.dao.impl;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.mocha.co.dao.*;
import com.mocha.co.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.security.model.BasicUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CommerceCustomerDaoImpl is a auto Generated class. Please don't modify it.
 * 
 * @author Coral
 */
public class CommerceCustomerDaoImpl extends JpaDao<CommerceCustomer>
		implements
			CommerceCustomerDao {

	Logger log = LoggerFactory.getLogger(CommerceCustomerDaoImpl.class);
	public CommerceCustomerDaoImpl() {
		super();
		log.debug("" + CommerceCustomerDaoImpl.class);
	}

	@Override
	public CommerceCustomer findCCByUser(BasicUser basicUser) {
		try {
			Query query = entityManager.createQuery(
					"from CommerceCustomer where referUser =:referUser",
					CommerceCustomer.class);
			query.setParameter("referUser", basicUser);
			CommerceCustomer ccs = (CommerceCustomer) query.getSingleResult();
			return ccs;
		} catch (NoResultException e) {
			return null;
		}
	}
}
