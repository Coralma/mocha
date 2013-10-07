package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.Contact + "</p>
  * <p>Description: The Contact entity </p>
  */
@Entity(name = "Contact")
@Table(name = "T_CONTACT")
public class Contact extends JPABaseEntity {
	
	@Id()
	@Column (name = "CONTACT_ID")
	@GeneratedValue(generator="CONTACTID_SEQ")
	@TableGenerator(name="CONTACTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long contactId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "USER_NAME" )
	private String userName;
	
	
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
	@JoinColumn(name="CONTACT_ID")
	private List<Email> emailAddress = new ArrayList<Email>();
	

	public void setContactId (Long contactId) {
		this.contactId = contactId;
	} 
	public Long getContactId () {
		return contactId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setUserName (String userName) {
		this.userName = userName;
	} 
	public String getUserName () {
		return userName;
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
	public void setEmailAddress (List<Email> emailAddress) {
		this.emailAddress = emailAddress;
	} 
	public List<Email> getEmailAddress () {
		return emailAddress;
	}

	public Long getID() {
		return getContactId();
	}
}

