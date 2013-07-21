package com.coral.foundation.linkedin;

import java.util.List;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Connections;
import com.google.code.linkedinapi.schema.Person;

public class LinkedinImpl {

	private String consumerKeyValue = "pps9akw5t85u";
	private String consumerSecretValue = "rz2R2HpXYQoQasr2";
	private String callBackUrl = "https://www.mocha-platform.com/cooperate";
	private final LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(consumerKeyValue,
			consumerSecretValue);
	private final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
	
	public LinkedinImpl(String consumerKeyValue, String consumerSecretValue, String callBackUrl) {
		this.consumerKeyValue = consumerKeyValue;
		this.consumerSecretValue = consumerSecretValue;
		this.callBackUrl = callBackUrl;
	}

	public LinkedinImpl() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedinImpl apiSample = new LinkedinImpl();
		String callbackUrl = "https://www.mocha-platform.com/cooperate";
		// LinkedInRequestToken requestToken = apiSample.getOauthService().getOAuthRequestToken();
		LinkedInRequestToken requestToken = apiSample.getOauthService().getOAuthRequestToken(callbackUrl);
		String authUrl = requestToken.getAuthorizationUrl();
		System.out.println(authUrl);
	}

	public LinkedInOAuthService getOauthService() {
		return oauthService;
	}

	public LinkedInRequestToken getLinkedInRequestToken() {
		LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken(callBackUrl);
		return requestToken;
	}

	public String getCallBackUrl() {
		return callBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}
	
	public LinkedInAccessToken getAccessToken(LinkedInRequestToken requestToken,String oauthVerifier){
		LinkedInAccessToken accessToken = oauthService.getOAuthAccessToken(requestToken, oauthVerifier);
		return accessToken;
	}
	
	
	public List<Person> getUserConnects(LinkedInAccessToken accessToken){
		LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
		Connections connects=client.getConnectionsForCurrentUser();
		List<Person> personList=connects.getPersonList();
		for(Person person:personList){
			System.out.println(person.getFirstName()+"."+person.getLastName());			
		}
		return personList;
	}
	
	public Person getProfileForCurrentUser(LinkedInAccessToken accessToken){
		LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
		Person profile = client.getProfileForCurrentUser();
		return profile;
	}
	
}
