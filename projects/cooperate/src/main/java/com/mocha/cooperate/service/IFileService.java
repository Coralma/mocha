/**
 * 
 */
package com.mocha.cooperate.service;

import java.util.List;

/**
 * @author Coral
 *
 */
public interface IFileService {

	public String createDefaultPhysicalFolder();
	public void createFile(com.mocha.cooperate.model.File file);
	public void createFolder(com.mocha.cooperate.model.File file);
	public List<com.mocha.cooperate.model.File> loadFiles();
	public List<com.mocha.cooperate.model.File> loadFiles(Long folderId);
	public com.mocha.cooperate.model.File loadFileById(Long id);
	public void updateFile(com.mocha.cooperate.model.File file);
	public void delete(com.mocha.cooperate.model.File file);
	public List<com.mocha.cooperate.model.File> fuzzySearchFile(String condition);
}
