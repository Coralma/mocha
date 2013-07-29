package com.coral.foundation.oauth;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.coral.foundation.linkedin.LinkedinImpl;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.LinkedinPersonProfile;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;

public class SimpleOAuthHandler extends OauthHandler {

	private String userName;
	private String referrUrl;
	private String soicalAppName;
	private String oauthToken;
	private String oauthVerifier;
	private HttpServletRequest request;

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
	public boolean saveUserAuthenToken(HttpServletRequest request) {
		// get oauth_verifier
		Enumeration en = request.getParameterNames();
		String token = null;

		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();
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

		if (oauthToken != null && oauthVerifier != null) {
			SoicalApp sa = saDao.findSoicaAppByRequestToken(oauthToken);
			LinkedinImpl linkedImple = new LinkedinImpl();
			// for(SoicalApp soicalApp:basicUser.getSoicalApp()){

			if (sa.getName().equals("linkedin") && sa.getRequesToken().equals(token)) {
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
