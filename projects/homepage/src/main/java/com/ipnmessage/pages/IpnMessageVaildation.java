package com.ipnmessage.pages;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gargoylesoftware.htmlunit.Page;
import com.homepage.model.AccountFee;
import com.homepage.model.AccountFeeDao;
import com.homepage.model.Order;
import com.homepage.model.OrderDao;
import com.homepage.model.OrderStatusType;
import com.homepage.model.User;
import com.homepage.model.UserDao;
import com.ibm.icu.text.DateFormat;

public class IpnMessageVaildation {

	private HttpServletRequest reqeust;

	public IpnMessageVaildation(HttpServletRequest reqeust, Map paramsMap) {
		this.reqeust = reqeust;
		validate(paramsMap);
	}

	public IpnMessageVaildation() {
		// System.out.println("Error populat here.. No param found");
	}

	private void validate(Map paramsMap) {
		if (reqeust != null) {

			Set<String> paramKeys = paramsMap.keySet();
			for (String key : paramKeys) {
				System.out.println("key name: " + key + ": "
						+ reqeust.getParameter(key).toString());
				if (key.contains("custom")) {
					validateOrder(reqeust.getParameter(key).toString());
				}
			}
		}
	}

	private void validateOrder(String orderOID) {
		System.out.println("Your order " + orderOID + " has already finished");
		OrderDao orderDao = new OrderDao();
		orderDao.updateOrderByOrderOID(orderOID, OrderStatusType.Finished);
		try {
			Order order = orderDao.findOrderByOrderOID(orderOID);
			System.out.println(order.getOrderStatus());
			AccountFeeDao accountFeeDao = new AccountFeeDao();
			accountFeeDao.createAccountFeeByOrder(order);
		} catch (NoResultException e) {
			System.out.println("Can't find the orderOD with " + orderOID);
		}
	}

}
