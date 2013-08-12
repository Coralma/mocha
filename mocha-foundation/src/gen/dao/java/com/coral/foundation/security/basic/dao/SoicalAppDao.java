package com.coral.foundation.security.basic.dao;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.SoicalApp;

/**
  * SoicalAppDao is a auto Generated class. Please don't modify it.
  */
public class SoicalAppDao extends BaseDao<SoicalApp> {
	
	@Override
	public Class<SoicalApp> getEntityClass() {
		return SoicalApp.class;
	}
	
	public SoicalApp findSoicaAppByRequestToken(String requestToken) {
		Query query = getEntityManager().createQuery("from SoicalApp s where s.requesToken = :requestToken", SoicalApp.class);
		query.setParameter("requestToken", requestToken);
		SoicalApp sa=(SoicalApp) query.getSingleResult();
		return sa;
	}

}

