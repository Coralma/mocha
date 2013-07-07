package com.mocha.co.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ebay.soap.eBLBaseComponents.AddressType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.TransactionArrayType;
import com.ebay.soap.eBLBaseComponents.TransactionType;
import com.google.common.collect.Lists;
import com.mocha.co.ebay.api.EbayAPICallImpl;
import com.mocha.co.model.CommerceCustomer;
import com.mocha.co.model.Order;
import com.mocha.co.model.OrderProduct;

public class OrderService {
	
	public List<Order> loadEbayOrders() {
		List<Order> orders = Lists.newArrayList();
		EbayAPICallImpl getOrder=new EbayAPICallImpl();
		List<OrderType> ebayOrders = getOrder.getSalesTranscation();	
		for(OrderType ebayOrder : ebayOrders){
			// create customer
			CommerceCustomer customer = new CommerceCustomer();
			AddressType address = ebayOrder.getShippingAddress();
			customer.setName(address.getName());
			customer.setSource("eBay");
			customer.setAddress(address.getCountryName() + " " + address.getStateOrProvince() 
					+ " " + address.getCityName() + " " + address.getStreet() + " " + address.getPostalCode());

			// create order
			Order commonOrder = new Order();
			orders.add(commonOrder);
			commonOrder.setCustomer(customer);
			commonOrder.setPurchaseDate(ebayOrder.getPaidTime() == null ? new Date() : ebayOrder.getPaidTime().getTime());
			commonOrder.setExceptedShipDate(ebayOrder.getShippedTime() == null ? new Date() : ebayOrder.getShippedTime().getTime());
			commonOrder.setOrderTotals(String.valueOf(ebayOrder.getTotal().getValue()));// total price

			String orderProductSummary = "";
			List<OrderProduct> orderProducts = Lists.newArrayList();
			commonOrder.setOrderProducts(orderProducts);
			TransactionArrayType t=ebayOrder.getTransactionArray();
			for(TransactionType tr:t.getTransaction()){
				OrderProduct orderProduct = new OrderProduct();
				
				ItemType it = tr.getItem();
				orderProduct.setProduct(it.getTitle());
				orderProduct.setQuantityOrdered(it.getQuantity() == null? 1 : new Long(it.getQuantity()));
				orderProducts.add(orderProduct);
				
				orderProductSummary = orderProductSummary + orderProduct.getProduct() + "[" + orderProduct.getQuantityOrdered() + "] ";
			}
			commonOrder.setOrderProductSummary(orderProductSummary);
		}
		return orders;
	}
}
