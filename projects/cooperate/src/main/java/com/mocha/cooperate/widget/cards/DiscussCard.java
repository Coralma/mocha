/**
 * 
 */
package com.mocha.cooperate.widget.cards;

import com.coral.foundation.utils.StrUtils;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.widget.ConfirmDialog;
import com.mocha.cooperate.widget.TodoProjectDisplayer;
import com.mocha.cooperate.widget.TodoProjectDisplayer.TodoItemDisplay;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral.Ma
 *
 */
public class DiscussCard extends AbstractCard {

	private TimeLine timeLine;
	private Discuss discuss;
	
	public DiscussCard(TimeLine timeline) {
		this.timeLine = timeline;
		this.discuss = timeline.getDiscuss();
		this.attachments = discuss.getAttachments();
		this.comments = discuss.getComments();
		this.createUser = discuss.getCreator();
		this.addStyleName("discuss-card");
	}
	
	public DiscussCard(NotifyLine notifyLine) {
		this.discuss = notifyLine.getDiscuss();
		this.comments = discuss.getComments();
		this.createUser = discuss.getCreator();
		this.addStyleName("status-card");
	}
	
	public DiscussCard(Discuss discuss) {
		this.discuss = discuss;
		this.comments = discuss.getComments();
		this.createUser = discuss.getCreator();
		this.attachments = discuss.getAttachments();
		this.addStyleName("status-card");
	}

	@Override
	public Component buildInformationArea() {
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth(content_size);
		layout.addComponent(buildDiscussArea(discuss.getContent()));
		return layout;
	}

	public Label buildDiscussTitle(String title) {
		Label titleLabel = new Label();
		titleLabel.setCaption(title);
		titleLabel.setContentMode(Label.CONTENT_XHTML);
		if("1".equals(discuss.getStatus())) {
			titleLabel.setIcon(new ThemeResource("images/questions.png"));
		} else if("2".equals(discuss.getStatus())) {
			titleLabel.setIcon(new ThemeResource("images/exclamation.png"));
		} else {
			titleLabel.setIcon(new ThemeResource("images/chat.png"));
		}
		
		titleLabel.addStyleName("discuss-card-title");
		return titleLabel;
	}
	
	public Label buildDiscussContent(String content) {
		Label contentLabel = new Label(StrUtils.asciiToXhtml(content), Label.CONTENT_XHTML);
		contentLabel.addStyleName("discuss-card-content");
		return contentLabel;	
	}

	public Component buildDiscussArea(String content) {
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("m-card-content");
		layout.setWidth(content_size);
		
		HorizontalLayout userInfo = buildUserInfoLabel(discuss.getCreateTime());
		layout.addComponent(userInfo);
		
		VerticalLayout discussContentLayout = new VerticalLayout();
		discussContentLayout.addStyleName("discuss-content-layout");
		discussContentLayout.setSpacing(true);
		discussContentLayout.addComponent(buildDiscussTitle(discuss.getTitle()));
		discussContentLayout.addComponent(buildDiscussContent(discuss.getContent()));
		layout.addComponent(discussContentLayout);
		
		// set attachment to card, if it has.
		Layout attachmentLayout = buildAttachment();
		if(attachmentLayout != null) {
			layout.addComponent(attachmentLayout);
		}
		Layout replyInfo = buildReply();
		layout.addComponent(replyInfo);
		return layout;	
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(deleteButton)) {
			ConfirmDialog confirmDialog = new ConfirmDialog("Do you want to delete this topic ?") {
				@Override
				public void confirm() {
					if(timeLine == null) {
						timeLine = timeLineService.queryTimelineByDiscuss(discuss); 
					}
					timeLineService.removeTimeLine(timeLine);
					Layout layout = (Layout) DiscussCard.this.getParent();
					layout.removeComponent(DiscussCard.this);
				}
				@Override
				public void cancel() {
				}
			};
			DiscussCard.this.getWindow().addWindow(confirmDialog);
		}
		if(event.getButton().equals(replyButton)) {
			if(cardReply.isVisible()) {
				cardReply.setVisible(false);
			} else {
				cardReply.setVisible(true);
			}
		}
		if(CardReply.REPLY_BUTTON.equals(event.getButton().getData())) {
			Comment comment = cardReply.getValue();
			discuss.getComments().add(comment);
			comment.setDiscuss(discuss);
			discuss = timeLineService.updateDiscuss(discuss);
			cardReply.setComments(discuss.getComments());
			cardReply.build();
		}
	}

	@Override
	public void refreshCard() {
		// TODO Auto-generated method stub
		
	}
}
