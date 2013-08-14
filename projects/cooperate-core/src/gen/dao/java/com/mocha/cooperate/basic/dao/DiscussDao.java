package com.mocha.cooperate.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.persistence.BaseDao;
import com.mocha.cooperate.model.Discuss;

/**
  * DiscussDao is a auto Generated class. Please don't modify it.
  */
public class DiscussDao extends BaseDao<Discuss> {
	
	@Override
	public Class<Discuss> getEntityClass() {
		return Discuss.class;
	}
	
	private int pageSize = RuntimeConstant.PAGING_SIZE;

	public List<Discuss> queryLastThreeNotification() {
		return queryLastThreeTopicByType("2");
	}
	
	
	public List<Discuss> queryLastThreeQuestion() {
		return queryLastThreeTopicByType("1");
	}
	
	
	public List<Discuss> queryLastThreeDiscuss() {
		return queryLastThreeTopicByType("0");
	}
	
	private List<Discuss> queryLastThreeTopicByType(String type) {
		Query query = getEntityManager().createQuery("from Discuss d where d.status = :status order by d.discussId desc",Discuss.class);
		query.setParameter("status", type);
		query.setMaxResults(3);
		List<Discuss> discusses = query.getResultList();
		return discusses;
	}
	
	public List<Discuss> queryTopic(String type, int page) {
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from Discuss d where d.status = :status order by d.discussId desc",Discuss.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("status", type);
		List<Discuss> discusses = query.getResultList();
		return discusses;
	}
}

