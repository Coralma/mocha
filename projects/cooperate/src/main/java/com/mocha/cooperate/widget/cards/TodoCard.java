package com.mocha.cooperate.widget.cards;

import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.widget.TodoProjectDisplayer;
import com.mocha.cooperate.widget.wrap.TodoProjectDisplayerWrap;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class TodoCard extends AbstractCard {

	private ToDo todo;
	private NotifyLine notifyLine;
	private TimeLine timeLine;
	private VerticalLayout todoLayout = new VerticalLayout();

	public TodoCard(TimeLine timeLine) {
		this.timeLine = timeLine;
		this.createUser = timeLine.getCreator();
		initTodoCard(timeLine.getTodo());
	}

	public TodoCard(NotifyLine notifyLine) {
		this.notifyLine = notifyLine;
		initTodoCard(notifyLine.getTodo());
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

		TodoProjectDisplayerWrap projectDisplayerWrap = new TodoProjectDisplayerWrap(todo, getCurrentUser());
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
			timeLineService.removeTimeLine(timeLine);
			Layout layout = (Layout) this.getParent();
			layout.removeComponent(this);
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
	}

	@Override
	public void refreshCard() {
		
	}

}
