package com.mocha.co.ebay.api;

import com.coral.foundation.ebay.ManageTransactions;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.soap.eBLBaseComponents.OrderType;

public class ebayAPICallImpl {
	public static String customToken="";
	public static String apiServiceURL="";
	
	public ebayAPICallImpl(){
		
	}
	
	public OrderType[] getSalesTranscation(){
		ManageTransactions m=new ManageTransactions(null, null);
		try {
			m.getSalesTransactions();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
