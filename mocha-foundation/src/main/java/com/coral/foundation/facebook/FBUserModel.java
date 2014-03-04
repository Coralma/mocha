package com.coral.foundation.facebook;

import java.util.ArrayList;
import java.util.List;

public class FBUserModel {

	private String uid;
	private String profile_url;
	private String username;
	private String name;
	private String pic_square;
	private String id;
	private String type;
	private FbTimeline fbTimeline = new FbTimeline();
	private String realName;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FbTimeline getFbTimeline() {
		return fbTimeline;
	}

	public void setFbTimeline(FbTimeline fbTimeline) {
		this.fbTimeline = fbTimeline;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public class FbTimeline {

		private String header;
		private List<String> content = new ArrayList<String>();

		public FbTimeline() {
		}

		public String getHeader() {
			return header;
		}

		public void setHeader(String header) {
			this.header = header;
		}

		public List<String> getContent() {
			return content;
		}

		public void setContent(List<String> content) {
			this.content = content;
		}
	}
}
