/**
 * 
 */
package com.mocha.cooperate.page.admin;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.listener.EnterClickListener;
import com.coral.vaadin.widget.view.CommonViewer;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.cooperate.page.event.UserPermissionListener;
import com.mocha.cooperate.widget.PagingVerticalLayout;
import com.mocha.cooperate.widget.UserPhotoUploadWidget;
import com.vaadin.data.Property;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

/**
 * @author Administrator
 *
 */
public class UserPermissionViewer extends CommonViewer implements ClickListener, EnterClickListener {

	private static final long serialVersionUID = -2872342575797183404L;
	protected BasicUser adminUser;
	protected VerticalLayout userOperationContentPanel = new VerticalLayout();
	protected PagingVerticalLayout usersListPanel = new PagingVerticalLayout(); 
	protected TextField searchField;
	protected List<BasicUser> userList;
	protected UserPermissionListener listener;
	
	public UserPermissionViewer(BasicUser adminUser) {
		this.adminUser = adminUser;
		this.setSpacing(true);
	}

	@Override
	public void attach() {
		super.attach();
//		ToolbarAdvance toolbar = new ToolbarAdvance();
//		Button userBtn = WidgetFactory.createLink("User", "user", this);
//		toolbar.addLeftComponent(userBtn);
//		// for next step
//		toolbar.addLeftComponent(WidgetFactory.createLink("Role", "role", this));
//		toolbar.addLeftComponent(WidgetFactory.createLink("Position", "position", this));
//		toolbar.addLeftComponent(WidgetFactory.createLink("Group", "group", this));
//		
//		this.addComponent(toolbar);
		userOperationContentPanel.setWidth("100%");
		userOperationContentPanel.setSpacing(true);
		usersListPanel.setWidth("100%");
		usersListPanel.setSpacing(true);
		usersListPanel.setMoreSize(userList.size());
		this.addComponent(userOperationContentPanel);
		
		this.buildUserPanel();
	}
	
	public void buildUserPanel() {
		userOperationContentPanel.removeAllComponents();
		ToolbarAdvance toolbar = new ToolbarAdvance();
		toolbar.addLeftComponent(WidgetFactory.createLink(message.getString("cooperate.up.ActiveUsers"),"activeUsers", this));
		toolbar.addLeftComponent(WidgetFactory.createLink(message.getString("cooperate.up.InactiveUsers"), "inactiveUsers", this));
		searchField = WidgetFactory.createSearchTextField(message.getString("cooperate.search"),"searchUsers",this);
		toolbar.addLeftComponent(searchField);
		toolbar.addRightComponent(WidgetFactory.createButton(message.getString("cooperate.up.NewUser"), "newUser", this));
		
		userOperationContentPanel.addComponent(toolbar);
		userOperationContentPanel.addComponent(usersListPanel);
		buildUserListPanel();
	}
	
	public void buildUserListPanel() {
		usersListPanel.removeAllComponents();
		// Loop Active users
		addUserToPanel(userList);
		usersListPanel.setMoreSize(userList.size());
	}
	
	private void addUserToPanel(List<BasicUser> basicUsers) {
		// Loop Active users
		for(BasicUser user : basicUsers) {
			UserCard userCard = new UserCard(user);
			usersListPanel.addComponent(userCard);
		}
	}
	
	public void moreUserList(List<BasicUser> basicUsers) {
		userList.addAll(basicUsers);
		addUserToPanel(basicUsers);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		String btnData = (String)event.getButton().getData();
		if("newUser".equals(btnData)) {
			UserEditorWindow userEditorWindow = new UserEditorWindow(new BasicUser(), message.getString("cooperate.up.NewUser"));
			this.getWindow().addWindow(userEditorWindow);
		} else if("activeUsers".equals(btnData)) {
			listener.showActiveUsers();
		} else if("inactiveUsers".equals(btnData)) {
			listener.showInActiveUsers();
		}
	}
	
	@Override
	public void handleEnter(String text) {
		listener.searchUser(text);
	}

	@Override
	public String getViewerTitle() {
		return "cooperate.up.title";
	}
	
	public class UserCard extends HorizontalLayout implements LayoutClickListener {
		
		private BasicUser user;
		private String frame_size = "90px";
		private String photo_size = "80px";

		public UserCard(BasicUser user) {
			this.user = user;
			this.setWidth("726px");
			this.addStyleName("user-info-card");
			this.addListener(this);
		}
		
		public void attach() {
			VerticalLayout userPhotoPanel = new VerticalLayout();
			userPhotoPanel.addStyleName("user-info-photo");
				VerticalLayout photoAreaPanel = new VerticalLayout();
				photoAreaPanel.addStyleName("user-photo-moderate");
				photoAreaPanel.setWidth(frame_size);
				photoAreaPanel.setHeight(frame_size);
				Embedded userPhoto = PageBuildHelper.buildUserPhoto(user.getUserPhoto(), getApplication());
				userPhoto.setWidth(photo_size);
				userPhoto.setHeight(photo_size);
				photoAreaPanel.addComponent(userPhoto);
			userPhotoPanel.addComponent(photoAreaPanel);
			this.addComponent(userPhotoPanel);
			
			VerticalLayout userInfoPanel = new VerticalLayout();
			userInfoPanel.setWidth("520px");
			userInfoPanel.addStyleName("user-info-detail");
			Label userNameLabel = new Label(user.getRealName());
			userNameLabel.addStyleName("user-name-caption");
			userInfoPanel.addComponent(userNameLabel);
			
			HorizontalLayout userDetailPanel = new HorizontalLayout();
			userDetailPanel.setWidth("520px");
			FormLayout basicInfo = new FormLayout();
			basicInfo.setSpacing(true);
//			basicInfo.setCaption(user.getRealName());
			basicInfo.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.JobTitle"), WidgetFactory.createProperty(user, "jobTitle")));
			basicInfo.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Email"), WidgetFactory.createProperty(user, "email")));
			basicInfo.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Mobile"), WidgetFactory.createProperty(user, "mobile")));
			userDetailPanel.addComponent(basicInfo);

			FormLayout basicInfo2 = new FormLayout();
			basicInfo2.setSpacing(true);
			//FIXME the basic role should always existed.
			basicInfo2.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Extension"), WidgetFactory.createProperty(user, "extension")));
			basicInfo2.addComponent(WidgetFactory.createCaptionLabel(message.getString("cooperate.up.Role"), user.getBasicRole()==null ? "User" : user.getBasicRole().getRoleName()));
//			basicInfo2.addComponent(WidgetFactory.createCaptionLabel("Status", WidgetFactory.createProperty(user, "status")));
			userDetailPanel.addComponent(basicInfo2);
			userInfoPanel.addComponent(userDetailPanel);
			this.addComponent(userInfoPanel);
		}

		@Override
		public void layoutClick(LayoutClickEvent event) {
			UserEditorWindow userEditorWindow = new UserEditorWindow(user, message.getString("cooperate.up.EditUser"));
			this.getWindow().addWindow(userEditorWindow);
		}
	}
	
	public class UserEditorWindow extends Window implements ClickListener {
		
		private BasicUser user;
		private UserPhotoUploadWidget photoUploader;
		private Button saveButton;
		private Button cancelButton;
		
		public UserEditorWindow(BasicUser user, String caption) {
			super();
			this.user = user;
			this.setCaption(caption);
			this.center();
			this.addStyleName("mocha-app");
			this.setWidth("400px");
			this.setClosable(false);
			this.setResizeLazy(true);
			this.setResizable(false);
			this.setModal(true);
			this.addStyleName(Reindeer.WINDOW_LIGHT);
		}
		
		public void attach() {
			VerticalLayout layout = new VerticalLayout();
			FormLayout editor = new FormLayout();
			editor.addComponent(WidgetFactory.createTextField(message.getString("cooperate.up.RealName"), WidgetFactory.createProperty(user, "realName")));
			
			if(user.getID() != null) {
				photoUploader = new UserPhotoUploadWidget(user);
				photoUploader.setCaption(message.getString("cooperate.up.Avatar"));
				photoUploader.setData("userPhoto");
				editor.addComponent(photoUploader);
			}
			
			editor.addComponent(WidgetFactory.createTextField(message.getString("cooperate.up.LoginName"), WidgetFactory.createProperty(user, "userName")));
			editor.addComponent(WidgetFactory.createTextField(message.getString("cooperate.up.InitPassword"), WidgetFactory.createProperty(user, "password")));
			editor.addComponent(WidgetFactory.createTextField(message.getString("cooperate.up.JobTitle"), WidgetFactory.createProperty(user, "jobTitle")));
			editor.addComponent(WidgetFactory.createTextField(message.getString("cooperate.up.Extension"), WidgetFactory.createProperty(user, "extension")));
			editor.addComponent(WidgetFactory.createTextField(message.getString("cooperate.up.Email"), WidgetFactory.createProperty(user, "email")));
			editor.addComponent(WidgetFactory.createTextField(message.getString("cooperate.up.Mobile"), WidgetFactory.createProperty(user, "mobile")));
//			editor.addComponent(WidgetFactory.createCaptionLabel("Role", user.getEmail())); //FIXME add the role choose combo.
			Property property = WidgetFactory.createProperty(user, "language");
			ComboBox language = WidgetFactory.createLanguageCombo(message.getString("cooperate.up.Language"), property);
			editor.addComponent(language);
			layout.addComponent(editor);

			// control buttons
			HorizontalLayout buttonLayout = new HorizontalLayout();
			buttonLayout.setSpacing(true);
			saveButton = WidgetFactory.createButton(message.getString("cooperate.save"), "save", this);
			cancelButton = WidgetFactory.createButton(message.getString("cooperate.cancel"), "cancel", this);
			buttonLayout.addComponent(saveButton);
			buttonLayout.addComponent(cancelButton);
			
			layout.addComponent(buttonLayout);
			layout.setComponentAlignment(buttonLayout, Alignment.MIDDLE_RIGHT);
			this.addComponent(layout);
		}

		@Override
		public void buttonClick(ClickEvent event) {
			if(saveButton.equals(event.getButton())) {
				if(photoUploader != null) {
					String photoUrl = (String)photoUploader.getValue();
					if(!StrUtils.isEmpty(photoUrl)) {
						user.setUserPhoto(photoUrl);
					}
				}
				listener.saveUser(user);
				this.close();
			}
			if(cancelButton.equals(event.getButton())) {
				this.close();
			}
		}
	}
	
	/**
	 * @return the activeUsers
	 */
	public List<BasicUser> getActiveUsers() {
		return userList;
	}

	/**
	 * @param activeUsers the activeUsers to set
	 */
	public void setActiveUsers(List<BasicUser> activeUsers) {
		this.userList = activeUsers;
	}

	/**
	 * @return the listener
	 */
	public UserPermissionListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(UserPermissionListener listener) {
		this.listener = listener;
	}

	/**
	 * @return the usersListPanel
	 */
	public PagingVerticalLayout getUsersListPanel() {
		return usersListPanel;
	}

	/**
	 * @return the userList
	 */
	public List<BasicUser> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<BasicUser> userList) {
		this.userList = userList;
	}

}
