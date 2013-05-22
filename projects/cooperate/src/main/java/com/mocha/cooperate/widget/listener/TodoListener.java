/**
 * 
 */
package com.mocha.cooperate.widget.listener;

import com.mocha.cooperate.model.SubToDoItem;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.widget.TodoProjectDisplayer;
import com.mocha.cooperate.widget.TodoProjectDisplayer.TodoItemDisplay;

/**
 * @author Coral
 *
 */
public interface TodoListener {

	public void newProject();
	public void showActivityTodo();
	public void showCompletedTodo();
}
