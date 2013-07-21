package com.coral.foundation.oauth;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.coral.foundation.linkedin.LinkedinImpl;
import com.coral.foundation.security.basic.dao.BasicUserDao;
import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.model.SoicalApp;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;

public class SimpleOAuthHandler extends OauthHandler {

	private String userName;
	private String referrUrl;
	private String soicalAppName;
	private String oauthToken;
	private String oauthVerifier;
	private HttpServletRequest request;

	public SimpleOAuthHandler(HttpServletRequest request) {
		super(request);
		this.request=request;
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
	public void saveUserAuthenToken(HttpServletRequest request) {
		// get oauth_verifier
		Enumeration en = request.getParameterNames();
		String token = null;
		
		while (en.hasMoreElements()) {
			String paramName = (String) en.nextElement();
			if (paramName.equals("oauth_verifier")) {
				setOauthVerifier(request.getParameter(paramName));
			}
			if(paramName.equals("fr")){
				token=request.getParameter(paramName);
				if(token.contains("linkedin?oauth_token=")){
					token=token.split("=")[1];
					System.out.println(token);
					setOauthToken(token);					
				}
			}
		}
		
		if (oauthToken != null && oauthVerifier != null) {
			if (userName != null && soicalAppName != null) {
				BasicUser basicUser = buDao.findUserByUserName(userName);
//				SoicalApp soicalApp = new SoicalApp();
//				soicalApp.setName(soicalAppName);
//				soicalApp.setAuthToken(oauthToken);
//				soicalApp.setOauthVerifier(oauthVerifier);
//				basicUser.getSoicalApp().add(soicalApp);
				//save the access token
				LinkedinImpl linkedImple=new LinkedinImpl();
				for(SoicalApp soicalApp:basicUser.getSoicalApp()){
					int i=0;
					if(soicalApp.getName().equals("linkedin") && soicalApp.getRequesToken().equals(token)){
						LinkedInRequestToken castToken=new LinkedInRequestToken(token,soicalApp.getRequesTokenSecret());
						LinkedInAccessToken linkedAccessToken=linkedImple.getAccessToken(castToken, oauthVerifier);
						soicalApp.setAuthToken(linkedAccessToken.getToken());
						soicalApp.setAuthTokenSecret(linkedAccessToken.getTokenSecret());
						System.out.println("LinkedInAccessToken is: "+linkedAccessToken.getToken());
						System.out.println("LinkedInAccessToken Secret is: "+linkedAccessToken.getTokenSecret());
						soicalApp.setUser(basicUser);
						basicUser.getSoicalApp().set(i,soicalApp);
						buDao.merge(basicUser);
						break;
					}
					i++;
				}
				
			}
		}
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
