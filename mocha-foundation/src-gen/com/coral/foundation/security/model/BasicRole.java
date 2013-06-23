package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.BasicRole + "</p>
  * <p>Description: The BasicRole entity </p>
  */
@Entity(name = "BasicRole")
@Table(name = "T_ROLE")
public class BasicRole extends BaseEntity {
	
	@Id()
	@Column (name = "BASIC_ROLE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long basicRoleId;
	
	@Basic(optional = true)
	@Column(name = "ROLE_NAME" )
	private String roleName;
	
	
	@Basic(optional = true)
	@Column(name = "ROLE_DESCRIPTION" )
	private String roleDescription;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="BASIC_ROLE_ID")
	private List<BasicUser> user = new ArrayList<BasicUser>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = MenuPermission.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="BASIC_ROLE_ID")
	private List<MenuPermission> menuPermissions = new ArrayList<MenuPermission>();
	

	public void setBasicRoleId (Long basicRoleId) {
		this.basicRoleId = basicRoleId;
	} 
	public Long getBasicRoleId () {
		return basicRoleId;
	}
	public void setRoleName (String roleName) {
		this.roleName = roleName;
	} 
	public String getRoleName () {
		return roleName;
	}
	public void setRoleDescription (String roleDescription) {
		this.roleDescription = roleDescription;
	} 
	public String getRoleDescription () {
		return roleDescription;
	}
	public void setUser (List<BasicUser> user) {
		this.user = user;
	} 
	public List<BasicUser> getUser () {
		return user;
	}
	public void setMenuPermissions (List<MenuPermission> menuPermissions) {
		this.menuPermissions = menuPermissions;
	} 
	public List<MenuPermission> getMenuPermissions () {
		return menuPermissions;
	}

	public Long getID() {
		return getBasicRoleId();
	}
	
	public void setID(Long id) {
		setBasicRoleId(id);
	}
}

