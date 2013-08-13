package com.coral.foundation.persistence;

public class Order {

	private boolean isAscending;
	private String property;
	
	public Order(String property, boolean isAscending) {
		this.property = property;
		this.isAscending = isAscending;
	}
	
	public boolean isAscending() {
		return isAscending;
	}
	public void setAscending(boolean isAscending) {
		this.isAscending = isAscending;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	
}
