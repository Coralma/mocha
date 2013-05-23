package com.homepage.model;

import java.beans.Beans;
import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import com.homepage.payment.AccountFeeType;

@Entity
@Table(name = "t_contactmessage")
public class ContactMessage extends Beans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id()
	@Column(name = "contactMessageId")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Basic(optional = false)
	@Column(name = "contactEmailaddress")
	private String emailAddress;

	@Basic(optional = false)
	@Column(name = "messageDetail")
	private String messageDetail;

	@Basic(optional = true)
	@Column(name = "subject")
	private String subject;

	@Basic(optional = false)
	@Column(name = "messageCreateDate")
	private java.sql.Date messageCreateDate;

	public ContactMessage() {
		super();
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMessageDetail() {
		return messageDetail;
	}

	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public java.sql.Date getMessageCreateDate() {
		return messageCreateDate;
	}

	public void setMessageCreateDate(java.sql.Date messageCreateDate) {
		this.messageCreateDate = messageCreateDate;
	}

}
