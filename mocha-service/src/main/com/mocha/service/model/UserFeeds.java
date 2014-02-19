package com.mocha.service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.ToDo;
import com.mocha.cooperate.restfulservice.ToDoAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserFeeds {

	
	@XmlJavaTypeAdapter(ToDoAdapter.class)
	private ToDo todo;

	@XmlElement
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
