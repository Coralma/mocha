package com.mocha.cooperate.basic.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.constant.RuntimeConstant;
import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;

/**
  * TimeLineDao is a auto Generated class. Please don't modify it.
  */
public class TimeLineDao extends BaseDao<TimeLine> {
	
	
	public Class<TimeLine> getEntityClass() {
		return TimeLine.class;
	}
	

	private int pageSize = RuntimeConstant.PAGING_SIZE;
	
	public List<TimeLine> loadTimeLine(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from TimeLine t order by t.timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}
	
	
	public List<TimeLine> loadTimeLineByUser(BasicUser basicUser, Date startDate, int page) {
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from TimeLine t where t.creator=:creator and t.createTime < :startDate order by t.timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("creator", basicUser);
		query.setParameter("startDate", startDate);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}

	
	public List<TimeLine> loadStatus(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from TimeLine t where t.status is not null order by t.timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}
	
	
	public List<TimeLine> loadStatusByUser(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from TimeLine t where t.status is not null and t.creator=:creator order by t.timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("creator", basicUser);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}

	
	public List<TimeLine> loadDiscuss(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from TimeLine t where t.discuss is not null order by t.timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}
	
	
	public List<TimeLine> loadDiscussByUser(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from TimeLine where t.discuss is not null and t.creator=:creator order by t.timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("creator", basicUser);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}

	
	public List<TimeLine> loadTodo(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from TimeLine t where t.todo is not null order by t.timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}
	
	
	public List<TimeLine> loadTodoByUser(BasicUser basicUser, int page) {
		int firstResult = page * pageSize;
		Query query = getEntityManager().createQuery("from TimeLine t where t.todo is not null and t.creator=:creator order by t.timeLineId desc",TimeLine.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter("creator", basicUser);
		List<TimeLine> timeLines = query.getResultList();
		return timeLines;
	}

	
	public TimeLine queryOldestData(BasicUser user) {
		Query query = getEntityManager().createQuery("from TimeLine t where t.creator=:creator order by t.timeLineId desc",TimeLine.class);
		query.setFirstResult(1);
		query.setMaxResults(1);
		query.setParameter("creator", user);
		List<TimeLine> timeLines = query.getResultList();
		if(timeLines.size() > 0) {
			return timeLines.get(0);
		}
		return null;
	}

	
	public TimeLine queryTimelineByTodo(ToDo todo) {
		Query query = getEntityManager().createQuery("from TimeLine t where t.todo=:todo",TimeLine.class);
		query.setParameter("todo", todo);
		List<TimeLine> timeLines = query.getResultList();
		if(timeLines.size() > 0) {
			return timeLines.get(0); 
		}
		return null;
	}

	
	public TimeLine queryTimelineByDiscuss(Discuss discuss) {
		Query query = getEntityManager().createQuery("from TimeLine t where t.discuss=:discuss",TimeLine.class);
		query.setParameter("discuss", discuss);
		List<TimeLine> timeLines = query.getResultList();
		if(timeLines.size() > 0) {
			return timeLines.get(0); 
		}
		return null;
	}

	
	public TimeLine queryTimelineByStatus(Status status) {
		Query query = getEntityManager().createQuery("from TimeLine t where t.status=:status",TimeLine.class);
		query.setParameter("status", status);
		List<TimeLine> timeLines = query.getResultList();
		if(timeLines.size() > 0) {
			return timeLines.get(0); 
		}
		return null;
	}
}

