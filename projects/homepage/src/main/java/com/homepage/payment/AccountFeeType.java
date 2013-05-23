package com.homepage.payment;

public enum AccountFeeType {
	Free("free"),Pro("pro"),Pemium("premium");
	
	private String typeName;
	
	AccountFeeType(String typeName){
		this.setTypeName(typeName);
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}