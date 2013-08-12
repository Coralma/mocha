package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.MenuPermission + "</p>
  * <p>Description: The MenuPermission entity </p>
  */
@Entity(name = "MenuPermission")
@Table(name = "T_MENU_PERMISSION")
public class MenuPermission extends JPABaseEntity {
	
	@Id()
	@Column (name = "MENU_PERMISSION_ID")
	@GeneratedValue(generator="MENUPERMISSIONID_SEQ")
	@TableGenerator(name="MENUPERMISSIONID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long menuPermissionId;
	
	@Column(name = "MENU_NAME" )
	private String menuName;
	
	
	@ManyToOne
	@JoinColumn(name="role")
	private BasicRole role;
	

	public void setMenuPermissionId (Long menuPermissionId) {
		this.menuPermissionId = menuPermissionId;
	} 
	public Long getMenuPermissionId () {
		return menuPermissionId;
	}
	public void setMenuName (String menuName) {
		this.menuName = menuName;
	} 
	public String getMenuName () {
		return menuName;
	}
	public void setRole (BasicRole role) {
		this.role = role;
	} 
	public BasicRole getRole () {
		return role;
	}

	public Long getID() {
		return getMenuPermissionId();
	}
}

