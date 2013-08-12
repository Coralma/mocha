package com.coral.foundation.security.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinPersonProfile;

/**
  * LinkedinConnectionDao is a auto Generated class. Please don't modify it.
  */
public class LinkedinConnectionDao extends BaseDao<LinkedinConnection> {
	
	@Override
	public Class<LinkedinConnection> getEntityClass() {
		return LinkedinConnection.class;
	}
	
	public List<LinkedinConnection> findFollowedConnectionByPerson(LinkedinPersonProfile profile) {
		Query query = getEntityManager().createQuery("from LinkedinConnection l where l.Linkedin_person_profile_id = :linkedinPersonProfile and l.needFollow = '00000001'", LinkedinConnection.class);
		query.setParameter("linkedinPersonProfile", profile);
		List<LinkedinConnection> followed = query.getResultList();
		return followed;
	}

	public List<LinkedinConnection> findEntireConnectionByConn(LinkedinConnection connUser) {
		Query query = getEntityManager().createQuery("from LinkedinConnection l where l.Linkedin_person_profile_id = :linkedinPersonProfile and l.firstName = :firstName and l.lastName = :lastName", LinkedinConnection.class);
		query.setParameter("firstName", connUser.getFirstName());
		query.setParameter("lastName", connUser.getLastName());
		query.setParameter("linkedinPersonProfile", connUser.getLinkedinPersonProfile());
		List<LinkedinConnection> followed = query.getResultList();
		return followed;
	}
}

