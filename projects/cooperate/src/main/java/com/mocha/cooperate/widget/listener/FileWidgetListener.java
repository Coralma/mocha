/**
 * 
 */
package com.mocha.cooperate.widget.listener;

import com.mocha.cooperate.model.File;

/**
 * @author Coral.Ma
 *
 */
public interface FileWidgetListener {

	public void folderClick(File file);
	public void folderRowClick(File file);
	public void shareFile(File file);
	public void deleteFile(File file);
}
