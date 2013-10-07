package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.Campaingns + "</p>
  * <p>Description: The Campaingns entity </p>
  */
@Entity(name = "Campaingns")
@Table(name = "T_CAMPAINGNS")
public class Campaingns extends JPABaseEntity {
	
	@Id()
	@Column (name = "CAMPAINGNS_ID")
	@GeneratedValue(generator="CAMPAINGNSID_SEQ")
	@TableGenerator(name="CAMPAINGNSID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long campaingnsId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "EMAIL_TITLE" )
	private String emailTitle;
	
	
	@Column(name = "EMAIL_CONTEXT" )
	private String emailContext;
	
	
	@Column(name = "CATEGORY" )
	private String category;
	
	
	@Column(name = "PREMIUM" )
	private String premium;
	
	
	@Column(name = "COMMISSION" )
	private String commission;
	
	
	@Column(name = "MARK" )
	private String mark;
	
	
	@Column(name = "DISTRICT" )
	private String district;
	
	
	@Column(name = "POSTCODE" )
	private String postcode;
	
	
	@Column(name = "ADDRESS" )
	private String address;
	
	
	@OneToMany(targetEntity=Email.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="CAMPAINGNS_ID")
	private List<Email> Emails = new ArrayList<Email>();
	
	@OneToMany(targetEntity=Contact.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="CAMPAINGNS_ID")
	private List<Contact> contacts = new ArrayList<Contact>();
	

	public void setCampaingnsId (Long campaingnsId) {
		this.campaingnsId = campaingnsId;
	} 
	public Long getCampaingnsId () {
		return campaingnsId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setEmailTitle (String emailTitle) {
		this.emailTitle = emailTitle;
	} 
	public String getEmailTitle () {
		return emailTitle;
	}
	public void setEmailContext (String emailContext) {
		this.emailContext = emailContext;
	} 
	public String getEmailContext () {
		return emailContext;
	}
	public void setCategory (String category) {
		this.category = category;
	} 
	public String getCategory () {
		return category;
	}
	public void setPremium (String premium) {
		this.premium = premium;
	} 
	public String getPremium () {
		return premium;
	}
	public void setCommission (String commission) {
		this.commission = commission;
	} 
	public String getCommission () {
		return commission;
	}
	public void setMark (String mark) {
		this.mark = mark;
	} 
	public String getMark () {
		return mark;
	}
	public void setDistrict (String district) {
		this.district = district;
	} 
	public String getDistrict () {
		return district;
	}
	public void setPostcode (String postcode) {
		this.postcode = postcode;
	} 
	public String getPostcode () {
		return postcode;
	}
	public void setAddress (String address) {
		this.address = address;
	} 
	public String getAddress () {
		return address;
	}
	public void setEmails (List<Email> Emails) {
		this.Emails = Emails;
	} 
	public List<Email> getEmails () {
		return Emails;
	}
	public void setContacts (List<Contact> contacts) {
		this.contacts = contacts;
	} 
	public List<Contact> getContacts () {
		return contacts;
	}

	public Long getID() {
		return getCampaingnsId();
	}
}

