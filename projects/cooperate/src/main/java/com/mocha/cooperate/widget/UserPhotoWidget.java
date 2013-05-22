/**
 * 
 */
package com.mocha.cooperate.widget;

import java.io.File;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 *
 */
public class UserPhotoWidget extends CustomComponent {

	private Embedded userPhoto;
	private String imageWidth = "140px";
	private String imageHeight = "140px";
	private String url;
	private boolean hideName = false;
	private BasicUser basicUser;
	
	public UserPhotoWidget(BasicUser basicUser) {
		this.basicUser = basicUser;
	}
	
	public UserPhotoWidget(String url) {
		this.url = url;
	}
	
	@Override
    public void attach() {
		super.attach();
//		BasicUser basicUser = (BasicUser)getApplication().getUser();
		url = basicUser.getUserPhoto();
		
		VerticalLayout userInfoArea = new VerticalLayout();
		userInfoArea.setSizeFull();
		
		if(!isHideName()) {
			Label userName = new Label(basicUser.getRealName());
			userName.addStyleName("user-name-title");
			userInfoArea.addComponent(userName);
		}
		
		VerticalLayout photoBackground = new VerticalLayout();
		photoBackground.setWidth("160px");
		photoBackground.setHeight("160px");
		photoBackground.addStyleName("user-photo");
		userPhoto = PageBuildHelper.buildUserPhoto(url, getApplication());
//		if(url == null) {
//			userPhoto = new Embedded(null, new ThemeResource("images/user_photo.jpg"));
//		} else {
//			File imageFile = new File(url);
//			FileResource fResource = new FileResource(imageFile, getApplication());
//			userPhoto = new Embedded(null, fResource);
//		}
		userPhoto.setWidth(imageWidth);
		userPhoto.setHeight(imageHeight);
		photoBackground.addComponent(userPhoto);
		userInfoArea.addComponent(photoBackground);
		setCompositionRoot(userInfoArea);
	}

	/**
	 * @return the imageWidth
	 */
	public String getImageWidth() {
		return imageWidth;
	}

	/**
	 * @param imageWidth the imageWidth to set
	 */
	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}

	/**
	 * @return the imageHeight
	 */
	public String getImageHeight() {
		return imageHeight;
	}

	/**
	 * @param imageHeight the imageHeight to set
	 */
	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the hideName
	 */
	public boolean isHideName() {
		return hideName;
	}

	/**
	 * @param hideName the hideName to set
	 */
	public void setHideName(boolean hideName) {
		this.hideName = hideName;
	}
}
