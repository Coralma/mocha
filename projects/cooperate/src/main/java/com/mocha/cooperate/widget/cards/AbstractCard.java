/**
 * 
 */
package com.mocha.cooperate.widget.cards;

import java.util.Date;
import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.DateUtils;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.cooperate.widget.AttachmentLayout;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral.Ma
 *
 */
public abstract class AbstractCard extends VerticalLayout implements ICard, ClickListener {

	protected String frame_size = "58px";
	protected String photo_size = "50px";
	protected String content_size = SystemProperty.content_card_width;
	protected String widgetWidth = SystemProperty.content_widget_width;
	protected BasicUser createUser;
	protected BasicUser currentUser;
	protected CardReply cardReply;
	protected List<Attachment> attachments;
	protected List<Comment> comments;
	protected boolean displayReply = false;
	
	protected Button replyButton;
	protected Button deleteButton;
	protected TimeLineService timeLineService = new TimeLineService();
	protected Message message;
	
	public void attach() {
		currentUser = (BasicUser)getApplication().getUser();
		message = new Message(getApplication().getLocale()); 
		this.setWidth(widgetWidth);
		this.addStyleName("m-card");
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.addStyleName("m-card-layout");
//		layout.setWidth(cardWidth);
		layout.setSpacing(true);
		Component userArea = buildUserArea();
		layout.addComponent(userArea);
		Component infoCardContent =  buildInformationArea();
		layout.addComponent(infoCardContent);
		layout.setComponentAlignment(infoCardContent, Alignment.MIDDLE_CENTER);
//		addComponent(layout);
		this.addComponent(layout);
	}
	
	public Component buildUserArea() {
		VerticalLayout userArea = new VerticalLayout();
		userArea.addStyleName("user-card-photo");
		userArea.setWidth(frame_size);
		userArea.setHeight(frame_size);
		// add user photo
		String url = createUser.getUserPhoto();
		Embedded userPhoto = PageBuildHelper.buildUserPhoto(url, getApplication());
		userPhoto.setWidth(photo_size);
		userPhoto.setHeight(photo_size);
		userArea.addComponent(userPhoto);
		return userArea;
	}
	
	public abstract Component buildInformationArea();
	
	public abstract void refreshCard();

	public HorizontalLayout buildUserInfoLabel(Date createDate) {
		// add user name
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setWidth(content_size);
		Label userNameLabel = new Label(createUser.getRealName());
		userNameLabel.addStyleName("card-username");
		hLayout.addComponent(userNameLabel);
		
		Label dateLabel = new Label(DateUtils.date2String(createDate, DateUtils.shortFormat));
		dateLabel.addStyleName("card-createDate");
		hLayout.addComponent(dateLabel);
		hLayout.setComponentAlignment(userNameLabel, Alignment.BOTTOM_LEFT);
		hLayout.setComponentAlignment(dateLabel, Alignment.BOTTOM_RIGHT);
		return hLayout;
	}
	
	public Layout buildAttachment() {
		if(attachments == null) {
			return null;
		}
		AttachmentLayout attachmentLayout = new AttachmentLayout((BasicUser)getApplication().getUser());
		attachmentLayout.setMessage(message);
		for(Attachment attachment : attachments) {
			attachmentLayout.addDisplayAttachment(attachment);
		}
		return attachmentLayout;
	}
	
	public Layout buildReply() {
		VerticalLayout replyLayout = new VerticalLayout();
		replyLayout.setWidth(content_size);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.addStyleName("action-layout");
		buttonLayout.setSpacing(true);
		String replyMsg = message.getString("cooperate.reply.Reply");
		replyButton = new Button(replyMsg);
		if(comments.size()>0) {
			replyButton.setCaption(replyMsg +"(" + comments.size() + ") ");
		}
		replyButton.addStyleName(BaseTheme.BUTTON_LINK);
		replyButton.addStyleName("card-reply");
		replyButton.setIcon(new ThemeResource("icons/reply_icon.png"));
		replyButton.addListener(this);
		buttonLayout.addComponent(replyButton);
		
		// get the ext button for different card.
		List<Button> extButtons = getExtButtons();
		if(extButtons != null) {
			for(Button extButton : extButtons) {
				buttonLayout.addComponent(extButton);	
			}
		}
		
		// create the delete button for the creator
		if(createUser.getBasicUserId().equals(((BasicUser)getApplication().getUser()).getBasicUserId())) {
			deleteButton = new Button(message.getString("cooperate.reply.Delete"));
			deleteButton.addStyleName(BaseTheme.BUTTON_LINK);
			deleteButton.addStyleName("card-reply");
			deleteButton.setIcon(new ThemeResource("icons/delete_icon.png"));
			deleteButton.addListener(this);
			buttonLayout.addComponent(deleteButton);
		}
		
		replyLayout.addComponent(buttonLayout);
		cardReply = new CardReply(comments);
		cardReply.setVisible(false);
		cardReply.setReplyListener(this);
		replyLayout.addComponent(cardReply);
		cardReply.setDisplayReply(displayReply);
//		if(displayReply) {
//			cardReply.reply(null);
//			cardReply.getInputArea().focus();
//		}
		return replyLayout;
	}
	
	public abstract List<Button> getExtButtons();
	
	public void setDisplayReply(boolean displayReply) {
		this.displayReply = displayReply;
	}
	
	/**
	 * @return the createUser
	 */
	public BasicUser getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(BasicUser createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the currentUser
	 */
	public BasicUser getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(BasicUser currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the cardReply
	 */
	public CardReply getCardReply() {
		return cardReply;
	}

	/**
	 * @param cardReply the cardReply to set
	 */
	public void setCardReply(CardReply cardReply) {
		this.cardReply = cardReply;
	}
}
