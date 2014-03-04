package com.coral.foundation.facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.coral.foundation.facebook.FBUserModel.FbTimeline;
import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.report.ProfileReport;
import com.coral.foundation.security.model.FacebookFriend;
import com.coral.foundation.security.model.FbFriendCurrentAddress;
import com.coral.foundation.security.model.FbFriendCurrentLocation;
import com.coral.foundation.security.model.FbFriendEduction;
import com.coral.foundation.security.model.FbFriendLanguage;
import com.coral.foundation.security.model.FbFriendProject;
import com.coral.foundation.security.model.FbFriendSport;
import com.coral.foundation.security.model.FbFriendWork;
import com.coral.foundation.socialAPI.FacebookToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import facebook4j.Account;
import facebook4j.Checkin;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.IdNameEntity;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.Subscriber;
import facebook4j.User;
import facebook4j.User.Education;
import facebook4j.User.Work;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.auth.OAuthSupport;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import facebook4j.conf.PropertyConfiguration;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class FBImpl {

	private Facebook facebook;
	private static Properties properties;
	private String accessToken;

	public FBUserModel.FbTimeline getTimelineInstance() {
		FBUserModel fbModel = new FBUserModel();
		return fbModel.new FbTimeline();
	}

	private Facebook getFacebookPublicInstance() {
		init();
		Configuration conf = createConfiguration();
		Facebook facebookClient = new FacebookFactory().getInstance(new OAuthAuthorization(conf));
		// set the//System Token here
		// AccessToken accessToken = new AccessToken(APIKeys.facebookSystemToken);
		// AccessToken accessToken = new AccessToken(accessToken);
		facebookClient.setOAuthAccessToken(new AccessToken(accessToken));
		return facebookClient;
	}

	public FBImpl(Facebook facebook) {
		this.facebook = facebook;
	}

	public FBImpl(String token) {
		this.accessToken = token;
		this.facebook = getFacebookPublicInstance();
	}

	public Facebook getFacebookInstance() {
		init();
		Configuration conf = createConfiguration();
		Facebook facebookClient = new FacebookFactory().getInstance(new OAuthAuthorization(conf));
		return facebookClient;
	}

	public static void main(String args[]) throws FBAuthException {
		init();
		Configuration configuration = createConfiguration();
		OAuthAuthorization oauth = new OAuthAuthorization(configuration);
		Facebook facebookClient = new FacebookFactory().getInstance(oauth);

		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// String code = null;
		// try {
		// code = br.readLine();
		// }
		// catch (IOException e) {
		// e.printStackTrace();
		// }
		// Facebook facebookClient = facebookFactory.getInstance();

		AccessToken accessToken = new AccessToken(
				"CAACEdEose0cBAIaQ7R8QYT7yLtKmXLryNRfICyzZASWZCSWKB8n6sSpCMKfHZCtfLhK6CbP0bobz22tDhZAcZCU7u4hjvN7IdagJDe7Yp0IyObGYKXqMMSogMHxrhDYB7sIARlB5UH0c46LxaFTAA8acOqGpQBZBII4X8zSjOpAGOc5Lx6Wo4UfN6QC0LG2l4npS60LRWMwQZDZD");
		try {
			OAuthSupport oAuthSupport = new OAuthAuthorization(configuration);
			// accessToken = oAuthSupport.getOAuthAppAccessToken();
			System.out.println("accessToken is: " + accessToken);
			facebookClient.setOAuthAccessToken(accessToken);
			ResponseList<Checkin> checkins = facebookClient.searchCheckins();
			System.out.println("Total checkins are : " + checkins.size());
			String id = facebookClient.getFriends().get(0).getId();
			////System.out.println("user id : " + id);
			// for (Post status : facebookClient.getStatuses(id)) {
			////System.out.println(status.getMessage() + " " + status.getName() + "" + status.getStatusType() + " " + status.getId());
			// }
			////System.out.println(facebookClient.getSubscribedto().size());
			////System.out.println(facebookClient.getSubscribers().size());
			////System.out.println(facebookClient.getCheckins().size());
			// for (Subscriber sub : facebookClient.getSubscribers()) {
			////System.out.println(sub.getName());
			// }
			// for (Checkin checkin : facebookClient.getCheckins()) {
			////System.out.println(checkin);
			// }
			// for (Friend firend : facebookClient.getFriends()) {
			////System.out.println(firend.getName() + firend.getId());
			// if (firend.getName().contains("Zhaoone  Vance")) {
			////System.out.println("Find ");
			// for (Post status : facebookClient.getStatuses(firend.getId())) {
			////System.out.println(status.getMessage() + " " + status.getName() + "" + status.getStatusType() + " " + status.getId());
			// }
			// }
			// }
			FBImpl fbImpl = new FBImpl(facebookClient);
			FacebookFriend fbFriend = fbImpl.getFacebookUserByProfile("https://www.facebook.com/zhaoone.vance");
			System.out.println(fbFriend);
		}
		catch (FacebookException e) {
			System.out.println("Error while creating access token " + e.getLocalizedMessage());
			if (e.getErrorCode() == 190 && e.getErrorSubcode() == 463) {
				System.out.println("Start to flush the access token now");
				reflushToken(accessToken);
			}
		}
	}

	/* TBD */
	private static void reflushToken(AccessToken accessToken) {
		System.out.println(accessToken.getToken());
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://graph.facebook.com/oauth/access_token?" + "client_id=" + APIKeys.facebookAPIId + "&" + "client_secret="
				+ APIKeys.facebookSecertKey + "&" + "grant_type=fb_exchange_token" + "&" + "fb_exchange_token=" + accessToken.getToken();

		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			System.out.println("Access Token has flush: ");
			String line = "";
			while ((line = rd.readLine()) != null) {

				System.out.println(line);
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
	}

	private static void init() {
		properties = new Properties();
		properties.setProperty("DEBUG_ENABLED", "true");
		properties.setProperty("APP_ID", APIKeys.facebookAPIId);
		properties.setProperty("APP_SECRET", APIKeys.facebookSecertKey);
		properties.setProperty("JSON_STORE_ENABLED", "true");
		properties.setProperty("REDIRECT_URL", APIKeys.facebookCallBackUrl);
	}

	private static Configuration createConfiguration() {
		ConfigurationBuilder confBuilder = new ConfigurationBuilder();
		confBuilder.setDebugEnabled(Boolean.parseBoolean(properties.getProperty("DEBUG_ENABLED")));
		confBuilder.setOAuthAppId(properties.getProperty("APP_ID"));
		confBuilder.setOAuthAppSecret(properties.getProperty("APP_SECRET"));
		confBuilder.setJSONStoreEnabled(Boolean.parseBoolean(properties.getProperty("JSON_STORE_ENABLED")));
		return confBuilder.build();
	}

	/*
	 * SELECT uid, username, name, pic_square, profile_url FROM user WHERE uid = me() OR uid IN (SELECT uid2 FROM friend WHERE uid1 = me()) and
	 * (strpos(name,'Chun') >=0 or strpos(name,'Chen')>=0 or strpos(name, 'Chun Chen')>=0 )
	 */
	public static List<FBUserModel> fuzzySearchFriends(AccessToken accessToken, String firstName, String lastName, String userName) throws FacebookException {
		Facebook facebook = getDefaultSystemInstance();
		facebook.setOAuthAccessToken(accessToken);
		List<FBUserModel> fbUsers = new ArrayList<FBUserModel>();
		try {
			// Single FQL
			String query = "SELECT uid, username, name, pic_square, profile_url FROM user WHERE uid = me() OR uid "
					+ "IN (SELECT uid2 FROM friend WHERE uid1 = me()) and(strpos(name,'" + firstName + "') >=0 or " + "strpos(name,'" + lastName
					+ "')>=0 or strpos(name, '" + userName + "')>=0 )";
			JSONArray jsonArray = facebook.executeFQL(query);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject;
				jsonObject = jsonArray.getJSONObject(i);
				FBUserModel fbUser = new FBUserModel();
				fbUser.setName(jsonObject.getString("name"));
				fbUser.setUid(jsonObject.getString("uid"));
				fbUser.setProfile_url(jsonObject.getString("profile_url"));
				fbUser.setUsername(jsonObject.getString("username"));
				fbUser.setPic_square(jsonObject.getString("pic_square"));
				fbUsers.add(fbUser);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		catch (FacebookException e) {
			e.printStackTrace();
		}
		return fbUsers;
	}

	public FBUserModel searchUserProfileByNameAndUrl(String userName, String profileUrl) throws FacebookException {
		facebook = getFacebookPublicInstance();
		// facebook.setOAuthAccessToken(accessToken);
		List<FBUserModel> fbUsers = new ArrayList<FBUserModel>();
		try {
			// Single FQL
			String query = "SELECT id, name, url, pic,username,pic_small, type FROM profile" + " where name = '" + userName + "'and url='" + profileUrl + "'";
			System.out.println("fql : " + query);
			JSONArray jsonArray = facebook.executeFQL(query);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject;
				jsonObject = jsonArray.getJSONObject(i);
				FBUserModel fbUser = new FBUserModel();
				fbUser.setName(jsonObject.getString("name"));
				fbUser.setUid(jsonObject.getString("id"));
				fbUser.setProfile_url(jsonObject.getString("url"));
				fbUser.setUsername(jsonObject.getString("username"));
				fbUser.setPic_square(jsonObject.getString("pic"));
				fbUser.setRealName(userName);
				fbUsers.add(fbUser);
				return fbUsers.get(0);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		catch (FacebookException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Facebook getDefaultSystemInstance() {
		init();
		Configuration conf = createConfiguration();
		Facebook facebookClient = new FacebookFactory().getInstance(new OAuthAuthorization(conf));
		// set the//System Token here
		AccessToken accessToken = new AccessToken(APIKeys.facebookSystemToken);
		facebookClient.setOAuthAccessToken(accessToken);
		return facebookClient;
	}

	public static List<FBUserModel> fuzzysearchFBUsers(String keyword) {
		Facebook facebook = getDefaultSystemInstance();
		List<FBUserModel> fbUsers = new ArrayList<FBUserModel>();
		try {
			// Single FQL
			String query = "SELECT uid, username, name, pic_square, profile_url FROM user WHERE contains(\"" + keyword + "\")";
			JSONArray jsonArray = facebook.executeFQL(query);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject;
				jsonObject = jsonArray.getJSONObject(i);
				FBUserModel fbUser = new FBUserModel();
				fbUser.setName(jsonObject.getString("name"));
				fbUser.setUid(jsonObject.getString("uid"));
				fbUser.setProfile_url(jsonObject.getString("profile_url"));
				fbUser.setUsername(jsonObject.getString("username"));
				fbUser.setPic_square(jsonObject.getString("pic_square"));
				System.out.println(fbUser);
				fbUsers.add(fbUser);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		catch (FacebookException e) {
			e.printStackTrace();
		}
		return fbUsers;
	}

	/* Get agent's connections */
	public ResponseList<Friend> getFacebookConnections(FacebookToken fbToken) {
		try {
			ResponseList<Friend> friends = facebook.getFriends();
			return friends;
		}
		catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static FbTimeline getTimelineByProfileUrl(String fbProfileUrl) {
		ProfileReport p = new ProfileReport();
		FbTimeline timeline = p.parseFacebookProfileTimeline(fbProfileUrl);
		// timelines.add(p.getDefaultFBHeader());
		////System.out.println(p.getDefaultFBHeader());
		// for (String s : timelines) {
		// ////System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		////System.out.println(s);
		// ////System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		// }
		return timeline;
	}

	public FacebookFriend getFacebookUserByProfile(String profileUrl) {
		String getUIdQuery = "SELECT uid,name " + "FROM user WHERE (uid = me() or uid IN (SELECT uid2 FROM friend WHERE uid1 = me())) "
				+ "and strpos(lower(profile_url),'" + profileUrl + "')>=0";
		try {
			////System.out.println(getUIdQuery);
			JSONArray jsonArray = facebook.executeFQL(getUIdQuery);
			if (jsonArray.length() <= 0) {
				return null;
			}
			if (jsonArray.length() == 1) {
				JSONObject jsonObject = jsonArray.getJSONObject(0);
				String uid = jsonObject.getString("uid");
				User user = facebook.getUser(uid);
				FacebookFriend facebookFriend = getBaiscFacebookfriendInfo(uid);
				if (user.getBirthday() != null) {
					facebookFriend.setBirthday(user.getBirthday());
				}
				if (user.getEmail() != null) {
					facebookFriend.setContact_email(user.getEmail());
				}

				for (Education ed : user.getEducation()) {
					FbFriendEduction fbEd = new FbFriendEduction();
					fbEd.setShcool_name(ed.getSchool().getName());
					fbEd.setType(ed.getType());
					fbEd.setYear_name(ed.getYear().getName());
					facebookFriend.getEducations().add(fbEd);
				}

				facebookFriend.setFirst_name(user.getFirstName());
				facebookFriend.setLast_name(user.getLastName());
				facebookFriend.setSex(user.getGender());
				for (IdNameEntity language : user.getLanguages()) {
					FbFriendLanguage fbLang = new FbFriendLanguage();
					fbLang.setName(language.getName());
				}

				facebookFriend.setProfile_url(user.getLink().toString());
				FbFriendCurrentLocation fbFriendCurrentLocaiton = new FbFriendCurrentLocation();
				if (user.getLocation() != null) {
					fbFriendCurrentLocaiton.setName(user.getLocation().getName());
				}
				facebookFriend.setCurrentLocation(fbFriendCurrentLocaiton);
				if (user.getPicture() != null) {
					facebookFriend.setPic(user.getPicture().toString());
				}
				if (user.getRelationshipStatus() != null) {
					facebookFriend.setRelationship_status(user.getRelationshipStatus());
				}
				if (user.getWebsite() != null) {
					facebookFriend.setWebsite(user.getWebsite().toString());
				}

				for (Work work : user.getWork()) {
					FbFriendWork fbWork = new FbFriendWork();
					fbWork.setStart_date(work.getStartDate());
					fbWork.setEmployer_name(work.getEmployer().getName());
					if (work.getLocation() != null && work.getLocation().getName() != null) {
						fbWork.setLocation_name(work.getLocation().getName());
					}
					facebookFriend.getWorks().add(fbWork);
				}
				////System.out.println(facebookFriend);
				return facebookFriend;
			}
		}
		catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// We will add more logic here
	// Just implement the single result return
	private FacebookFriend getBaiscFacebookfriendInfo(String uid) {
		String query = "SELECT uid, name, relationship_status,profile_url,education,email,birthday,"
				+ "about_me,activities,affiliations,allowed_restrictions,books,contact_email,currency,"
				+ "current_address,devices,first_name,last_name,friend_count,has_timeline,hometown_location,"
				+ "inspirational_people,interests,languages,likes_count,locale,meeting_for,meeting_sex,"
				+ "middle_name,movies,music,mutual_friend_count,notes_count,online_presence,payment_pricepoints,pic,"
				+ "pic_small,political,profile_blurb,profile_update_time,profile_url,quotes,religion,search_tokens,security_settings,"
				+ "sex,sports,subscriber_count,third_party_id,timezone,tv,viewer_can_send_gift,website,work,games "
				+ "FROM user WHERE (uid = me() or uid IN (SELECT uid2 FROM friend WHERE uid1 = me())) " + "and uid ='" + uid + "'";

		try {
			System.out.println(query);
			JSONArray jsonArray = facebook.executeFQL(query);
			if (jsonArray.length() > 1) {
				System.out.println("More than one result return");
			}
			if (jsonArray.length() == 1) {
				FacebookFriend facebookFriend = new FacebookFriend();
				JSONObject jsonObject;
				jsonObject = jsonArray.getJSONObject(0);
				facebookFriend.setUid(jsonObject.getString("uid"));
				facebookFriend.setName(jsonObject.getString("name"));
				facebookFriend.setRelationship_status(jsonObject.getString("relationship_status"));
				facebookFriend.setProfile_url(jsonObject.getString("profile_url"));
				facebookFriend.setEmail(jsonObject.getString("email"));
				facebookFriend.setBirthday(jsonObject.getString("birthday"));
				facebookFriend.setAbout_me(jsonObject.getString("about_me"));
				facebookFriend.setActivities(jsonObject.getString("activities"));
				facebookFriend.setAllowed_restrictions(jsonObject.getString("allowed_restrictions"));
				facebookFriend.setBooks(jsonObject.getString("books"));
				facebookFriend.setContact_email(jsonObject.getString("contact_email"));
				facebookFriend.setCurrency(jsonObject.getString("currency"));

				////System.out.println("current_address " + jsonObject.getString("current_address"));
				// if (jsonObject.has("current_address") && jsonObject.getString("current_address") != null) {
				// FbFriendCurrentAddress currentAddress = new FbFriendCurrentAddress();
				// JSONObject currentAddressObject = new JSONObject(jsonObject.get("current_address"));
				////System.out.println("currentAddressObject " + currentAddressObject.names());
				// if (currentAddressObject.has("name")) {
				// currentAddress.setName(currentAddressObject.getString("name"));
				// }
				// if (currentAddressObject.has("street")) {
				// currentAddress.setStreet(currentAddressObject.getString("street"));
				// }
				// if (currentAddressObject.has("city")) {
				// currentAddress.setCity(currentAddressObject.getString("city"));
				// }
				// if (currentAddressObject.has("state")) {
				// currentAddress.setState(currentAddressObject.getString("state"));
				// }
				// currentAddress.setCountry(currentAddressObject.getString("country"));
				// currentAddress.setZip(currentAddressObject.getString("zip"));
				// currentAddress.setLatitude(currentAddressObject.getString("latitude"));
				// currentAddress.setLongitude(currentAddressObject.getString("longitude"));
				// currentAddress.setLocated_in(currentAddressObject.getString("located_in"));
				// currentAddress.setRegion(currentAddressObject.getString("region"));
				// facebookFriend.setCurrentAddress(currentAddress);
				// }
				//
				// if (jsonObject.has("current_location")) {
				// FbFriendCurrentLocation currentLocation = new FbFriendCurrentLocation();
				// JSONObject currentAddressObject = new JSONObject(jsonObject.get("current_location"));
				// currentLocation.setName(currentAddressObject.getString("name"));
				// currentLocation.setStreet(currentAddressObject.getString("street"));
				// currentLocation.setCity(currentAddressObject.getString("city"));
				// currentLocation.setState(currentAddressObject.getString("state"));
				// currentLocation.setCountry(currentAddressObject.getString("country"));
				// currentLocation.setZip(currentAddressObject.getString("zip"));
				// currentLocation.setLatitude(currentAddressObject.getString("latitude"));
				// currentLocation.setLongitude(currentAddressObject.getString("longitude"));
				// currentLocation.setLocated_in(currentAddressObject.getString("located_in"));
				// currentLocation.setRegion(currentAddressObject.getString("region"));
				// facebookFriend.setCurrentLocation(currentLocation);
				// }
				//
				// if (jsonObject.getJSONArray("education") != null && jsonObject.getJSONArray("education").length() > 0) {
				// JSONArray educations = jsonObject.getJSONArray("education");
				// for (int i = 0; i < educations.length(); i++) {
				// JSONObject eductionObject = educations.getJSONObject(i);
				// FbFriendEduction education = new FbFriendEduction();
				// // school
				// if (eductionObject.has("school")) {
				// education.setShcool_id(eductionObject.getJSONObject("school").getString("id"));
				// education.setShcool_name(eductionObject.getJSONObject("school").getString("name"));
				// }
				// // degree
				// if (eductionObject.has("degree")) {
				// education.setDegree_id(eductionObject.getJSONObject("degree").getString("id"));
				// education.setDegree_name(eductionObject.getJSONObject("degree").getString("name"));
				// }
				// // year
				// if (eductionObject.has("year")) {
				// education.setYear_id(eductionObject.getJSONObject("year").getString("id"));
				// education.setYear_name(eductionObject.getJSONObject("year").getString("name"));
				// }
				// facebookFriend.getEducations().add(education);
				// }
				// }
				//
				// if (jsonObject.getJSONArray("languages") != null && jsonObject.getJSONArray("languages").length() > 0) {
				// JSONArray languageArrays = jsonObject.getJSONArray("languages");
				// for (int i = 0; i < languageArrays.length(); i++) {
				// JSONObject languageObject = languageArrays.getJSONObject(i);
				// FbFriendLanguage fbFriendLanguage = new FbFriendLanguage();
				// if (languageObject.has("description")) {
				// fbFriendLanguage.setDescription(languageObject.getString("description"));
				// }
				// if (languageObject.has("name")) {
				// fbFriendLanguage.setName(languageObject.getString("name"));
				// }
				// facebookFriend.getLanguages().add(fbFriendLanguage);
				// }
				// }
				//
				// if (jsonObject.getJSONArray("sports") != null && jsonObject.getJSONArray("sports").length() > 0) {
				// JSONArray sportsArray = jsonObject.getJSONArray("sports");
				// for (int i = 0; i < sportsArray.length(); i++) {
				// JSONObject sportsObject = sportsArray.getJSONObject(i);
				// FbFriendSport fbFriendSport = new FbFriendSport();
				// fbFriendSport.setDescription(sportsObject.getString("description"));
				// fbFriendSport.setName(sportsObject.getString("name"));
				// facebookFriend.getSports().add(fbFriendSport);
				// }
				// }
				//
				// if (jsonObject.getJSONArray("work") != null && jsonObject.getJSONArray("work").length() > 0) {
				// JSONArray array = jsonObject.getJSONArray("work");
				// for (int i = 0; i < array.length(); i++) {
				// FbFriendWork fbFriendWork = new FbFriendWork();
				// JSONObject object = array.getJSONObject(i);
				// fbFriendWork.setEmployer_name(object.getJSONObject("employer").getString("name"));
				// fbFriendWork.setLocation_name(object.getJSONObject("location").getString("name"));
				// fbFriendWork.setPostion_name(object.getJSONObject("position").getString("name"));
				// if (object.has("description")) {
				// fbFriendWork.setDescription(object.getString("description"));
				// }
				// if (object.has("start_date")) {
				// fbFriendWork.setDescription(object.getString("start_date"));
				// }
				// if (object.has("end_date")) {
				// fbFriendWork.setDescription(object.getString("end_date"));
				// }
				// if (object.has("projects") && object.getJSONArray("projects") != null) {
				// for (int j = 0; j < object.getJSONArray("projects").length(); j++) {
				// JSONObject projectObj = object.getJSONArray("projects").getJSONObject(j);
				// FbFriendProject project = new FbFriendProject();
				// project.setDescription(projectObj.getString("description"));
				// project.setName(projectObj.getString("name"));
				// project.setStart_date(projectObj.getString("start_date"));
				// project.setEnd_date(projectObj.getString("end_date"));
				// fbFriendWork.getFbFriendProjects().add(project);
				// }
				// }
				// facebookFriend.getWorks().add(fbFriendWork);
				// }
				// }

				facebookFriend.setFirst_name(jsonObject.getString("first_name"));
				facebookFriend.setLast_name(jsonObject.getString("last_name"));
				facebookFriend.setFriend_count(jsonObject.getString("friend_count"));
				facebookFriend.setHas_timeline(jsonObject.getString("has_timeline"));
				facebookFriend.setHometown_location(jsonObject.getString("hometown_location"));
				facebookFriend.setInterests(jsonObject.getString("interests"));
				facebookFriend.setLikes_count(jsonObject.getString("likes_count"));
				facebookFriend.setLocale(jsonObject.getString("locale"));
				facebookFriend.setMeeting_for(jsonObject.getString("meeting_for"));
				facebookFriend.setMeeting_sex(jsonObject.getString("meeting_sex"));
				facebookFriend.setMiddle_name(jsonObject.getString("middle_name"));
				facebookFriend.setMovies(jsonObject.getString("movies"));
				facebookFriend.setMusic(jsonObject.getString("music"));
				facebookFriend.setMutual_friend_count(jsonObject.getString("mutual_friend_count"));
				facebookFriend.setNotes_count(jsonObject.getString("notes_count"));
				facebookFriend.setOnline_presence(jsonObject.getString("online_presence"));
				// facebookFriend.setPayment_pricepoints(jsonObject.getString("payment_pricepoints"));
				facebookFriend.setPic(jsonObject.getString("pic"));
				facebookFriend.setPic_small(jsonObject.getString("pic_small"));
				facebookFriend.setPolitical(jsonObject.getString("political"));
				facebookFriend.setProfile_blurb(jsonObject.getString("profile_blurb"));
				facebookFriend.setProfile_update_time(jsonObject.getString("profile_update_time"));
				facebookFriend.setQuotes(jsonObject.getString("quotes"));
				facebookFriend.setReligion(jsonObject.getString("religion"));
				facebookFriend.setSecurity_settings(jsonObject.getString("security_settings"));
				facebookFriend.setSex(jsonObject.getString("sex"));
				facebookFriend.setSubscriber_count(jsonObject.getString("subscriber_count"));
				facebookFriend.setThird_party_id(jsonObject.getString("third_party_id"));
				facebookFriend.setTimezone(jsonObject.getString("timezone"));
				facebookFriend.setTv(jsonObject.getString("tv"));
				facebookFriend.setViewer_can_send_gift(jsonObject.getString("viewer_can_send_gift"));
				facebookFriend.setWebsite(jsonObject.getString("website"));
				facebookFriend.setGames(jsonObject.getString("games"));
				/* Languages */
				JSONArray languagArrays = jsonObject.getJSONArray("languages");
				if (languagArrays != null) {
					System.out.println("Total Language size: " + languagArrays.length());
				}
				return facebookFriend;
			}
		}
		catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getProfileUpdateTimeByUid(String uid) {
		String query = "SELECT profile_update_time from user where uid = '" + uid + "'";
		try {
			JSONArray jsonArray = facebook.executeFQL(query);
			return jsonArray.getJSONObject(0).getString("profile_update_time");
		}
		catch (FacebookException e) {
			e.printStackTrace();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
