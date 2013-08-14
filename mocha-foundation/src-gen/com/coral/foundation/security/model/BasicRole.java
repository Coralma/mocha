package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.BasicRole + "</p>
  * <p>Description: The BasicRole entity </p>
  */
@Entity(name = "BasicRole")
@Table(name = "T_ROLE")
public class BasicRole extends JPABaseEntity {
	
	@Id()
	@Column (name = "BASIC_ROLE_ID")
	@GeneratedValue(generator="BASICROLEID_SEQ")
	@TableGenerator(name="BASICROLEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long basicRoleId;
	
	@Column(name = "ROLE_NAME" )
	private String roleName;
	
	
	@Column(name = "ROLE_DESCRIPTION" )
	private String roleDescription;
	
	
	@OneToMany(targetEntity=BasicUser.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="BASIC_ROLE_ID")
	private List<BasicUser> user = new ArrayList<BasicUser>();
	
	@OneToMany(targetEntity=MenuPermission.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
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
}

