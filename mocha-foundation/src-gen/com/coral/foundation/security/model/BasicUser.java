package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.BasicUser + "</p>
  * <p>Description: The BasicUser entity </p>
  */
@Entity(name = "BasicUser")
@Table(name = "T_USER")
public class BasicUser extends JPABaseEntity {
	
	@Id()
	@Column (name = "BASIC_USER_ID")
	@GeneratedValue(generator="BASICUSERID_SEQ")
	@TableGenerator(name="BASICUSERID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long basicUserId;
	
	@Column(name = "USER_NAME" )
	private String userName;
	
	
	@Column(name = "USER_PHOTO" )
	private String userPhoto;
	
	
	@Column(name = "USER_ICON" )
	private String userIcon;
	
	
	@Column(name = "PASSWORD" )
	private String password;
	
	
	@Column(name = "RE_PASSWORD" )
	private String rePassword;
	
	
	@Column(name = "REAL_NAME" )
	private String realName;
	
	
	@Column(name = "ENGLISH_NAME" )
	private String englishName;
	
	
	@Column(name = "EMAIL" )
	private String email;
	
	
	@Column(name = "LANGUAGE" )
	private String language;
	
	
	@Column(name = "JOB_TITLE" )
	private String jobTitle;
	
	
	@Column(name = "EXTENSION" )
	private String extension;
	
	
	@Column(name = "MOBILE" )
	private String mobile;
	
	
	@Column(name = "STATUS" )
	private String status;
	
	
	@Column(name = "TYPE" )
	private String type;
	
	
	@Column(name = "INIT" )
	private Long init = new Long(0);
	
	
	@Transient
	private Boolean rememberMe;
	
	
	@Transient
	private org.apache.shiro.authz.SimpleAuthorizationInfo simpleAuthorizationInfo;
	
	
	@ManyToOne
	@JoinColumn(name="basicRole")
	private BasicRole basicRole;
	
	@ManyToOne
	@JoinColumn(name="account")
	private Account account;
	
	@OneToMany(targetEntity=SoicalApp.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="BASIC_USER_ID")
	private List<SoicalApp> soicalApp = new ArrayList<SoicalApp>();
	

	public void setBasicUserId (Long basicUserId) {
		this.basicUserId = basicUserId;
	} 
	public Long getBasicUserId () {
		return basicUserId;
	}
	public void setUserName (String userName) {
		this.userName = userName;
	} 
	public String getUserName () {
		return userName;
	}
	public void setUserPhoto (String userPhoto) {
		this.userPhoto = userPhoto;
	} 
	public String getUserPhoto () {
		return userPhoto;
	}
	public void setUserIcon (String userIcon) {
		this.userIcon = userIcon;
	} 
	public String getUserIcon () {
		return userIcon;
	}
	public void setPassword (String password) {
		this.password = password;
	} 
	public String getPassword () {
		return password;
	}
	public void setRePassword (String rePassword) {
		this.rePassword = rePassword;
	} 
	public String getRePassword () {
		return rePassword;
	}
	public void setRealName (String realName) {
		this.realName = realName;
	} 
	public String getRealName () {
		return realName;
	}
	public void setEnglishName (String englishName) {
		this.englishName = englishName;
	} 
	public String getEnglishName () {
		return englishName;
	}
	public void setEmail (String email) {
		this.email = email;
	} 
	public String getEmail () {
		return email;
	}
	public void setLanguage (String language) {
		this.language = language;
	} 
	public String getLanguage () {
		return language;
	}
	public void setJobTitle (String jobTitle) {
		this.jobTitle = jobTitle;
	} 
	public String getJobTitle () {
		return jobTitle;
	}
	public void setExtension (String extension) {
		this.extension = extension;
	} 
	public String getExtension () {
		return extension;
	}
	public void setMobile (String mobile) {
		this.mobile = mobile;
	} 
	public String getMobile () {
		return mobile;
	}
	public void setStatus (String status) {
		this.status = status;
	} 
	public String getStatus () {
		return status;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setInit (Long init) {
		this.init = init;
	} 
	public Long getInit () {
		return init;
	}
	public void setRememberMe (Boolean rememberMe) {
		this.rememberMe = rememberMe;
	} 
	public Boolean getRememberMe () {
		return rememberMe;
	}
	public void setSimpleAuthorizationInfo (org.apache.shiro.authz.SimpleAuthorizationInfo simpleAuthorizationInfo) {
		this.simpleAuthorizationInfo = simpleAuthorizationInfo;
	} 
	public org.apache.shiro.authz.SimpleAuthorizationInfo getSimpleAuthorizationInfo () {
		return simpleAuthorizationInfo;
	}
	public void setBasicRole (BasicRole basicRole) {
		this.basicRole = basicRole;
	} 
	public BasicRole getBasicRole () {
		return basicRole;
	}
	public void setAccount (Account account) {
		this.account = account;
	} 
	public Account getAccount () {
		return account;
	}
	public void setSoicalApp (List<SoicalApp> soicalApp) {
		this.soicalApp = soicalApp;
	} 
	public List<SoicalApp> getSoicalApp () {
		return soicalApp;
	}

	public Long getID() {
		return getBasicUserId();
	}
}

