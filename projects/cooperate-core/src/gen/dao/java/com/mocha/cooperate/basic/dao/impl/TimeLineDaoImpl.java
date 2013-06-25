package com.mocha.cooperate.basic.dao.impl;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.security.model.BasicUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * TimeLineDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public class TimeLineDaoImpl extends JpaDao<TimeLine> implements TimeLineDao {
	
	Logger log=LoggerFactory.getLogger(TimeLineDaoImpl.class);
	public TimeLineDaoImpl() {
		super();
		log.debug(""+TimeLineDaoImpl.class);
	}
	
	private int pageSize = RuntimeConstant.PAGING_SIZE;
	
	public List<TimeLine> loadTimeLine(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from TimeLine order by timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}
	
	@Override
	public List<TimeLine> loadTimeLineByUser(BasicUser basicUser, Date startDate, int page) {
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from TimeLine where creator=:creator and createTime < :startDate order by timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("creator", basicUser);
		query.setParameter("startDate", startDate);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}

	@Override
	public List<TimeLine> loadStatus(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from TimeLine where status is not null order by timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}
	
	@Override
	public List<TimeLine> loadStatusByUser(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from TimeLine where status is not null and creator=:creator order by timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("creator", basicUser);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}

	@Override
	public List<TimeLine> loadDiscuss(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from TimeLine where discuss is not null order by timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}
	
	@Override
	public List<TimeLine> loadDiscussByUser(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from TimeLine where discuss is not null and creator=:creator order by timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("creator", basicUser);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}

	@Override
	public List<TimeLine> loadTodo(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from TimeLine where todo is not null order by timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}
	
	@Override
	public List<TimeLine> loadTodoByUser(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = entityManager.createQuery("from TimeLine where todo is not null and creator=:creator order by timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("creator", basicUser);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}

	@Override
	public TimeLine queryOldestData(BasicUser user) {
		Query query = entityManager.createQuery("from TimeLine where creator=:creator order by timeLineId desc",TimeLine.class);
		query.setFirstResult(1);
		query.setMaxResults(1);
		query.setParameter("creator", user);
		List<TimeLine> timeLines = query.getResultList();
		if(timeLines.size() > 0) {
			return timeLines.get(0);
		}
		return null;
	}

	@Override
	public TimeLine queryTimelineByTodo(ToDo todo) {
		Query query = entityManager.createQuery("from TimeLine where todo=:todo",TimeLine.class);
		query.setParameter("todo", todo);
		List<TimeLine> timeLines = query.getResultList();
		if(timeLines.size() > 0) {
			return timeLines.get(0); 
		}
		return null;
	}

	@Override
	public TimeLine queryTimelineByDiscuss(Discuss discuss) {
		Query query = entityManager.createQuery("from TimeLine where discuss=:discuss",TimeLine.class);
		query.setParameter("discuss", discuss);
		List<TimeLine> timeLines = query.getResultList();
		if(timeLines.size() > 0) {
			return timeLines.get(0); 
		}
		return null;
	}

	@Override
	public TimeLine queryTimelineByStatus(Status status) {
		Query query = entityManager.createQuery("from TimeLine where status=:status",TimeLine.class);
		query.setParameter("status", status);
		List<TimeLine> timeLines = query.getResultList();
		if(timeLines.size() > 0) {
			return timeLines.get(0); 
		}
		return null;
	}

//	@Override
//	public List<TimeLine> loadActivityTodo(BasicUser basicUser) {
//		Query query = entityManager.createQuery("from TimeLine where todo is not null and todo.status = 0 order by timeLineId desc",TimeLine.class);
//		List<TimeLine> timeLines = query.getResultList();
//		return timeLines;
//	}
//	
//	@Override
//	public List<TimeLine> loadCompletedTodo(BasicUser basicUser) {
//		Query query = entityManager.createQuery("from TimeLine where todo is not null and todo.status = 1 order by timeLineId desc",TimeLine.class);
//		List<TimeLine> timeLines = query.getResultList();
//		return timeLines;
//	}
}

