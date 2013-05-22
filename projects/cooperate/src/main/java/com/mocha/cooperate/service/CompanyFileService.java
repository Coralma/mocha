/**
 * 
 */
package com.mocha.cooperate.service;

import java.util.List;

import com.coral.foundation.utils.FileUtils;
import com.google.common.collect.Lists;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.model.File;

/**
 * @author Coral
 *
 */
public class CompanyFileService extends AbstractFileService implements IFileService {
	
	private String accountName;
	
	public CompanyFileService(String accountName) {
		this.accountName = accountName;
	}
	
	public String createDefaultPhysicalFolder() {
		return createPhysicalFolder(getUserFolderPath(null));
	}

	private String getUserFolderPath(String folderPath) {
		String defaultFolder = SystemProperty.FILE_PATH + accountName + "/" + SystemProperty.FILE_Folder + "/";
		if(folderPath == null) {
			return defaultFolder;
		} else {
			return defaultFolder + "/" + folderPath + "/";
		}
	}
	
	public List<com.mocha.cooperate.model.File> loadFiles() {
		return loadFiles(null);
	}
	
	public void createFolder(com.mocha.cooperate.model.File file) {
		file.setAccountName(accountName);
		super.createFolder(file);
	}
	
	public void createFile(com.mocha.cooperate.model.File file) {
		file.setAccountName(accountName);
		super.createFile(file);
	}
	
	public List<com.mocha.cooperate.model.File> loadFiles(Long folderId) {
		List<com.mocha.cooperate.model.File> files = fileDao.loadCompanyFiles(accountName, folderId);
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

	@Override
	public List<File> fuzzySearchFile(String condition) {
		return fileDao.fuzzySearchFile(accountName, condition);
	}
}
