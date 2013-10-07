package com.coral.foundation.security.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate;
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
		Query query = getEntityManager().createQuery("from LinkedinConnection l where l.linkedinPersonProfile = :linkedinPersonProfile and l.needFollow='1'",
				LinkedinConnection.class);
		query.setParameter("linkedinPersonProfile", profile);
		List<LinkedinConnection> followed = query.getResultList();
		return followed;
	}

	@SuppressWarnings("unchecked")
	public List<LinkedinConnection> findEntireConnectionByConn(LinkedinConnection connUser) {
		Query query = getEntityManager().createQuery("from LinkedinConnection l where l.linkedinPersonProfile = :linkedinPersonProfile",
				LinkedinConnection.class);
		query.setParameter("linkedinPersonProfile", connUser.getLinkedinPersonProfile());
		List<LinkedinConnection> followed = query.getResultList();
		return followed;
	}

	public List<LinkedinConnectionNetworkUpdate> findUpdateStatusWithFollowedConnections(LinkedinPersonProfile profile) {
		Query query = getEntityManager().createQuery(
				"from LinkedinConnectionNetworkUpdate lc left join lc.linkedinConnection l"
						+ " where l.linkedinPersonProfile = :linkedinPersonProfile and l.needFollow='1' " + "order by lc.createTime desc",
				LinkedinConnection.class);
		query.setParameter("linkedinPersonProfile", profile);
		List<LinkedinConnectionNetworkUpdate> udpateStatus = query.getResultList();
		return udpateStatus;
	}

	public LinkedinConnection findConnectByPublicProfile(String linkedinProfileUrl) {
		Query query = getEntityManager().createQuery("from LinkedinConnection l where l.publicProfileUrl = :publicProfileUrl", LinkedinConnection.class);
		query.setParameter("publicProfileUrl", linkedinProfileUrl);
		LinkedinConnection conn = (LinkedinConnection) query.getSingleResult();
		return conn;
	}
}
