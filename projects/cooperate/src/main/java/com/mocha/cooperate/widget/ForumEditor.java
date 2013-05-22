/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.List;
import java.util.Set;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.Message;
import com.coral.foundation.utils.StrUtils;
import com.google.common.collect.Lists;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.page.event.ForumEditorListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral
 *
 */
public class ForumEditor extends VerticalLayout implements ClickListener {
	
	private ForumEditorListener listener;
	private static String DISCUSS = "Discuss";
	private static String QUESTION = "Question";
	private static String NOTIFICATION = "Notification";
	private String type;
	private String inputHeight = "350px";
	private String widgetWidth = "708px";
	private String editorWidth = "700px";
	private TextField titleField = new TextField();
//	private OptionGroup statusGroup;
	private RichTextArea inputArea = new RichTextArea();
	private AttachmentLayout attachmentLayout;
	private NotifyTokenField tokenField;
	private NativeButton postBtn;
//	private Button cancelBtn;
	private Message message;
	
	private String topicCategory;
	private String categoryTitle;
	
	public ForumEditor(String topicCategory, String categoryTitle) {
		this.addStyleName("forum-editor");
		this.setSpacing(true);
		this.setWidth(widgetWidth);
		this.topicCategory = topicCategory;
		this.categoryTitle = categoryTitle;
	}

	public void attach() {
		message = new Message(getApplication().getLocale());
		
		VerticalLayout titleLayout = new VerticalLayout();
		titleLayout.setWidth(editorWidth);
		
		Label titleLabel = new Label(message.getString("cooperate.forum."+ categoryTitle + "Title"));
		titleLabel.setWidth(editorWidth);
		titleLayout.addComponent(titleLabel);
		
		titleField.setWidth(editorWidth);
		titleLayout.addComponent(titleField);
		this.addComponent(titleLayout);

		inputArea.setWidth(editorWidth);
		inputArea.setHeight(inputHeight);
		inputArea.addStyleName("input-area");
		this.addComponent(inputArea);
		
		// set attachment layout into page.
		attachmentLayout = new AttachmentLayout((BasicUser)getApplication().getUser());
		this.addComponent(attachmentLayout);

		Layout attachLayout = buildPostArea();
		this.addComponent(attachLayout);
		
//		Toolbar toolbar = new Toolbar();
//		toolbar.setWidth(editorWidth);
//		toolbar.addRightLink("Post", "post", Toolbar.BUTTON, this);
//		toolbar.addRightLink("Cancel & Back", "back", this);
//		this.addComponent(toolbar);
	}
	
	public Layout buildPostArea() {
		HorizontalLayout attachLayout = new HorizontalLayout();
		attachLayout.addStyleName("attachment-function-panel");
		attachLayout.setWidth(editorWidth);

		tokenField = new NotifyTokenField();
		tokenField.setInputPrompt(message.getString("cooperate.publisher.NotificateOthers"));
		tokenField.setWidth("575px");
		attachLayout.addComponent(tokenField);
		attachLayout.setComponentAlignment(tokenField, Alignment.BOTTOM_LEFT);

		// build attachment area
		HorizontalLayout referLayout = new HorizontalLayout();
		referLayout.setSpacing(true);
		AttachmentUpload upload = new AttachmentUpload(attachmentLayout);
//		upload.setIcon(new ThemeResource("icons/file_icon.png"));
		referLayout.addComponent(upload);
		upload.setButtonCaption(message.getString("cooperate.publisher.File"));
		
		// build post button
		HorizontalLayout postLayout = new HorizontalLayout();
		postLayout.setWidth("160px");
		postBtn = new NativeButton(message.getString("cooperate.publisher.Post"));
		postBtn.addStyleName("mocha-button");
		postBtn.setWidth("70px");
		postBtn.addListener(this);
//		cancelBtn = new Button("Cancel & Back");
//		cancelBtn.addStyleName(BaseTheme.BUTTON_LINK);
//		cancelBtn.addListener(this);
		postLayout.addComponent(postBtn);
//		postLayout.addComponent(cancelBtn);
//		postLayout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_LEFT);
		attachLayout.addComponent(referLayout);
		attachLayout.setComponentAlignment(referLayout,Alignment.MIDDLE_RIGHT);
		attachLayout.addComponent(postLayout);
		attachLayout.setComponentAlignment(postLayout, Alignment.BOTTOM_RIGHT);
		return attachLayout;
	}
	
	public List<String> getStatus() {
		List<String> statusList = Lists.newArrayList();
		statusList.add("As a Discuss");
		statusList.add("As a Question");
		statusList.add("As a Notification");
		return statusList;
	}

	public Object getValue() {
		Object title = titleField.getValue();
		Object input = inputArea.getValue();
		if (StrUtils.isEmpty(title) || StrUtils.isEmpty(input)) {
			return null;
		}
		Discuss discuss = new Discuss();
		discuss.setTitle((String) title);
		discuss.setContent((String) input);
		discuss.setStatus(topicCategory);
		discuss.setCreator((BasicUser) getApplication().getUser());
		return discuss;
	}

	public void clean() {
		titleField.setValue("");
		inputArea.setValue("");
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(listener != null) {
			if(postBtn.equals(event.getButton())) {
				Discuss discuss = (Discuss)getValue();
				listener.saveTopic(discuss, (BasicUser)getApplication().getUser(), (Set<BasicUser>)tokenField.getValue(), attachmentLayout.getAttachments());
//			} else if(cancelBtn.equals(event.getButton())) {
//				listener.cancelAndBack();
			}
		}
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the listener
	 */
	public ForumEditorListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(ForumEditorListener listener) {
		this.listener = listener;
	}
}
