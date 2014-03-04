package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.FbFriendWork + "</p>
  * <p>Description: The FbFriendWork entity </p>
  */
@Entity(name = "FbFriendWork")
@Table(name = "T_FB_FRIEND_WORK")
public class FbFriendWork extends JPABaseEntity {
	
	@Id()
	@Column (name = "FB_FRIEND_WORK_ID")
	@GeneratedValue(generator="FBFRIENDWORKID_SEQ")
	@TableGenerator(name="FBFRIENDWORKID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long fbFriendWorkId;
	
	@Column(name = "EMPLOYER_ID" )
	private String employer_id;
	
	
	@Column(name = "EMPLOYER_NAME" )
	private String employer_name;
	
	
	@Column(name = "LOCATION_ID" )
	private String location_id;
	
	
	@Column(name = "LOCATION_NAME" )
	private String location_name;
	
	
	@Column(name = "POSTION_ID" )
	private String postion_id;
	
	
	@Column(name = "POSTION_NAME" )
	private String postion_name;
	
	
	@Column(name = "FROM_ID" )
	private String from_id;
	
	
	@Column(name = "FROM_NAME" )
	private String from_name;
	
	
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@Column(name = "START_DATE" )
	private String start_date;
	
	
	@Column(name = "END_DATE" )
	private String end_date;
	
	
	@OneToMany(targetEntity=FbFriendProject.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinColumn(name="FB_FRIEND_WORK_ID")
	private List<FbFriendProject> fbFriendProjects = new ArrayList<FbFriendProject>();
	

	public void setFbFriendWorkId (Long fbFriendWorkId) {
		this.fbFriendWorkId = fbFriendWorkId;
	} 
	public Long getFbFriendWorkId () {
		return fbFriendWorkId;
	}
	public void setEmployer_id (String employer_id) {
		this.employer_id = employer_id;
	} 
	public String getEmployer_id () {
		return employer_id;
	}
	public void setEmployer_name (String employer_name) {
		this.employer_name = employer_name;
	} 
	public String getEmployer_name () {
		return employer_name;
	}
	public void setLocation_id (String location_id) {
		this.location_id = location_id;
	} 
	public String getLocation_id () {
		return location_id;
	}
	public void setLocation_name (String location_name) {
		this.location_name = location_name;
	} 
	public String getLocation_name () {
		return location_name;
	}
	public void setPostion_id (String postion_id) {
		this.postion_id = postion_id;
	} 
	public String getPostion_id () {
		return postion_id;
	}
	public void setPostion_name (String postion_name) {
		this.postion_name = postion_name;
	} 
	public String getPostion_name () {
		return postion_name;
	}
	public void setFrom_id (String from_id) {
		this.from_id = from_id;
	} 
	public String getFrom_id () {
		return from_id;
	}
	public void setFrom_name (String from_name) {
		this.from_name = from_name;
	} 
	public String getFrom_name () {
		return from_name;
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
	public void setFbFriendProjects (List<FbFriendProject> fbFriendProjects) {
		this.fbFriendProjects = fbFriendProjects;
	} 
	public List<FbFriendProject> getFbFriendProjects () {
		return fbFriendProjects;
	}

	public Long getID() {
		return getFbFriendWorkId();
	}
}

