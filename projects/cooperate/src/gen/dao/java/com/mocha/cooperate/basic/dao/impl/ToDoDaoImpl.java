package com.mocha.cooperate.basic.dao.impl;
import java.util.List;

import javax.persistence.Query;

import com.mocha.cooperate.basic.dao.*;
import com.mocha.cooperate.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import com.coral.foundation.security.model.BasicUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * ToDoDaoImpl is a auto Generated class. Please don't modify it.
  * @author Coral
  */
@SuppressWarnings("unchecked")
public class ToDoDaoImpl extends JpaDao<ToDo> implements ToDoDao {
	
	Logger log=LoggerFactory.getLogger(ToDoDaoImpl.class);
	public ToDoDaoImpl() {
		super();
		log.debug(""+ToDoDaoImpl.class);
	}
	
	@Override
	public List<ToDo> loadActivityTodo(BasicUser basicUser) {
		Query query = entityManager.createQuery("from ToDo where status = 0 and assginedUser =:assginedUser order by toDoId desc",ToDo.class);
		query.setParameter("assginedUser", basicUser);
		List<ToDo> todos = query.getResultList();
		return todos;
	}
	
	@Override
	public List<ToDo> loadActivityItem(BasicUser basicUser) {
		Query query = entityManager.createQuery("SELECT DISTINCT t from ToDo t where t.status = 0 and EXISTS (SELECT i FROM t.subToDoItems i WHERE i.assginedUser =:assginedUser) order by t.toDoId desc",ToDo.class);
		query.setParameter("assginedUser", basicUser);
		List<ToDo> todos = query.getResultList();
		return todos;
	}
	
	@Override
	public List<ToDo> loadCompletedTodo(BasicUser basicUser) {
		Query query = entityManager.createQuery("from ToDo where status = 1 and assginedUser =:assginedUser order by toDoId desc",ToDo.class);
		query.setParameter("assginedUser", basicUser);
		List<ToDo> todos = query.getResultList();
		return todos;
	}
	
	@Override
	public List<ToDo> loadCompletedItem(BasicUser basicUser) {
		Query query = entityManager.createQuery("SELECT DISTINCT t from ToDo t where t.status = 1 and EXISTS (SELECT i FROM t.subToDoItems i WHERE i.assginedUser =:assginedUser) order by t.toDoId desc",ToDo.class);
		query.setParameter("assginedUser", basicUser);
		List<ToDo> todos = query.getResultList();
		return todos;
	}
}

