package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.FbFriendProject + "</p>
  * <p>Description: The FbFriendProject entity </p>
  */
@Entity(name = "FbFriendProject")
@Table(name = "T_FB_FRIEND_PROJECT")
public class FbFriendProject extends JPABaseEntity {
	
	@Id()
	@Column (name = "FB_FRIEND_PROJECT_ID")
	@GeneratedValue(generator="FBFRIENDPROJECTID_SEQ")
	@TableGenerator(name="FBFRIENDPROJECTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long fbFriendProjectId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@Column(name = "START_DATE" )
	private String start_date;
	
	
	@Column(name = "END_DATE" )
	private String end_date;
	
	

	public void setFbFriendProjectId (Long fbFriendProjectId) {
		this.fbFriendProjectId = fbFriendProjectId;
	} 
	public Long getFbFriendProjectId () {
		return fbFriendProjectId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setDescription (String description) {
		this.description = description;
	} 
	public String getDescription () {
		return description;
	}
	public void setStart_date (String start_date) {
		this.start_date = start_date;
	} 
	public String getStart_date () {
		return start_date;
	}
	public void setEnd_date (String end_date) {
		this.end_date = end_date;
	} 
	public String getEnd_date () {
		return end_date;
	}

	public Long getID() {
		return getFbFriendProjectId();
	}
}

