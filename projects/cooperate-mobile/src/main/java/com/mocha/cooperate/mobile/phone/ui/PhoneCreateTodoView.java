package com.mocha.cooperate.mobile.phone.ui;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.UserComboBox;
import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class PhoneCreateTodoView extends NavigationView implements MobileView, ClickListener {
	
	private CssLayout taskPanelLayout = new CssLayout();
	private Button subTaskButton = new Button("Add Sub-Task");
	private Button postButton = new Button("Post New ToDo");
	private BasicUser currentUser;
	
	public PhoneCreateTodoView(BasicUser currentUser) {
		this.currentUser = currentUser;
		this.build();
	}
	
	public void build() {
		setCaption("New ToDo");
		
		CssLayout newTodoLayout = new CssLayout();
		newTodoLayout.setSizeFull();
		newTodoLayout.setMargin(true);
		
		taskPanelLayout.setWidth("100%");
		
		TaskPanel mainTaskPanel = buildTaskPanel(true);
		taskPanelLayout.addComponent(mainTaskPanel);
		newTodoLayout.addComponent(taskPanelLayout);
		
		subTaskButton.addListener(this);
		
		VerticalComponentGroup buttonGroup = new VerticalComponentGroup(); 
		buttonGroup.addComponent(subTaskButton);
		buttonGroup.addComponent(postButton);
		newTodoLayout.addComponent(buttonGroup);
		
		setContent(newTodoLayout);
	}
	
	public TaskPanel buildTaskPanel(boolean isMainTask) {
		TaskPanel taskPanel = new TaskPanel(isMainTask);
		return taskPanel;
	}
	
	public class TaskPanel extends VerticalComponentGroup {
		
		private boolean mainTask;
		private FormLayout formLayout = new FormLayout();
		private TextField taskField = new TextField();
		private DateField finishField = new DateField("Expiry Date");
		private UserComboBox userCombo = WidgetFactory.createUserCombo(currentUser); 
		
		public TaskPanel(boolean mainTask) {
			this.mainTask = mainTask;
			this.setWidth("100%");
			build();
		}
		
		public void build() {
			formLayout.setMargin(false);
	        formLayout.setSpacing(false);
	        Form form = new Form(formLayout);
	        form.setWriteThrough(true);
	        this.addComponent(form);
			
			taskField.setWidth("100%");
			taskField.setCaption("Task");
			formLayout.addComponent(taskField);
			
			finishField.setWidth("100%");
			finishField.setResolution(DateField.RESOLUTION_DAY);
			formLayout.addComponent(finishField);
			
			userCombo.setWidth("100%");
			userCombo.setCaption("Assigner");
			formLayout.addComponent(userCombo);
			
			if(!mainTask) {
				taskField.setCaption("Sub-Task");
				Button cancelButton = new Button("Cancel This Sub-Task");
				cancelButton.setData(this);
				cancelButton.addListener(PhoneCreateTodoView.this);
				this.addComponent(cancelButton);
			}
		}
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		Button clickedButton = event.getButton();
		Object taskPanel = clickedButton.getData();
		if(subTaskButton.equals(clickedButton)) {
			taskPanelLayout.addComponent(buildTaskPanel(false));
		}
		if(taskPanel != null && taskPanel instanceof TaskPanel) {
			taskPanelLayout.removeComponent((TaskPanel)taskPanel);
		}
	}

}
