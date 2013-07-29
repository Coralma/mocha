package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.LinkedinGroup + "</p>
  * <p>Description: The LinkedinGroup entity </p>
  */
@Entity(name = "LinkedinGroup")
@Table(name = "T_LINKEDIN_GROUP")
public class LinkedinGroup extends BaseEntity {
	
	@Id()
	@Column (name = "LINKEDIN_GROUP_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long linkedinGroupId;
	
	@Basic(optional = true)
	@Column(name = "NAME" )
	private String name;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = LinkedinConnection.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "linkedinConnection") })
	@Fetch(FetchMode.JOIN)
	private LinkedinConnection linkedinConnection;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = LinkedinPersonProfile.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "LinkedinPersonProfile") })
	@Fetch(FetchMode.JOIN)
	private LinkedinPersonProfile LinkedinPersonProfile;
	

	public void setLinkedinGroupId (Long linkedinGroupId) {
		this.linkedinGroupId = linkedinGroupId;
	} 
	public Long getLinkedinGroupId () {
		return linkedinGroupId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setLinkedinConnection (LinkedinConnection linkedinConnection) {
		this.linkedinConnection = linkedinConnection;
	} 
	public LinkedinConnection getLinkedinConnection () {
		return linkedinConnection;
	}
	public void setLinkedinPersonProfile (LinkedinPersonProfile LinkedinPersonProfile) {
		this.LinkedinPersonProfile = LinkedinPersonProfile;
	} 
	public LinkedinPersonProfile getLinkedinPersonProfile () {
		return LinkedinPersonProfile;
	}

	public Long getID() {
		return getLinkedinGroupId();
	}
	
	public void setID(Long id) {
		setLinkedinGroupId(id);
	}
}

