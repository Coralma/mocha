package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.FbFriendLanguage + "</p>
  * <p>Description: The FbFriendLanguage entity </p>
  */
@Entity(name = "FbFriendLanguage")
@Table(name = "T_FB_FRIEND_LANGUAGE")
public class FbFriendLanguage extends JPABaseEntity {
	
	@Id()
	@Column (name = "FB_FRIEND_LANGUAGE_ID")
	@GeneratedValue(generator="FBFRIENDLANGUAGEID_SEQ")
	@TableGenerator(name="FBFRIENDLANGUAGEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long fbFriendLanguageId;
	
	@Column(name = "LANGUAGE_ID" )
	private String languageId;
	
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "DESCRIPTION" )
	private String description;
	
	

	public void setFbFriendLanguageId (Long fbFriendLanguageId) {
		this.fbFriendLanguageId = fbFriendLanguageId;
	} 
	public Long getFbFriendLanguageId () {
		return fbFriendLanguageId;
	}
	public void setLanguageId (String languageId) {
		this.languageId = languageId;
	} 
	public String getLanguageId () {
		return languageId;
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
		return getFbFriendLanguageId();
	}
}

