package com.test;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Page;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.UrlResourceReference;
import org.apache.wicket.util.string.Strings;

public class Pay extends WebPage {

	public Pay() {
		super();
		build();
	}

	private void build() {
		OrderForm orderForm = new OrderForm("orderForm");
		add(orderForm);
	}

	public class OrderForm<T> extends Form<T> {

		public OrderForm(String id) {
			super(id);
			build();
		}

		private void build() {

			Url url = new Url();
//			url.setHost("https://www.sandbox.paypal.com/zh_XC/i/btn/btn_subscribeCC_LG.gif");
			ImageButton orderButton = new ImageButton("order",
					new UrlResourceReference(url)) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onSubmit() {
				}
				
				
			};

//			orderButton
//					.add(new AttributeModifier("src",
//							"https://www.sandbox.paypal.com/zh_XC/i/btn/btn_subscribeCC_LG.gif"));
			add(orderButton);
		}

		@Override
		public void onSubmit() {
			System.out.println("User submit ");
			Page page=new RedirectPage("http://www.theserverside.com");
			setResponsePage(page);
		}

	}
}
