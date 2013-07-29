package com.coral.foundation.security.basic.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LinkedinConnectionNetworkUpdateDaoImpl is a auto Generated class. Please don't modify it.
 * 
 * @author Coral
 */
public class LinkedinConnectionNetworkUpdateDaoImpl extends JpaDao<LinkedinConnectionNetworkUpdate> implements LinkedinConnectionNetworkUpdateDao {

	Logger log = LoggerFactory.getLogger(LinkedinConnectionNetworkUpdateDaoImpl.class);

	public LinkedinConnectionNetworkUpdateDaoImpl() {
		super();
		log.debug("" + LinkedinConnectionNetworkUpdateDaoImpl.class);
	}

	@Override
	public List<LinkedinConnectionNetworkUpdate> findUpdateByConnection(LinkedinConnection conn) {
		Query query = entityManager.createQuery("from LinkedinConnectionNetworkUpdate where linkedinConnection = :linkedinConnection ",
				LinkedinConnectionNetworkUpdate.class);
		query.setParameter("linkedinConnection", conn);
		List<LinkedinConnectionNetworkUpdate> results = query.getResultList();
		return results;
	}

	@Override
	public boolean findDuplicateUpdate(LinkedinConnection con, LinkedinConnectionNetworkUpdate update) {
		try {
			Query query = entityManager
					.createQuery(
							"from LinkedinConnectionNetworkUpdate where linkedinConnection = :linkedinConnection and firstName = :firstName and lastName = :lastName and updatedKey = :updateKey",
							LinkedinConnectionNetworkUpdate.class);
			
			query.setParameter("linkedinConnection", con);
			query.setParameter("firstName", update.getFirstName().trim());
			query.setParameter("lastName", update.getLastName().trim());
			query.setParameter("updateKey", update.getUpdatedKey().trim());
			System.out.println("linkedinConnection " + con.getID());
			System.out.println("firstName " + update.getFirstName().trim());
			System.out.println("lastName " + update.getLastName().trim());
			System.out.println("updateKey " + update.getUpdatedKey().trim());

			int totalCnt = query.getResultList().size();
			if (totalCnt > 1) {
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
