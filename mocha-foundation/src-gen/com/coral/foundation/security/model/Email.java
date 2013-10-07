package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.Email + "</p>
  * <p>Description: The Email entity </p>
  */
@Entity(name = "Email")
@Table(name = "T_EMAIL")
public class Email extends JPABaseEntity {
	
	@Id()
	@Column (name = "EMAIL_ID")
	@GeneratedValue(generator="EMAILID_SEQ")
	@TableGenerator(name="EMAILID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long emailId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "EMAIL_ADDRESS" )
	private String emailAddress;
	
	
	@Column(name = "EMAIL_TITLE" )
	private String emailTitle;
	
	
	@Column(name = "EMAIL_CONTEXT" )
	private String emailContext;
	
	
	@Column(name = "CATEGORY" )
	private String category;
	
	
	@Column(name = "EFFECTIVE_DATE" )
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;
	
	@Column(name = "EXPIRY_DATE" )
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	
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
	
	
	@OneToOne(targetEntity = com.coral.foundation.security.model.BasicUser.class)
	private com.coral.foundation.security.model.BasicUser referUser;
	

	public void setEmailId (Long emailId) {
		this.emailId = emailId;
	} 
	public Long getEmailId () {
		return emailId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setEmailAddress (String emailAddress) {
		this.emailAddress = emailAddress;
	} 
	public String getEmailAddress () {
		return emailAddress;
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
	public void setEffectiveDate (Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	} 
	public Date getEffectiveDate () {
		return effectiveDate;
	}
	public void setExpiryDate (Date expiryDate) {
		this.expiryDate = expiryDate;
	} 
	public Date getExpiryDate () {
		return expiryDate;
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
	public void setReferUser (com.coral.foundation.security.model.BasicUser referUser) {
		this.referUser = referUser;
	} 
	public com.coral.foundation.security.model.BasicUser getReferUser () {
		return referUser;
	}

	public Long getID() {
		return getEmailId();
	}
}

