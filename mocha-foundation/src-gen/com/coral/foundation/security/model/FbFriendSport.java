package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.FbFriendSport + "</p>
  * <p>Description: The FbFriendSport entity </p>
  */
@Entity(name = "FbFriendSport")
@Table(name = "T_FB_FRIEND_SPORT")
public class FbFriendSport extends JPABaseEntity {
	
	@Id()
	@Column (name = "FB_FRIEND_SPORT_ID")
	@GeneratedValue(generator="FBFRIENDSPORTID_SEQ")
	@TableGenerator(name="FBFRIENDSPORTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long fbFriendSportId;
	
	@Column(name = "ID" )
	private String id;
	
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "DESCRIPTION" )
	private String description;
	
	

	public void setFbFriendSportId (Long fbFriendSportId) {
		this.fbFriendSportId = fbFriendSportId;
	} 
	public Long getFbFriendSportId () {
		return fbFriendSportId;
	}
	public void setId (String id) {
		this.id = id;
	} 
	public String getId () {
		return id;
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

	public Long getID() {
		return getFbFriendSportId();
	}
}

