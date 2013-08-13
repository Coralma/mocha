package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.Shotcut + "</p>
  * <p>Description: The Shotcut entity </p>
  */
@Entity(name = "Shotcut")
@Table(name = "T_SHOTCUT")
public class Shotcut extends JPABaseEntity {
	
	@Id()
	@Column (name = "SHOTCUT_ID")
	@GeneratedValue(generator="SHOTCUTID_SEQ")
	@TableGenerator(name="SHOTCUTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long shotcutId;
	
	@Column(name = "TITLE" )
	private String title;
	
	
	@OneToMany(targetEntity=ShotcutItem.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="SHOTCUT_ID")
	private List<ShotcutItem> shotcutItems = new ArrayList<ShotcutItem>();
	

	public void setShotcutId (Long shotcutId) {
		this.shotcutId = shotcutId;
	} 
	public Long getShotcutId () {
		return shotcutId;
	}
	public void setTitle (String title) {
		this.title = title;
	} 
	public String getTitle () {
		return title;
	}
	public void setShotcutItems (List<ShotcutItem> shotcutItems) {
		this.shotcutItems = shotcutItems;
	} 
	public List<ShotcutItem> getShotcutItems () {
		return shotcutItems;
	}

	public Long getID() {
		return getShotcutId();
	}
}

