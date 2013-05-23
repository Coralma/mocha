package com.homepage.model;

public enum OrderStatusType {
	
	New("new"), Processing("progress"), Finished("finished");

	private String orderStatus;

	OrderStatusType(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
