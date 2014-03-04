/**
 * 
 */
package com.mocha.cooperate.restfulservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.ws.WebServiceException;

import org.springframework.stereotype.Service;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.common.collect.Lists;
import com.mocha.cooperate.basic.dao.DiscussDao;
import com.mocha.cooperate.basic.dao.SubToDoItemDao;
import com.mocha.cooperate.basic.dao.ToDoDao;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.SubToDoItem;
import com.mocha.cooperate.model.ToDo;
import com.mocha.service.model.MobileFeeds;
import com.mocha.service.model.UserFeeds;

/**
 * @author Coral
 * 
 */
// Use Spring IoC to create and manage this bean.
@Service("todoService")
@Path("/users")
public class RestFulServiceImpl implements RestFulService {

	private ToDoDao toDoDao = SpringContextUtils.getBean(ToDoDao.class);
	private DiscussDao discussDao = SpringContextUtils.getBean(DiscussDao.class);
	private SubToDoItemDao subItemDao = SpringContextUtils.getBean(SubToDoItemDao.class);

	public RestFulServiceImpl() {

	}

	@Override
	public List<ToDo> loadActivityTodo(BasicUser basicUser) {
		List<ToDo> todoList = Lists.newArrayList();
		addToDoList(todoList, toDoDao.loadActivityTodo(basicUser));
		addToDoList(todoList, toDoDao.loadActivityItem(basicUser));
		return todoList;
	}

	@Override
	public List<ToDo> loadCompletedTodo(BasicUser basicUser) {
		List<ToDo> todoList = Lists.newArrayList();
		addToDoList(todoList, toDoDao.loadCompletedTodo(basicUser));
		addToDoList(todoList, toDoDao.loadCompletedItem(basicUser));
		return todoList;
	}

	private void addToDoList(List<ToDo> todoList, List<ToDo> resultList) {
		for (ToDo todo : resultList) {
			boolean isExisted = false;
			for (ToDo existedTodo : todoList) {
				if (existedTodo.getID() == todo.getID()) {
					isExisted = true;
				}
			}
			if (!isExisted) {
				todoList.add(todo);
			}
		}
	}

	public void merge(ToDo todo) {
		toDoDao.merge(todo);
	}

	public void removeSubItem(SubToDoItem subItem) {
		subItemDao.remove(subItem.getID());
	}

	public static void main(String args[]) {
		try {
			JAXBContext context = JAXBContext.newInstance(com.coral.foundation.security.model.BasicUser.class);
		}
		catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	@GET
	@Path("/getToDo/{uuid}")
	// @Consumes({ "application/json" })
	@Produces("application/json")
	public List<ToDo> getFullToDos(String uuid) {
		List<ToDo> returnTodos = new ArrayList<ToDo>();
		returnTodos.addAll(toDoDao.loadAll());
		// JSONWithPadding returnJSONP = new JSONWithPadding(new GenericEntity<List<ToDo>>(returnTodos) {
		// }, callback);
		System.out.println("uuid is:" + uuid);
		return returnTodos;
	}

	@Override
	@GET
	@Path("/getMobileFeeds/{uuid}")
	@Produces("application/json")
	public MobileFeeds getMobileFeeds() throws WebServiceException {
		System.out.println("start to get the mobile feeds");
		MobileFeeds mobileFeeds = new MobileFeeds();
		List<UserFeeds> userFeeds = new ArrayList<UserFeeds>();
		List<ToDo> returnTodos = new ArrayList<ToDo>();
		returnTodos.addAll(toDoDao.loadAll());
		for (ToDo todo : returnTodos) {
			UserFeeds userFeed = new UserFeeds();
			userFeed.setTodo(todo);
			userFeeds.add(userFeed);
		}
		
		List<Discuss> returnDiscusses =new ArrayList<Discuss>();
		returnDiscusses.addAll(discussDao.loadAll());
		for (Discuss discuss : returnDiscusses) {
			UserFeeds userFeed = new UserFeeds();
			userFeed.setDiscuss(discuss);
			userFeeds.add(userFeed);
		}
		mobileFeeds.setUserFeeds(userFeeds);
		return mobileFeeds;
	}
}
