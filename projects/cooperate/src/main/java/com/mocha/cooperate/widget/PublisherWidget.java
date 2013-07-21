/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.List;
import java.util.Set;

import org.vaadin.hene.expandingtextarea.ExpandingTextArea;

import com.coral.foundation.core.impl.MochaEventBus;
import com.coral.foundation.email.EmailUtils;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.Message;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.helper.NotificationHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.Status;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.page.event.HomePageListener;
import com.mocha.cooperate.service.TimeLineService;
import com.mocha.email.cooperate.CooperateMailFactory;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 * 
 */
@SuppressWarnings("unchecked")
public class PublisherWidget extends VerticalLayout implements ClickListener {

	private static final long serialVersionUID = 3182399657534581927L;
	private String widgetWidth = SystemProperty.publishWidgetWidth;
	private VerticalLayout layout = new VerticalLayout();
	private AttachmentLayout attachmentLayout;
	private NativeButton updateStatusBtn;
	private NativeButton discussBtn;
	private NativeButton todoBtn;
	private NotifyTokenField tokenField;
	private NativeButton postBtn;

	private List<NativeButton> buttons = Lists.newArrayList();
	private IPublisher currentWidget;
	private TimeLineService timeLineService = new TimeLineService();
	private HomePageListener listener;
	private Message message;
	
	private BasicUser currentUser;

	public PublisherWidget(BasicUser currentUser) {
		this.currentUser = currentUser;
		this.setWidth("746px");
		this.addStyleName("publisher-widget");
		layout.addStyleName("c-publisher");
		layout.setSizeFull();
	}

	@Override
	public void attach() {
		message = new Message(getApplication().getLocale());
		// build function button.
		updateStatusBtn = createButton(message.getString("cooperate.publisher.newStatus"),"icons/status_icon.png");
		discussBtn = createButton(message.getString("cooperate.publisher.newDisucss"),"icons/discuss_icon.png");
		todoBtn = createButton(message.getString("cooperate.publisher.newTask"),"icons/todo-icon.png");
		changeSelectedStyle(updateStatusBtn);

		HorizontalLayout createTypeLayout = new HorizontalLayout();
		createTypeLayout.addStyleName("publisher-control");
		createTypeLayout.addComponent(updateStatusBtn);
		createTypeLayout.addComponent(discussBtn);
		createTypeLayout.addComponent(todoBtn);
		layout.addComponent(createTypeLayout);
		// build function widget.
		currentWidget = buildStatusInput();
		layout.addComponent(currentWidget);

		// set attachment layout into page.
		attachmentLayout = new AttachmentLayout((BasicUser)getApplication().getUser());
		layout.addComponent(attachmentLayout);
		// build post button area. 
		Layout attachLayout = buildPostArea();
		layout.addComponent(attachLayout);
		addComponent(layout);
	}
	
	public Layout buildPostArea() {
		HorizontalLayout attachLayout = new HorizontalLayout();
		attachLayout.addStyleName("attach-area");
		attachLayout.setWidth(widgetWidth);

		tokenField = new NotifyTokenField();
		tokenField.setInputPrompt(message.getString("cooperate.publisher.NotificateOthers"));
		tokenField.setWidth("600px");
		attachLayout.addComponent(tokenField);
		attachLayout.setComponentAlignment(tokenField, Alignment.BOTTOM_LEFT);

		// build attachment area
		HorizontalLayout referLayout = new HorizontalLayout();
		referLayout.setSpacing(true);
		AttachmentUpload upload = new AttachmentUpload(attachmentLayout, currentUser);
//		upload.setIcon(new ThemeResource("icons/file_icon.png"));
		referLayout.addComponent(upload);
		upload.setButtonCaption(message.getString("cooperate.publisher.File"));
		
		// build post button
		HorizontalLayout postLayout = new HorizontalLayout();
		postBtn = new NativeButton(message.getString("cooperate.publisher.Post"));
		postBtn.addStyleName("mocha-button");
		postBtn.setWidth("70px");
		postBtn.addListener(this);
		postLayout.addComponent(postBtn);
		attachLayout.addComponent(referLayout);
		attachLayout.setComponentAlignment(referLayout,
				Alignment.MIDDLE_RIGHT);
		attachLayout.addComponent(postLayout);
		attachLayout.setComponentAlignment(postLayout, Alignment.BOTTOM_RIGHT);
		return attachLayout;
	}

	public IPublisher buildStatusInput() {
		IPublisher inputArea = new StatusPublisher();
		inputArea.build();
		return inputArea;
	}

	public IPublisher buildDiscussInput() {
		IPublisher discussPublisher = new DiscussPublisher();
		discussPublisher.build();
		return discussPublisher;
	}

	public IPublisher buildTodoListInput() {
		IPublisher toDoPublisher = new TodoListPublisher();
//		toDoPublisher.build();
		return toDoPublisher;
	}

	public NativeButton createButton(String caption, String icon) {
		NativeButton btn = new NativeButton(caption);
		if(icon != null) {
			btn.setIcon(new ThemeResource(icon));
		}
		btn.setWidth("150px");
		btn.addListener(this);
		btn.addStyleName("p-button");
		buttons.add(btn);
		return btn;
	}

	public void changeSelectedStyle(NativeButton selected) {
		selected.addStyleName("v-nativebutton-selected");
		for (NativeButton nativeBtn : buttons) {
			if (!selected.equals(nativeBtn)) {
				nativeBtn.removeStyleName("v-nativebutton-selected");
			}
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(postBtn)) {
			TimeLine newTimeLine = null;
			Object value = currentWidget.getValue();
			if (value != null) {
				Set<BasicUser> notifyUsers = getNotifyUsers();
				BasicUser currentUser = (BasicUser)getApplication().getUser();
				List<Attachment> attachments = attachmentLayout.getAttachments();
				if (value instanceof Status) {
					newTimeLine = timeLineService.saveStatus((Status)value,currentUser, notifyUsers, attachments);
				} else if (value instanceof Discuss) {
					newTimeLine = timeLineService.saveDiscuss((Discuss)value, currentUser, notifyUsers, attachments);
				} else if (value instanceof ToDo) {
					notifyUsers = timeLineService.mergeTodoUser((ToDo)value, notifyUsers);
					newTimeLine = timeLineService.saveToDo((ToDo)value, currentUser, notifyUsers, attachments);
				}
				// send notification email for user
				for(BasicUser reciever : notifyUsers) {
					EmailUtils emailUtils = new EmailUtils(CooperateMailFactory.getNotifyEmail(currentUser, newTimeLine, reciever));
					emailUtils.start();
				}
				currentWidget.clean();
				attachmentLayout.clean();
				tokenField.setValue(null);
				listener.newPost(newTimeLine);
			} else {
				getWindow()
						.showNotification(
								NotificationHelper
										.getErrorNotification(message.getString("cooperate.publisher.inputError")));
			}
		} else {
			changeSelectedStyle((NativeButton) event.getButton());
			IPublisher selectedComponent = null;
			if (event.getButton().equals(updateStatusBtn)) {
				selectedComponent = buildStatusInput();
			} else if (event.getButton().equals(discussBtn)) {
				selectedComponent = buildDiscussInput();
			} else if (event.getButton().equals(todoBtn)) {
				selectedComponent = buildTodoListInput();
			}
			layout.replaceComponent(currentWidget, selectedComponent);
			currentWidget = selectedComponent;
		}
	}

	public interface IPublisher extends Component {
		public Object getValue();

		public void build();

		public void clean();
	}

	public class StatusPublisher extends VerticalLayout implements IPublisher {
		private static final long serialVersionUID = 6368116528860778993L;
		private ExpandingTextArea inputArea = new ExpandingTextArea();

		public StatusPublisher() {
			this.setWidth(widgetWidth);
		}

		@Override
		public void build() {
			inputArea.setWidth(widgetWidth);
			inputArea.setRows(2);
			inputArea.addStyleName("input-area");
			this.addComponent(inputArea);
		}

		@Override
		public Object getValue() {
			Object value = inputArea.getValue();
			if (StrUtils.isEmpty(value)) {
				return null;
			}
			Status status = new Status();
			status.setContent((String) value);
			status.setCreator((BasicUser) getApplication().getUser());
			return status;
		}

		@Override
		public void clean() {
			inputArea.setValue("");
		}
	}

	@SuppressWarnings("serial")
	public class DiscussPublisher extends VerticalLayout implements IPublisher {
		private TextField titleField = new TextField();
		private OptionGroup statusGroup;
		private RichTextArea inputArea = new RichTextArea();

		public DiscussPublisher() {
			this.addStyleName("discuss-input-area");
			this.setSpacing(true);
			this.setWidth(widgetWidth);
		}

		public void build() {
			titleField.setWidth(widgetWidth);
			titleField.setInputPrompt(message.getString("cooperate.publisher.topicTitle"));
			this.addComponent(titleField);

			List<String> statusList = getStatus();
			statusGroup = new OptionGroup(null, statusList);
			statusGroup.setValue(statusList.get(0));
			statusGroup.addStyleName("horizontal");
			this.addComponent(statusGroup);

			inputArea.setWidth(widgetWidth);
			inputArea.setHeight("200px");
			this.addComponent(inputArea);
		}
		
		public List<String> getStatus() {
			List<String> statusList = Lists.newArrayList();
			statusList.add(message.getString("cooperate.publisher.asDiscuss"));
			statusList.add(message.getString("cooperate.publisher.asQuestion"));
			statusList.add(message.getString("cooperate.publisher.asNotification"));
			return statusList;
		}

		@Override
		public Object getValue() {
			Object title = titleField.getValue();
			Object input = inputArea.getValue();
			if (StrUtils.isEmpty(title) || StrUtils.isEmpty(input)) {
				return null;
			}
			Discuss discuss = new Discuss();
			discuss.setTitle((String) title);
			discuss.setContent((String) input);
			// set discuss status
			Object status = statusGroup.getValue();
			List<String> statusList = getStatus();
			for(int i = 0; i < statusList.size(); i++) {
				String statusDef = statusList.get(i);
				if(statusDef.equals(status)) {
					discuss.setStatus(String.valueOf(i));
					break;
				}
			}
			discuss.setCreator((BasicUser) getApplication().getUser());
			return discuss;
		}

		@Override
		public void clean() {
			titleField.setValue("");
			inputArea.setValue("");
		}
	}

	/**
	 * Todo List Publisher
	 * @author Coral
	 */
	public class TodoListPublisher extends VerticalLayout implements IPublisher {
	
		private static final long serialVersionUID = -3411230743455414309L;
		private String widgetWidth = "745px";
		private String contentLayoutWidth = "745px";
		private String contentWidth = "745px";
		private String contentTextWidth = "743px";
		private ToDo todo = new ToDo();
		private TodoProjectEditor todoProjectEditor;
		
		public TodoListPublisher() {
			this.setWidth(widgetWidth);
			this.addStyleName("todo-publisher-border");
		}
	
		@Override
		public Object getValue() {
			return todoProjectEditor.getValue();
		}
	
		@Override
		public void attach() {
			build();
		}
		
		public void build() {
			todoProjectEditor = new TodoProjectEditor(todo, currentUser);	
			todoProjectEditor.setPublisherStatus(true);
			todoProjectEditor.setContentLayoutWidth(contentLayoutWidth);
			todoProjectEditor.setContentWidth(contentWidth);
			todoProjectEditor.setContentTextWidth(contentTextWidth);
			this.addComponent(todoProjectEditor);
		}
	
		@Override
		public void clean() {
			this.removeAllComponents();
			this.todo = new ToDo();
			this.build();
		}
		
	}

	/**
	 * @return the listener
	 */
	public HomePageListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(HomePageListener listener) {
		this.listener = listener;
	}

	/**
	 * @return the tokenField
	 */
	public Set<BasicUser> getNotifyUsers() {
		Set<BasicUser> notifyusers = (Set<BasicUser>)tokenField.getValue();
		if(notifyusers == null) {
			notifyusers = Sets.newHashSet();
		}
		return notifyusers;
	}
}
