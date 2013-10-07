package com.coral.foundation.socialAuthAPI;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

import com.coral.foundation.socialAuthAPI.GoogleResult.Result;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class Sample {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// // Create an instance of SocialAuthConfgi object
		// SocialAuthConfig config = SocialAuthConfig.getDefault();
		//
		// // load configuration. By default load the configuration from oauth_consumer.properties.
		// // You can also pass input stream, properties object or properties file name.
		// config.load();
		//
		// // Create an instance of SocialAuthManager and set config
		// SocialAuthManager manager = new SocialAuthManager();
		// manager.setSocialAuthConfig(config);
		//
		// // URL of YOUR application which will be called after authentication
		// String successUrl = "http://opensource.brickred.com/socialauthdemo/socialAuthSuccessAction.do";
		//
		// // get Provider URL to which you should redirect for authentication.
		// // id can have values "facebook", "twitter", "yahoo" etc. or the OpenID URL
		// String url = manager.getAuthenticationUrl("yahoo", successUrl);
		//
		// System.out.println(url);

//		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
//		String search = "site:linkedin.com 'china area' advertising -dir 'search for'";
//		String startWith = "start=";
//		int start = 1;
//		int end = 3;
//		int totalCnt = 1000;
//		String charset = "UTF-8";
//
//		URL url = new URL(google + URLEncoder.encode(search, charset) + URLEncoder.encode(startWith, charset));
//
//		for (int i = start; i < totalCnt; i = i + end) {
//
//			url = new URL(url + URLEncoder.encode(String.valueOf(start), charset));
//			System.out.println(url);
//			Reader reader = new InputStreamReader(url.openStream(), charset);
//			GoogleResult results = new Gson().fromJson(reader, GoogleResult.class);
//
//			// Show title and URL of 1st result.
//			for (Result rs : results.getResponseData().getResults()) {
//				System.out.println(rs.getTitle());
//				System.out.println(rs.getUrl());
//			}
//		}
		
		HttpResponse<JsonNode> request = Unirest.get("https://duckduckgo-duckduckgo-zero-click-info.p.mashape.com/?q=DuckDuckGo&callback=process_duckduckgo&no_html=1&no_redirect=1&skip_disambig=1&format=json")
				  .header("X-Mashape-Authorization", "9G5Bgc4OY8kmid6tte3WnduIBsYQl2hH")
				  .asJson();
		System.out.println(request);
	}
}
