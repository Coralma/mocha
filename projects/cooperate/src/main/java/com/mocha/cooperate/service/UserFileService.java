/**
 * 
 */
package com.mocha.cooperate.service;

import java.io.File;
import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.utils.FileUtils;
import com.google.common.collect.Lists;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.Attachment;

/**
 * @author Coral.Ma
 *
 */
public class UserFileService extends AbstractFileService implements IFileService {

	private BasicUser currentUser;
	
	public UserFileService(BasicUser currentUser) {
		this.currentUser = currentUser;
	}
	
	public String generateFilePath(String folderPath, String fileName) {
		return getUserFolderPath(folderPath) + "/" + fileName;
	}
	
	public String createDefaultPhysicalFolder() {
		return createPhysicalFolder(getUserFolderPath(null));
	}

	private String getUserFolderPath(String folderPath) {
		String defaultFolder = SystemProperty.FILE_PATH + currentUser.getAccount().getName() + "/" + currentUser.getUserName() + "/" + SystemProperty.FILE_Folder +  "/";
		if(folderPath == null) {
			return defaultFolder;
		} else {
			return defaultFolder + "/" + folderPath + "/";
		}
	}
	
	public List<com.mocha.cooperate.model.File> loadFiles() {
		return loadFiles(null);
	}
	
	public List<com.mocha.cooperate.model.File> loadFiles(Long folderId) {
		List<com.mocha.cooperate.model.File> files = fileDao.loadFiles(currentUser, folderId);
		List<com.mocha.cooperate.model.File> allFiles = Lists.newArrayList();
		List<com.mocha.cooperate.model.File> physicalFiles = Lists.newArrayList();
		for(com.mocha.cooperate.model.File file : files) {
			if(FileUtils.isFolder(file.getPhysicalType())) {
				allFiles.add(file);
			} else {
				physicalFiles.add(file);
			}
		}
		allFiles.addAll(physicalFiles);
		return allFiles;
	}
	
	/**
	 * Search file
	 */
	public List<com.mocha.cooperate.model.File> fuzzySearchFile(String condition) {
		return fileDao.fuzzySearchFile(currentUser, condition);
	}
	
	public com.mocha.cooperate.model.File findFileByShareKey(String shareKey) {
		return fileDao.findFileByShareKey(shareKey);
	}
	
	public String userAttachmentFolder(BasicUser basicUser) {
		return SystemProperty.getUserAttachmentFolder(basicUser); 
	}
	
	public void removeAttachment(Attachment attachment, BasicUser basicUser) {
		try {
			File file = new File(userAttachmentFolder(basicUser) + attachment.getFileName());
			if(file.isFile()) {
				file.delete();
			}
			//TODO Remove the attached data of database. 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public File getFile(Attachment attachment, BasicUser basicUser) {
		try {
			File file = new File(attachment.getFilePath());
			if(file.isFile()) {
				return file;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
