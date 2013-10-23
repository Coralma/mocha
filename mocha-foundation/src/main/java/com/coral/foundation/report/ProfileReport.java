package com.coral.foundation.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.coral.foundation.security.basic.dao.LinkedinConnectionDao;
import com.coral.foundation.security.model.LinkedinConnection;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.gwt.dom.client.Element;

public class ProfileReport {

	private String linkedinProfileUrl;
	private String profileExperenceCSSPath;
	private String profileEductionCSSPath;

	private LinkedinConnectionDao dao = SpringContextUtils.getBean(LinkedinConnectionDao.class);

	public ProfileReport(String linkedinProfileUrl, String profileExperenceCSSPath, String profileEductionCSSPath) {
		this.setLinkedinProfileUrl(linkedinProfileUrl);
		this.setProfileEductionCSSPath(profileEductionCSSPath);
		this.setProfileExperenceCSSPath(profileExperenceCSSPath);
	}

	public ProfileReport() {

	}

	private Map<String, String> faceBookLogin() {
		Map<String, String> jSoupCookie = new HashMap<String, String>();
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();

			HttpGet httpget = new HttpGet("http://www.facebook.com/login.php");

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			System.out.println("Login form get: " + response.getStatusLine());
			if (entity != null) {
				entity.consumeContent();
			}
			System.out.println("Initial set of cookies:");
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			if (cookies.isEmpty()) {
				System.out.println("None");
			}
			else {
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("- " + cookies.get(i).toString());
				}
			}

			HttpPost httpost = new HttpPost("http://www.facebook.com/login.php");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("email", "vancezhao@gmail.com"));
			nvps.add(new BasicNameValuePair("pass", "jsvskk"));

			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			String fbLoginAuth = null;
			response = httpclient.execute(httpost);
			for (Header h : response.getAllHeaders()) {
				if (h.getName().contains("Location")) {
					fbLoginAuth = h.getValue();
				}
			}
			entity = response.getEntity();
			System.out.println("Double check we've got right page " + EntityUtils.toString(entity));
			System.out.println("Login form get: " + response.getStatusLine());
			if (entity != null) {
				entity.consumeContent();
			}

			System.out.println("Post logon cookies:");
			cookies = httpclient.getCookieStore().getCookies();

			if (cookies.isEmpty()) {
				System.out.println("None");
			}
			else {
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("- " + cookies.get(i).toString());
					jSoupCookie.put(cookies.get(i).getName(), cookies.get(i).getValue());
				}
			}
			return jSoupCookie;
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public LinkedinConnection parseProfilePage() {
		Document doc;
		try {
			doc = Jsoup.connect(linkedinProfileUrl).get();
			LinkedinConnection conn = dao.findConnectByPublicProfile(linkedinProfileUrl);
			Elements profileEduction = doc.select(profileEductionCSSPath);
			Elements profileExperience = doc.select(profileExperenceCSSPath);
			conn.setEduction(profileEduction.html());
			conn.setExperience(profileExperience.html());
			System.out.println(profileEduction.html());
			System.out.println(profileExperience.html());
			return conn;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> parseFacebookProfilePage(String fbProfileUrl) {
		fbProfileUrl = fbProfileUrl + "/about";
		ArrayList<String> fbTimelineSections = new ArrayList<String>();
		Document doc;
		try {
			doc = Jsoup.connect(fbProfileUrl).cookies(faceBookLogin()).get();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("fb.log")));
			bw.write(doc.html());
			System.out.println(doc.html());
			String profileMainHtml = doc.html();
			for (String str : profileMainHtml.split("\n")) {
				// find this user's timeline section only
				if (str.contains("</code>") && str.contains("fbTimelineSection") && !str.contains("Others Named")) {
					str = StringUtils.removeStart(StringUtils.removeEnd(str.trim(), "--></code>"), "<!--");
					fbTimelineSections.add(str);
				}
			}
			if (!fbProfileUrl.endsWith("/about")) {
				fbTimelineSections.addAll(parseFacebookProfilePage(fbProfileUrl + "/about"));
			}
			return fbTimelineSections;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// String linkedinProfileUrl = "";
		// String profileEductionCSSPath =
		// "html.os-other body#pagekey-nprofile-public-success.en div#body div.wrapper div#main.profile div#content.resume div#profile-education.section";
		// String profileExperenceCSSPath =
		// "html.os-other body#pagekey-nprofile-public-success.en div#body div.wrapper div#main.profile div#content.resume div#profile-experience.section div.content";
		// ProfileReport p = new ProfileReport(linkedinProfileUrl, profileExperenceCSSPath, profileEductionCSSPath);
		// // p.parseProfilePage();
		// p.faceBookLogin();
		ProfileReport p = new ProfileReport();
		String fbProfileUrl;
		fbProfileUrl = "https://www.facebook.com/chun.chen.1422";
		p.parseFacebookProfilePage(fbProfileUrl);
	}

	public String getLinkedinProfileUrl() {
		return linkedinProfileUrl;
	}

	public void setLinkedinProfileUrl(String linkedinProfileUrl) {
		this.linkedinProfileUrl = linkedinProfileUrl;
	}

	public String getProfileExperenceCSSPath() {
		return profileExperenceCSSPath;
	}

	public void setProfileExperenceCSSPath(String profileExperenceCSSPath) {
		this.profileExperenceCSSPath = profileExperenceCSSPath;
	}

	public String getProfileEductionCSSPath() {
		return profileEductionCSSPath;
	}

	public void setProfileEductionCSSPath(String profileEductionCSSPath) {
		this.profileEductionCSSPath = profileEductionCSSPath;
	}
}
