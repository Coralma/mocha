package com.mocha.cooperate.help;

import com.vaadin.terminal.ThemeResource;

public class ForumGuide extends AbstractGuide implements IGuidePanel {

	public ForumGuide() {
		super();
	}
	
	public void build() {
		this.addComponent(createTitle("Forum"));
		this.addComponent(createHelpText("There are three main component for this feature: Notification,Question,Discussion. Basically, we combine these three different things is all of these features are focus on the team conversations."));
		this.addComponent(createHelpImage(new ThemeResource("helps/gs-forum.jpg")));
	}
}
