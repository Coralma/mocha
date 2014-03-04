package com.mocha.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.restfulservice.UserFeedAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserFeeds {

	@XmlElement
	@XmlJavaTypeAdapter(UserFeedAdapter.class)
	private ToDo todo;

	@XmlElement
	@XmlJavaTypeAdapter(UserFeedAdapter.class)
	private Discuss discuss;

	public Discuss getDiscuss() {
		return discuss;
	}

	public void setDiscuss(Discuss discuss) {
		this.discuss = discuss;
	}

	public ToDo getTodo() {
		return todo;
	}

	public void setTodo(ToDo todo) {
		this.todo = todo;
	}
}
