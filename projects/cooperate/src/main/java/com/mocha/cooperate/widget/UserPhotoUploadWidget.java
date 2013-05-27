/**
 * 
 */
package com.mocha.cooperate.widget;

import java.io.File;

import org.vaadin.easyuploads.FileFactory;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.easyuploads.UploadField.FieldType;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.FileUtils;
import com.coral.vaadin.widget.Field;
import com.coral.vaadin.widget.Result;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.coral.vaadin.widget.view.builder.PageBuildHelper;
import com.mocha.cooperate.SystemProperty;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.FileResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Administrator
 * 
 */
public class UserPhotoUploadWidget extends HorizontalLayout implements Field {

	private Embedded userPhoto;
	private UploadField uploadField;
	private BasicUser user;

	public UserPhotoUploadWidget(BasicUser user) {
		this.user = user;
	}
	
	public void attach() {
		userPhoto = PageBuildHelper.buildUserPhoto(user.getUserPhoto(), getApplication());
		
		userPhoto.setWidth("80px");
		userPhoto.setHeight("80px");
		addComponent(userPhoto);
		
		VerticalLayout uploadLayout = new VerticalLayout();
		uploadLayout.addStyleName("user-photo-upload");
		uploadLayout.setWidth("100%");
		uploadField = new UploadField();
		uploadField.addStyleName("file-upload-button");
		uploadField.setWidth("200px");
		uploadField.setFieldType(FieldType.FILE);
		uploadField.setButtonCaption("Change Avatar");
		uploadField.setCaption(null);
		uploadField.setValidationVisible(false);
		uploadField.setFileDeletesAllowed(false);
		uploadField.setFileFactory(new FileFactory() {
			public File createFile(String fileName, String mimeType) {
				String userFolder = SystemProperty.getUserFolder(user);
				FileUtils.createDir(userFolder);
				File f = new File(userFolder + fileName);
				return f;
			}
		});
		uploadField.addListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				File file = (File)uploadField.getValue();
				FileResource fileResource = new FileResource(file, getApplication());
				Embedded newUserPhoto = new Embedded(null, fileResource);
				newUserPhoto.setWidth("80px");
				newUserPhoto.setHeight("80px");
				UserPhotoUploadWidget.this.replaceComponent(userPhoto, newUserPhoto);
				UserPhotoUploadWidget.this.requestRepaintAll();
				userPhoto = newUserPhoto;
				
			}
		});
		uploadLayout.addComponent(uploadField);
		addComponent(uploadLayout);
	}
	
	@Override
	public Object getValue() {
		File file = (File)uploadField.getValue();
		if(file != null) {
			return file.getPath();
		}
		return null;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Result validate(String type) {
		return new Result();
	}

	@Override
	public void addListener(ValueChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRequired(boolean required) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFieldWidth(String width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setError(boolean isError, String errorMsg) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FieldStatus getFieldStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFieldStatus(FieldStatus fieldStatus) {
		// TODO Auto-generated method stub
		
	}

}
