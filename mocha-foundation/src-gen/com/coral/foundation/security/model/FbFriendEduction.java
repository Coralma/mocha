package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.FbFriendEduction + "</p>
  * <p>Description: The FbFriendEduction entity </p>
  */
@Entity(name = "FbFriendEduction")
@Table(name = "T_FB_FRIEND_EDUCTION")
public class FbFriendEduction extends JPABaseEntity {
	
	@Id()
	@Column (name = "FB_FRIEND_EDUCTION_ID")
	@GeneratedValue(generator="FBFRIENDEDUCTIONID_SEQ")
	@TableGenerator(name="FBFRIENDEDUCTIONID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long fbFriendEductionId;
	
	@Column(name = "SHCOOL_ID" )
	private String shcool_id;
	
	
	@Column(name = "SHCOOL_NAME" )
	private String shcool_name;
	
	
	@Column(name = "DEGREE_ID" )
	private String degree_id;
	
	
	@Column(name = "DEGREE_NAME" )
	private String degree_name;
	
	
	@Column(name = "YEAR_ID" )
	private String year_id;
	
	
	@Column(name = "YEAR_NAME" )
	private String year_name;
	
	
	@Column(name = "CONCENTRATION_ID" )
	private String concentration_id;
	
	
	@Column(name = "CONCENTRATION_NAME" )
	private String concentration_name;
	
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@Column(name = "WITH_ID" )
	private String with_id;
	
	
	@Column(name = "WITH_NAME" )
	private String with_name;
	
	
	@Column(name = "CLASSES_ID" )
	private String classes_id;
	
	
	@Column(name = "CLASSES_NAME" )
	private String classes_name;
	
	
	@Column(name = "CLASSES_DESCRIPTION" )
	private String classes_description;
	
	
	@Column(name = "FROM_ID" )
	private String from_id;
	
	
	@Column(name = "FROM_NAME" )
	private String from_name;
	
	

	public void setFbFriendEductionId (Long fbFriendEductionId) {
		this.fbFriendEductionId = fbFriendEductionId;
	} 
	public Long getFbFriendEductionId () {
		return fbFriendEductionId;
	}
	public void setShcool_id (String shcool_id) {
		this.shcool_id = shcool_id;
	} 
	public String getShcool_id () {
		return shcool_id;
	}
	public void setShcool_name (String shcool_name) {
		this.shcool_name = shcool_name;
	} 
	public String getShcool_name () {
		return shcool_name;
	}
	public void setDegree_id (String degree_id) {
		this.degree_id = degree_id;
	} 
	public String getDegree_id () {
		return degree_id;
	}
	public void setDegree_name (String degree_name) {
		this.degree_name = degree_name;
	} 
	public String getDegree_name () {
		return degree_name;
	}
	public void setYear_id (String year_id) {
		this.year_id = year_id;
	} 
	public String getYear_id () {
		return year_id;
	}
	public void setYear_name (String year_name) {
		this.year_name = year_name;
	} 
	public String getYear_name () {
		return year_name;
	}
	public void setConcentration_id (String concentration_id) {
		this.concentration_id = concentration_id;
	} 
	public String getConcentration_id () {
		return concentration_id;
	}
	public void setConcentration_name (String concentration_name) {
		this.concentration_name = concentration_name;
	} 
	public String getConcentration_name () {
		return concentration_name;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setWith_id (String with_id) {
		this.with_id = with_id;
	} 
	public String getWith_id () {
		return with_id;
	}
	public void setWith_name (String with_name) {
		this.with_name = with_name;
	} 
	public String getWith_name () {
		return with_name;
	}
	public void setClasses_id (String classes_id) {
		this.classes_id = classes_id;
	} 
	public String getClasses_id () {
		return classes_id;
	}
	public void setClasses_name (String classes_name) {
		this.classes_name = classes_name;
	} 
	public String getClasses_name () {
		return classes_name;
	}
	public void setClasses_description (String classes_description) {
		this.classes_description = classes_description;
	} 
	public String getClasses_description () {
		return classes_description;
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

	public Long getID() {
		return getFbFriendEductionId();
	}
}

