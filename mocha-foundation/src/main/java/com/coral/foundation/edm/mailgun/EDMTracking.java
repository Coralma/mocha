package com.coral.foundation.edm.mailgun;

import java.beans.Beans;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.core.MediaType;

import org.apache.http.protocol.HttpService;
import org.glassfish.jersey.client.ClientResponse;

import com.coral.foundation.edm.mailgun.M.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gwt.json.client.JSONObject;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.threewks.thundr.mailgun.Mailgun;
import com.threewks.thundr.mailgun.MailgunImpl;

public class EDMTracking extends AbstractEDM {

	// private WebResource webResource;

	@Override
	public ClientResponse execute() {
		// webResource = client.resource("https://api.mailgun.net/v2/mocha-demo.mailgun.org" + "/stats");
		// MultivaluedMapImpl queryParams1 = new MultivaluedMapImpl();
		// ClientResponse cr1 = webResource.queryParams(queryParams1).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		// String t = cr1.getEntity(String.class);
		// JsonParser parser = new JsonParser();
		// JsonObject jsonObject = parser.parse(t).getAsJsonObject();
		// JsonObject a = jsonObject.getAsJsonObject();
		// JsonArray jArray = a.get("items").getAsJsonArray();
		// // for (JsonElement je : jArray) {
		// // System.out.println(je);
		// // }
		// String sample = "{\"total_count\":2,\"created_at\":\"Sat, 21 Sep 2013 00:00:00 GMT\",\"id\":\"523d05997c6036ccc5b53549\",\"event\":\"sent\"}";
		// Gson gson = new Gson();
		//
		// M.Item item = gson.fromJson(sample, M.Item.class);
		// System.out.println(item);
		return null;
	}

	public static void main(String args[]) {
		// EDMTracking edm = new EDMTracking();
		// edm.execute();
		HttpService httpService;
		// Mailgun mailGun=new MailgunImpl();

	}

}

class M implements Serializable {

	@SerializedName("total_count")
	private String totalCount;
	@SerializedName("items")
	private Item[] items;

	public class Item {

		@SerializedName("total_count")
		private String total_count;
		@SerializedName("created_at")
		private String created_at;
		@SerializedName("tags")
		private String[] tags;
		@SerializedName("id")
		private String id;
		@SerializedName("event")
		private String event;

		public Item() {

		}

		public String getTotal_count() {
			return total_count;
		}

		public void setTotal_count(String total_count) {
			this.total_count = total_count;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getEvent() {
			return event;
		}

		public void setEvent(String event) {
			this.event = event;
		}

		public String[] getTags() {
			return tags;
		}

		public void setTags(String[] tags) {
			this.tags = tags;
		}
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

}
