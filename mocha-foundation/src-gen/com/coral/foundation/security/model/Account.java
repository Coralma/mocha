package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.Account + "</p>
  * <p>Description: The Account entity </p>
  */
@Entity(name = "Account")
@Table(name = "T_ACCOUNT")
public class Account extends JPABaseEntity {
	
	@Id()
	@Column (name = "ACCOUNT_ID")
	@GeneratedValue(generator="ACCOUNTID_SEQ")
	@TableGenerator(name="ACCOUNTID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long accountId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "EMAIL" )
	private String email;
	
	
	@OneToMany(targetEntity=BasicUser.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="ACCOUNT_ID")
	private List<BasicUser> users = new ArrayList<BasicUser>();
	
	@OneToMany(targetEntity=App.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="ACCOUNT_ID")
	private List<App> apps = new ArrayList<App>();
	

	public void setAccountId (Long accountId) {
		this.accountId = accountId;
	} 
	public Long getAccountId () {
		return accountId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setEmail (String email) {
		this.email = email;
	} 
	public String getEmail () {
		return email;
	}
	public void setUsers (List<BasicUser> users) {
		this.users = users;
	} 
	public List<BasicUser> getUsers () {
		return users;
	}
	public void setApps (List<App> apps) {
		this.apps = apps;
	} 
	public List<App> getApps () {
		return apps;
	}

	public Long getID() {
		return getAccountId();
	}
}

