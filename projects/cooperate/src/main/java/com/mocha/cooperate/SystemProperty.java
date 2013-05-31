/**
 * 
 */
package com.mocha.cooperate;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.coral.foundation.security.model.BasicUser;

/**
 * @author Administrator
 * 
 */
public class SystemProperty {
	
	private static Logger log=LoggerFactory.getLogger(SystemProperty.class);
//	public static String FILE_PATH = "D:/cooperateCache/";
	public static String FILE_PATH;
	/*
	 * specify the file_path here, use System.getProperty("user.home") under mac or ubuntu env
	 * 
	 * */
	static {
		if(FILE_PATH == null) {
			// check if the OS is windows, if it is using the 
			if(System.getProperty("os.name").startsWith("Win")) {
				FILE_PATH = "D:/cooperateCache/";
			} else if(System.getProperty("os.name").startsWith("mac") || System.getProperty("os.name").startsWith("ubuntu")) {
				FILE_PATH = System.getProperty("user.home")+"/cooperateCache/";
				File file=new File(FILE_PATH);
				if(!file.exists()){
					file.mkdir();
				}
			}
		}
	}
	
	public static String FILE_Folder = "files";
	public static String USER_PHOTO_PATH = FILE_PATH + "users" + File.separator;
	public static String user_icon_path = FILE_PATH + "users" + File.separator;
	
	public static String user_defalut_photo_name = "defalut_user_photo.jpg";
	public static String uesr_default_icon_name = "defalut_user_icon.jpg";
	public static long file_limited_Size = 5000000;
	public static long file_name_limited_Length = 50;

	public static String application_page_Width = "980px";
	public static String content_page_width="770px";
	public static String fixed_content_page_width="768px";
	public static String content_widget_width = "750px";
	public static String content_card_width = "660px";
	public static String content_size = "700px";
	public static String publishWidgetWidth = "744px";

	// function name
	public static String ALL = "all";
	public static String STATUS = "status";
	public static String DISCUSS = "discuss";
	public static String ToDo = "todo";
	
	public static String PAGE_TYPE = "page";
	
	// forum category name
	public static String FORUM_CATEGORY = "ForumCategory";
	public static String NOTIFY_CATEGORY = "2";
	public static String QUESTION_CATEGORY = "1";
	public static String DISCUSS_CATEGORY = "0";
	public static String SELECTED_TOPIC = "SelectedTopic";

	public static String getForumCategoryTitle(String category) {
		String categoryTitle = "Topic";
		if(SystemProperty.NOTIFY_CATEGORY.equals(category)) {
			categoryTitle = "Notification";
		} else if(SystemProperty.QUESTION_CATEGORY.equals(category)) {
			categoryTitle = "Question";
		} else if(SystemProperty.DISCUSS_CATEGORY.equals(category)) {
			categoryTitle = "Discussion";
		}
		return categoryTitle;
	}
	
	// name and password.
	public static String COOKIE_USERNAME = "cooperateUsername";
	public static String COOKIE_LANGUAGE = "cooperateLanguage";
	
	public static Long COMPANY_FOLDER = Long.MAX_VALUE;
	public static Long SEARCH_FOLDER = Long.MAX_VALUE - 1;

	public static String getCompanyFolder(String accountName) {
		return FILE_PATH + accountName + File.separator + SystemProperty.FILE_Folder + File.separator;
	}

	public static String getUserFileFolder(BasicUser currentUser) {
		return SystemProperty.FILE_PATH + currentUser.getAccount().getName() + File.separator + currentUser.getUserName() + File.separator + SystemProperty.FILE_Folder +  File.separator;
	}
	
	public static String getUserFolder(BasicUser currentUser) {
		if(currentUser.getAccount()==null || currentUser.getUserName()==null){
			log.error("account or userName can't be null");
		}
		return SystemProperty.FILE_PATH + currentUser.getAccount().getName() + File.separator + currentUser.getUserName() + File.separator;
	}
	
	
//	public static String[] SUPPORTED_LANGUAGES = new String[]{"English", "Chinese"};
	
}
