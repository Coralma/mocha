package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.Shotcut + "</p>
  * <p>Description: The Shotcut entity </p>
  */
@Entity(name = "Shotcut")
@Table(name = "T_SHOTCUT")
public class Shotcut extends BaseEntity {
	
	@Id()
	@Column (name = "SHOTCUT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long shotcutId;
	
	@Basic(optional = true)
	@Column(name = "TITLE" )
	private String title;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = ShotcutItem.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setShotcutId(id);
	}
}

