package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.ShotcutItem + "</p>
  * <p>Description: The ShotcutItem entity </p>
  */
@Entity(name = "ShotcutItem")
@Table(name = "T_SHOTCUT_ITEM")
public class ShotcutItem extends BaseEntity {
	
	@Id()
	@Column (name = "SHOTCUT_ITEM_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long shotcutItemId;
	
	@Basic(optional = true)
	@Column(name = "LABEL" )
	private String label;
	
	
	@Basic(optional = true)
	@Column(name = "NUMBER" )
	private Long number = new Long(0);
	
	
	@Basic(optional = true)
	@Column(name = "ACTION" )
	private String action;
	
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@Basic(optional = true)
	@Column(name = "ICON" )
	private String icon;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = Shotcut.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "shotcut") })
	@Fetch(FetchMode.JOIN)
	private Shotcut shotcut;
	

	public void setShotcutItemId (Long shotcutItemId) {
		this.shotcutItemId = shotcutItemId;
	} 
	public Long getShotcutItemId () {
		return shotcutItemId;
	}
	public void setLabel (String label) {
		this.label = label;
	} 
	public String getLabel () {
		return label;
	}
	public void setNumber (Long number) {
		this.number = number;
	} 
	public Long getNumber () {
		return number;
	}
	public void setAction (String action) {
		this.action = action;
	} 
	public String getAction () {
		return action;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setIcon (String icon) {
		this.icon = icon;
	} 
	public String getIcon () {
		return icon;
	}
	public void setShotcut (Shotcut shotcut) {
		this.shotcut = shotcut;
	} 
	public Shotcut getShotcut () {
		return shotcut;
	}

	public Long getID() {
		return getShotcutItemId();
	}
	
	public void setID(Long id) {
		setShotcutItemId(id);
	}
}

