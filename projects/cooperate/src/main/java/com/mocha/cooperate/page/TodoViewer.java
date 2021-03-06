package com.mocha.cooperate.page;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.widget.TodoProjectDisplayer;
import com.mocha.cooperate.widget.listener.TodoListener;
import com.mocha.cooperate.widget.wrap.TodoProjectDisplayerWrap;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class TodoViewer extends CommonViewer implements Viewer, ClickListener {

	private List<ToDo> todos;
	private TodoListener listener;
	private VerticalLayout todoListPanel = new VerticalLayout();
	private MochaEventBus eventBus;
	
	public TodoViewer(List<ToDo> todos, MochaEventBus eventBus) {
		this.todos = todos;
		this.eventBus = eventBus;
		this.addStyleName("todo-viewer");
		this.setSpacing(true);
		this.setWidth(SystemProperty.content_page_width);
		this.todoListPanel.setWidth(SystemProperty.content_page_width);
	}
	
	public void attach() {
		super.attach();
		ToolbarAdvance toolbar = new ToolbarAdvance();
		toolbar.setWidth("768px");
		
//		Button backLink = WidgetFactory.createLink(message.getString("cooperate.todo.backtoTaskList"),"back",this);
//		backLink.setIcon(new ThemeResource("icons/back.png"));
		toolbar.addLeftComponent(WidgetFactory.createLink(message.getString("cooperate.todo.allmytask"),"allTask",this));
		toolbar.addLeftComponent(WidgetFactory.createLink(message.getString("cooperate.todo.completedtask"),"completedTask",this));
		
		toolbar.addRightComponent(WidgetFactory.createButton(message.getString("cooperate.todo.newproject"),"newProject",this));
		
//		toolbar.addLeftLink("cooperate.todo.allmytask", "allTask", this);
//		toolbar.addLeftLink("cooperate.todo.completedtask", "completedTask", this);
//		toolbar.addRightLink("cooperate.todo.newproject", "newProject", Toolbar.BUTTON, this);
		this.addComponent(toolbar);
		this.addComponent(todoListPanel);
		this.buildTodoListPanel();
	}
	
	public void buildTodoListPanel() {
		todoListPanel.removeAllComponents();
		for(int i=0; i<todos.size(); i++) {
			ToDo todo = todos.get(i);
			TodoProjectDisplayerWrap displayerWrap = new TodoProjectDisplayerWrap(todo, (BasicUser)getApplication().getUser(), eventBus);
			TodoProjectDisplayer displayer = displayerWrap.getProjectDisplayer();
			displayer.setListDisplay(true);
			if(i % 2 == 1) {
				displayer.setSpecialRowStyle(true);
			}
			todoListPanel.addComponent(displayer);
		}
	}

	@Override
	public String getViewerTitle() {
		return "cooperate.todo.title";
	}

	@Override
	public void buttonClick(ClickEvent event) {
		String btnData = (String)event.getButton().getData();
		if("newProject".equals(btnData)) {
			listener.newProject();
		} else if("allTask".equals(btnData)) {
			listener.showActivityTodo();
		} else if("completedTask".equals(btnData)) {
			listener.showCompletedTodo();
		}
	}

	/**
	 * @return the listener
	 */
	public TodoListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(TodoListener listener) {
		this.listener = listener;
	}

	/**
	 * @return the todos
	 */
	public List<ToDo> getTodos() {
		return todos;
	}

	/**
	 * @param todos the todos to set
	 */
	public void setTodos(List<ToDo> todos) {
		this.todos = todos;
	}

}
