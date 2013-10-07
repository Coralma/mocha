package com.coral.foundation.facebook;

public class FBUserModel {

	private String uid;
	private String profile_url;
	private String username;
	private String name;
	private String pic_square;

	public FBUserModel() {

	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getProfile_url() {
		return profile_url;
	}

	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPic_square() {
		return pic_square;
	}

	public void setPic_square(String pic_square) {
		this.pic_square = pic_square;
	}
}
