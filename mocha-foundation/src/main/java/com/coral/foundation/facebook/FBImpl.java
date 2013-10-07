package com.coral.foundation.facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.JsonObject;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.conf.PropertyConfiguration;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class FBImpl {

	private static final String appId = "207409882754187";
	private static final String appSecret = "d8a9c0f327aa1770e6fee1864658a037";
	private static Facebook facebook = new FacebookFactory().getInstance();
	private static String token = "CAAC8o2BibIsBAPgj0BMaC1qWNQ3OKjLTSTMkmkOLDIZCRxAUBLPBQyrkevzbMVAOwUejSm9VUH9idZBLXmitsHdtMZAyn1oQ4oqZBXRjCOoIyVKSppJC59WbMx7Swe8FoeVFhDA1ZCIySq0XQHZBqMe1zMMbBO8iLYvbphEzZAqbfUQ8A31vP4mpaZALnIB4bxQZD";
	private static Properties properties;
	public static String facebookCallBackUrl = "https://www.mocha-platform.com/cooperate/facebook";

	private static Facebook getFacebookPublicInstance() {
		init();
		Configuration conf = createConfiguration();
		Facebook facebookClient = new FacebookFactory().getInstance(new OAuthAuthorization(conf));
		// se the System Token here
		AccessToken accessToken = new AccessToken(token);
		facebookClient.setOAuthAccessToken(accessToken);
		return facebookClient;
	}

	public static Facebook getFacebookInstance() {
		init();
		Configuration conf = createConfiguration();
		Facebook facebookClient = new FacebookFactory().getInstance(new OAuthAuthorization(conf));
		return facebookClient;
	}

	public static void main(String args[]) throws FacebookException, JSONException {
		init();
		Configuration conf = createConfiguration();
		Facebook facebookClient = new FacebookFactory().getInstance(new OAuthAuthorization(conf));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = null;

		System.out.println("Paste this URL to your browser:");
		String fbAuthUrl = facebookClient.getOAuthAuthorizationURL(properties.getProperty("REDIRECT_URL"));
		System.out.println(facebookClient.getOAuthAuthorizationURL(properties.getProperty("REDIRECT_URL")));
		System.out.println("and now paste the code here and press enter");
		try {
			code = br.readLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		AccessToken accessToken = new AccessToken(token);
		AccessToken fbAccessToken = facebookClient.getOAuthAccessToken(code);
		facebookClient.setOAuthAccessToken(fbAccessToken);
		// String fbRenewTokenURL = "https://graph.facebook.com/oauth/authorize?code=" + code + "&client_id=" + properties.getProperty("APP_ID")
		// + "&redirect_uri=" + properties.getProperty("REDIRECT_URL") + "&machine_id=" + UUID.randomUUID().toString();
		// System.out.println(fbRenewTokenURL);
		// HttpGet httpost = new HttpGet(fbRenewTokenURL);
		// DefaultHttpClient client = new DefaultHttpClient();
		// try {
		//
		// HttpResponse response = client.execute(httpost);
		// ResponseHandler<String> handler = new BasicResponseHandler();
		// String str = handler.handleResponse(response);
		// System.out.println(str);
		// }
		// catch (ClientProtocolException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// searchFriends(facebookClient);
	}

	private static void init() {
		properties = new Properties();
		properties.setProperty("DEBUG_ENABLED", "true");
		properties.setProperty("APP_ID", appId);
		properties.setProperty("APP_SECRET", appSecret);
		properties.setProperty("JSON_STORE_ENABLED", "true");
		properties.setProperty("REDIRECT_URL", facebookCallBackUrl);
	}

	private static Configuration createConfiguration() {
		ConfigurationBuilder confBuilder = new ConfigurationBuilder();

		confBuilder.setDebugEnabled(Boolean.parseBoolean(properties.getProperty("DEBUG_ENABLED")));
		confBuilder.setOAuthAppId(properties.getProperty("APP_ID"));
		confBuilder.setOAuthAppSecret(properties.getProperty("APP_SECRET"));
		confBuilder.setJSONStoreEnabled(Boolean.parseBoolean(properties.getProperty("JSON_STORE_ENABLED")));

		return confBuilder.build();
	}

	/*
	 * SELECT uid, username, name, pic_square, profile_url FROM user WHERE uid = me() OR uid IN (SELECT uid2 FROM friend WHERE uid1 = me()) and
	 * (strpos(name,'Chun') >=0 or strpos(name,'Chen')>=0 or strpos(name, 'Chun Chen')>=0 )
	 */

	public static List<FBUserModel> fuzzySearchFriends(AccessToken accessToken, String firstName, String lastName, String userName) throws FacebookException {
		facebook = getFacebookPublicInstance();
		facebook.setOAuthAccessToken(accessToken);
		List<FBUserModel> fbUsers = new ArrayList<FBUserModel>();
		try {
			// Single FQL
			String query = "SELECT uid, username, name, pic_square, profile_url FROM user WHERE uid = me() OR uid "
					+ "IN (SELECT uid2 FROM friend WHERE uid1 = me()) and(strpos(name,'" + firstName + "') >=0 or " + "strpos(name,'" + lastName
					+ "')>=0 or strpos(name, '" + userName + "')>=0 )";
			JSONArray jsonArray = facebook.executeFQL(query);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject;
				jsonObject = jsonArray.getJSONObject(i);
				FBUserModel fbUser = new FBUserModel();
				fbUser.setName(jsonObject.getString("name"));
				fbUser.setUid(jsonObject.getString("uid"));
				fbUser.setProfile_url(jsonObject.getString("profile_url"));
				fbUser.setUsername(jsonObject.getString("username"));
				fbUser.setPic_square(jsonObject.getString("pic_square"));
				fbUsers.add(fbUser);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		catch (FacebookException e) {
			e.printStackTrace();
		}
		return fbUsers;
	}

	public static List<FBUserModel> fuzzysearchFBUsers(String keyword) {
		facebook = getFacebookPublicInstance();
		List<FBUserModel> fbUsers = new ArrayList<FBUserModel>();
		try {
			// Single FQL
			String query = "SELECT uid, username, name, pic_square, profile_url FROM user WHERE contains(\"" + keyword + "\")";
			JSONArray jsonArray = facebook.executeFQL(query);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject;
				jsonObject = jsonArray.getJSONObject(i);
				FBUserModel fbUser = new FBUserModel();
				fbUser.setName(jsonObject.getString("name"));
				fbUser.setUid(jsonObject.getString("uid"));
				fbUser.setProfile_url(jsonObject.getString("profile_url"));
				fbUser.setUsername(jsonObject.getString("username"));
				fbUser.setPic_square(jsonObject.getString("pic_square"));
				System.out.println(fbUser);
				fbUsers.add(fbUser);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		catch (FacebookException e) {
			e.printStackTrace();
		}
		return fbUsers;
	}
}
