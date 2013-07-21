/**
 * 
 */
package com.mocha.cooperate.widget.cards;

import java.util.List;

import com.coral.foundation.utils.StrUtils;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.model.NotifyLine;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.widget.ConfirmDialog;
import com.vaadin.ui.Button;
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
public class StatusCard extends AbstractCard {

	private TimeLine timeLine;
	private NotifyLine notifyLine;
	private Status status;
	
	public StatusCard(TimeLine timeLine) {
		this.timeLine = timeLine;
		this.status = timeLine.getStatus();
		this.attachments = status.getAttachments();
		this.comments = status.getComments();
		this.createUser = status.getCreator();
		this.addStyleName("status-card");
	}
	
	public StatusCard(NotifyLine notifyLine) {
		this.notifyLine = notifyLine;
		this.status = notifyLine.getStatus();
		this.comments = status.getComments();
		this.createUser = status.getCreator();
		this.addStyleName("status-card");
	}
	
	@Override
	public Component buildInformationArea() {
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth(content_size);
		layout.addComponent(buildStatusContent(status.getContent()));
		return layout;
	}
	
	public Component buildStatusContent(String content) {
		VerticalLayout layout = new VerticalLayout();
		layout.addStyleName("m-card-content");
		layout.setWidth(content_size);
		HorizontalLayout userInfo = buildUserInfoLabel(status.getCreateTime());
		layout.addComponent(userInfo);
		String htmlContentStr = StrUtils.asciiToXhtml(content);
		Label contentLabel = new Label(htmlContentStr, Label.CONTENT_XHTML);
//		contentLabel.addStyleName("card-content");
//		contentLabel.setWidth("500px");
		layout.addComponent(contentLabel);
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
			ConfirmDialog confirmDialog = new ConfirmDialog("Do you want to delete this Status ?") {
				@Override
				public void confirm() {
					if(timeLine == null) {
						timeLine = timeLineService.queryTimelineByStatus(status); 
					}
					timeLineService.removeTimeLine(timeLine);
					Layout layout = (Layout) StatusCard.this.getParent();
					layout.removeComponent(StatusCard.this);
				}
				@Override
				public void cancel() {
				}
			};
			StatusCard.this.getWindow().addWindow(confirmDialog);
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
			status.getComments().add(comment);
			comment.setStatus(status);
//			status = timeLineService.updateStatus(status);
			status = timeLineService.replyStatus(status,cardReply.getNotifyUsers());
			cardReply.setComments(status.getComments());
			cardReply.build();
		}
	}

	@Override
	public void refreshCard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Button> getExtButtons() {
		// TODO Auto-generated method stub
		return null;
	}
}