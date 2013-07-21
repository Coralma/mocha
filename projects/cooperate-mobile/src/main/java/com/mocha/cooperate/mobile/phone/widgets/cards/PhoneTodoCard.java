package com.mocha.cooperate.mobile.phone.widgets.cards;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.google.common.collect.Lists;
import com.mocha.cooperate.model.SubToDoItem;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.service.ToDoService;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PhoneTodoCard extends AbstractPhoneCard implements ClickListener {
	
	private BasicUser currentUser;
	private Switch mainSwitch;
	private List<Switch> subSwitchs = Lists.newArrayList();
	private ToDoService toDoService = new ToDoService();
	private ToDo todo;
	
	public PhoneTodoCard(ToDo todo, BasicUser currentUser) {
		this.currentUser = currentUser;
		this.todo = todo;
		super.createUser = todo.getCreator();
	}
	
	public void attach() {
		HorizontalLayout headLayout = buildCardHead(todo.getCreateTime(),todo.getComments().size());
		this.addComponent(headLayout);
		
		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setMargin(false, true, true, true);
		
		HorizontalLayout todoLayout = buildSelectLayout(todo, null);
		todoLayout.addStyleName("topic-title");
		contentLayout.addComponent(todoLayout);
		
		for(SubToDoItem item : todo.getSubToDoItems()) {
			HorizontalLayout subItemLayout = buildSelectLayout(todo, item);
			contentLayout.addComponent(subItemLayout);
		}
		this.addComponent(contentLayout);
		
		super.addDetailButtonListener(todo);
	}
	
	public HorizontalLayout buildSelectLayout(ToDo todo, SubToDoItem item) {
		Object data = todo;
		String content = "";
		if(item != null) {
			data = item;
			content = item.getContent();
		} else if(todo != null) {
			content = todo.getName();
		}
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);
		// switch box
		Switch switchBox = new Switch();
		switchBox.setData(data);
		switchBox.setImmediate(true);
		switchBox.addListener(this);
		layout.addComponent(switchBox);
		layout.setExpandRatio(switchBox, 1.0f);
		
		// label
		Label contentLabel = new Label(content, Label.CONTENT_XHTML);
		layout.addComponent(contentLabel);
		layout.setExpandRatio(contentLabel, 100.0f);
		layout.setComponentAlignment(contentLabel, Alignment.MIDDLE_LEFT);
		
		// add logic of switch and label
		if(item != null) {
			subSwitchs.add(switchBox);
			// check if this box is enable or not
			if(isSameUser(currentUser, todo.getAssginedUser()) || isSameUser(currentUser, item.getAssginedUser())) {
				switchBox.setEnabled(true);
			} else {
				switchBox.setEnabled(false);
			}
			switchBox.setValue(item.getStatus() == 1);
			contentLabel.setStyleName("todo-card-content");
		} else {
			mainSwitch = switchBox;
			// check if this box is enable or not
			if(isSameUser(currentUser, todo.getAssginedUser())) {
				switchBox.setEnabled(true);
			} else {
				switchBox.setEnabled(false);
			}
			switchBox.setValue(todo.getStatus() == 1);
			contentLabel.setStyleName("todo-card-title");			
		}
		return layout;
	}
	
	public boolean isSameUser(BasicUser sourceUser, BasicUser targetUser) {
		if(sourceUser.getID().equals(targetUser.getID())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Switch switchBox = (Switch)event.getButton();
		Boolean mainValue = (Boolean)mainSwitch.getValue();
		ToDo todo = (ToDo)mainSwitch.getData();
		if(switchBox.equals(mainSwitch)) {
			Long status = mainValue? Long.valueOf(1) : Long.valueOf(0);
			todo.setStatus(status);
			for(Switch subSwitch : subSwitchs) {
				SubToDoItem item = (SubToDoItem)subSwitch.getData();
				item.setStatus(status);
				subSwitch.setValue(mainValue);
			}
		} else {
			if(mainValue) {
				todo.setStatus(Long.valueOf(0));
				mainSwitch.setValue(false);
			}
			Boolean itemValue = (Boolean)switchBox.getValue();
			Long status = itemValue? Long.valueOf(1) : Long.valueOf(0);switchBox.getValue();
			SubToDoItem item = (SubToDoItem)switchBox.getData();
			item.setStatus(status);
		}
		toDoService.merge(todo);
	}

}
