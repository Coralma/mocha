/**
 * 
 */
package com.coral.foundation.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * 
 */
public class FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			logger.info(destDirName + " folder has already existed.");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		if (dir.mkdirs()) {
			logger.info("Create " + destDirName + " folder success.");
			return true;
		} else {
			logger.info("Create " + destDirName + " folder fail.");
			return false;
		}
	}
	
	/**
	 * 0 means normal file, user can download it. 1 means folder.
	 * @return boolean
	 */
	public static boolean isFolder(Long physicalType) {
		if(physicalType == null) {
			return false;
		} else if (physicalType.intValue() == 1){
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Clean all its own sub file and folder.
	 * @param dir
	 */
	public static void cleanFolder(String dir) {
		File delfolder = new File(dir);
		File oldFile[] = delfolder.listFiles();
		try {
			for (int i = 0; i < oldFile.length; i++) {
				if (oldFile[i].isDirectory()) {
					cleanFolder(dir + File.separator +  oldFile[i].getName());
				}
				oldFile[i].delete();
			}
		} catch (Exception e) {
			logger.info("Delete the " + dir +" folder error.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete folder and all sub-item by given folder.
	 * @param dir
	 */
	public static void deleteFolder(String dir) {
		cleanFolder(dir);
		File delfolder = new File(dir);
		delfolder.delete();
	}
}
