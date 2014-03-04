package com.coral.foundation.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.hssf.record.formula.eval.NameEval;

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
		Enumeration<?> en = request.getParameterNames();
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

				if (saDao.findSoicaAppByAuthToken(linkedAccessToken.getToken()) != null) {
					return false;
				}

				Person person = linkedImple.getProfileForCurrentUser(linkedAccessToken);
				personProfile.setFirstName(person.getFirstName());
				personProfile.setLastName(person.getLastName());
				personProfile.setPictUrl(person.getPictureUrl());
				personProfile.setHeadline(person.getHeadline());
				sa.getLinkedinPersonProfiles().add(personProfile);
				sa.setAuthToken(linkedAccessToken.getToken());
				sa.setAuthTokenSecret(linkedAccessToken.getTokenSecret());
				sa.setUser(user);
				saDao.merge(sa);
			}
		}
		return false;
	}

	// create new facebook token
	public boolean saveUserFBAccessToken(BasicUser user) {
		SoicalApp sa = saDao.findSoicaAppByName(user, "facebook");
		if (sa == null) {
			String accessTokenStr = request.getParameter("access_token");
			Enumeration names = request.getParameterNames();
			AccessToken accessToken = null;
			while (names.hasMoreElements()) {
				String paramName = names.nextElement().toString();
				if (paramName.equals("fr")) {
					String token = request.getParameter(paramName);
					accessToken = new AccessToken(token);

					// if (token.contains("access_token")) {
					// token = token.split("=")[1];
					// System.out.println(token);
					// break;
					// }
				}
			}
			SoicalApp newSa = new SoicalApp();
			newSa.setUser(user);
			newSa.setName("facebook");
			newSa.setAuthToken(getFbLongLiveToken(accessToken.getToken()).getToken());
			if (newSa != null && newSa.getAuthToken() != null) {
				user.getSoicalApp().add(newSa);
				buDao.merge(user);
				// saDao.persist(newSa);
			}
		}
		return false;
	}

	private AccessToken getFbLongLiveToken(String token) {
		String fbRenewTokenURL = "https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token" + "&client_id=" + APIKeys.facebookAPIId
				+ "&client_secret=" + APIKeys.facebookSecertKey + "&fb_exchange_token=" + token;
		System.out.println(fbRenewTokenURL);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String line;
		String newAccessToken = "";
		Long expire = null;

		HttpGet httpGet = new HttpGet(fbRenewTokenURL);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			System.out.println("Access Token has flush: ");
			while ((line = rd.readLine()) != null) {
				System.out.println("response line: " + line);
				newAccessToken = StringUtils.substringAfter(StringUtils.substringBefore(line, "&"), "=");
				System.out.println("newAccessToken is: " + newAccessToken);
				expire = Long.valueOf(line.split("expires=")[1].toString());
				if (expire != null) {
					break;
				}
			}
			System.out.println("Access Token has Done flush: ");
		}
		catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AccessToken returnToken = new AccessToken(newAccessToken, expire);
		System.out.println("old token is: " + token + "\n" + "new token is: " + returnToken.getToken());
		return returnToken;
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

	public static void main(String args[]) {
		String line = "access_token=CAAC8o2BibIsBACZCFNsGhLbCca1ZA3TXzZB0xrkqpjNpr7gyVohOIRw41ThYVP5L9M405LVLXDKuizx7mO7d4W68PwtIpTmZAY7R4VcNZBfaJtrC70g25QUJGysmUtNfMDQ98ZAyna0HPOnVEFSF98fYUuh23HJHXRFuy5PqtYL5DdVqNZBTYelXSFWDltYq7kZD&expires=5182324";
		String newStr = StringUtils.substringAfter(StringUtils.substringBefore(line, "&"), "=");
		System.out.println("str: " + newStr);
	}
}
