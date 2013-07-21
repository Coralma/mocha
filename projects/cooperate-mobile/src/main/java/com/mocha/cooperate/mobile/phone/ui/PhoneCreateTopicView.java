/**
 * 
 */
package com.mocha.cooperate.mobile.phone.ui;

import com.mocha.mobile.controller.MobileView;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * @author Coral
 *
 */
public class PhoneCreateTopicView extends NavigationView implements MobileView, ClickListener {

	private Button postButton = new Button("Post New Topic");
	private TextArea titleField = new TextArea();
	private TextArea contentArea = new TextArea();
	
	public PhoneCreateTopicView() {
//		setRightComponent(postButton);
	}
	
	@Override
	public void attach() {
		super.attach();
		setCaption("New Topic");
		
		CssLayout newTopicLayout = new CssLayout();
		newTopicLayout.setSizeFull();
		newTopicLayout.setMargin(true);
		VerticalComponentGroup componentGroup = new VerticalComponentGroup();
		componentGroup.setWidth("100%");
		titleField.setWidth("100%");
		titleField.setRows(2);
		titleField.setInputPrompt("Title");
		componentGroup.addComponent(titleField);
		
		contentArea.setWidth("100%");
		contentArea.setRows(10);
		contentArea.setInputPrompt("Content");
		componentGroup.addComponent(contentArea);
		
		componentGroup.addComponent(postButton);
		
		newTopicLayout.addComponent(componentGroup);
		setContent(newTopicLayout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
}
