package com.coral.foundation.security.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.StrUtils;

/**
  * BasicUserDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class BasicUserDaoImpl extends JpaDao<BasicUser> implements BasicUserDao {
	
	Logger log=LoggerFactory.getLogger(BasicUserDaoImpl.class);
	public BasicUserDaoImpl() {
		super();
		log.debug(""+BasicUserDaoImpl.class);
	}
	
	private int pageSize = RuntimeConstant.PAGING_SIZE;

	@Override
	public List<BasicUser> findActiveUsers(int page) {
		//FIXME filte by status
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from BasicUser order by basicUserId desc",BasicUser.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<BasicUser> basicUsers = query.getResultList();
		return basicUsers;
	}

	@Override
	public List<BasicUser> findInActiveUsers(int page) {
		//FIXME filte as status
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from BasicUser order by basicUserId desc",BasicUser.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<BasicUser> basicUsers = query.getResultList();
		return basicUsers;
	}
	
	public List<BasicUser> fuzzyUserSearch(String condition) {
		Query query = entityManager.createQuery("from BasicUser where realName like :condition order by basicUserId desc", BasicUser.class);
		query.setParameter("condition", StrUtils.like(condition));
		List<BasicUser> basicUsers = query.getResultList();
		return basicUsers;
	}
}

