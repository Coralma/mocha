package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.LinkedinPersonProfile + "</p>
  * <p>Description: The LinkedinPersonProfile entity </p>
  */
@Entity(name = "LinkedinPersonProfile")
@Table(name = "T_LINKEDIN_PERSON_PROFILE")
public class LinkedinPersonProfile extends JPABaseEntity {
	
	@Id()
	@Column (name = "LINKEDIN_PERSON_PROFILE_ID")
	@GeneratedValue(generator="LINKEDINPERSONPROFILEID_SEQ")
	@TableGenerator(name="LINKEDINPERSONPROFILEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long linkedinPersonProfileId;
	
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
	
	
	@ManyToOne
	@JoinColumn(name="soicalApp")
	private SoicalApp soicalApp;
	
	@OneToMany(targetEntity=LinkedinGroup.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="LINKEDIN_PERSON_PROFILE_ID")
	private List<LinkedinGroup> linkedinGroups = new ArrayList<LinkedinGroup>();
	
	@OneToMany(targetEntity=LinkedinConnection.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="LINKEDIN_PERSON_PROFILE_ID")
	private List<LinkedinConnection> linkedinConnections = new ArrayList<LinkedinConnection>();
	

	public void setLinkedinPersonProfileId (Long linkedinPersonProfileId) {
		this.linkedinPersonProfileId = linkedinPersonProfileId;
	} 
	public Long getLinkedinPersonProfileId () {
		return linkedinPersonProfileId;
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
	public void setSoicalApp (SoicalApp soicalApp) {
		this.soicalApp = soicalApp;
	} 
	public SoicalApp getSoicalApp () {
		return soicalApp;
	}
	public void setLinkedinGroups (List<LinkedinGroup> linkedinGroups) {
		this.linkedinGroups = linkedinGroups;
	} 
	public List<LinkedinGroup> getLinkedinGroups () {
		return linkedinGroups;
	}
	public void setLinkedinConnections (List<LinkedinConnection> linkedinConnections) {
		this.linkedinConnections = linkedinConnections;
	} 
	public List<LinkedinConnection> getLinkedinConnections () {
		return linkedinConnections;
	}

	public Long getID() {
		return getLinkedinPersonProfileId();
	}
}

