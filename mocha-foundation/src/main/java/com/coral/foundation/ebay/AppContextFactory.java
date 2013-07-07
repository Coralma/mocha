package com.coral.foundation.ebay;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;

public class AppContextFactory {
	
	private static ApiContext apiContext;

	static String appID = "mocha-pl-0bf9-4d3a-badb-d141a0ee6121".trim();
	static String devID = "423f79ca-a5e9-492d-a1fa-98b3b27b5317".trim();
	static String cert = "7a7a7aa6-bfe7-40da-8174-fb7b851b7b24".trim();
	static String ruName = "mocha-platform-mocha-pl-0bf9-4-bhioe".trim();
	static String serviceUrl="https://api.sandbox.ebay.com/wsapi";

	public static ApiContext getInstance() {
		
		if(apiContext==null){
			apiContext=new ApiContext();
			apiContext.setApiServerUrl(serviceUrl);  
	        ApiCredential credential = new ApiCredential();  
	        ApiAccount account = new ApiAccount();  
			account.setApplication(appID);  
			account.setDeveloper(devID);  
			account.setCertificate(cert);  
	        credential.setApiAccount(account);  
	        apiContext.setApiCredential(credential);  
		}
		
		return apiContext;
	}
	
	

}
