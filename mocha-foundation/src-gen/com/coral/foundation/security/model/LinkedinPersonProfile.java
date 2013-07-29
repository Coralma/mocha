package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.LinkedinPersonProfile + "</p>
  * <p>Description: The LinkedinPersonProfile entity </p>
  */
@Entity(name = "LinkedinPersonProfile")
@Table(name = "T_LINKEDIN_PERSON_PROFILE")
public class LinkedinPersonProfile extends BaseEntity {
	
	@Id()
	@Column (name = "LINKEDIN_PERSON_PROFILE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long linkedinPersonProfileId;
	
	@Basic(optional = true)
	@Column(name = "FIRST_NAME" )
	private String firstName;
	
	
	@Basic(optional = true)
	@Column(name = "LAST_NAME" )
	private String lastName;
	
	
	@Basic(optional = true)
	@Column(name = "COMPANY_NAME" )
	private String companyName;
	
	
	@Basic(optional = true)
	@Column(name = "HEADLINE" )
	private String headline;
	
	
	@Basic(optional = true)
	@Column(name = "CURRENT_COMPNAY" )
	private String currentCompnay;
	
	
	@Basic(optional = true)
	@Column(name = "PICT_URL" )
	private String pictUrl;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = SoicalApp.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "soicalApp") })
	@Fetch(FetchMode.JOIN)
	private SoicalApp soicalApp;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = LinkedinGroup.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="LINKEDIN_PERSON_PROFILE_ID")
	private List<LinkedinGroup> linkedinGroups = new ArrayList<LinkedinGroup>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = LinkedinConnection.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setLinkedinPersonProfileId(id);
	}
}

