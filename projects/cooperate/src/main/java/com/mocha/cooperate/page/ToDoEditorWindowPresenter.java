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
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;
/**
 * 
 * @author Coral
 *
 */
public class ToDoEditorWindowPresenter extends CommonPresenter implements Presenter {
	
	private TimeLineService timeLineService = new TimeLineService();
	private TodoWindow todoWindow;
	
	public ToDoEditorWindowPresenter(MochaEventBus eventBus) {
		this.eventBus = eventBus;
		ToDo todo = (ToDo) eventBus.getContext().get("todo");
		if(todo == null) {
			todo = new ToDo();
		}
		eventBus.resetContext();
		ToDoEditorViewer toDoEditorViewer = new ToDoEditorViewer(todo, eventBus.getUser()) {
			@Override
			public String getViewerTitle() {
				return null;
			}
		};
		toDoEditorViewer.getToolbar().setVisible(false);
		this.viewer = toDoEditorViewer;
	}
	
	public Window getWindow() {
		todoWindow = new TodoWindow("New Todo");
		todoWindow.addComponent(viewer);
		return todoWindow;
	}
	
	public class TodoWindow extends Window {
		public TodoWindow(String caption) {
			this.setCaption(caption);
			this.center();
			this.addStyleName("mocha-app");
			this.setWidth("805px");
			this.setClosable(true);
			this.setResizeLazy(true);
			this.setResizable(false);
			this.setModal(true);
			this.addStyleName(Reindeer.WINDOW_LIGHT);
		}
		
		public void closeWindow() {
			this.close();
		}
	}

	@Override
	public void bind() {
		ToDoEditorViewer toDoEditorViewer = (ToDoEditorViewer)viewer;
		toDoEditorViewer.setListener(new TodoEditorListener() {
			
			@Override
			public void saveTodo(ToDo todo, BasicUser creator, Set<BasicUser> notifiedUsers, List<Attachment> attachments) {
				timeLineService.saveToDo(todo, creator, notifiedUsers, attachments);
				todoWindow.closeWindow();
			}
			
			@Override
			public void cancelAndBack() {
				todoWindow.closeWindow();
//				backToToDo();
			}
			
//			private void backToToDo() {
//				ContentChangeEvent changeEvent = new ContentChangeEvent();
//				changeEvent.setPresenterName(PresenterProperty.TODO);
//				eventBus.post(changeEvent);
//			}
		});
	}

	@Override
	public String getPresenterName() {
		return null;
	}

}
