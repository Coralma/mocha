/**
 * 
 */
package com.mocha.cooperate.widget;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 * @deprecated
 */
public class TodoListWidget extends VerticalLayout {

	private VerticalLayout layout = new VerticalLayout();
	
	public TodoListWidget() {
		this.addStyleName("todo-widget");
		this.setMargin(true, false, false, false);
		this.setSpacing(true);
		layout.setSpacing(true);
		build();
	}
	
	public void build() {
		final TextField todoTitle = new TextField();
		todoTitle.addStyleName("textfield-title");
		todoTitle.setHeight("30px");
		todoTitle.setWidth("100%");
		todoTitle.setInputPrompt("To-do-list title");
		addComponent(todoTitle);
		
		CheckBoxTextField checkBoxTextField = new CheckBoxTextField();
		layout.addComponent(checkBoxTextField);
		addComponent(layout);
		
		FunctionButtonLayout buttonLayout = new FunctionButtonLayout();
		final Button saveButton = new Button("Save");
		final Button addButton = new Button("Add");
		final Button cancelButton = new Button("Cancel");
		saveButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				todoTitle.setReadOnly(true);
				saveButton.setVisible(false);
				addButton.setVisible(true);
			}
		});
		
		addButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				addTodo();
			}
		});
		addButton.setVisible(false);
		buttonLayout.addButton(saveButton);
		buttonLayout.addButton(addButton);
		buttonLayout.addButton(cancelButton);
		addComponent(buttonLayout);
	}
	
	public void addTodo() {
		CheckBoxTextField checkBoxTextField = new CheckBoxTextField();
		layout.addComponent(checkBoxTextField);
	}
}
