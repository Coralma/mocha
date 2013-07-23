package com.coral.foundation.security.basic.dao.impl;
import javax.persistence.Query;

import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * SoicalAppDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class SoicalAppDaoImpl extends JpaDao<SoicalApp> implements SoicalAppDao {
	
	Logger log=LoggerFactory.getLogger(SoicalAppDaoImpl.class);
	public SoicalAppDaoImpl() {
		super();
		log.debug(""+SoicalAppDaoImpl.class);
	}
	@Override
	public SoicalApp findSoicaAppByRequestToken(String requestToken) {
		Query query = entityManager.createQuery("from SoicalApp where requesToken = :requestToken", SoicalApp.class);
		query.setParameter("requestToken", requestToken);
		SoicalApp sa=(SoicalApp) query.getSingleResult();
		return sa;
	}
}

