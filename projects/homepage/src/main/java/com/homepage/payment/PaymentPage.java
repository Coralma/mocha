package com.homepage.payment;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.homepage.pages.BasePage;

public class PaymentPage extends BasePage {

	public PaymentPage() {
		build();
	}

	public void build() {
		AjaxLink payNowButton = new AjaxLink("payNowButton") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(CheckOut.class);
			}
		};

		add(payNowButton);

	}

}
