package com.coral.foundation.security.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.security.model.*;
import com.coral.foundation.persistence.BaseDao;

/**
 * CampaingnsDao is a auto Generated class. Please don't modify it.
 */
public class CampaingnsDao extends BaseDao<Campaingns> {

	@Override
	public Class<Campaingns> getEntityClass() {
		return Campaingns.class;
	}

	public List<Campaingns> findAll() {
		Query query = getEntityManager().createQuery("from Campaingns c order by c.campaingnsId desc", Campaingns.class);
		List<Campaingns> allCampaingns = query.getResultList();
		return allCampaingns;
	}
}
