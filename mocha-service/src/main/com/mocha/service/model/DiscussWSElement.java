package com.mocha.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.model.Comment;
import com.mocha.cooperate.model.NotifyLine;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DiscussWSElement {

	public DiscussWSElement() {

	}

	@XmlElement(name = "attribute")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
