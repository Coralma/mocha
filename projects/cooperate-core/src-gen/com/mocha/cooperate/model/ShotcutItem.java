package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.mocha.cooperate.model.ShotcutItem + "</p>
  * <p>Description: The ShotcutItem entity </p>
  */
@Entity(name = "ShotcutItem")
@Table(name = "T_SHOTCUT_ITEM")
public class ShotcutItem extends JPABaseEntity {
	
	@Id()
	@Column (name = "SHOTCUT_ITEM_ID")
	@GeneratedValue(generator="SHOTCUTITEMID_SEQ")
	@TableGenerator(name="SHOTCUTITEMID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long shotcutItemId;
	
	@Column(name = "LABEL" )
	private String label;
	
	
	@Column(name = "NUMBER" )
	private Long number = new Long(0);
	
	
	@Column(name = "ACTION" )
	private String action;
	
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@Column(name = "ICON" )
	private String icon;
	
	
	@ManyToOne
	@JoinColumn(name="shotcut")
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
}

