package com.mocha.ib.socialAPI.fb;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.coral.foundation.oauth.APIKeys;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FacebookImplTest extends TestSuite {

	@Test
	public void execTestCase() {
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(APIKeys.facebookAPIId, APIKeys.facebookSecertKey);
		AccessToken token = new AccessToken(
				"CAACEdEose0cBABIgo7ynAYdscHKgdkN74TBj72ZBLbuFhLAjmnQxVXwjZAwXDxJeO7AGNfxWRU3baFwJs0PZAZAmsH3MOYWtWOBtCqxO5WYjwmt2NRcezoezVA0VICxlpoFDAENOEtmaHG9uIQ98P9ckVjWaaLWTqIXRoISQ5mKwVjQS9u3gZCA8csG75sR1mpgCGx7L82gZDZD");
		facebook.setOAuthAccessToken(token);
		System.out.println(facebook.getOAuthAccessToken().getExpires());
		try {
			// Multiple FQL
			Map<String, String> queries = new HashMap<String, String>();
			queries.put("all friends", "SELECT uid2 FROM friend WHERE uid1=me()");
			queries.put("my name", "SELECT name FROM user WHERE uid=me()");
			Map<String, JSONArray> result = facebook.executeMultiFQL(queries);
			JSONArray allFriendsJSONArray = result.get("all friends");
			for (int i = 0; i < allFriendsJSONArray.length(); i++) {
				JSONObject jsonObject = allFriendsJSONArray.getJSONObject(i);
				System.out.println(jsonObject.get("uid2"));
			}
			JSONArray myNameJSONArray = result.get("my name");
			System.out.println(myNameJSONArray.getJSONObject(0).get("name"));
		}
		catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
