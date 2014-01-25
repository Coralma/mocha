package com.mocha.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import com.mocha.cooperate.model.ToDo;

public class UserFeedType {

	private List<UserFeeds> userFeeds = new ArrayList<UserFeeds>();

	public UserFeedType() {

	}

	public List<UserFeeds> getUserFeeds() {
		return userFeeds;
	}

	public void setUserFeeds(List<UserFeeds> userFeeds) {
		this.userFeeds = userFeeds;
	}
}
