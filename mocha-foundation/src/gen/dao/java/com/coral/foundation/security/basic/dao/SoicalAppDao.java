package com.coral.foundation.security.basic.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.springframework.orm.jpa.aspectj.JpaExceptionTranslatorAspect;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.BasicUser;
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
		try {
			Query query = getEntityManager().createQuery("from SoicalApp s where s.requesToken = :requestToken", SoicalApp.class);
			query.setParameter("requestToken", requestToken);
			SoicalApp sa = (SoicalApp) query.getSingleResult();
			return sa;
		}
		catch (NonUniqueResultException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SoicalApp findSoicaAppByAuthToken(String authToken) {
		try {
			Query query = getEntityManager().createQuery("from SoicalApp s where s.authToken = :authToken", SoicalApp.class);
			query.setParameter("authToken", authToken);
			SoicalApp sa = (SoicalApp) query.getSingleResult();
			return sa;
		}
		catch (NoResultException e) {
			System.out.println("New Access Token generate");
		}
		return null;
	}

	public SoicalApp findSoicaAppByName(BasicUser user, String appName) {
		try {
			Query query = getEntityManager().createQuery("from SoicalApp s where s.name=:name and s.user=:user", SoicalApp.class);
			query.setParameter("name", appName);
			query.setParameter("user", user);
			SoicalApp sa = (SoicalApp) query.getSingleResult();
			return sa;
		}
		catch (NoResultException e) {
			System.out.println("No Facebook account find this user");
		}
		return null;
	}

	public List<SoicalApp> findSoicalAppByUser(BasicUser user) {
		Query query = getEntityManager().createQuery("from SoicalApp s where s.user=:user", SoicalApp.class);
		query.setParameter("user", user);
		return query.getResultList();
	}
}
