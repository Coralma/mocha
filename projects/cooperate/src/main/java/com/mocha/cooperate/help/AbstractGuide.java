package com.mocha.cooperate.help;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractGuide extends VerticalLayout {

	public AbstractGuide() {
		this.setMargin(true);
		this.setWidth("800px");
		this.setHeight("500px");
		build();
	}
	
	public abstract void build();
	
	public Label createTitle(String title) {
		Label titleLabel = new Label(title);
		titleLabel.addStyleName("gs-title");
		return titleLabel;
	}
	
	public Label createHelpText(String content) {
		Label helpText = new Label(content,Label.CONTENT_XHTML);
		helpText.addStyleName("gs-helptext");
		return helpText;
	}
	
	public Label createHelpImage(ThemeResource resource) {
		Label helpImage = new Label();
		helpImage.setIcon(resource);
		this.addComponent(helpImage);
		return helpImage;
	}
}
