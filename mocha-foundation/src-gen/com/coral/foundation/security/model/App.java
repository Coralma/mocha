package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.App + "</p>
  * <p>Description: The App entity </p>
  */
@Entity(name = "App")
@Table(name = "T_APP")
public class App extends JPABaseEntity {
	
	@Id()
	@Column (name = "APP_ID")
	@GeneratedValue(generator="APPID_SEQ")
	@TableGenerator(name="APPID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long appId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "DESCRIPTION" )
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name="account")
	private Account account;
	
	@OneToMany(targetEntity=CodeTable.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="APP_ID")
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
}

