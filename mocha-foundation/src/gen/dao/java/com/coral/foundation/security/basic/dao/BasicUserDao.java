package com.coral.foundation.security.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.StrUtils;

/**
  * BasicUserDao is a auto Generated class. Please don't modify it.
  */
public class BasicUserDao extends BaseDao<BasicUser> {
	
	@Override
	public Class<BasicUser> getEntityClass() {
		return BasicUser.class;
	}
	
	private int pageSize = RuntimeConstant.PAGING_SIZE;

	public List<BasicUser> findActiveUsers(int page) {
		//FIXME filte by status
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from BasicUser b order by b.basicUserId desc",BasicUser.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<BasicUser> basicUsers = query.getResultList();
		return basicUsers;
	}

	public List<BasicUser> findInActiveUsers(int page) {
		//FIXME filte as status
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from BasicUser b order by b.basicUserId desc",BasicUser.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<BasicUser> basicUsers = query.getResultList();
		return basicUsers;
	}

	public List<BasicUser> fuzzyUserSearch(String condition) {
		Query query = getEntityManager().createQuery("from BasicUser b where b.realName like :condition order by b.basicUserId desc", BasicUser.class);
		query.setParameter("condition", StrUtils.like(condition));
		List<BasicUser> basicUsers = query.getResultList();
		return basicUsers;
	}

	public BasicUser findUserByUserName(String userName) {
		Query query = getEntityManager().createQuery("from BasicUser t where t.userName = :userName", BasicUser.class);
		query.setParameter("userName", userName);
		BasicUser basicUser=(BasicUser) query.getSingleResult();
		return basicUser;
	}
}

