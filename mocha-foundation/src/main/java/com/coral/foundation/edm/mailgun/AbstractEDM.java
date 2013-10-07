package com.coral.foundation.edm.mailgun;

import com.coral.foundation.security.model.Campaingns;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public abstract class AbstractEDM {

	private Campaingns campaings;
	protected Client client = new Client();
	private String apiKey = "key-1gj5050qr8l257fbdcpn4zs9j745o431";

	public AbstractEDM() {
		initClient();
	}

	private void initClient() {
		client.addFilter(new HTTPBasicAuthFilter("api", apiKey));
	}

	abstract ClientResponse execute();

	public Campaingns getCampaings() {
		return campaings;
	}

	public void setCampaings(Campaingns campaings) {
		this.campaings = campaings;
	}

}
