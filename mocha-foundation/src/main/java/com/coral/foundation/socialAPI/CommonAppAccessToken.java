package com.coral.foundation.socialAPI;

import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;

public class CommonAppAccessToken implements AppAccessToken {

	private LinkedInAccessToken linkedToken;
	private FacebookToken faceBookToken;

	public CommonAppAccessToken() {

	}

	@Override
	public void setToken(LinkedInAccessToken linkedToken, FacebookToken faceBookToken) {
		setLinkedToken(linkedToken);
		setFaceBookToken(faceBookToken);
	}

	public LinkedInAccessToken getLinkedToken() {
		return linkedToken;
	}

	public void setLinkedToken(LinkedInAccessToken linkedToken) {
		this.linkedToken = linkedToken;
	}

	public FacebookToken getFaceBookToken() {
		return faceBookToken;
	}

	public void setFaceBookToken(FacebookToken faceBookToken) {
		this.faceBookToken = faceBookToken;
	}

	@Override
	public LinkedInAccessToken getLinkedinAccessToken() {
		return getLinkedToken();
	}

	@Override
	public FacebookToken getFacebookToken() {
		return getFaceBookToken();
	}

}
