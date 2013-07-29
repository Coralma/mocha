package com.mocha.soicalAPI;

import java.io.File;

public class APIKeys {
	
	public static String FILE_PATH;
	
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
	
	// ebay
	public static String ebayProdServerURL = "https://api.ebay.com/ws/api.dll";
	public static String ebaySandboxServerURL = "https://api.sandbox.ebay.com/ws/api.dll";
	public static String devID = "423f79ca-a5e9-492d-a1fa-98b3b27b5317";
	public static String appID = "mocha-pl-0bf9-4d3a-badb-d141a0ee6121";
	public static String certID = "7a7a7aa6-bfe7-40da-8174-fb7b851b7b24";
	public static String ebayToken = "AgAAAA**AQAAAA**aAAAAA**nZ3VUQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhCpaDpAWdj6x9nY+seQ**VkQCAA**AAMAAA**Vzj1jcmu0wiy1aMmDeIAp5mcgNSzT4jlBaWBbyCxVY6ba0OMqeXyc1LrbA8LkSh0eNxRKaM4JqMj+CrXuj7wMJWa+qZNUSUB4lzMT31m9qfVslWbIFB7qVobXrFW+nakP1PXvD8ILEddM+crgPHcSe77AUfEsc3jbi7iGtQJ/q9bJbc4B6WsXJtfIjY7vAPT1Hdov5jHgcSnTMw/vx7yeySiudtx/wj9kwU27kvI7IndrhwRO4X29EVcLw85BGjUb7nPpbMXPAOHk25kmPMEUrE383pngYxquViZgDikFIjrE1C64+Pkqoh9qmixT+UzakEKstidLb2m8twOkZ9oolo/UErquA99EohLKiaLb+eKmPwzQBKjY6cI8k1n/cQdjdVqUx5mRarvxPB1XL7MKWo+lKQzn87uh5taze4vQZfXRIFAYlC87Ls3UHpgwyKMXrQsZaSBTk/dMzsoSjqbatzQj1IdndV1aAaHyK5BiMT33zThJHONmE2U2JMcO22eXOWSPGnuWGjvu+jHtNPxkvr8SsGy7YTH+adwrp7SbDZp1QnsMG8qEfRqLF3UC0b1+GbjrDKREBQzFASOVGa7se43fBYS7RYSr58bTSVo/+868id+by6mihXBsYVUT3EGVuRTdxFW6Pz4p1f7lX04eoOVASl7P+IO+TikCbJyrP7JY0Li6UPHDsS9xgIjsdgrHSLDAXhCWwtKV/u1UBwfwwn1yiDdi/JtwG9/wao+wnI+BEJK07FgXuRSgiCFvHX7";
	public static String ebayRuName = "mocha-platform-mocha-pl-0bf9-4-bhioe";

	// linkedIn
	public static String linkedInAPIId = "pps9akw5t85u";
	public static String linkedInSecertKey = "rz2R2HpXYQoQasr2";
	public static String LinkedinCallBackUrl = "https://www.mocha-platform.com/cooperate/oauth#linkedin";
	protected static long linkedinSyncNetworkStatusInternval=1000*60*10;
}
