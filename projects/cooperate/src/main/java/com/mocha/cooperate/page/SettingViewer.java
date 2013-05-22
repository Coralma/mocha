/**
 * 
 */
package com.mocha.cooperate.page;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Widget;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.ToolbarAdvance;
import com.coral.vaadin.widget.helper.NotificationHelper;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.page.event.SettingListener;
import com.mocha.cooperate.widget.UserPhotoUploadWidget;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 *
 */
public class SettingViewer extends CommonViewer implements ClickListener {

	private VerticalLayout settingContentPanel = new VerticalLayout();
	private Widget photoUploader;
	private Button changeButton;
	private String labelWidth = "120px";
	private BasicUser user;
	private SettingListener listener;
	
	private String BASIC_INFO = "BasicInfo";
	private String CHANGE_PASSWORD = "ChangePassword";
	
	public SettingViewer(BasicUser user) {
		this.addStyleName("mocha-coding-viewer");
		this.setEntityClass(BasicUser.class);
		this.user = user;
	}
	
	@Override
	public void attach() {
		ToolbarAdvance toolbar = new ToolbarAdvance();
		Button basicInfoBtn = WidgetFactory.createLink("Basic Information", "BasicInfo", this);
		toolbar.addRightComponent(basicInfoBtn);
		Button changePassword = WidgetFactory.createLink("Change Password", "ChangePassword", this);
		toolbar.addRightComponent(changePassword);
		this.addComponent(toolbar);

		settingContentPanel.setWidth("100%");
		this.addComponent(settingContentPanel);
		
		this.buildBasicInformationPanel();
	}
	
	public void buildBasicInformationPanel() {
		settingContentPanel.removeAllComponents();
		
		VerticalLayout settingSection = new VerticalLayout();
		settingSection.addStyleName("setting-user-info");
		settingSection.setWidth("500px");
		
		FormLayout userFormLayout = new FormLayout();
		userFormLayout.setCaption(user.getRealName() + "'s account");
		userFormLayout.setSpacing(true);
		Label realname = WidgetFactory.createCaptionLabel("Real Name", user.getRealName());
		userFormLayout.addComponent(realname);
		
		photoUploader = new UserPhotoUploadWidget(user);
		photoUploader.setCaption("Avatar");
		photoUploader.setData("userPhoto");
		userFormLayout.addComponent(photoUploader);
		
		Label email = WidgetFactory.createCaptionLabel("Email Address", user.getEmail());
		userFormLayout.addComponent(email);
		
		Property property = WidgetFactory.createProperty(user, "language");
		ComboBox language = WidgetFactory.createLanguageCombo("Language", property);
		userFormLayout.addComponent(language);
		
		changeButton = WidgetFactory.createButton("Change Setting");
		changeButton.addListener(this);
		userFormLayout.addComponent(changeButton);
		settingSection.addComponent(userFormLayout);
		
		settingContentPanel.addComponent(settingSection);
	}
	
	public void buildChangePasswordPanel() {
		settingContentPanel.removeAllComponents();
		
		VerticalLayout passwordSection = new VerticalLayout();
		passwordSection.addStyleName("setting-user-info");
		passwordSection.setWidth("500px");
		
		FormLayout paswordFormLayout = new FormLayout();
		paswordFormLayout.setCaption("Change " + user.getRealName() + "'s password");
		paswordFormLayout.setSpacing(true);
		final PasswordField currentPassword = WidgetFactory.createPasswordField("Current Password");
		final PasswordField newPassword = WidgetFactory.createPasswordField("New Password");
		final PasswordField confirmPassword = WidgetFactory.createPasswordField("Confirm New Password");
		
		paswordFormLayout.addComponent(currentPassword);
		paswordFormLayout.addComponent(newPassword);
		paswordFormLayout.addComponent(confirmPassword);
		
		Button changePasswordButton = WidgetFactory.createButton("Change Setting");
		changePasswordButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				String cp = (String)currentPassword.getValue();
				String np = (String)newPassword.getValue();
				String cnp = (String)confirmPassword.getValue();
				if(StrUtils.isEmpty(cp) || StrUtils.isEmpty(np) || StrUtils.isEmpty(cnp)) {
					SettingViewer.this.getWindow().showNotification(NotificationHelper.getErrorNotification("Please fill-in the password."));
					return;
				}
				if(!cp.equals(user.getPassword())) {
					SettingViewer.this.getWindow().showNotification(NotificationHelper.getErrorNotification("Your current password is not correct."));
					return;
				}
				if(!np.equals(cnp)) {
					SettingViewer.this.getWindow().showNotification(NotificationHelper.getErrorNotification("The new password should be same as the confirm new password."));
					return;
				}
				listener.passwordChange(np);
			}
		});
		paswordFormLayout.addComponent(changePasswordButton);
		
		passwordSection.addComponent(paswordFormLayout);
		settingContentPanel.addComponent(passwordSection);
	}
	
	
	@Override
	public String getViewerTitle() {
		return "Setting";
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(changeButton)) {
			listener.userInfoChange();
		}
		Object data = event.getButton().getData();
		if(BASIC_INFO.equals(data)) {
			buildBasicInformationPanel();
		} else if(CHANGE_PASSWORD.equals(data)) {
			buildChangePasswordPanel();
		}
	}

	/**
	 * @return the listener
	 */
	public SettingListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(SettingListener listener) {
		this.listener = listener;
	}

	/**
	 * @return the user
	 */
	public BasicUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(BasicUser user) {
		this.user = user;
	}

	/**
	 * @return the photoUploader
	 */
	public Widget getPhotoUploader() {
		return photoUploader;
	}

}
