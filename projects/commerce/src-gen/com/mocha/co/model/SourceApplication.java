package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.co.model.SourceApplication + "</p>
  * <p>Description: The SourceApplication entity </p>
  */
@Entity(name = "SourceApplication")
@Table(name = "T_SOURCE_APPLICATION")
public class SourceApplication extends JPABaseEntity {
	
	@Id()
	@Column (name = "SOURCE_APPLICATION_ID")
	@GeneratedValue(generator="SOURCEAPPLICATIONID_SEQ")
	@TableGenerator(name="SOURCEAPPLICATIONID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long sourceApplicationId;
	
	@ManyToOne
	@JoinColumn(name="customer")
	private CommerceCustomer customer;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "SESSION_I_D" )
	private String sessionID;
	
	
	@Column(name = "SECRET_I_D" )
	private String secretID;
	
	
	@Column(name = "AUTH_TOKEN" )
	private String authToken;
	
	
	@Column(name = "AUTH_TOKEN_EXPIRE_DATE" )
	private String authTokenExpireDate;
	
	

	public void setSourceApplicationId (Long sourceApplicationId) {
		this.sourceApplicationId = sourceApplicationId;
	} 
	public Long getSourceApplicationId () {
		return sourceApplicationId;
	}
	public void setCustomer (CommerceCustomer customer) {
		this.customer = customer;
	} 
	public CommerceCustomer getCustomer () {
		return customer;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setSessionID (String sessionID) {
		this.sessionID = sessionID;
	} 
	public String getSessionID () {
		return sessionID;
	}
	public void setSecretID (String secretID) {
		this.secretID = secretID;
	} 
	public String getSecretID () {
		return secretID;
	}
	public void setAuthToken (String authToken) {
		this.authToken = authToken;
	} 
	public String getAuthToken () {
		return authToken;
	}
	public void setAuthTokenExpireDate (String authTokenExpireDate) {
		this.authTokenExpireDate = authTokenExpireDate;
	} 
	public String getAuthTokenExpireDate () {
		return authTokenExpireDate;
	}

	public Long getID() {
		return getSourceApplicationId();
	}
}

