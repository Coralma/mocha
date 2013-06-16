/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.vaadin.controller.ContentChangeEvent;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.google.common.collect.Lists;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.model.SubToDoItem;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.cooperate.service.ToDoService;
import com.mocha.cooperate.widget.TodoProjectDisplayer;
import com.mocha.cooperate.widget.TodoProjectDisplayer.TodoItemDisplay;
import com.mocha.cooperate.widget.listener.TodoListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;

/**
 * @author Coral
 *
 */
public class TodoPresenter extends CommonPresenter implements Presenter {
	
	private ToDoService todoService = new ToDoService();
	
	public TodoPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		List<ToDo> todos = todoService.loadActivityTodo(eventBus.getUser());
//		List<ToDo> todos = Lists.newArrayList();
//		for(TimeLine timeline : timelines) {
//			if (timeline.getTodo() != null) {
//				todos.add(timeline.getTodo());
//			}
//		}
		this.viewer = new TodoViewer(todos, eventBus);
	}

	@Override
	public void bind() {
		final TodoViewer todoViewer = (TodoViewer)viewer;
		todoViewer.setListener(new TodoListener() {
			@Override
			public void newProject() {
				ContentChangeEvent changeEvent = new ContentChangeEvent();
				changeEvent.setPresenterName(PresenterProperty.TODO_EDIT);
				eventBus.post(changeEvent);
			}

			@Override
			public void showActivityTodo() {
				List<ToDo> activityTodos = todoService.loadActivityTodo(eventBus.getUser());
				todoViewer.setTodos(activityTodos);
				todoViewer.buildTodoListPanel();
			}

			@Override
			public void showCompletedTodo() {
				List<ToDo> completedTodos = todoService.loadCompletedTodo(eventBus.getUser());
				todoViewer.setTodos(completedTodos);
				todoViewer.buildTodoListPanel();
			}
		});
	}

	@Override
	public String getPresenterName() {
		return PresenterProperty.TODO;
	}
}
