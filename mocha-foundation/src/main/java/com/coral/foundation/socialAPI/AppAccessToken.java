package com.coral.foundation.socialAPI;

import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;

import facebook4j.Facebook;

public abstract interface AppAccessToken {

	abstract void setToken(LinkedInAccessToken linkedToken, FacebookToken faceBookToken);

	abstract LinkedInAccessToken getLinkedinAccessToken();

	abstract FacebookToken getFacebookToken();
}
