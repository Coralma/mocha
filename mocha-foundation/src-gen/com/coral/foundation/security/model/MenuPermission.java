package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.MenuPermission + "</p>
  * <p>Description: The MenuPermission entity </p>
  */
@Entity(name = "MenuPermission")
@Table(name = "T_MENU_PERMISSION")
public class MenuPermission extends BaseEntity {
	
	@Id()
	@Column (name = "MENU_PERMISSION_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long menuPermissionId;
	
	@Basic(optional = true)
	@Column(name = "MENU_NAME" )
	private String menuName;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = BasicRole.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "role") })
	@Fetch(FetchMode.JOIN)
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
	
	public void setID(Long id) {
		setMenuPermissionId(id);
	}
}

