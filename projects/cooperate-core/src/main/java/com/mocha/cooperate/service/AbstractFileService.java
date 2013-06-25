package com.mocha.cooperate.service;

import java.io.File;
import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.FileUtils;
import com.mocha.cooperate.basic.dao.FileDao;

public abstract class AbstractFileService {

	protected FileDao fileDao = SpringContextUtils.getBean(FileDao.class);

	/**
	 * Get the file list by given user.
	 */
	public List<com.mocha.cooperate.model.File> loadFiles(BasicUser user, Long folderId) {
		return fileDao.loadFiles(user, null);
	}
	
	public com.mocha.cooperate.model.File loadFileById(Long id) {
		return fileDao.findById(id);
	}
	
	public String createPhysicalFolder(String folderPath) {
		FileUtils.createDir(folderPath);
		return folderPath;
	}
	
	public void createFolder(com.mocha.cooperate.model.File file) {
		file.setType("FOLDER"); //FIXME to i18n
		file.setPhysicalType(new Long(1));
		FileUtils.createDir(file.getPath());
		fileDao.merge(file);
	}
	
	public void createFile(com.mocha.cooperate.model.File file) {
		if(file.getType() == null) {
			file.setType(generateTypeByFileName(file.getName()));
		}
		fileDao.merge(file);
	}
	
	private String generateTypeByFileName(String fileName) {
		String type = null;
		int index = fileName.lastIndexOf(".");
		if(index > 0) {
			type = fileName.substring(index+1);
		}
		return type.toUpperCase();
	}
	
	public void updateFile(com.mocha.cooperate.model.File file) {
		fileDao.merge(file);
	}
	
	/**
	 * Delete file.
	 * @param file
	 */
	public void delete(com.mocha.cooperate.model.File file) {
		try {
			fileDao.remove(file.getFileId());
			File physicalFile = new File(file.getPath());
			if(physicalFile.isFile() || physicalFile.isDirectory()) {
				physicalFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
