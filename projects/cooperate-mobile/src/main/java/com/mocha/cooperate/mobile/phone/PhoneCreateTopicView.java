/**
 * 
 */
package com.mocha.cooperate.mobile.phone;

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

	private Button backButton = new Button("Back");
	private Button postButton = new Button("Post");
	private TextArea titleField = new TextArea();
	private TextArea contentArea = new TextArea();
	
	public PhoneCreateTopicView() {
		setLeftComponent(backButton);
		setRightComponent(postButton);
	}
	
	@Override
	public void attach() {
		super.attach();
		setCaption("New Status");
		
		CssLayout newStatusLayout = new CssLayout();
		newStatusLayout.setSizeFull();
		VerticalComponentGroup componentGroup = new VerticalComponentGroup();
		componentGroup.setWidth("100%");
		titleField.setWidth("100%");
		titleField.setInputPrompt("Topic Title");
		componentGroup.addComponent(titleField);
		
		contentArea.setWidth("100%");
		contentArea.setInputPrompt("Topic Content");
		componentGroup.addComponent(contentArea);
		
		newStatusLayout.addComponent(componentGroup);
		setContent(newStatusLayout);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
}
