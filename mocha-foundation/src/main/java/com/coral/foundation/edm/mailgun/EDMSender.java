package com.coral.foundation.edm.mailgun;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientResponse;

import com.coral.foundation.security.model.Campaingns;
import com.coral.foundation.security.model.Contact;
import com.coral.foundation.security.model.Email;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class EDMSender extends AbstractEDM {

	private ArrayList<Contact> contactLists = new ArrayList<Contact>();
	private Campaingns campaingns = new Campaingns();
//	private WebResource webResource = client.resource("https://api.mailgun.net/v2/mocha-demo.mailgun.org" + "/messages");

	public ArrayList<Contact> getContactLists() {
		return contactLists;
	}

	public void setContactLists(ArrayList<Contact> contactLists) {
		this.contactLists = contactLists;
	}

	public Campaingns getCampaingns() {
		return campaingns;
	}

	public void setCampaingns(Campaingns campaingns) {
		this.campaingns = campaingns;
	}

	@Override
	public ClientResponse execute() {
//		for (Contact contact : campaingns.getContacts()) {
//			for (Email email : contact.getEmailAddress()) {
//				client.addFilter(new HTTPBasicAuthFilter("api", "key-1gj5050qr8l257fbdcpn4zs9j745o431"));
//				MultivaluedMapImpl formData = new MultivaluedMapImpl();
//				formData.add("from", "Excited User <me@samples.mailgun.org>");
//				formData.add("to", email.getEmailAddress());
//				formData.add("subject", "Demo EDM");
//				formData.add("html", campaingns.getEmailContext());
//				formData.add("o:tracking", true);
//				formData.add("o:tracking-opens", true);
//				formData.add("o:tracking-clicks", true);
//				webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
//			}
//		}
		return null;
	}

	public static void main(String args[]) {
		EDMSender emSender = new EDMSender();
		Contact contact = new Contact();
		Email email = new Email();
		email.setEmailAddress("vancezhao@gmail.com");
		contact.getEmailAddress().add(email);
		Campaingns campaingns = new Campaingns();
		campaingns.getContacts().add(contact);
		String emailContext = "<h1>Hi This is what I need to talk to u</h1>";
		campaingns.setEmailContext(emailContext);
		emSender.setCampaingns(campaingns);
		ClientResponse cr = emSender.execute();
	}

}
