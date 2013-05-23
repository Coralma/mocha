package com.test;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

public class OrderForm<T> extends Form<T> {

	public OrderForm(String id) {
		super(id);
		build();
	}

	private void build() {
		Button button=new Button("order");
		add(button);
	}
	
	@Override
	public void onSubmit(){
		System.out.println("User click order now");
	}

}
