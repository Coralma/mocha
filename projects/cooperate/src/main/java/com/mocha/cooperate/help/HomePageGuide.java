package com.mocha.cooperate.help;

import com.vaadin.terminal.ThemeResource;

public class HomePageGuide extends AbstractGuide implements IGuidePanel {

	public HomePageGuide() {
		super();
	}
	
	public void build() {
		this.addComponent(createTitle("Welcome to Mocha Platform"));
		this.addComponent(createHelpText("Mocha Platform makes it easier to communicate, collaborate and software operation. By unifying all your workflows, by breaking-barriers of hierarchy, task, topic discuss and file management, you can now transform the way you communicate and collaborate with your colleagues."));
		this.addComponent(createHelpImage(new ThemeResource("helps/gs-homepage.jpg")));
		
//		Label title = new Label("Welcome to Mocha Platform");
//		title.addStyleName("gs-title");
//		this.addComponent(title);
//		
//		Label helpText = new Label("Mocha Platform makes it easier to communicate, collaborate and software operation. By unifying all your workflows, by breaking-barriers of hierarchy, task, topic discuss and file management, you can now transform the way you communicate and collaborate with your colleagues.");
//		helpText.addStyleName("gs-helptext");
//		this.addComponent(helpText);
//		
//		Label homepage = new Label();
//		homepage.setIcon(new ThemeResource("helps/gs-homepage.jpg"));
//		this.addComponent(homepage);
	}
}
