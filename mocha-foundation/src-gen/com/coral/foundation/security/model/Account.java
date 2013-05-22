package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.Account + "</p>
  * <p>Description: The Account entity </p>
  */
@Entity(name = "Account")
@Table(name = "T_ACCOUNT")
public class Account extends BaseEntity {
	
	@Id()
	@Column (name = "ACCOUNT_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long accountId;
	
	@Basic(optional = true)
	@Column(name = "NAME" )
	private String name;
	
	
	@Basic(optional = true)
	@Column(name = "EMAIL" )
	private String email;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<BasicUser> users = new ArrayList<BasicUser>();
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = App.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
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
	
	public void setID(Long id) {
		setAccountId(id);
	}
}

