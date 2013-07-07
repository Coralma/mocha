package com.mocha.co.ebay.api;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.ebay.ManageTransactions;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.TransactionArrayType;
import com.ebay.soap.eBLBaseComponents.TransactionType;
import com.mocha.cooperate.SystemProperty;

public class EbayAPICallImpl {
	public static String customToken=SystemProperty.ebayToken;
	public static String apiServiceURL=SystemProperty.ebaySandboxServerURL;
	
	public EbayAPICallImpl(){
		
	}
	
	public List<OrderType> getSalesTranscation(){
		ManageTransactions m=new ManageTransactions(customToken);
		try {
			List<OrderType> orders=m.getSalesTransactionsByEbayToken(m.getCustomToken());
			for(OrderType order:orders){
			TransactionArrayType t=order.getTransactionArray();
				for(TransactionType tr:t.getTransaction()){
					System.out.println("Order Item Ttitle is: "+tr.getItem().getTitle());
				}
			}
			return orders;
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
	
	 public static void main(String[] args) {
		 EbayAPICallImpl getOrder=new EbayAPICallImpl();
		 getOrder.getSalesTranscation();	
  }

}
