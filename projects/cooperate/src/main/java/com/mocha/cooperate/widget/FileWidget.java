/**
 * 
 */
package com.mocha.cooperate.widget;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.Message;
import com.coral.vaadin.widget.component.SearchTextField;
import com.mocha.cooperate.model.File;
import com.mocha.cooperate.widget.listener.FileWidgetListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Coral
 *
 */
public class FileWidget extends VerticalLayout {
	
	private List<File> files;
	private BasicUser user;
	
	private BreadCrumb breadCrumb = new BreadCrumb();
	private FileUpload fileUpload = new FileUpload();
	private NativeButton newFolderButton = new NativeButton();
	private TextField searchField = new TextField();
	private FileWidgetListener fileWidgetListener;
	private FileListWidget fileWidget;
	private String currentFolder;
	private Long currentFolderId;
	private Message message;
	
	public FileWidget(List<File> files, BasicUser user) {
		this.files = files;
		this.user = user;
		this.setSpacing(true);
	}
	
	public void attach() {
		message = new Message(getApplication().getLocale());
		HorizontalLayout fileController = new HorizontalLayout();
		fileController.setWidth("768px");
		fileController.addComponent(breadCrumb);
		fileController.setComponentAlignment(breadCrumb,Alignment.MIDDLE_LEFT);
		
		HorizontalLayout buttonController = new HorizontalLayout();
		buttonController.setSpacing(true);
		
		fileUpload.setButtonCaption(message.getString("cooperate.file.upload"));
		fileUpload.addStyleName("file-upload-button");
		fileUpload.setCurrentFolder(currentFolder);
		buttonController.addComponent(fileUpload);
		newFolderButton.setCaption(message.getString("cooperate.file.newFolder"));
		newFolderButton.addStyleName("mocha-button");
		buttonController.addComponent(newFolderButton);
		searchField.setInputPrompt(message.getString("cooperate.search"));
		searchField.setWidth("120px");
		searchField.setNullRepresentation("");
		buttonController.addComponent(searchField);
		buttonController.setComponentAlignment(searchField, Alignment.MIDDLE_LEFT);
		
		fileController.addComponent(buttonController);
		fileController.setComponentAlignment(buttonController, Alignment.MIDDLE_RIGHT);
		addComponent(fileController);
		
		fileWidget = new FileListWidget(files, user);
		fileWidget.setFileWidgetListener(fileWidgetListener);
		addComponent(fileWidget);
	}
	
	/**
	 * @return the breadCrumb
	 */
	public BreadCrumb getBreadCrumb() {
		return breadCrumb;
	}

	/**
	 * @return the newFolderButton
	 */
	public Button getNewFolderButton() {
		return newFolderButton;
	}

	/**
	 * @return the searchField
	 */
	public TextField getSearchField() {
		return searchField;
	}

	/**
	 * @return the files
	 */
	public List<File> getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(List<File> files) {
		this.files = files;
	}

	/**
	 * @return the fileWidget
	 */
	public FileListWidget getFileListWidget() {
		return fileWidget;
	}

	/**
	 * @return the fileUpload
	 */
	public FileUpload getFileUpload() {
		return fileUpload;
	}

	/**
	 * @return the currentFolder
	 */
	public String getCurrentFolder() {
		return currentFolder;
	}

	/**
	 * @param currentFolder the currentFolder to set
	 */
	public void setCurrentFolder(String currentFolder) {
		this.currentFolder = currentFolder;
		fileUpload.setCurrentFolder(currentFolder);
	}

	/**
	 * @return the currentFolderId
	 */
	public Long getCurrentFolderId() {
		return currentFolderId;
	}

	/**
	 * @param currentFolderId the currentFolderId to set
	 */
	public void setCurrentFolderId(Long currentFolderId) {
		this.currentFolderId = currentFolderId;
	}

	/**
	 * @return the fileWidgetListener
	 */
	public FileWidgetListener getFileWidgetListener() {
		return fileWidgetListener;
	}

	/**
	 * @param fileWidgetListener the fileWidgetListener to set
	 */
	public void setFileWidgetListener(FileWidgetListener fileWidgetListener) {
		this.fileWidgetListener = fileWidgetListener;
	}
}
