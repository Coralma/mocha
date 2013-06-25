package com.mocha.cooperate.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.jpa.impl.JpaDao;
import com.mocha.cooperate.basic.dao.DiscussDao;
import com.mocha.cooperate.model.Discuss;

/**
  * DiscussDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class DiscussDaoImpl extends JpaDao<Discuss> implements DiscussDao {
	
	
	Logger log=LoggerFactory.getLogger(DiscussDaoImpl.class);
	private int pageSize = RuntimeConstant.PAGING_SIZE;
	
	public DiscussDaoImpl() {
		super();
		log.debug(""+DiscussDaoImpl.class);
	}
	
	@Override
	public List<Discuss> queryLastThreeNotification() {
		return queryLastThreeTopicByType("2");
	}
	
	@Override
	public List<Discuss> queryLastThreeQuestion() {
		return queryLastThreeTopicByType("1");
	}
	
	@Override
	public List<Discuss> queryLastThreeDiscuss() {
		return queryLastThreeTopicByType("0");
	}
	
	private List<Discuss> queryLastThreeTopicByType(String type) {
		Query query = entityManager.createQuery("from Discuss where status = :status order by discussId desc",Discuss.class);
		query.setParameter("status", type);
		query.setMaxResults(3);
		List<Discuss> discusses = query.getResultList();
		return discusses;
	}
	
	public List<Discuss> queryTopic(String type, int page) {
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from Discuss where status = :status order by discussId desc",Discuss.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("status", type);
		List<Discuss> discusses = query.getResultList();
		return discusses;
	}
}

