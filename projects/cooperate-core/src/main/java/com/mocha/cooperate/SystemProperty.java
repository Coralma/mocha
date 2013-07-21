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

	private static Logger log = LoggerFactory.getLogger(SystemProperty.class);
	// public static String FILE_PATH = "D:/cooperateCache/";
	public static String FILE_PATH;
	/*
	 * specify the file_path here, use System.getProperty("user.home") under mac
	 * or ubuntu env
	 */
	static {
		if (FILE_PATH == null) {
			// check if the OS is windows, if it is using the
			if (System.getProperty("os.name").startsWith("Win")) {
				FILE_PATH = "D:/cooperateCache/";
			} else {
				FILE_PATH = System.getProperty("user.home")
						+ "/cooperateCache/";
				File file = new File(FILE_PATH);
				if (!file.exists()) {
					file.mkdir();
				}
			}
		}
	}

	public static String FILE_Folder = "files";
	public static String ATTACHMENT_Folder = "attachments";
	public static String USER_PHOTO_PATH = FILE_PATH + "users" + File.separator;
	public static String user_icon_path = FILE_PATH + "users" + File.separator;

	public static String user_defalut_photo_name = "defalut_user_photo.jpg";
	public static String uesr_default_icon_name = "defalut_user_icon.jpg";
	public static long file_limited_Size = 50000000;
	public static long file_name_limited_Length = 50;

	public static String application_page_Width = "980px";
	public static String content_page_width = "770px";
	public static String fixed_content_page_width = "768px";
	public static String content_widget_width = "750px";
	public static String content_card_width = "660px";
	public static String content_size = "700px";
	public static String publishWidgetWidth = "744px";

	// function name
	public static String ALL = "all";
	public static String STATUS = "status";
	public static String DISCUSS = "discuss";
	public static String ToDo = "todo";
	public static String Work = "work";

	public static String PAGE_TYPE = "page";

	// forum category name
	public static String FORUM_CATEGORY = "ForumCategory";
	public static String NOTIFY_CATEGORY = "2";
	public static String QUESTION_CATEGORY = "1";
	public static String DISCUSS_CATEGORY = "0";
	public static String SELECTED_TOPIC = "SelectedTopic";
	
	//ebay 
	public static String ebayProdServerURL ="https://api.ebay.com/ws/api.dll";
    public static String ebaySandboxServerURL="https://api.sandbox.ebay.com/ws/api.dll";
    public static String devID="423f79ca-a5e9-492d-a1fa-98b3b27b5317";
    public static String appID="mocha-pl-0bf9-4d3a-badb-d141a0ee6121";
    public static String certID="7a7a7aa6-bfe7-40da-8174-fb7b851b7b24";
    public static String ebayToken="AgAAAA**AQAAAA**aAAAAA**nZ3VUQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhCpaDpAWdj6x9nY+seQ**VkQCAA**AAMAAA**Vzj1jcmu0wiy1aMmDeIAp5mcgNSzT4jlBaWBbyCxVY6ba0OMqeXyc1LrbA8LkSh0eNxRKaM4JqMj+CrXuj7wMJWa+qZNUSUB4lzMT31m9qfVslWbIFB7qVobXrFW+nakP1PXvD8ILEddM+crgPHcSe77AUfEsc3jbi7iGtQJ/q9bJbc4B6WsXJtfIjY7vAPT1Hdov5jHgcSnTMw/vx7yeySiudtx/wj9kwU27kvI7IndrhwRO4X29EVcLw85BGjUb7nPpbMXPAOHk25kmPMEUrE383pngYxquViZgDikFIjrE1C64+Pkqoh9qmixT+UzakEKstidLb2m8twOkZ9oolo/UErquA99EohLKiaLb+eKmPwzQBKjY6cI8k1n/cQdjdVqUx5mRarvxPB1XL7MKWo+lKQzn87uh5taze4vQZfXRIFAYlC87Ls3UHpgwyKMXrQsZaSBTk/dMzsoSjqbatzQj1IdndV1aAaHyK5BiMT33zThJHONmE2U2JMcO22eXOWSPGnuWGjvu+jHtNPxkvr8SsGy7YTH+adwrp7SbDZp1QnsMG8qEfRqLF3UC0b1+GbjrDKREBQzFASOVGa7se43fBYS7RYSr58bTSVo/+868id+by6mihXBsYVUT3EGVuRTdxFW6Pz4p1f7lX04eoOVASl7P+IO+TikCbJyrP7JY0Li6UPHDsS9xgIjsdgrHSLDAXhCWwtKV/u1UBwfwwn1yiDdi/JtwG9/wao+wnI+BEJK07FgXuRSgiCFvHX7";
    public static String ebayRuName="mocha-platform-mocha-pl-0bf9-4-bhioe";
    

	public static String getForumCategoryTitle(String category) {
		String categoryTitle = "Topic";
		if (SystemProperty.NOTIFY_CATEGORY.equals(category)) {
			categoryTitle = "Notification";
		} else if (SystemProperty.QUESTION_CATEGORY.equals(category)) {
			categoryTitle = "Question";
		} else if (SystemProperty.DISCUSS_CATEGORY.equals(category)) {
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
		return FILE_PATH + accountName + File.separator
				+ SystemProperty.FILE_Folder + File.separator;
	}

	public static String getUserFileFolder(BasicUser currentUser) {
		return SystemProperty.FILE_PATH + currentUser.getAccount().getName()
				+ File.separator + currentUser.getUserName() + File.separator
				+ SystemProperty.FILE_Folder + File.separator;
	}

	public static String getUserAttachmentFolder(BasicUser currentUser) {
		return getUserFileFolder(currentUser) + ATTACHMENT_Folder
				+ File.separator;
	}

	public static String getUserFolder(BasicUser currentUser) {
		if (currentUser.getAccount() == null
				|| currentUser.getUserName() == null) {
			log.error("account or userName can't be null");
		}
		return SystemProperty.FILE_PATH + currentUser.getAccount().getName()
				+ File.separator + currentUser.getUserName() + File.separator;
	}

	// public static String[] SUPPORTED_LANGUAGES = new String[]{"English",
	// "Chinese"};
	
	
}
