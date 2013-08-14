package com.coral.foundation.persistence;


public class Filter {
	
	public enum Type {
		GREATER_AND_EQUAL, LESS_AND_EQUAL, IN
	}
	
	private Type type;
	private Object value;
	public Filter(Object value, Type type) {
		this.type = type;
		this.value = value;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object property) {
		this.value = property;
	}
	
}
