package com.homepage.shorturl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
/*
 * Based on http://is.gd
 */

import com.homepage.model.SystemPropertyDao;

public class SimpleShortUrl {
	private static String localmochaCooperateURL = "http://localhost:8080/cooperate";
	private static String prodmochaCooperateURL = "http://ec2-54-249-155-18.ap-northeast-1.compute.amazonaws.com:8080/cooperate/";
	private static String buildType = "local";
	private String url;
	/*
	 * "http://is.gd/create.php"
	 * 
	 * http://tinyurl.com/create.php?source=indexpage&url=http%3A%2F%2Fec2-54-249
	 * -
	 * 155-18.ap-northeast-1.compute.amazonaws.com%3A8080%2Fcooperate%2F&submit=
	 * Make+TinyURL%21&alias=mocha%2Faccount1
	 */
	private static String shortUrlMaker = "http://tinyurl.com/api-create.php";

	public SimpleShortUrl() {
		checkUrl();
	}

	private void checkUrl() {
		SystemPropertyDao systemPropertyDao = new SystemPropertyDao();
		String cooperateUrl = systemPropertyDao
				.findSystemValueByKey("cooperateUrl");
		initCooperateUrl(cooperateUrl);
	}

	private void initCooperateUrl(String cooperateUrl) {

		HttpClient httpclient = new HttpClient();
		HttpMethod method = new GetMethod(shortUrlMaker);
		method.setQueryString(new NameValuePair[]{
				new NameValuePair("source", "indexpage"),
				new NameValuePair("url", cooperateUrl),
				new NameValuePair("submit", "Make+TinyURL%21"),
				new NameValuePair("alias", "vancezhao/a")});
		try {
			System.out.println(method.getPath() + " "
					+ method.getURI().getPathQuery());
			httpclient.executeMethod(method);
			InputStream response = method.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					response));
			setUrl(br.readLine());

		} catch (HttpException e) {
			// TODO Auto-generated catch block·
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static void main(String args[]) {
		SimpleShortUrl s = new SimpleShortUrl();
		System.out.println(s.getUrl());
	}

}
