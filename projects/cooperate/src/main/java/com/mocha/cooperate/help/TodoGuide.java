package com.mocha.cooperate.help;

import com.vaadin.terminal.ThemeResource;

public class TodoGuide extends AbstractGuide implements IGuidePanel {

	public TodoGuide() {
		super();
	}
	
	public void build() {
		this.addComponent(createTitle("TODO"));
		this.addComponent(createHelpText("Get Things Done (GTD) in our todo features. Easy to define the cooperation tasks for different colleagues. It makes team work intuitively clear and easy to manage the work project."));
		this.addComponent(createHelpImage(new ThemeResource("helps/gs-todo.jpg")));
	}
}
