package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.App + "</p>
  * <p>Description: The App entity </p>
  */
@Entity(name = "App")
@Table(name = "T_APP")
public class App extends BaseEntity {
	
	@Id()
	@Column (name = "APP_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long appId;
	
	@Basic(optional = true)
	@Column(name = "NAME" )
	private String name;
	
	
	@Basic(optional = true)
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = Account.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "account") })
	@Fetch(FetchMode.JOIN)
	private Account account;
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = CodeTable.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<CodeTable> codeTables = new ArrayList<CodeTable>();
	

	public void setAppId (Long appId) {
		this.appId = appId;
	} 
	public Long getAppId () {
		return appId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setDescription (String description) {
		this.description = description;
	} 
	public String getDescription () {
		return description;
	}
	public void setAccount (Account account) {
		this.account = account;
	} 
	public Account getAccount () {
		return account;
	}
	public void setCodeTables (List<CodeTable> codeTables) {
		this.codeTables = codeTables;
	} 
	public List<CodeTable> getCodeTables () {
		return codeTables;
	}

	public Long getID() {
		return getAppId();
	}
	
	public void setID(Long id) {
		setAppId(id);
	}
}

