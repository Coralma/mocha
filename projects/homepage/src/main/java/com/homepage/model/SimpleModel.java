package com.homepage.model;

import org.apache.wicket.model.IModel;

public class SimpleModel<T> implements IModel<T> {
	
	private T t;
	
	public SimpleModel(T t){
		this.t=t;
	}

	@Override
	public void detach() {
		// TODO Auto-generated method stub

	}

	@Override
	public T getObject() {
		// TODO Auto-generated method stub
		return t;
	}

	@Override
	public void setObject(T object) {
		// TODO Auto-generated method stub

	}

}
