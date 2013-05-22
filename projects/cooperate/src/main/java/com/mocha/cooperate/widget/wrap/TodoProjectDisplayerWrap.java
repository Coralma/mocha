/**
 * 
 */
package com.mocha.cooperate.widget.wrap;

import java.util.Date;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.mocha.cooperate.basic.dao.SubToDoItemDao;
import com.mocha.cooperate.basic.dao.ToDoDao;
import com.mocha.cooperate.model.SubToDoItem;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.widget.TodoProjectDisplayer;
import com.mocha.cooperate.widget.TodoProjectDisplayer.TodoItemDisplay;
import com.mocha.cooperate.widget.listener.TodoProjectDisplayerListener;
import com.vaadin.ui.Label;

/**
 * @author Coral
 *
 */
public class TodoProjectDisplayerWrap {

	private ToDo toDo;
	private ToDoDao toDoDao = SpringContextUtils.getBean(ToDoDao.class);
	private SubToDoItemDao subToDoItemDao = SpringContextUtils.getBean(SubToDoItemDao.class);
	private BasicUser currentUser;
	private TodoProjectDisplayer projectDisplayer;
	
	public TodoProjectDisplayerWrap(ToDo toDo, BasicUser user) {
		this.toDo = toDo;
		this.currentUser = user;
		this.build();
	}
	
	public void build() {
		projectDisplayer = new TodoProjectDisplayer(toDo, currentUser);
		projectDisplayer.setListener(new TodoProjectDisplayerListener() {
			@Override
			public void todoProjectClick(TodoProjectDisplayer project) {
				ToDo todo = project.getToDo();
				Label titleLabel = project.getTitleContent();
				Boolean isChecked = (Boolean)project.getTitleBox().getValue();
				if(isChecked) {
					todo.setStatus(new Long(1));
//					titleLabel.setStyleName("todo-item-checked");
					todo.setFinishDate(new Date());
				} else {
					todo.setStatus(new Long(0));
//					titleLabel.setStyleName("todo-item-uncheck");
					todo.setFinishDate(null);
				}
				project.setToDoContent();
				toDoDao.persist(todo);
			}
			@Override
			public void todoSubItemClick(TodoItemDisplay subItem) {
				SubToDoItem subToDoItem = subItem.getSubItem();
				Label subItemLabel = subItem.getSubTaskContent();
				Boolean isChecked = (Boolean)subItem.getSubTaskCheck().getValue();
				if(isChecked) {
					subToDoItem.setStatus(new Long(1));
//					subItemLabel.setStyleName("todo-item-checked");
					subToDoItem.setFinishDate(new Date());
				} else {
					subToDoItem.setStatus(new Long(0));
//					subItemLabel.setStyleName("todo-item-uncheck");
					subToDoItem.setFinishDate(null);
				}
				subItem.setSubTaskContent();
				subToDoItemDao.persist(subToDoItem);
			}
		});
	}

	/**
	 * @return the projectDisplayer
	 */
	public TodoProjectDisplayer getProjectDisplayer() {
		return projectDisplayer;
	}

	/**
	 * @param projectDisplayer the projectDisplayer to set
	 */
	public void setProjectDisplayer(TodoProjectDisplayer projectDisplayer) {
		this.projectDisplayer = projectDisplayer;
	}
}
