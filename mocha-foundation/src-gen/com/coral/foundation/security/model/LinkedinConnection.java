package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.LinkedinConnection + "</p>
  * <p>Description: The LinkedinConnection entity </p>
  */
@Entity(name = "LinkedinConnection")
@Table(name = "T_LINKEDIN_CONNECTION")
public class LinkedinConnection extends JPABaseEntity {
	
	@Id()
	@Column (name = "LINKEDIN_CONNECTION_ID")
	@GeneratedValue(generator="LINKEDINCONNECTIONID_SEQ")
	@TableGenerator(name="LINKEDINCONNECTIONID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long linkedinConnectionId;
	
	@Column(name = "FIRST_NAME" )
	private String firstName;
	
	
	@Column(name = "LAST_NAME" )
	private String lastName;
	
	
	@Column(name = "COMPANY_NAME" )
	private String companyName;
	
	
	@Column(name = "HEADLINE" )
	private String headline;
	
	
	@Column(name = "CURRENT_COMPNAY" )
	private String currentCompnay;
	
	
	@Column(name = "PICT_URL" )
	private String pictUrl;
	
	
	@Column(name = "LAST_UPDATE_DATE" )
	@Temporal(TemporalType.DATE)
	private Date lastUpdateDate;
	
	@Column(name = "NEED_FOLLOW" )
	private Boolean needFollow;
	
	
	@Column(name = "LOCATION" )
	private String location;
	
	
	@Column(name = "LOCATION_COUNTRY" )
	private String locationCountry;
	
	
	@Column(name = "SUMMARY" )
	private String summary;
	
	
	@Column(name = "CURRENT_STATUS" )
	private String currentStatus;
	
	
	@Column(name = "IM_ACCOUNT" )
	private String imAccount;
	
	
	@Column(name = "TWITTER_ACCOUNT" )
	private String twitterAccount;
	
	
	@Column(name = "EMAIL_ADDRESS" )
	private String emailAddress;
	
	
	@Column(name = "INDUSTRY" )
	private String industry;
	
	
	@Column(name = "EXPERIENCE" )
	private String experience;
	
	
	@Column(name = "EDUCTION" )
	private String eduction;
	
	
	@Column(name = "PUBLIC_PROFILE_URL" )
	private String publicProfileUrl;
	
	
	@ManyToOne
	@JoinColumn(name="linkedinPersonProfile")
	private LinkedinPersonProfile linkedinPersonProfile;
	
	@OneToMany(targetEntity=LinkedinGroup.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="LINKEDIN_CONNECTION_ID")
	private List<LinkedinGroup> linkedinGroups = new ArrayList<LinkedinGroup>();
	
	@OneToMany(targetEntity=LinkedinConnectionNetworkUpdate.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="LINKEDIN_CONNECTION_ID")
	private List<LinkedinConnectionNetworkUpdate> linkedinConnectionNetworkUpdate = new ArrayList<LinkedinConnectionNetworkUpdate>();
	

	public void setLinkedinConnectionId (Long linkedinConnectionId) {
		this.linkedinConnectionId = linkedinConnectionId;
	} 
	public Long getLinkedinConnectionId () {
		return linkedinConnectionId;
	}
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	} 
	public String getFirstName () {
		return firstName;
	}
	public void setLastName (String lastName) {
		this.lastName = lastName;
	} 
	public String getLastName () {
		return lastName;
	}
	public void setCompanyName (String companyName) {
		this.companyName = companyName;
	} 
	public String getCompanyName () {
		return companyName;
	}
	public void setHeadline (String headline) {
		this.headline = headline;
	} 
	public String getHeadline () {
		return headline;
	}
	public void setCurrentCompnay (String currentCompnay) {
		this.currentCompnay = currentCompnay;
	} 
	public String getCurrentCompnay () {
		return currentCompnay;
	}
	public void setPictUrl (String pictUrl) {
		this.pictUrl = pictUrl;
	} 
	public String getPictUrl () {
		return pictUrl;
	}
	public void setLastUpdateDate (Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	} 
	public Date getLastUpdateDate () {
		return lastUpdateDate;
	}
	public void setNeedFollow (Boolean needFollow) {
		this.needFollow = needFollow;
	} 
	public Boolean getNeedFollow () {
		return needFollow;
	}
	public void setLocation (String location) {
		this.location = location;
	} 
	public String getLocation () {
		return location;
	}
	public void setLocationCountry (String locationCountry) {
		this.locationCountry = locationCountry;
	} 
	public String getLocationCountry () {
		return locationCountry;
	}
	public void setSummary (String summary) {
		this.summary = summary;
	} 
	public String getSummary () {
		return summary;
	}
	public void setCurrentStatus (String currentStatus) {
		this.currentStatus = currentStatus;
	} 
	public String getCurrentStatus () {
		return currentStatus;
	}
	public void setImAccount (String imAccount) {
		this.imAccount = imAccount;
	} 
	public String getImAccount () {
		return imAccount;
	}
	public void setTwitterAccount (String twitterAccount) {
		this.twitterAccount = twitterAccount;
	} 
	public String getTwitterAccount () {
		return twitterAccount;
	}
	public void setEmailAddress (String emailAddress) {
		this.emailAddress = emailAddress;
	} 
	public String getEmailAddress () {
		return emailAddress;
	}
	public void setIndustry (String industry) {
		this.industry = industry;
	} 
	public String getIndustry () {
		return industry;
	}
	public void setExperience (String experience) {
		this.experience = experience;
	} 
	public String getExperience () {
		return experience;
	}
	public void setEduction (String eduction) {
		this.eduction = eduction;
	} 
	public String getEduction () {
		return eduction;
	}
	public void setPublicProfileUrl (String publicProfileUrl) {
		this.publicProfileUrl = publicProfileUrl;
	} 
	public String getPublicProfileUrl () {
		return publicProfileUrl;
	}
	public void setLinkedinPersonProfile (LinkedinPersonProfile linkedinPersonProfile) {
		this.linkedinPersonProfile = linkedinPersonProfile;
	} 
	public LinkedinPersonProfile getLinkedinPersonProfile () {
		return linkedinPersonProfile;
	}
	public void setLinkedinGroups (List<LinkedinGroup> linkedinGroups) {
		this.linkedinGroups = linkedinGroups;
	} 
	public List<LinkedinGroup> getLinkedinGroups () {
		return linkedinGroups;
	}
	public void setLinkedinConnectionNetworkUpdate (List<LinkedinConnectionNetworkUpdate> linkedinConnectionNetworkUpdate) {
		this.linkedinConnectionNetworkUpdate = linkedinConnectionNetworkUpdate;
	} 
	public List<LinkedinConnectionNetworkUpdate> getLinkedinConnectionNetworkUpdate () {
		return linkedinConnectionNetworkUpdate;
	}

	public Long getID() {
		return getLinkedinConnectionId();
	}
}

