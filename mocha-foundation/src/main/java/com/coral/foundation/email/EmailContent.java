/**
 * 
 */
package com.coral.foundation.email;

import java.util.List;

import com.coral.foundation.security.model.BasicUser;
import com.google.common.collect.Lists;

/**
 * @author Coral
 *
 */
public class EmailContent {

	public String subject;
	public String content;
	public List<BasicUser> reciever = Lists.newArrayList();

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the sender
	 */
	public BasicUser getReciever() {
		return reciever.get(0);
	}
	/**
	 * @param reciever the sender to set
	 */
	public void setReciever(BasicUser reciever) {
		this.reciever.add(reciever);
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the senders
	 */
	public List<BasicUser> getRecievers() {
		return reciever;
	}
	/**
	 * @param senders the senders to set
	 */
	public void setRecievers(List<BasicUser> senders) {
		this.reciever = senders;
	}
	
	/**
	 * @param reciever the sender to set
	 */
	public void addReciever(BasicUser reciever) {
		this.reciever.add(reciever);
	}
}
