package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.SoicalApp + "</p>
  * <p>Description: The SoicalApp entity </p>
  */
@Entity(name = "SoicalApp")
@Table(name = "T_SOICAL_APP")
public class SoicalApp extends BaseEntity {
	
	@Id()
	@Column (name = "SOICAL_APP_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long soicalAppId;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = BasicUser.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "user") })
	@Fetch(FetchMode.JOIN)
	private BasicUser user;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = LinkedinPersonProfile.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="SOICAL_APP_ID")
	private List<LinkedinPersonProfile> linkedinPersonProfiles = new ArrayList<LinkedinPersonProfile>();
	
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
	@Column(name = "AUTH_TOKEN_SECRET" )
	private String authTokenSecret;
	
	
	@Basic(optional = true)
	@Column(name = "AUTH_TOKEN_EXPIRE_DATE" )
	private String authTokenExpireDate;
	
	
	@Basic(optional = true)
	@Column(name = "OAUTH_VERIFIER" )
	private String oauthVerifier;
	
	
	@Basic(optional = true)
	@Column(name = "REQUES_TOKEN" )
	private String requesToken;
	
	
	@Basic(optional = true)
	@Column(name = "REQUES_TOKEN_SECRET" )
	private String requesTokenSecret;
	
	

	public void setSoicalAppId (Long soicalAppId) {
		this.soicalAppId = soicalAppId;
	} 
	public Long getSoicalAppId () {
		return soicalAppId;
	}
	public void setUser (BasicUser user) {
		this.user = user;
	} 
	public BasicUser getUser () {
		return user;
	}
	public void setLinkedinPersonProfiles (List<LinkedinPersonProfile> linkedinPersonProfiles) {
		this.linkedinPersonProfiles = linkedinPersonProfiles;
	} 
	public List<LinkedinPersonProfile> getLinkedinPersonProfiles () {
		return linkedinPersonProfiles;
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
	public void setAuthTokenSecret (String authTokenSecret) {
		this.authTokenSecret = authTokenSecret;
	} 
	public String getAuthTokenSecret () {
		return authTokenSecret;
	}
	public void setAuthTokenExpireDate (String authTokenExpireDate) {
		this.authTokenExpireDate = authTokenExpireDate;
	} 
	public String getAuthTokenExpireDate () {
		return authTokenExpireDate;
	}
	public void setOauthVerifier (String oauthVerifier) {
		this.oauthVerifier = oauthVerifier;
	} 
	public String getOauthVerifier () {
		return oauthVerifier;
	}
	public void setRequesToken (String requesToken) {
		this.requesToken = requesToken;
	} 
	public String getRequesToken () {
		return requesToken;
	}
	public void setRequesTokenSecret (String requesTokenSecret) {
		this.requesTokenSecret = requesTokenSecret;
	} 
	public String getRequesTokenSecret () {
		return requesTokenSecret;
	}

	public Long getID() {
		return getSoicalAppId();
	}
	
	public void setID(Long id) {
		setSoicalAppId(id);
	}
}

