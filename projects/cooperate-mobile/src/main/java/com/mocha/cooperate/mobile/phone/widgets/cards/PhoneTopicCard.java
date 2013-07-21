/**
 * 
 */
package com.mocha.cooperate.mobile.phone.widgets.cards;

import com.mocha.cooperate.model.Discuss;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class PhoneTopicCard extends AbstractPhoneCard  {

	private Discuss discuss;
	
	public PhoneTopicCard(Discuss discuss) {
		this.discuss = discuss;
		super.createUser = discuss.getCreator();
	}

	public void attach() {
		HorizontalLayout headLayout = buildCardHead(discuss.getCreateTime(),discuss.getComments().size());
		this.addComponent(headLayout);
		
		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setMargin(false, true, true, true);
		Label title = new Label(discuss.getTitle(), Label.CONTENT_XHTML);
		title.setStyleName("topic-title");
		contentLayout.addComponent(title);
		Label content = new Label(discuss.getContent(), Label.CONTENT_XHTML);
		content.setStyleName("card-content");
		contentLayout.addComponent(content);
		
		this.addComponent(contentLayout);
		
		super.addDetailButtonListener(discuss);
	}
}
