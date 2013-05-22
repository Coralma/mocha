/**
 * 
 */
package com.mocha.cooperate.widget.deprecate;

import com.coral.vaadin.widget.field.StringField;
import com.coral.vaadin.widget.field.StringRichField;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.widget.FunctionButtonLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 *
 */
@Deprecated
public class DiscussWidget extends VerticalLayout implements Button.ClickListener {

	private String discusswidth ="630px";
	private String discussHeight ="400px";
	private String POST_COMMENT = "postComment";
	private String POST_MESSAGE = "postMessage";
	
	private VerticalLayout discussContentLayout = new VerticalLayout();
	private StringField titleField = new StringField("Title");
	private StringRichField contentField =new StringRichField("Content");
	private Discuss discuss;
	private DiscussCommentWidget commentWidget;
	
	public DiscussWidget(Discuss discuss) {
		if(discuss == null) {
			discuss = new Discuss();
		}
		this.discuss = discuss;
		this.setSpacing(true);
		this.addStyleName("mocha-discuss");
		buildNewDiscuss();
	}
	
	public void buildNewDiscuss() {
		discussContentLayout.setMargin(true);
		discussContentLayout.setWidth("99%");
		discussContentLayout.setSpacing(true);
		discussContentLayout.addStyleName("mocha-box-layout");
		
		titleField.setFieldWidth(discusswidth);
		discussContentLayout.addComponent(titleField);
		
		contentField.setFieldWidth(discusswidth);
		contentField.setFieldHeight(discussHeight);
		discussContentLayout.addComponent(contentField);
		
		addComponent(discussContentLayout);
		
//		HorizontalLayout btnLayout = new HorizontalLayout();
//		btnLayout.setSpacing(true);
		FunctionButtonLayout btnLayout = new FunctionButtonLayout();
		Button post = new Button("Post this message");
		post.setData(POST_MESSAGE);
		post.addListener(this);
		Button cancel = new Button("Cancel");
		cancel.setData("cancel");
		cancel.addListener(this);
		btnLayout.addButton(post);
		btnLayout.addButton(cancel);
		addComponent(btnLayout);
	}

	public void buildDiscuss(Discuss discuss) {
		this.removeAllComponents();
//		VerticalLayout discussTitle = PageBuildHelper.buildPageTitle("A New Discussions");
//		addComponent(discussTitle);
		discussContentLayout.removeAllComponents();
		
		Label title = new Label(discuss.getTitle());
		title.addStyleName("discuss-title");
		discussContentLayout.addComponent(title);
		
		Label context = new Label(discuss.getContent(), Label.CONTENT_XHTML);
		context.addStyleName("discuss-content");
		discussContentLayout.addComponent(context);
		addComponent(discussContentLayout);
		
		for(Comment comment : discuss.getComments()) {
			DiscussCommentWidget commentWidget = new DiscussCommentWidget(comment);
			addComponent(commentWidget);
		}
		
		commentWidget = new DiscussCommentWidget(null);
		addComponent(commentWidget);
		
		HorizontalLayout btnLayout = new HorizontalLayout();
		btnLayout.setSpacing(true);
		Button post = new Button("Post Comment");
		post.setData(POST_COMMENT);
		post.addListener(this);
		Button cancel = new Button("Cancel");
		cancel.setData("cancel");
		cancel.addListener(this);
		btnLayout.addComponent(post);
		btnLayout.addComponent(cancel);
		addComponent(btnLayout);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().getData().equals(POST_MESSAGE)) {
			discuss.setTitle((String)titleField.getValue());
			discuss.setContent((String)contentField.getValue());
			buildDiscuss(discuss);
		} else if(event.getButton().getData().equals(POST_COMMENT)) {
			Comment discussComment = commentWidget.getValue();
			discuss.getComments().add(discussComment);
			buildDiscuss(discuss);
		}
	}
}
