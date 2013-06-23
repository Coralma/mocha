package com.coral.foundation.security.model;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.*;
import com.coral.foundation.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
  * <p>Title: com.coral.foundation.security.model.CodeTable + "</p>
  * <p>Description: The CodeTable entity </p>
  */
@Entity(name = "CodeTable")
@Table(name = "T_CODE_TABLE")
public class CodeTable extends BaseEntity {
	
	@Id()
	@Column (name = "CODE_TABLE_ID")
	@GeneratedValue(strategy = GenerationType. AUTO)
	private Long codeTableId;
	
	@Basic(optional = true)
	@Column(name = "NAME" )
	private String name;
	
	
	@Basic(optional = true)
	@Column(name = "IDS" )
	private String ids;
	
	
	@Basic(optional = true)
	@Column(name = "PARENT" )
	private String parent;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, targetEntity = CodeTableValue.class, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="CODE_TABLE_ID")
	private List<CodeTableValue> codeTableValues = new ArrayList<CodeTableValue>();
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH }, targetEntity = App.class, fetch=FetchType.EAGER)
	@JoinColumns({ @JoinColumn(name = "app") })
	@Fetch(FetchMode.JOIN)
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
	
	public void setID(Long id) {
		setCodeTableId(id);
	}
}

