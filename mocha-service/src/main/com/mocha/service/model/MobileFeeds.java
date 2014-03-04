package com.mocha.service.model;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.TimeLine;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.restfulservice.ServiceDomainAdapter;

@Entity
@XmlRootElement(name = "MobileFeeds")
@XmlAccessorType(XmlAccessType.FIELD)
public class MobileFeeds {
	
	@XmlElement
	private List<UserFeeds> userFeeds = new ArrayList<UserFeeds>();

	public MobileFeeds() {

	}

	public List<UserFeeds> getUserFeeds() {
		return userFeeds;
	}

	public void setUserFeeds(List<UserFeeds> userFeeds) {
		this.userFeeds = userFeeds;
	}

}