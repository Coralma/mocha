/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.Date;

import org.vaadin.hene.expandingtextarea.ExpandingTextArea;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.DateUtils;
import com.coral.foundation.utils.Message;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.image.AbstractUserIconHelper;
import com.coral.vaadin.widget.layout.AssginedUserSelect;
import com.mocha.cooperate.InnerStyle;
import com.mocha.cooperate.image.UserIconHelper;
import com.mocha.cooperate.model.SubToDoItem;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author Coral
 *
 */
public class TodoTaskEditor extends VerticalLayout implements LayoutClickListener {

	boolean added = false;
//	private String contentLayoutWidth = "720px";
//	private String contentWidth = "718px";
	private String contentLayoutWidth = "740px";
	private String contentWidth = "735px";
	String subtaskContentWidth = "728px";
	String taskLabelWidth = "680px";
	String selectFieldWidth = "100px";
	VerticalLayout taskEditorLayout = new VerticalLayout();
	ExpandingTextArea taskField = new ExpandingTextArea();
	ComboBox userCombox;
	DateField expireDateField = new DateField();
	NativeButton addTaskButton = new NativeButton();
	Button cancelTaskButton = new Button();
	
	VerticalLayout taskCardLayout = new VerticalLayout();
	HorizontalLayout taskDisplayLayout = new HorizontalLayout();
	HorizontalLayout assignmentLayout = new HorizontalLayout();
	
	Label taskDisplayLabel = new Label("",Label.CONTENT_XHTML);
//	Label assignerLabel = new Label("",Label.CONTENT_XHTML);
//	Label duetoLabel = new Label("",Label.CONTENT_XHTML);
//	Button taskEditButton = new Button();
	Button taskDeleteButton = new Button();
	
	boolean isEditPanel = true;
	private SubToDoItem subToDoItem;
	private BasicUser currentUser;
	private Message message;
	
	public TodoTaskEditor(SubToDoItem subToDoItem, BasicUser currentUser) {
		this.addStyleName("task-panel");
		this.subToDoItem = subToDoItem;
		this.currentUser = currentUser;
	}
	
	public Object getTaskContent() {
		return taskField.getValue();
	}
	
	public SubToDoItem getValue() {
		subToDoItem.setContent((String) taskField.getValue());
		BasicUser assignedUser = (BasicUser) userCombox.getValue();
		if(assignedUser == null) {
			assignedUser = (BasicUser) getApplication().getUser();
		}
		subToDoItem.setAssginedUser(assignedUser);
		subToDoItem.setExpiredDate((Date) expireDateField.getValue());
		return subToDoItem;
	}
	
	public void attach() {
		message = new Message(getApplication().getLocale());
		this.setWidth(contentLayoutWidth);
		taskEditorLayout.setWidth(contentWidth);
		taskEditorLayout.setSpacing(true);
		taskEditorLayout.addStyleName("task-content");
		this.addComponent(taskEditorLayout);
		
//		taskField.setRows(1);
		taskField.setInputPrompt(message.getString("cooperate.todo.NewTask"));
		taskField.setWidth(subtaskContentWidth);
		taskEditorLayout.addComponent(taskField);
		
		HorizontalLayout controlLayout = new HorizontalLayout();
		controlLayout.setWidth(subtaskContentWidth);
		// button control
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		
		addTaskButton.addStyleName("mocha-button");
		addTaskButton.setCaption(message.getString("cooperate.todo.AddTask"));
		buttonLayout.addComponent(addTaskButton);
		
		cancelTaskButton.setCaption(message.getString("cooperate.cancel"));
		cancelTaskButton.addStyleName(BaseTheme.BUTTON_LINK);
		buttonLayout.addComponent(cancelTaskButton);
		buttonLayout.setComponentAlignment(cancelTaskButton, Alignment.MIDDLE_LEFT);
		
		// task information
		HorizontalLayout taskInfoLayout = new HorizontalLayout();
		taskInfoLayout.setSpacing(true);
		AbstractUserIconHelper userIconHelper = new UserIconHelper();
		AssginedUserSelect assginedUser = new AssginedUserSelect(userIconHelper);
		userCombox = assginedUser.buildAssginedUser();
		userCombox.setValue(getApplication().getUser());
		userCombox.setInputPrompt(message.getString("cooperate.todo.Assigner"));
		userCombox.setWidth(selectFieldWidth);
		taskInfoLayout.addComponent(userCombox);
		
		expireDateField.setResolution(InlineDateField.RESOLUTION_DAY);
		expireDateField.setWidth(selectFieldWidth);
		expireDateField.setDescription(message.getString("cooperate.todo.ExpiredDate"));
		expireDateField.setValue(DateUtils.addDay(new Date(), 1));
		taskInfoLayout.addComponent(userCombox);
		taskInfoLayout.addComponent(expireDateField);
		
		controlLayout.addComponent(buttonLayout);
		controlLayout.setComponentAlignment(buttonLayout,Alignment.MIDDLE_LEFT);
		
		controlLayout.addComponent(taskInfoLayout);
		controlLayout.setComponentAlignment(taskInfoLayout,Alignment.MIDDLE_RIGHT);
		
		taskEditorLayout.addComponent(controlLayout);
		
		// task display
		taskCardLayout.addStyleName("task-display-panel");
		taskCardLayout.setWidth(subtaskContentWidth);
		
		taskDisplayLayout.setWidth(subtaskContentWidth);
		taskDisplayLayout.setVisible(false);
		taskDisplayLayout.addListener(this);
		taskDisplayLabel.setWidth(taskLabelWidth);
		
//		HorizontalLayout taskInfoDisplayControlLayout = new HorizontalLayout();
//		taskInfoDisplayControlLayout.setSpacing(true);
		taskDeleteButton.addStyleName(BaseTheme.BUTTON_LINK);
		taskDeleteButton.setIcon(new ThemeResource("icons/error.png"));
//		taskInfoDisplayControlLayout.addComponent(taskDeleteButton);
		
		taskDisplayLayout.addComponent(taskDisplayLabel);
		taskDisplayLayout.addComponent(taskDeleteButton);
		taskDisplayLayout.setComponentAlignment(taskDeleteButton, Alignment.MIDDLE_RIGHT);
		
		assignmentLayout.setSpacing(true);
//		assignmentLayout.addComponent(assignerLabel);
//		assignmentLayout.addComponent(duetoLabel);
		taskCardLayout.addComponent(taskDisplayLayout);
		taskCardLayout.addComponent(assignmentLayout);
		taskCardLayout.setComponentAlignment(assignmentLayout, Alignment.MIDDLE_RIGHT);
		
		this.addComponent(taskCardLayout);
	}
	
	@Override
	public void layoutClick(LayoutClickEvent event) {
		if(!isEditPanel) {
			setDisplay(true);
		}
	}
	
	public void setDisplay(boolean newEditPanel) {
		if(!newEditPanel) {
			String userName = ((BasicUser)getApplication().getUser()).getRealName();
			if(userCombox.getValue() != null)
				userName = ((BasicUser)userCombox.getValue()).getRealName();
			String expireDate = DateUtils.date2String(DateUtils.addDay(new Date(),1), "MM-dd");
			if(expireDateField.getValue() != null)
				expireDate = DateUtils.date2String((Date) expireDateField.getValue(), "MM-dd");
			taskDisplayLabel.setValue(StrUtils.asciiToXhtml(taskField.getValue().toString()) 
					+ "&nbsp;<span style=\""+ InnerStyle.todo_assigner +"\">" + userName + "</span>&nbsp;"
					+ "<span style=\""+ InnerStyle.todo_dueto +"\">" + expireDate + "</span>");
			taskDisplayLayout.setVisible(true);
			taskEditorLayout.setVisible(false);
			this.removeStyleName("task-panel");
		} else {
			taskDisplayLayout.setVisible(false);
			taskEditorLayout.setVisible(true);
			if(isAdded()) {
				addTaskButton.setCaption("Save");
			}
			this.addStyleName("task-panel");
		}
		this.isEditPanel = newEditPanel;
	}
	
	public boolean isDisplay() {
		return isEditPanel;
	}

	/**
	 * @return the addTaskButton
	 */
	public Button getAddTaskButton() {
		return addTaskButton;
	}

	/**
	 * @return the cancelTaskButton
	 */
	public Button getCancelTaskButton() {
		return cancelTaskButton;
	}

	/**
	 * @return the added
	 */
	public boolean isAdded() {
		return added;
	}

	/**
	 * @param added the added to set
	 */
	public void setAdded(boolean added) {
		this.added = added;
	}

	/**
	 * @return the contentLayoutWidth
	 */
	public String getContentLayoutWidth() {
		return contentLayoutWidth;
	}

	/**
	 * @param contentLayoutWidth the contentLayoutWidth to set
	 */
	public void setContentLayoutWidth(String contentLayoutWidth) {
		this.contentLayoutWidth = contentLayoutWidth;
	}

	/**
	 * @return the contentWidth
	 */
	public String getContentWidth() {
		return contentWidth;
	}

	/**
	 * @param contentWidth the contentWidth to set
	 */
	public void setContentWidth(String contentWidth) {
		this.contentWidth = contentWidth;
	}

	/**
	 * @return the subtaskContentWidth
	 */
	public String getSubtaskContentWidth() {
		return subtaskContentWidth;
	}

	/**
	 * @param subtaskContentWidth the subtaskContentWidth to set
	 */
	public void setSubtaskContentWidth(String subtaskContentWidth) {
		this.subtaskContentWidth = subtaskContentWidth;
	}

}
