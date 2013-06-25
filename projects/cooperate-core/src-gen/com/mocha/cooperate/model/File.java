package com.mocha.cooperate.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.mocha.cooperate.model.File + "</p>
  * <p>Description: The File entity </p>
  */
@Entity(name = "File")
@Table(name = "T_FILE")
public class File extends BaseEntity {
	
	@Id()
	@Column (name = "FILE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long fileId;
	
	@Basic(optional = true)
	@Column(name = "PARENT_I_D" )
	private Long parentID;
	
	
	@Basic(optional = true)
	@Column(name = "NAME" )
	private String name;
	
	
	@Basic(optional = true)
	@Column(name = "TYPE" )
	private String type;
	
	
	@Basic(optional = true)
	@Column(name = "PATH" )
	private String path;
	
	
	@Basic(optional = true)
	@Column(name = "PHYSICAL_TYPE" )
	private Long physicalType;
	
	
	@Basic(optional = true)
	@Column(name = "SIZE" )
	private Long size;
	
	
	@Basic(optional = true)
	@Column(name = "SHARE_KEY" )
	private String shareKey;
	
	
	@Basic(optional = true)
	@Column(name = "SHARE_DATE" )
	@Temporal(TemporalType.DATE)
	private Date shareDate;
	
	@Basic(optional = true)
	@Column(name = "ACCOUNT_NAME" )
	private String accountName;
	
	
	@Basic(optional = true)
	@Column(name = "FILE_TYPE" )
	private Long fileType = new Long(0);
	
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = com.coral.foundation.security.model.BasicUser.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private com.coral.foundation.security.model.BasicUser creator;
	

	public void setFileId (Long fileId) {
		this.fileId = fileId;
	} 
	public Long getFileId () {
		return fileId;
	}
	public void setParentID (Long parentID) {
		this.parentID = parentID;
	} 
	public Long getParentID () {
		return parentID;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setType (String type) {
		this.type = type;
	} 
	public String getType () {
		return type;
	}
	public void setPath (String path) {
		this.path = path;
	} 
	public String getPath () {
		return path;
	}
	public void setPhysicalType (Long physicalType) {
		this.physicalType = physicalType;
	} 
	public Long getPhysicalType () {
		return physicalType;
	}
	public void setSize (Long size) {
		this.size = size;
	} 
	public Long getSize () {
		return size;
	}
	public void setShareKey (String shareKey) {
		this.shareKey = shareKey;
	} 
	public String getShareKey () {
		return shareKey;
	}
	public void setShareDate (Date shareDate) {
		this.shareDate = shareDate;
	} 
	public Date getShareDate () {
		return shareDate;
	}
	public void setAccountName (String accountName) {
		this.accountName = accountName;
	} 
	public String getAccountName () {
		return accountName;
	}
	public void setFileType (Long fileType) {
		this.fileType = fileType;
	} 
	public Long getFileType () {
		return fileType;
	}
	public void setCreator (com.coral.foundation.security.model.BasicUser creator) {
		this.creator = creator;
	} 
	public com.coral.foundation.security.model.BasicUser getCreator () {
		return creator;
	}

	public Long getID() {
		return getFileId();
	}
	
	public void setID(Long id) {
		setFileId(id);
	}
}

