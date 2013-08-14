package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.LinkedinConnectionNetworkUpdate + "</p>
  * <p>Description: The LinkedinConnectionNetworkUpdate entity </p>
  */
@Entity(name = "LinkedinConnectionNetworkUpdate")
@Table(name = "T_LINKEDIN_CONNECTION_NETWORK_UPDATE")
public class LinkedinConnectionNetworkUpdate extends JPABaseEntity {
	
	@Id()
	@Column (name = "LINKEDIN_CONNECTION_NETWORK_UPDATE_ID")
	@GeneratedValue(generator="LINKEDINCONNECTIONNETWORKUPDATEID_SEQ")
	@TableGenerator(name="LINKEDINCONNECTIONNETWORKUPDATEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long linkedinConnectionNetworkUpdateId;
	
	@Column(name = "LINKEDIN_UPDATE_KEY" )
	private String linkedinUpdateKey;
	
	
	@Column(name = "UPDATED_KEY" )
	private String updatedKey;
	
	
	@Column(name = "UPDATE_TYPE" )
	private String updateType;
	
	
	@Column(name = "FIRST_NAME" )
	private String firstName;
	
	
	@Column(name = "LAST_NAME" )
	private String lastName;
	
	
	@Column(name = "UPDATE_MESSAGE" )
	private String updateMessage;
	
	
	@Column(name = "TIME_STAMP" )
	private String timeStamp;
	
	
	@ManyToOne
	@JoinColumn(name="linkedinConnection")
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
}

