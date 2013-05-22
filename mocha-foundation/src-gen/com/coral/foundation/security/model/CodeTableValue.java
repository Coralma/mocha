package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.CodeTableValue + "</p>
  * <p>Description: The CodeTableValue entity </p>
  */
@Entity(name = "CodeTableValue")
@Table(name = "T_CODE_TABLE_VALUE")
public class CodeTableValue extends BaseEntity {
	
	@Id()
	@Column (name = "CODE_TABLE_VALUE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long codeTableValueId;
	
	@Basic(optional = true)
	@Column(name = "CODE_TABLE_NAME" )
	private String codeTableName;
	
	
	@Basic(optional = true)
	@Column(name = "LANGUAGE" )
	private String language;
	
	
	@Basic(optional = true)
	@Column(name = "DATAS" )
	private String datas;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = CodeTable.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "codeTable") })
	@Fetch(FetchMode.JOIN)
	private CodeTable codeTable;
	

	public void setCodeTableValueId (Long codeTableValueId) {
		this.codeTableValueId = codeTableValueId;
	} 
	public Long getCodeTableValueId () {
		return codeTableValueId;
	}
	public void setCodeTableName (String codeTableName) {
		this.codeTableName = codeTableName;
	} 
	public String getCodeTableName () {
		return codeTableName;
	}
	public void setLanguage (String language) {
		this.language = language;
	} 
	public String getLanguage () {
		return language;
	}
	public void setDatas (String datas) {
		this.datas = datas;
	} 
	public String getDatas () {
		return datas;
	}
	public void setCodeTable (CodeTable codeTable) {
		this.codeTable = codeTable;
	} 
	public CodeTable getCodeTable () {
		return codeTable;
	}

	public Long getID() {
		return getCodeTableValueId();
	}
	
	public void setID(Long id) {
		setCodeTableValueId(id);
	}
}

