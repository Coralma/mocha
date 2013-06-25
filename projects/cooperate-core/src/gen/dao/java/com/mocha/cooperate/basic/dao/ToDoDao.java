package com.mocha.cooperate.basic.dao;
import java.util.List;
import com.coral.foundation.jpa.Dao;
import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.*;

/**
  * ToDoDao is a auto Generated class. Please don't modify it.
  * @author Coral
  */
public interface ToDoDao extends Dao<ToDo> {
	
	public List<ToDo> loadActivityTodo(BasicUser basicUser);
	public List<ToDo> loadActivityItem(BasicUser basicUser);
	
	public List<ToDo> loadCompletedTodo(BasicUser basicUser);
	public List<ToDo> loadCompletedItem(BasicUser basicUser);
}

