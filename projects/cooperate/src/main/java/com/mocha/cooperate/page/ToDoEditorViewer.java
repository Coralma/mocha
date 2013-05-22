/**
 * 
 */
package com.mocha.cooperate.page;

import java.util.Set;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.component.Toolbar;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.page.event.TodoEditorListener;
import com.mocha.cooperate.widget.AttachmentLayout;
import com.mocha.cooperate.widget.AttachmentUpload;
import com.mocha.cooperate.widget.NotifyTokenField;
import com.mocha.cooperate.widget.TodoProjectEditor;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class ToDoEditorViewer extends CommonViewer implements Viewer, ClickListener {

	private static final long serialVersionUID = -5736772858193532820L;
	private TodoEditorListener listener;
	private ToDo todo;
	private AttachmentLayout attachmentLayout;
	private TodoProjectEditor todoProjectEditor;
	private String editorWidth = "745px";
	private NotifyTokenField tokenField;
	private NativeButton postBtn;
//	private Button cancelBtn;
	
	String contentLayoutWidth = "743px";
	String contentWidth = "741px";
	String contentTextWidth = "739px";
	private BasicUser currentUser;
	
	public ToDoEditorViewer(ToDo todo, BasicUser currentUser) {
		this.todo = todo;
		this.currentUser = currentUser;
//		if(todo==null) {
//			this.todo = new ToDo();
//		}
	}

	public void attach() {
		super.attach();
		this.addComponent(buildToolbar());
		
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setWidth(editorWidth);
		layout.addStyleName("todo-editor");
		

		todoProjectEditor = new TodoProjectEditor(todo, currentUser);
		todoProjectEditor.setPublisherStatus(true);
		todoProjectEditor.setContentLayoutWidth(contentLayoutWidth);
		todoProjectEditor.setContentWidth(contentWidth);
		todoProjectEditor.setContentTextWidth(contentTextWidth);
		layout.addComponent(todoProjectEditor);
		
		attachmentLayout = new AttachmentLayout((BasicUser)getApplication().getUser());
//		attachmentLayout.setVisible(false);
		layout.addComponent(attachmentLayout);
		
		Layout attachLayout = buildPostArea();
		layout.addComponent(attachLayout);
		
		this.addComponent(layout);
	}
	
	public Toolbar buildToolbar() {
		Toolbar toolbar = new Toolbar(message);
		toolbar.setWidth("768px");
		toolbar.addLeftLink("cooperate.todo.backtoTaskList", "back", this);
		return toolbar;
	}
	
	public Layout buildPostArea() {
		HorizontalLayout attachLayout = new HorizontalLayout();
		attachLayout.addStyleName("attach-area");
//		attachLayout.setWidth("770px");
		attachLayout.setWidth(contentLayoutWidth);

		tokenField = new NotifyTokenField();
		tokenField.setInputPrompt(message.getString("cooperate.publisher.NotificateOthers"));
		tokenField.setWidth("625px");
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
	
	@Override
	public String getViewerTitle() {
		return "cooperate.todo.newproject";
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(listener != null) {
			if(postBtn.equals(event.getButton())) {
				ToDo todo = (ToDo)todoProjectEditor.getValue();
				listener.saveTodo(todo, (BasicUser)getApplication().getUser(), (Set<BasicUser>)tokenField.getValue(), attachmentLayout.getAttachments());
			} else if("back".equals(event.getButton().getData())) {
				listener.cancelAndBack();
			}
		}
	}

	/**
	 * @return the listener
	 */
	public TodoEditorListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(TodoEditorListener listener) {
		this.listener = listener;
	}

}
