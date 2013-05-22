package com.mocha.cooperate.widget.listener;

import com.mocha.cooperate.model.SubToDoItem;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.widget.TodoProjectDisplayer;
import com.mocha.cooperate.widget.TodoProjectDisplayer.TodoItemDisplay;

public interface TodoProjectDisplayerListener {

	public void todoProjectClick(TodoProjectDisplayer project);
	public void todoSubItemClick(TodoItemDisplay subItem);
}
