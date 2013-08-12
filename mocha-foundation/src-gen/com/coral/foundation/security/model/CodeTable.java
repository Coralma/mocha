package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.persistence.*;

/**
  * <p>Title: com.coral.foundation.security.model.CodeTable + "</p>
  * <p>Description: The CodeTable entity </p>
  */
@Entity(name = "CodeTable")
@Table(name = "T_CODE_TABLE")
public class CodeTable extends JPABaseEntity {
	
	@Id()
	@Column (name = "CODE_TABLE_ID")
	@GeneratedValue(generator="CODETABLEID_SEQ")
	@TableGenerator(name="CODETABLEID_SEQ", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
	private Long codeTableId;
	
	@Column(name = "NAME" )
	private String name;
	
	
	@Column(name = "IDS" )
	private String ids;
	
	
	@Column(name = "PARENT" )
	private String parent;
	
	
	@OneToMany(targetEntity=CodeTableValue.class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="CODE_TABLE_ID")
	private List<CodeTableValue> codeTableValues = new ArrayList<CodeTableValue>();
	
	@ManyToOne
	@JoinColumn(name="app")
	private App app;
	

	public void setCodeTableId (Long codeTableId) {
		this.codeTableId = codeTableId;
	} 
	public Long getCodeTableId () {
		return codeTableId;
	}
	public void setName (String name) {
		this.name = name;
	} 
	public String getName () {
		return name;
	}
	public void setIds (String ids) {
		this.ids = ids;
	} 
	public String getIds () {
		return ids;
	}
	public void setParent (String parent) {
		this.parent = parent;
	} 
	public String getParent () {
		return parent;
	}
	public void setCodeTableValues (List<CodeTableValue> codeTableValues) {
		this.codeTableValues = codeTableValues;
	} 
	public List<CodeTableValue> getCodeTableValues () {
		return codeTableValues;
	}
	public void setApp (App app) {
		this.app = app;
	} 
	public App getApp () {
		return app;
	}

	public Long getID() {
		return getCodeTableId();
	}
}

