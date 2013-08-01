package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate + "</p>
  * <p>Description: The LinkedinConnectionNetworkUpdate entity </p>
  */
@Entity(name = "LinkedinConnectionNetworkUpdate")
@Table(name = "T_LINKEDIN_CONNECTION_NETWORK_UPDATE")
public class LinkedinConnectionNetworkUpdate extends BaseEntity {
	
	@Id()
	@Column (name = "LINKEDIN_CONNECTION_NETWORK_UPDATE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long linkedinConnectionNetworkUpdateId;
	
	@Basic(optional = true)
	@Column(name = "LINKEDIN_UPDATE_KEY" )
	private String linkedinUpdateKey;
	
	
	@Basic(optional = true)
	@Column(name = "UPDATED_KEY" )
	private String updatedKey;
	
	
	@Basic(optional = true)
	@Column(name = "UPDATE_TYPE" )
	private String updateType;
	
	
	@Basic(optional = true)
	@Column(name = "FIRST_NAME" )
	private String firstName;
	
	
	@Basic(optional = true)
	@Column(name = "LAST_NAME" )
	private String lastName;
	
	
	@Basic(optional = true)
	@Column(name = "UPDATE_MESSAGE" )
	private String updateMessage;
	
	
	@Basic(optional = true)
	@Column(name = "TIME_STAMP" )
	private String timeStamp;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = LinkedinConnection.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "linkedinConnection") })
	@Fetch(FetchMode.JOIN)
	private LinkedinConnection linkedinConnection;
	

	public void setLinkedinConnectionNetworkUpdateId (Long linkedinConnectionNetworkUpdateId) {
		this.linkedinConnectionNetworkUpdateId = linkedinConnectionNetworkUpdateId;
	} 
	public Long getLinkedinConnectionNetworkUpdateId () {
		return linkedinConnectionNetworkUpdateId;
	}
	public void setLinkedinUpdateKey (String linkedinUpdateKey) {
		this.linkedinUpdateKey = linkedinUpdateKey;
	} 
	public String getLinkedinUpdateKey () {
		return linkedinUpdateKey;
	}
	public void setUpdatedKey (String updatedKey) {
		this.updatedKey = updatedKey;
	} 
	public String getUpdatedKey () {
		return updatedKey;
	}
	public void setUpdateType (String updateType) {
		this.updateType = updateType;
	} 
	public String getUpdateType () {
		return updateType;
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
	public void setUpdateMessage (String updateMessage) {
		this.updateMessage = updateMessage;
	} 
	public String getUpdateMessage () {
		return updateMessage;
	}
	public void setTimeStamp (String timeStamp) {
		this.timeStamp = timeStamp;
	} 
	public String getTimeStamp () {
		return timeStamp;
	}
	public void setLinkedinConnection (LinkedinConnection linkedinConnection) {
		this.linkedinConnection = linkedinConnection;
	} 
	public LinkedinConnection getLinkedinConnection () {
		return linkedinConnection;
	}

	public Long getID() {
		return getLinkedinConnectionNetworkUpdateId();
	}
	
	public void setID(Long id) {
		setLinkedinConnectionNetworkUpdateId(id);
	}
}

