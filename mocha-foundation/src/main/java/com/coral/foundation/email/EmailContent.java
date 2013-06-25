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
	public List<BasicUser> senders = Lists.newArrayList();

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
	public BasicUser getSender() {
		return senders.get(0);
	}
	/**
	 * @param sender the sender to set
	 */
	public void setSender(BasicUser sender) {
		this.senders.add(sender);
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
	public List<BasicUser> getSenders() {
		return senders;
	}
	/**
	 * @param senders the senders to set
	 */
	public void setSenders(List<BasicUser> senders) {
		this.senders = senders;
	}
	
	/**
	 * @param sender the sender to set
	 */
	public void addSender(BasicUser sender) {
		this.senders.add(sender);
	}
}
