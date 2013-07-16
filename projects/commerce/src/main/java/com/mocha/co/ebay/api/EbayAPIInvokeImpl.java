package com.mocha.co.ebay.api;

import java.util.ArrayList;
import java.util.List;

import com.coral.foundation.ebay.ManageTransactions;
import com.coral.foundation.jpa.search.SearchFilter;
import com.coral.foundation.jpa.search.SearchFilterBuilder;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.TransactionArrayType;
import com.ebay.soap.eBLBaseComponents.TransactionType;
import com.mocha.co.dao.CommerceCustomerDao;
import com.mocha.co.model.CommerceCustomer;
import com.mocha.cooperate.SystemProperty;

public class EbayAPIInvokeImpl {
	public static String customToken = SystemProperty.ebayToken;
	public static String apiServiceURL = SystemProperty.ebaySandboxServerURL;
	private CommerceCustomerDao ccDao = SpringContextUtils
			.getBean(CommerceCustomerDao.class);
	private BasicUser user;

	public EbayAPIInvokeImpl() {
	}
	public EbayAPIInvokeImpl(BasicUser user) {
		this.user = user;
	}

	public List<OrderType> getSalesTranscation() {
		CommerceCustomer cc = ccDao.findCCByUser(user);
		String authToken=cc.getSourceApplications().get(0).getAuthToken();
		ManageTransactions m = new ManageTransactions();
		System.out.println("cc user name is"+ cc.getReferUser().getUserName());
		if(authToken==null){			
			String sessionID = cc.getSourceApplications().get(0).getSessionID();
			String secretID = cc.getSourceApplications().get(0).getSecretID();
			authToken = m.getGetFetchTokenBySessionID(sessionID);
			cc.getSourceApplications().get(0).setAuthToken(authToken);
			ccDao.merge(cc);
			System.out.println("Current user token is: " + authToken);
		}
		try {
			List<OrderType> orders = m
					.getSalesTransactionsByEbayToken(authToken);
			for (OrderType order : orders) {
				TransactionArrayType t = order.getTransactionArray();
				for (TransactionType tr : t.getTransaction()) {
					System.out.println("Order Item Ttitle is: "
							+ tr.getItem().getTitle());
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

}
