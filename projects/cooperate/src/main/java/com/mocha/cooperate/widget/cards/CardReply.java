/**
 * 
 */
package com.mocha.cooperate.widget.cards;

import java.util.List;
import java.util.Set;

import org.vaadin.hene.expandingtextarea.ExpandingTextArea;
import org.vaadin.tokenfield.TokenField;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.DateUtils;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.helper.BasicHelper;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.google.common.collect.Sets;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.basic.dao.CommentDao;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.cooperate.widget.NotifyTokenField;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral.Ma
 *
 */
public class CardReply extends VerticalLayout {

	public static String REPLY_BUTTON = "reply";
	private String frame_size = "37px";
	private String photo_size = "35px";
	private String reply_content_size = "620px";
	private String card_content_width = SystemProperty.content_card_width;
	private String card_text_width = "658px";
	private List<Comment> comments;
	private ExpandingTextArea inputArea = new ExpandingTextArea();
	private NativeButton postButton = new NativeButton("Reply");
	protected TimeLineService timeLineService = new TimeLineService();
	private VerticalLayout replyLayout;
	private TextField textField;
	private Message message;
	protected boolean displayReply = false;
	private NotifyTokenField tokenField;
	
	public CardReply(List<Comment> comments) {
		this.comments = comments; 
		this.setWidth(card_content_width);
		this.addStyleName("m-card-reply");
	}
	
	public void attach() {
		build();
	}
	
	public void build() {
		message = new Message(getApplication().getLocale());
		this.removeAllComponents();
		// display all comment.
		if(comments != null) {
			for(Comment comment : comments) {
				HorizontalLayout layout = new HorizontalLayout();
				layout.addStyleName("m-card-reply-layout");
				layout.setSpacing(true);
				Component userArea = buildUserArea(comment.getCreator());
				layout.addComponent(userArea);
				
				VerticalLayout contentLayout = new VerticalLayout();
				Component replyContent = buildReplyContent(comment);
				contentLayout.addComponent(replyContent);
				contentLayout.setComponentAlignment(replyContent, Alignment.MIDDLE_LEFT);
				Component replyAction = buildReplyAction(comment,layout);
				contentLayout.addComponent(replyAction);
				contentLayout.setComponentAlignment(replyAction, Alignment.MIDDLE_RIGHT);
				layout.addComponent(contentLayout);
				
				addComponent(layout);
			}
		}
		buildReplySection();
		if(displayReply) {
			setVisible(true);
			reply(null);
		}
	}
	
	public Component buildUserArea(BasicUser createUser) {
		VerticalLayout userArea = new VerticalLayout();
		userArea.setWidth(frame_size);
		userArea.setHeight(frame_size);
		// add user photo
		String url = createUser.getUserPhoto();
		Embedded userPhoto = PageBuildHelper.buildUserPhoto(url, getApplication());
		userPhoto.addStyleName("user-card-reply-photo");
		userPhoto.setWidth(photo_size);
		userPhoto.setHeight(photo_size);
		userArea.addComponent(userPhoto);
		return userArea;
	}

	public Component buildReplyContent(Comment comment) {
		String userName = comment.getCreator().getRealName();
		String content = comment.getContent();
		String replyDate = " (" + DateUtils.date2String(comment.getCreateTime(), DateUtils.shortFormat) + ")";
		String replyContent = BasicHelper.styleUsername(userName) + content + replyDate;
		Label contentLabel = new Label(replyContent, Label.CONTENT_XHTML);
		contentLabel.setWidth(reply_content_size);
		return contentLabel;
	}
	
	public Component buildReplyAction(final Comment comment, final HorizontalLayout layout) {
		final Button button = new Button();
		if(BasicHelper.isCurrentUser(comment.getCreator(),(BasicUser) getApplication().getUser())) {
			button.setCaption(message.getString("cooperate.reply.Delete"));
			button.setIcon(new ThemeResource("icons/delete_icon.png"));
			button.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					CardReply.this.removeComponent(layout);
					timeLineService.removeComment(comment);
				}
			});
		} else {
			button.setCaption(message.getString("cooperate.reply.Reply"));
			button.setIcon(new ThemeResource("icons/reply_icon.png"));
			button.addListener(new ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					reply("Reply @" + comment.getCreator().getRealName() + ": ");
				}
			});
		}
		button.addStyleName(BaseTheme.BUTTON_LINK);
		button.addStyleName("m-card-reply-button");
		return button;
	}
	
	public void buildReplySection() {
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth(card_content_width);
		layout.addStyleName("reply-content");
		replyLayout = new VerticalLayout();
		replyLayout.addStyleName("reply-area-layout");
		textField = new TextField();
		textField.addStyleName("reply-area-layout");
		
		textField.setWidth(card_content_width);
		textField.setInputPrompt(message.getString("cooperate.reply.replyInfo"));
		textField.addListener(new FocusListener() {
			@Override
			public void focus(FocusEvent event) {
				reply(null);
			}
		});
		layout.addComponent(textField);
		
		replyLayout.setVisible(false);
		inputArea.setWidth(card_text_width);
		replyLayout.addComponent(inputArea);
		
		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.addStyleName("reply-button");
		hlayout.setWidth(card_content_width);
		
		tokenField = new NotifyTokenField();
		tokenField.setInputPrompt(message.getString("cooperate.reply.NotificateOthers"));
		hlayout.addComponent(tokenField);
		
		postButton.setCaption(message.getString("cooperate.reply.Reply"));
		postButton.setData(REPLY_BUTTON);
		postButton.addStyleName("mocha-button");
		hlayout.addComponent(postButton);
		hlayout.setComponentAlignment(postButton, Alignment.MIDDLE_RIGHT);
		
		replyLayout.addComponent(hlayout);
		layout.addComponent(replyLayout);
		this.addComponent(layout);
	}
	
	public void reply(String content) {
		replyLayout.setVisible(true);
		if(content == null) {
			inputArea.setValue("");
		} else {
			inputArea.setValue(content);
		}
		inputArea.focus();
		textField.setVisible(false);
	}
	
	public void setReplyListener(ClickListener clickListener) {
		postButton.addListener(clickListener);
	}
	
	public Comment getValue() {
		Object value = inputArea.getValue();
		if(value == null) return null;
		Comment comment = new Comment();
		comment.setContent((String) value);
		comment.setCreator((BasicUser) getApplication().getUser());
		return comment;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the inputArea
	 */
	public ExpandingTextArea getInputArea() {
		return inputArea;
	}

	/**
	 * @return the displayReply
	 */
	public boolean isDisplayReply() {
		return displayReply;
	}

	/**
	 * @param displayReply the displayReply to set
	 */
	public void setDisplayReply(boolean displayReply) {
		this.displayReply = displayReply;
	}

	/**
	 * @return the tokenField
	 */
	public Set<BasicUser> getNotifyUsers() {
		Set<BasicUser> notifyUsers = (Set<BasicUser>)tokenField.getValue();
		if(notifyUsers == null) {
			notifyUsers = Sets.newHashSet();
		}
		return notifyUsers;
	}

}
