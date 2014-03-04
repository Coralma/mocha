package com.coral.foundation.security.basic.dao;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import com.coral.foundation.security.model.*;
import com.coral.foundation.persistence.BaseDao;

/**
 * FacebookFriendDao is a auto Generated class. Please don't modify it.
 */
public class FacebookFriendDao extends BaseDao<FacebookFriend> {

	@Override
	public Class<FacebookFriend> getEntityClass() {
		return FacebookFriend.class;
	}

	public FacebookFriend findFacebookFriendByProfileUrl(String profileUrl) {
		Query query = getEntityManager().createQuery("from FacebookFriend f where f.profile_url = :profile_Url", FacebookFriend.class);
		try {
			query.setParameter("profile_Url", profileUrl);
			FacebookFriend ff = (FacebookFriend) query.setMaxResults(1).getSingleResult();
			return ff;
		}
		catch (NonUniqueResultException e) {
			e.printStackTrace();
		}
		catch (NoResultException e) {
			return null;
		}
		return null;
	}
}
