package com.homepage.application;

public class HomepageSystemProperty {
	
	private static String paypal_ipn_url;
	
	private static String cooperate_full_url;
	
	private static String cooperate_short_url;
	
	public static String getPaypal_ipn_url() {
		return paypal_ipn_url;
	}

	public static void setPaypal_ipn_url(String paypal_ipn_url) {
		HomepageSystemProperty.paypal_ipn_url = paypal_ipn_url;
	}

	public static String getCooperate_short_url() {
		return cooperate_short_url;
	}

	public static void setCooperate_short_url(String cooperate_short_url) {
		HomepageSystemProperty.cooperate_short_url = cooperate_short_url;
	}

	public static String getCooperate_full_url() {
		return cooperate_full_url;
	}

	public static void setCooperate_full_url(String cooperate_full_url) {
		HomepageSystemProperty.cooperate_full_url = cooperate_full_url;
	}

}
