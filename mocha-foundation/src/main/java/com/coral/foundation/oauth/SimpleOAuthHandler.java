package com.coral.foundation.oauth;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.coral.foundation.facebook.FBImpl;
import com.coral.foundation.linkedin.LinkedinImpl;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.basic.dao.SoicalAppDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

public class SimpleOAuthHandler extends OauthHandler {

	private String userName;
	private String referrUrl;
	private String soicalAppName;
	private String oauthToken;
	private String oauthVerifier;
	private HttpServletRequest request;
	private String fbCode;

	// private static final String appId = "207409882754187";
	// private static final String appSecret = "d8a9c0f327aa1770e6fee1864658a037";
	// public static String facebookCallBackUrl = "https://www.mocha-platform.com/cooperate/facebook";

	private SoicalAppDao saDao = SpringContextUtils.getBean(SoicalAppDao.class);

	public SimpleOAuthHandler(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReferrUrl() {
		return referrUrl;
	}

	public void setReferrUrl(String referrUrl) {
		this.referrUrl = referrUrl;
	}

	public String getSoicalAppName() {
		return soicalAppName;
	}

	public void setSoicalAppName(String soicalAppName) {
		this.soicalAppName = soicalAppName;
	}

	@Override
	public boolean saveUserAuthenToken(BasicUser user) {
		// get oauth_verifier
		Enumeration en = request.getParameterNames();
		String token = null;

		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();
			System.out.println(paramName);
			if (paramName.equals("oauth_verifier")) {
				setOauthVerifier(request.getParameter(paramName));
			}
			if (paramName.equals("fr")) {
				token = request.getParameter(paramName);
				if (token.contains("linkedin?oauth_token=")) {
					token = token.split("=")[1];
					System.out.println(token);
					setOauthToken(token);
				}
			}
		}

		if (referrUrl.contains("oauth_token=") && referrUrl.contains("&oauth_verifier=")) {
			token = referrUrl.split("oauth_token=")[1].split("&oauth_verifier=")[0];
			setOauthToken(token);
			setOauthVerifier(referrUrl.split("oauth_token=")[1].split("&oauth_verifier=")[1]);
		}

		if (oauthToken != null && oauthVerifier != null) {
			SoicalApp sa = saDao.findSoicaAppByRequestToken(oauthToken);
			LinkedinImpl linkedImple = new LinkedinImpl();
			// for(SoicalApp soicalApp:basicUser.getSoicalApp()){

			if (sa.getName().equals("linkedin") && sa.getRequesToken().equals(token) && sa.getAuthToken() == null) {
				LinkedInRequestToken castToken = new LinkedInRequestToken(token, sa.getRequesTokenSecret());
				LinkedInAccessToken linkedAccessToken = linkedImple.getAccessToken(castToken, oauthVerifier);
				LinkedinPersonProfile personProfile = new LinkedinPersonProfile();

				Person person = linkedImple.getProfileForCurrentUser(linkedAccessToken);
				personProfile.setFirstName(person.getFirstName());
				personProfile.setLastName(person.getLastName());
				personProfile.setPictUrl(person.getPictureUrl());
				personProfile.setHeadline(person.getHeadline());
				sa.getLinkedinPersonProfiles().add(personProfile);
				sa.setAuthToken(linkedAccessToken.getToken());
				sa.setAuthTokenSecret(linkedAccessToken.getTokenSecret());
				saDao.merge(sa);

				// getUser().getSoicalApp().remove(soicalApp);
				// soicalApp.getLinkedinPersonProfiles().add(personProfile);
				// getUser().getSoicalApp().add(soicalApp);
				// buDao.merge(getUser());
				// setUser(user);
				// sa.setAuthToken(linkedAccessToken.getToken());
				// sa.setAuthTokenSecret(linkedAccessToken.getTokenSecret());
				// System.out.println("LinkedInAccessToken is: " + linkedAccessToken.getToken());
				// System.out.println("LinkedInAccessToken Secret is: " + linkedAccessToken.getTokenSecret());
				// saDao.merge(sa);
				// return true;
			}
		}
		return false;
	}

	public boolean saveUserFBAccessToken(BasicUser user) {
		SoicalApp sa = saDao.findSoicaAppByName(user, "facebook");
		if (sa == null) {

			// get facebook code
			String refererName = request.getHeader("referer");
			fbCode = refererName.split("code=")[1].trim();
			System.out.println("facebook code is: " + fbCode);
			// get facebook access token

			Properties properties = new Properties();
			properties.setProperty("DEBUG_ENABLED", "true");
			properties.setProperty("APP_ID", APIKeys.facebookAPIId);
			properties.setProperty("APP_SECRET", APIKeys.facebookSecertKey);
			properties.setProperty("JSON_STORE_ENABLED", "true");
			properties.setProperty("REDIRECT_URL", APIKeys.facebookCallBackUrl);

			ConfigurationBuilder confBuilder = new ConfigurationBuilder();
			confBuilder.setDebugEnabled(Boolean.parseBoolean(properties.getProperty("DEBUG_ENABLED")));
			confBuilder.setOAuthAppId(properties.getProperty("APP_ID"));
			confBuilder.setOAuthAppSecret(properties.getProperty("APP_SECRET"));
			confBuilder.setJSONStoreEnabled(Boolean.parseBoolean(properties.getProperty("JSON_STORE_ENABLED")));

			Configuration conf = confBuilder.build();

			String fbRenewTokenURL = "https://graph.facebook.com/oauth/access_token?code=" + fbCode + "&client_id=" + properties.getProperty("APP_ID")
					+ "&redirect_uri=" + properties.getProperty("REDIRECT_URL") + "&client_secret=" + properties.getProperty("APP_SECRET");

			System.out.println(fbRenewTokenURL);
			HttpGet httpost = new HttpGet(fbRenewTokenURL);
			DefaultHttpClient client = new DefaultHttpClient();
			String token = "";
			try {
				HttpResponse response = client.execute(httpost);
				ResponseHandler<String> handler = new BasicResponseHandler();
				token = StringUtils.removeStart(handler.handleResponse(response), "access_token=").split("&expires=")[0];
				System.out.println("Access Token is" + token);
			}
			catch (ClientProtocolException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}

			AccessToken accessToken = new AccessToken(token);
			// Facebook facebookClient = new FacebookFactory().getInstance(new OAuthAuthorization(conf));
			SoicalApp newSa = new SoicalApp();
			newSa.setUser(user);
			newSa.setName("facebook");

			newSa.setAuthToken(accessToken.getToken());
			if (newSa != null && newSa.getAuthToken() != null) {
				saDao.persist(newSa);
			}
		}
		return false;
	}

	public String getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}

	public String getOauthVerifier() {
		return oauthVerifier;
	}

	public void setOauthVerifier(String oauthVerifier) {
		this.oauthVerifier = oauthVerifier;
	}

}
