package com.mocha.cooperate.widget.cards;

import java.util.List;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.vaadin.controller.ContentChangeEvent;
import com.coral.vaadin.widget.WidgetFactory;
import com.google.common.collect.Lists;
import com.mocha.cooperate.PresenterProperty;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.basic.dao.TimeLineDao;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.widget.ConfirmDialog;
import com.mocha.cooperate.widget.TodoProjectDisplayer;
import com.mocha.cooperate.widget.wrap.TodoProjectDisplayerWrap;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class TodoCard extends AbstractCard {

	protected ToDo todo;
	private NotifyLine notifyLine;
	private TimeLine timeLine;
	private VerticalLayout todoLayout = new VerticalLayout();
	protected Button editButton;
	protected MochaEventBus eventBus;
	
	public TodoCard() {}
	
	public TodoCard(TimeLine timeLine, MochaEventBus eventBus) {
		this.timeLine = timeLine;
		this.createUser = timeLine.getCreator();
		this.eventBus = eventBus;
		initTodoCard(timeLine.getTodo());
	}

	public TodoCard(NotifyLine notifyLine) {
		this.notifyLine = notifyLine;
		initTodoCard(notifyLine.getTodo());
	}
	
	public TodoCard(ToDo todo) {
		this.todo = todo;
		initTodoCard(todo);
	}
	
	public void initTodoCard(ToDo todo) {
		this.todo = todo;
		this.attachments = todo.getAttachments();
		this.comments = todo.getComments();
		if (todo.getComments() != null) {
			this.comments = todo.getComments();

		}
		this.addStyleName("todo-card");
	}

	@Override
	public Component buildInformationArea() {
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth(content_size);

		HorizontalLayout userInfo = buildUserInfoLabel(todo.getCreateTime());
		layout.addComponent(userInfo);

		TodoProjectDisplayerWrap projectDisplayerWrap = new TodoProjectDisplayerWrap(todo, getCurrentUser(), eventBus);
		TodoProjectDisplayer projectDisplayer = projectDisplayerWrap.getProjectDisplayer(); 
		projectDisplayer.setProjectWidth(SystemProperty.content_card_width, "630px", "600px");
		projectDisplayer.setNeedExpend(false);
		layout.addComponent(projectDisplayer);

		Layout attachmentLayout = buildAttachment();
		if(attachmentLayout != null) {
			layout.addComponent(attachmentLayout);
		}
		Layout replyInfo = buildReply();
		layout.addComponent(replyInfo);
		return layout;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(deleteButton)) {
			ConfirmDialog confirmDialog = new ConfirmDialog("Do you want to delete this Todo ?") {
				@Override
				public void confirm() {
					if(timeLine == null) {
						timeLine = timeLineService.queryTimelineByTodo(todo);
					}
					timeLineService.removeTimeLine(timeLine);
					Layout layout = (Layout) TodoCard.this.getParent();
					layout.removeComponent(TodoCard.this);
				}
				@Override
				public void cancel() {
				}
			};
			TodoCard.this.getWindow().addWindow(confirmDialog);
		}
		if(event.getButton().equals(replyButton)) {
			if(cardReply.isVisible()) {
				cardReply.setVisible(false);
			} else {
				cardReply.setVisible(true);
			}
		}
		if(CardReply.REPLY_BUTTON.equals(event.getButton().getData())) {
			Comment comment = cardReply.getValue();
			todo.getComments().add(comment);
			comment.setTodo(todo);
			todo = timeLineService.updateToDo(todo);
			cardReply.setComments(todo.getComments());
			cardReply.build();
		}
		if(event.getButton().equals(editButton)) {
			ContentChangeEvent changeEvent = new ContentChangeEvent();
			changeEvent.setPresenterName(PresenterProperty.TODO_EDIT);
			eventBus.put("todo", todo);
			eventBus.post(changeEvent);
		}
	}
	
	@Override
	public List<Button> getExtButtons() {
		List<Button> extButtons = Lists.newArrayList();
		Long currentUserId = ((BasicUser)getApplication().getUser()).getBasicUserId();
		if(createUser.getBasicUserId().equals(currentUserId) || todo.getAssginedUser().getBasicUserId().equals(currentUserId)) {
			editButton = WidgetFactory.createLink("Edit");
			editButton.setIcon(new ThemeResource("icons/change-icon.png"));
			editButton.addListener(this);
			extButtons.add(editButton);
		}
		return extButtons;
	}
	
	@Override
	public void refreshCard() {
		
	}
}
