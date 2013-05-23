package com.homepage.payment;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;

public class Payment {

	public static Component buildPaymentButton() {

		AjaxButton paymentButton = new AjaxButton("payment") {

			@Override
			public void onSubmit() {

			}
		};
		return null;
	}

}
