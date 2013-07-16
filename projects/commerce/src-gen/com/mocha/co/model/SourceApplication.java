package com.mocha.co.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.co.model.SourceApplication + "</p>
  * <p>Description: The SourceApplication entity </p>
  */
@Entity(name = "SourceApplication")
@Table(name = "T_SOURCE_APPLICATION")
public class SourceApplication extends BaseEntity {
	
	@Id()
	@Column (name = "SOURCE_APPLICATION_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long sourceApplicationId;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = CommerceCustomer.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "customer") })
	@Fetch(FetchMode.JOIN)
	private CommerceCustomer customer;
	
	@Basic(optional = true)
	@Column(name = "NAME" )
	private String name;
	
	
	@Basic(optional = true)
	@Column(name = "SESSION_I_D" )
	private String sessionID;
	
	
	@Basic(optional = true)
	@Column(name = "SECRET_I_D" )
	private String secretID;
	
	
	@Basic(optional = true)
	@Column(name = "AUTH_TOKEN" )
	private String authToken;
	
	
	@Basic(optional = true)
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
	
	public void setID(Long id) {
		setSourceApplicationId(id);
	}
}

