package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.SoicalApp + "</p>
  * <p>Description: The SoicalApp entity </p>
  */
@Entity(name = "SoicalApp")
@Table(name = "T_SOICAL_APP")
public class SoicalApp extends JPABaseEntity {
	
	@Id()
	@Column (name = "SOICAL_APP_ID")
	@GeneratedValue(generator="SOICALAPPID_SEQ")
	@TableGenerator(name="SOICALAPPID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long soicalAppId;
	
	@ManyToOne
	@JoinColumn(name="user")
	private BasicUser user;
	
	@OneToMany(targetEntity=LinkedinPersonProfile.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="SOICAL_APP_ID")
	private List<LinkedinPersonProfile> linkedinPersonProfiles = new ArrayList<LinkedinPersonProfile>();
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "SESSION_I_D" )
	private String sessionID;
	
	
	@Column(name = "SECRET_I_D" )
	private String secretID;
	
	
	@Column(name = "AUTH_TOKEN" )
	private String authToken;
	
	
	@Column(name = "AUTH_TOKEN_SECRET" )
	private String authTokenSecret;
	
	
	@Column(name = "AUTH_TOKEN_EXPIRE_DATE" )
	private String authTokenExpireDate;
	
	
	@Column(name = "OAUTH_VERIFIER" )
	private String oauthVerifier;
	
	
	@Column(name = "REQUES_TOKEN" )
	private String requesToken;
	
	
	@Column(name = "REQUES_TOKEN_SECRET" )
	private String requesTokenSecret;
	
	
	@OneToMany(targetEntity=FacebookFriend.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinColumn(name="SOICAL_APP_ID")
	private List<FacebookFriend> FacebookFriends = new ArrayList<FacebookFriend>();
	

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
	public void setFacebookFriends (List<FacebookFriend> FacebookFriends) {
		this.FacebookFriends = FacebookFriends;
	} 
	public List<FacebookFriend> getFacebookFriends () {
		return FacebookFriends;
	}

	public Long getID() {
		return getSoicalAppId();
	}
}

