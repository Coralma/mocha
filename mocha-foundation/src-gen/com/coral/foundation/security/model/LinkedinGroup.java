package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.LinkedinGroup + "</p>
  * <p>Description: The LinkedinGroup entity </p>
  */
@Entity(name = "LinkedinGroup")
@Table(name = "T_LINKEDIN_GROUP")
public class LinkedinGroup extends JPABaseEntity {
	
	@Id()
	@Column (name = "LINKEDIN_GROUP_ID")
	@GeneratedValue(generator="LINKEDINGROUPID_SEQ")
	@TableGenerator(name="LINKEDINGROUPID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long linkedinGroupId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name="linkedinConnection")
	private LinkedinConnection linkedinConnection;
	
	@ManyToOne
	@JoinColumn(name="LinkedinPersonProfile")
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
}

