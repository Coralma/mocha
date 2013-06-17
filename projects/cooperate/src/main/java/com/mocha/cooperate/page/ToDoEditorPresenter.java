package com.mocha.cooperate.page;

import java.util.List;
import java.util.Set;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.controller.ContentChangeEvent;
import com.coral.vaadin.controller.Presenter;
import com.coral.vaadin.widget.view.CommonPresenter;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.page.event.TodoEditorListener;
import com.mocha.cooperate.service.TimeLineService;
/**
 * 
 * @author Coral
 *
 */
public class ToDoEditorPresenter extends CommonPresenter implements Presenter {
	
	private TimeLineService timeLineService = new TimeLineService();
	
	public ToDoEditorPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		ToDo todo = (ToDo) eventBus.getContext().get("todo");
		if(todo == null) {
			todo = new ToDo();
		}
		eventBus.resetContext();
		this.viewer = new ToDoEditorViewer(todo, eventBus.getUser());
	}

	@Override
	public void bind() {
		ToDoEditorViewer toDoEditorViewer = (ToDoEditorViewer)viewer;
		toDoEditorViewer.setListener(new TodoEditorListener() {
			
			@Override
			public void saveTodo(ToDo todo, BasicUser creator, Set<BasicUser> notifiedUsers, List<Attachment> attachments) {
				timeLineService.saveToDo(todo, creator, notifiedUsers, attachments);
				backToToDo();
			}
			
			@Override
			public void cancelAndBack() {
				backToToDo();
			}
			
			private void backToToDo() {
				ContentChangeEvent changeEvent = new ContentChangeEvent();
				changeEvent.setPresenterName(PresenterProperty.TODO);
				eventBus.post(changeEvent);
			}
		});
	}

	@Override
	public String getPresenterName() {
		return null;
	}

}
