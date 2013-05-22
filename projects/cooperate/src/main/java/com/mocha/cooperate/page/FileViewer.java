package com.mocha.cooperate.page;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.component.SearchTextField;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.File;
import com.mocha.cooperate.widget.BreadCrumb;
import com.mocha.cooperate.widget.FileUpload;
import com.mocha.cooperate.widget.FileListWidget;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class FileViewer extends CommonViewer implements Viewer {

	private static final long serialVersionUID = -724745435795411542L;

	private BreadCrumb breadCrumb = new BreadCrumb();
	private FileUpload fileUpload = new FileUpload();
	private NativeButton newFolderButton = new NativeButton();
	private TextField searchField = new TextField();
	private FileListWidget fileWidget;
	private List<File> files;
	private String currentFolder;
	private Long currentFolderId;
	
	public FileViewer(List<File> files) {
		this.files = files;
	}
	
	@Override
	public String getViewerTitle() {
		return "cooperate.file.title";
	}

	public void attach() {
		super.attach();
		HorizontalLayout fileController = new HorizontalLayout();
		fileController.setWidth("768px");
//		breadCrumb.setMessage(message);
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
		
		fileWidget = new FileListWidget(files, (BasicUser)getApplication().getUser());
		addComponent(fileWidget);
	}
	
	public void setFileEditDisplay(boolean isDisplay) {
		fileUpload.setVisible(isDisplay);
		newFolderButton.setVisible(isDisplay);
		fileWidget.setAllowDelete(isDisplay);
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
	
	public void cleanSearchField() {
		searchField.setValue(null);
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
	public FileListWidget getFileWidget() {
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
}
