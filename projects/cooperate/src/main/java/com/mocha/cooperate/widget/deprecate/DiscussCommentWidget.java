package com.mocha.cooperate.widget.deprecate;

import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Comment;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@Deprecated
public class DiscussCommentWidget extends CustomComponent {
	
	private String discusswidth = "630px";
	private String commontHeight ="200px";
	private Label commentLabel = new Label("Comment");
	private TextField commentField = new TextField();
	private RichTextArea commentArea = new RichTextArea();
//	private StringRichField contentArae =new StringRichField("Comment");
	private HorizontalLayout commentWidget = new HorizontalLayout();
	private Comment comment;
	
	public DiscussCommentWidget(Comment commont) {
		this.comment = commont;
	}
	
	@Override
    public void attach() {
		if(comment == null) {
			buildNewComment();
		} else {
			buildCommentText();
		}
	}
	
	public void buildCommentText() {
		VerticalLayout commentLayout = new VerticalLayout();
		commentLayout.setMargin(true);
		commentLayout.addStyleName("mocha-box-layout");
		commentLayout.setWidth("99%");
		commentLayout.setSpacing(true);
		
		Label context = new Label(comment.getContent(), Label.CONTENT_XHTML);
		context.addStyleName("comment-content");
		commentLayout.addComponent(context);
		setCompositionRoot(commentLayout);
	}
	
	public void buildNewComment() {
		VerticalLayout commentLayout = new VerticalLayout();
		commentLayout.setMargin(true);
		commentLayout.addStyleName("mocha-box-layout");
		commentLayout.setWidth("99%");
		commentLayout.setSpacing(true);
		
		Label comment = new Label("Post your discuss");
		comment.addStyleName("discuss-comment");
		
		commentLayout.addComponent(comment);
		
		commentWidget.setWidth("100%");
		commentLabel.setWidth("100px");
		commentField.setWidth("200px");
		commentWidget.addComponent(commentLabel);
		commentWidget.addComponent(commentField);
		commentField.addListener(new FocusListener() {
			@Override
			public void focus(FocusEvent event) {
				commentArea.setVisible(true);
				commentArea.focus();
				commentField.setVisible(false);
				commentWidget.setExpandRatio(commentLabel, 1);
				commentWidget.setExpandRatio(commentField, 0);
				commentWidget.setExpandRatio(commentArea, 4);
			}
		});
		commentArea.setVisible(false);
		commentArea.setWidth(discusswidth);
		commentArea.setHeight(commontHeight);
		commentWidget.addComponent(commentArea);
		commentWidget.setExpandRatio(commentLabel, 1);
		commentWidget.setExpandRatio(commentField, 4);
		commentWidget.setExpandRatio(commentArea, 0);
		
		commentLayout.addComponent(commentWidget);
//		commentLayout.addComponent(contentArae);
		setCompositionRoot(commentLayout);
	}
	
	public Comment getValue() {
		Comment commont = new Comment();
		commont.setContent((String)commentArea.getValue());
		return commont;
	}
	
}
