/**
 * 
 */
package com.mocha.cooperate.page.admin;

import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.widget.FileWidget;
import com.mocha.cooperate.widget.wrap.FileWidgetWrap;

/**
 * @author Coral
 *
 */
public class CompanyFileViewer extends CommonViewer implements Viewer {

	private static final long serialVersionUID = -724745435795411542L;

	private FileWidget fileWidget;
//	private List<File> files;
	private String currentFolder;
	private Long currentFolderId;
	
//	public CompanyFileViewer(List<File> files) {
//		this.files = files;
//	}
	
	public void attach() {
		super.attach();
		BasicUser user = (BasicUser)getApplication().getUser();
		FileWidgetWrap fileWidgetWrap = new FileWidgetWrap(user.getAccount().getName());
		fileWidget = fileWidgetWrap.getFileWidget();
		addComponent(fileWidget);
	}

//	/**
//	 * @return the files
//	 */
//	public List<File> getFiles() {
//		return files;
//	}
//
//	/**
//	 * @param files the files to set
//	 */
//	public void setFiles(List<File> files) {
//		this.files = files;
//	}

	/**
	 * @return the currentFolder
	 */
	public String getCurrentFolder() {
		return currentFolder;
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
	
	@Override
	public String getViewerTitle() {
		return "cooperate.file.companyPublicFile";
	}

}
