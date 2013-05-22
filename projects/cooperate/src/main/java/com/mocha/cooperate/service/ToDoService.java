/**
 * 
 */
package com.mocha.cooperate.service;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.common.collect.Lists;
import com.mocha.cooperate.basic.dao.ToDoDao;
import com.mocha.cooperate.model.ToDo;

/**
 * @author Coral
 *
 */
public class ToDoService {

	private ToDoDao toDoDao = SpringContextUtils.getBean(ToDoDao.class);
	
	public List<ToDo> loadActivityTodo(BasicUser basicUser) {
		List<ToDo> todoList = Lists.newArrayList();
		addToDoList(todoList, toDoDao.loadActivityTodo(basicUser));
		addToDoList(todoList, toDoDao.loadActivityItem(basicUser));
		return todoList;
	}
	
	public List<ToDo> loadCompletedTodo(BasicUser basicUser) {
		List<ToDo> todoList = Lists.newArrayList();
		addToDoList(todoList, toDoDao.loadCompletedTodo(basicUser));
		addToDoList(todoList, toDoDao.loadCompletedItem(basicUser));
		return todoList;
	}
	
	private void addToDoList(List<ToDo> todoList, List<ToDo> resultList) {
		for(ToDo todo : resultList) {
			boolean isExisted = false;
			for(ToDo existedTodo : todoList) {
				if(existedTodo.getID() == todo.getID()) {
					isExisted = true;
				}
			}
			if(!isExisted) {
				todoList.add(todo);
			}
		}
	}
}
