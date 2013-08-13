package com.mocha.cooperate.basic.dao;

import java.util.List;

import javax.persistence.Query;

import com.coral.foundation.persistence.BaseDao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.ToDo;

/**
  * ToDoDao is a auto Generated class. Please don't modify it.
  */
public class ToDoDao extends BaseDao<ToDo> {
	
	@Override
	public Class<ToDo> getEntityClass() {
		return ToDo.class;
	}
	
	
	public List<ToDo> loadActivityTodo(BasicUser basicUser) {
		Query query = getEntityManager().createQuery("from ToDo t where t.status = 0 and t.assginedUser =:assginedUser order by t.toDoId desc",ToDo.class);
		query.setParameter("assginedUser", basicUser);
		List<ToDo> todos = query.getResultList();
		return todos;
	}
	
	
	public List<ToDo> loadActivityItem(BasicUser basicUser) {
		Query query = getEntityManager().createQuery("SELECT DISTINCT t from ToDo t where t.status = 0 and EXISTS (SELECT i FROM t.subToDoItems i WHERE i.assginedUser =:assginedUser) order by t.toDoId desc",ToDo.class);
		query.setParameter("assginedUser", basicUser);
		List<ToDo> todos = query.getResultList();
		return todos;
	}
	
	
	public List<ToDo> loadCompletedTodo(BasicUser basicUser) {
		Query query = getEntityManager().createQuery("from ToDo t where t.status = 1 and t.assginedUser =:assginedUser order by t.toDoId desc",ToDo.class);
		query.setParameter("assginedUser", basicUser);
		List<ToDo> todos = query.getResultList();
		return todos;
	}
	
	
	public List<ToDo> loadCompletedItem(BasicUser basicUser) {
		Query query = getEntityManager().createQuery("SELECT DISTINCT t from ToDo t where t.status = 1 and EXISTS (SELECT i FROM t.subToDoItems i WHERE i.assginedUser =:assginedUser) order by t.toDoId desc",ToDo.class);
		query.setParameter("assginedUser", basicUser);
		List<ToDo> todos = query.getResultList();
		return todos;
	}
}

