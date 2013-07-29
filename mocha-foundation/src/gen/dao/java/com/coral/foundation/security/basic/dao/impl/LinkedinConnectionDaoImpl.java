package com.coral.foundation.security.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.utils.StrUtils;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * LinkedinConnectionDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class LinkedinConnectionDaoImpl extends JpaDao<LinkedinConnection> implements LinkedinConnectionDao {
	
	Logger log=LoggerFactory.getLogger(LinkedinConnectionDaoImpl.class);
	public LinkedinConnectionDaoImpl() {
		super();
		log.debug(""+LinkedinConnectionDaoImpl.class);
	}
	
	@Override
	public List<LinkedinConnection> findFollowedConnectionByPerson(LinkedinPersonProfile profile) {
		Query query = entityManager.createQuery("from LinkedinConnection where Linkedin_person_profile_id = :linkedinPersonProfile and needFollow = '00000001'", LinkedinConnection.class);
		query.setParameter("linkedinPersonProfile", profile);
		List<LinkedinConnection> followed = query.getResultList();
		return followed;
	}

	@Override
	public List<LinkedinConnection> findEntireConnectionByConn(LinkedinConnection connUser) {
		Query query = entityManager.createQuery("from LinkedinConnection where Linkedin_person_profile_id = :linkedinPersonProfile and firstName = :firstName and lastName = :lastName", LinkedinConnection.class);
		query.setParameter("firstName", connUser.getFirstName());
		query.setParameter("lastName", connUser.getLastName());
		query.setParameter("linkedinPersonProfile", connUser.getLinkedinPersonProfile());
		List<LinkedinConnection> followed = query.getResultList();
		return followed;
	}

	
}

