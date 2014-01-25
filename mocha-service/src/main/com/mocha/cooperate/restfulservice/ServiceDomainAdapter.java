package com.mocha.cooperate.restfulservice;

import java.util.ArrayList;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.codehaus.jackson.xc.XmlAdapterJsonDeserializer;

import com.mocha.service.model.UserFeedType;
import com.mocha.service.model.UserFeeds;

public class ServiceDomainAdapter extends XmlAdapter<UserFeedType, ArrayList<UserFeeds>> {

	@Override
	public ArrayList<UserFeeds> unmarshal(UserFeedType userFeedType) throws WebApplicationException {
		System.out.println("start to unmarshall the object");
		return null;
	}

	@Override
	public UserFeedType marshal(ArrayList<UserFeeds> userFeeds) throws WebApplicationException {
		System.out.println("start to marshall the object");
		UserFeedType userFeedType = new UserFeedType();
		for (UserFeeds userFeed : userFeeds) {
			userFeedType.getUserFeeds().add(userFeed);
		}
		return userFeedType;
	}
}
