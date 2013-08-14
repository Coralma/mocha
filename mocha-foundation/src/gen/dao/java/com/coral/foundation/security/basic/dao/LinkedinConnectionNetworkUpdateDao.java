package com.coral.foundation.security.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate;

/**
  * LinkedinConnectionNetworkUpdateDao is a auto Generated class. Please don't modify it.
  */
public class LinkedinConnectionNetworkUpdateDao extends BaseDao<LinkedinConnectionNetworkUpdate> {
	
	@Override
	public Class<LinkedinConnectionNetworkUpdate> getEntityClass() {
		return LinkedinConnectionNetworkUpdate.class;
	}
	
	public List<LinkedinConnectionNetworkUpdate> findUpdateByConnection(LinkedinConnection conn) {
		Query query = getEntityManager().createQuery("from LinkedinConnectionNetworkUpdate l where l.linkedinConnection = :linkedinConnection ",
				LinkedinConnectionNetworkUpdate.class);
		query.setParameter("linkedinConnection", conn);
		List<LinkedinConnectionNetworkUpdate> results = query.getResultList();
		return results;
	}

	public boolean findDuplicateUpdate(LinkedinConnection con, LinkedinConnectionNetworkUpdate update) {
		try {
			Query query = getEntityManager().createQuery(
							"from LinkedinConnectionNetworkUpdate l where l.linkedinConnection = :linkedinConnection and l.firstName = :firstName and l.lastName = :lastName and l.updatedKey = :updateKey",
							LinkedinConnectionNetworkUpdate.class);
			query.setParameter("linkedinConnection", con);
			query.setParameter("firstName", update.getFirstName().trim());
			query.setParameter("lastName", update.getLastName().trim());
			query.setParameter("updateKey", update.getUpdatedKey().trim());

			int totalCnt = query.getResultList().size();
			if (totalCnt >0) {
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}

